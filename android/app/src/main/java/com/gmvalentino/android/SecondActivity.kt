package com.gmvalentino.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gmvalentino.android.databinding.ActivityMainBinding
import com.gmvalentino.second.SecondIntent
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class SecondActivity : AppCompatActivity(), KoinComponent {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel by viewModel<SecondViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            viewModel.dispatch(SecondIntent.Increment)
        }

        binding.buttonMinus.setOnClickListener {
            viewModel.dispatch(SecondIntent.Decrement)
        }

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                binding.textCounter.text = it.counter.toString()
                binding.textName.text = if (it.isLoading) {
                    "Loading"
                } else {
                    "User"
                }
            }
        }
    }
}
