package com.mikesmithinc.classifyds.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.mikesmithinc.classifyds.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var viewDataBinding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = MainFragmentBinding.inflate(inflater, container, false)
        viewDataBinding.message.text = viewModel.getSomething()

        lifecycleScope.launch {
            viewModel.getImage(
                requireActivity(),
                viewDataBinding.toolbarImage,
                "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/godzilla-vs-kong-king-kong-1614031050.png"
            )
                .collect()
        }

        return viewDataBinding.root
    }

}
