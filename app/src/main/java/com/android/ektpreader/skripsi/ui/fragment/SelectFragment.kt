package com.android.ektpreader.skripsi.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.ektpreader.skripsi.databinding.FragmentSelectBinding
import com.android.ektpreader.skripsi.ui.activity.PopUpActivity
import com.android.ektpreader.skripsi.ui.viewmodel.DataViewModel
import com.android.ektpreader.skripsi.ui.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SelectFragment : Fragment() {

    private var _binding: FragmentSelectBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var viewModel: DataViewModel
    private lateinit var nik: String
    private lateinit var tag: String

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSelectBinding.inflate(inflater, container, false)
        val root: View = binding.root

        connectViewModel()

        viewModel = ViewModelProvider(requireActivity())[DataViewModel::class.java]
        viewModel.nik.observe(viewLifecycleOwner) { data ->
            nik = data
            nik.let {
                viewModel.tag.observe(viewLifecycleOwner) { data ->
                    tag = data
                    select(it, tag)
                }
            }
        }
        return root
    }

    private fun connectViewModel(){
        mainViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[MainViewModel::class.java]
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    private fun getCurrentDateTime(): Date {
        return Calendar.getInstance().time
    }

    private fun select(nik: String, tag: String) {
        val date = getCurrentDateTime()
        val dateInString = date.toString("yyyy/MM/dd HH:mm:ss")

        binding.card1.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "1")
            Toast.makeText(requireContext(), "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), PopUpActivity::class.java)
            intent.putExtra("popuptitle", "SELAMAT")
            intent.putExtra("darkstatusbar", false)
            startActivity(intent)
        }
        binding.card2.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "2")
            Toast.makeText(requireContext(), "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), PopUpActivity::class.java)
            intent.putExtra("popuptitle", "SELAMAT")
            intent.putExtra("darkstatusbar", false)
            startActivity(intent)
        }
        binding.card3.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "3")
            Toast.makeText(requireContext(), "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), PopUpActivity::class.java)
            intent.putExtra("popuptitle", "SELAMAT")
            intent.putExtra("darkstatusbar", false)
            startActivity(intent)
        }
        binding.card4.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "4")
            Toast.makeText(requireContext(), "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), PopUpActivity::class.java)
            intent.putExtra("popuptitle", "SELAMAT")
            intent.putExtra("darkstatusbar", false)
            startActivity(intent)
        }
        binding.card5.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "5")
            Toast.makeText(requireContext(), "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), PopUpActivity::class.java)
            intent.putExtra("darkstatusbar", false)
            startActivity(intent)
        }
    }

}