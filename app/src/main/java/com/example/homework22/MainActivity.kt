package com.example.homework22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val requestButton: Button = findViewById(R.id.requestButton)
        val textContent: TextView = findViewById(R.id.content)
        requestButton.setOnClickListener {
            viewModel.getData()
        }
        viewModel.uiState.observe(this) {
            when (it) {
                is MyViewModel.UIState.Empty -> Unit
                is MyViewModel.UIState.Result -> {
                    textContent.text = it.title
                }

                is MyViewModel.UIState.Processing -> {
                    textContent.text = "Processing"
                }

                is MyViewModel.UIState.Error -> {
                    textContent.text = it.description
                }
            }
        }
    }
}