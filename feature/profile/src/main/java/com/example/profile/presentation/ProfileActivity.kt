package com.example.profile.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.profile.databinding.ActivityProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private val viewModel: ProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            viewModel.populateData()
        }

        listenEvent()
    }

    @SuppressLint("SetTextI18n")
    private fun listenEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.event.collect { event ->
                    when (event) {
                        is ProfileViewModel.Event.OnPopulateData -> {
                            viewModel.onDataPopulated()
                        }
                        is ProfileViewModel.Event.OnDataObtained -> {
                            binding.textView.text = "Data loaded ${event.data.size}"
                        }
                        is ProfileViewModel.Event.Loading -> {
                            binding.textView.text = "Loading..."
                        }
                        is ProfileViewModel.Event.OnDataPopulated -> {
                            binding.textView.text = "Data populated ${event.data.size}"
                        }
                        is ProfileViewModel.Event.Empty -> {
                            binding.textView.text = "Empty"
                        }
                    }
                }
            }
        }
    }
}