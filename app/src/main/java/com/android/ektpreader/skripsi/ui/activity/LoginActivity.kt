package com.android.ektpreader.skripsi.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.android.ektpreader.skripsi.R
import com.android.ektpreader.skripsi.databinding.ActivityLoginBinding

//class LoginActivity : AppCompatActivity() {
//
//    private var _binding: ActivityLoginBinding? = null
//    private val binding get() = _binding!!
//    private lateinit var viewModel: LoginViewModel
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        _binding = ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        viewModel = obtainViewModel(this@LoginActivity)
//        setupAction()
//    }
//
//    private fun setupAction() {
//        binding.buttonLogin.setOnClickListener {
//            val email = binding.txtEmail.text.toString()
//            val password = binding.txtPassword.text.toString()
//
//            when{
//                email.isEmpty() -> {
//                    binding.layoutEmail.error = "Masukkan email"
//                }
//                password.isEmpty() -> {
//                    binding.layoutPassword.error = "Masukkan password"
//                }
//                else -> {
//                    viewModel.login(binding.txtEmail.text.toString())
//                }
//            }
//        }
//
//        viewModel.account.observe(this) {
//            if (it != null) {
//                if (binding.txtPassword.text.toString() == it.passwd) {
//                    startActivity(Intent(this, MainActivity::class.java))
//                } else {
//                    binding.txtPassword.error = "Password is incorrect"
//                }
//            } else {
//                binding.txtEmail.error = "Username not found"
//            }
//        }
//
//    }
//
//    private fun obtainViewModel(activity: AppCompatActivity): LoginViewModel {
//        val factory = LoginViewModelFactory.getInstance(activity.application)
//        return ViewModelProvider(activity, factory).get(LoginViewModel::class.java)
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
//}