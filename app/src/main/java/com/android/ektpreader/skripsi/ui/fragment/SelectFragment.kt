package com.android.ektpreader.skripsi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.ektpreader.skripsi.databinding.FragmentSelectBinding
import com.android.ektpreader.skripsi.ui.activity.DashboardActivity
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

        //sectionPagerAdapterSetup()

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

//    private fun sectionPagerAdapterSetup() {
//        val sectionsPagerAdapter = ProfileSectionsPagerAdapter(this)
//        val viewPager: ViewPager2 = binding.viewPager
//        viewPager.adapter = sectionsPagerAdapter
//
//        val tabs: TabLayout = binding.tabs
//
//        TabLayoutMediator(tabs, viewPager) { tab, position ->
//            tab.text = resources.getString(TAB_TITLES[position])
//        }.attach()
//    }

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
            binding.card.visibility = INVISIBLE
            binding.popUp.visibility = VISIBLE
            binding.popUp.setOnClickListener{
                DashboardActivity.start(requireContext(), tag)
                binding.card.visibility = VISIBLE
                binding.popUp.visibility = GONE
            }
        }
        binding.card2.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "2")
            Toast.makeText(requireContext(), "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            binding.card.visibility = INVISIBLE
            binding.popUp.visibility = VISIBLE
            binding.popUp.setOnClickListener{
                DashboardActivity.start(requireContext(), tag)
                binding.card.visibility = VISIBLE
                binding.popUp.visibility = GONE
            }
        }
        binding.card3.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "3")
            Toast.makeText(requireContext(), "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            binding.card.visibility = INVISIBLE
            binding.popUp.visibility = VISIBLE
            binding.popUp.setOnClickListener{
                DashboardActivity.start(requireContext(), tag)
                binding.card.visibility = VISIBLE
                binding.popUp.visibility = GONE
            }
        }
        binding.card4.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "4")
            Toast.makeText(requireContext(), "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            binding.card.visibility = INVISIBLE
            binding.popUp.visibility = VISIBLE
            binding.popUp.setOnClickListener{
                DashboardActivity.start(requireContext(), tag)
                binding.card.visibility = VISIBLE
                binding.popUp.visibility = GONE
            }
        }
        binding.card5.setOnClickListener{
            mainViewModel.uploadLog(dateInString, nik, "5")
            Toast.makeText(requireContext(), "$nik Pengajuan Telah Dibuat", Toast.LENGTH_SHORT).show()
            binding.card.visibility = INVISIBLE
            binding.popUp.visibility = VISIBLE
            binding.popUp.setOnClickListener{
                DashboardActivity.start(requireContext(), tag)
                binding.card.visibility = VISIBLE
                binding.popUp.visibility = GONE
            }
        }
    }

//    companion object {
//        fun start(context: Context, nik: String, tag: String) {
//            Intent(context, SelectActivity::class.java).apply {
//                this.putExtra("KEY_NIK", nik)
//                this.putExtra("KEY_TAG", tag)
//                context.startActivity(this)
//            }
//        }
//    }
}