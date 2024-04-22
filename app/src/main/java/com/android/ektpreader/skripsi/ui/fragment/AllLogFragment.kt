package com.android.ektpreader.skripsi.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.ektpreader.skripsi.data.LogModel
import com.android.ektpreader.skripsi.data.response.NewLogResponse
import com.android.ektpreader.skripsi.databinding.FragmentAllLogBinding
import com.android.ektpreader.skripsi.ui.adapter.MainAdapter
import com.android.ektpreader.skripsi.ui.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllLogFragment : Fragment() {

    private var _binding: FragmentAllLogBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainAdapter: MainAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllLogBinding.inflate(inflater, container, false)
        val root: View = binding.root

        connectViewModel()
        getAllData()
        getHistoryData()
        setAdapter()
        return root
    }

    private fun updateListData(list: List<LogModel>) {
        mainAdapter.listLog.clear()
        mainAdapter.listLog.addAll(list)
        mainAdapter.setData(list as MutableList<LogModel>)
    }

    private fun getHistoryData(){
        showLoading(true)
        listLog()
    }

    private fun getAllData(){
        mainViewModel.getListLog().observe(requireActivity()) {
            if (it != null) {
                updateListData(it)
                showLoading(false)
            }
        }
    }

    private fun listLog() {
        val call = mainViewModel.request.getAllLog()

        call.enqueue(object : Callback<NewLogResponse> {
            override fun onResponse(
                call: Call<NewLogResponse>,
                response: Response<NewLogResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        mainViewModel.listLog.postValue(it.list_log)
                        binding.toolbar.visibility = View.GONE
                    }
                }
                else {
                    Log.d("Error", "Tidak Ada Data")
                    binding.rvLog.visibility = View.GONE
                    binding.tvNoData.visibility = View.VISIBLE
                    showLoading(false)
                    Toast.makeText(requireContext(), "Tidak Ada Data", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewLogResponse>, t: Throwable) {
                Log.d("Error", t.message.toString())
            }
        })
    }

    private fun showLoading(state: Boolean) {
        binding.apply {
            spinDetail.visibility = if (state) View.VISIBLE else View.GONE
            rvLog.visibility = if (state) View.GONE else View.VISIBLE
        }
    }

    private fun connectViewModel(){
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    private fun setAdapter(){
        mainAdapter = MainAdapter(requireContext(), mutableListOf())
        binding.rvLog.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL)
            )
            adapter = mainAdapter
        }
    }
}