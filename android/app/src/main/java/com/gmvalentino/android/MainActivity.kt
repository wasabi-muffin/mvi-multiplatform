package com.gmvalentino.android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import co.touchlab.kermit.Kermit
import com.gmvalentino.android.databinding.ActivityMainBinding
import com.gmvalentino.main.MainIntent
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), KoinComponent {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val log: Kermit by inject { parametersOf("MainActivity") }
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            viewModel.dispatch(MainIntent.Increment)
        }

        binding.buttonMinus.setOnClickListener {
            viewModel.dispatch(MainIntent.Decrement)
        }

        binding.buttonNext.setOnClickListener {
            startActivity(
                Intent(this, SecondActivity::class.java)
            )
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
