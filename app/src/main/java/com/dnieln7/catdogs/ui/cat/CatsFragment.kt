package com.dnieln7.catdogs.ui.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnieln7.catdogs.CatDogsApplication
import com.dnieln7.catdogs.databinding.CatsFragmentBinding

class CatsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = CatsFragmentBinding.inflate(inflater, container, false)
        val serviceLocator = (requireActivity().application as CatDogsApplication).serviceLocator
        val viewModel = ViewModelProvider(
            this,
            CatsViewModel.Factory(serviceLocator.catRemoteDataSource)
        ).get(CatsViewModel::class.java)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}