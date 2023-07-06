package com.example.bestkotlinprojectgithub.presentation

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bestkotlinprojectgithub.databinding.ActivityMainBinding
import com.example.bestkotlinprojectgithub.utils.createDialog
import com.example.bestkotlinprojectgithub.utils.createProgressDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity()  {

    private val dialog by lazy { createProgressDialog() }
    private val viewModel by viewModel<MainViewModel>()
    private val kotlinAdapter by lazy { KotlinProjectAdapter() }
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        viewModel.initViewModelGetRepositories()

        binding.recyclerView.adapter = kotlinAdapter

        viewModel.list.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> dialog.show()
                is MainViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is MainViewModel.State.Success -> {
                    dialog.dismiss()
                    kotlinAdapter.submitData(lifecycle, it.list)
                }
            }
        }
    }
}