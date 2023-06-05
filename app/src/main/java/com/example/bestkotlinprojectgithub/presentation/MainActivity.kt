package com.example.bestkotlinprojectgithub.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
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

        binding.recyclerView.adapter = kotlinAdapter
        viewModel.list.observe(this, Observer {
            kotlinAdapter.submitData(lifecycle, it)

        })

    }
}