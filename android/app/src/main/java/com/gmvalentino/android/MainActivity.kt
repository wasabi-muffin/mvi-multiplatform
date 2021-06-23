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

        binding.buttonToggle.setOnClickListener {
            viewModel.dispatch(MainIntent.Toggle("1"))
        }

        lifecycleScope.launchWhenResumed {
            viewModel.state.collect {
                binding.textName.text = if (it.isLoading) {
                    "Loading"
                } else {
                    it.tasks.firstOrNull()?.title
                }

                binding.textStatus.text = it.tasks.firstOrNull()?.isComplete.toString()
            }
        }
    }
}
