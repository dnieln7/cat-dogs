package com.dnieln7.catdogs.ui.cat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.dnieln7.catdogs.CatDogsApplication
import com.dnieln7.catdogs.R
import com.dnieln7.catdogs.databinding.CatsFragmentBinding
import com.dnieln7.catdogs.utils.ApiState


class CatsFragment : Fragment() {
    private var _binding: CatsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CatsFragmentBinding.inflate(inflater, container, false)

        val serviceLocator = (requireActivity().application as CatDogsApplication).serviceLocator

        viewModel = ViewModelProvider(
            this,
            CatsViewModel.Factory(serviceLocator.catRemoteDataSource)
        ).get(CatsViewModel::class.java)

        binding.refresh.setOnRefreshListener { viewModel.fetchCats() }
        binding.items.setHasFixedSize(true)

        viewModel.apiState.observe(viewLifecycleOwner, {
            when (it) {
                is ApiState.Error -> {
                    with(binding) {
                        errorImage.load(R.drawable.ic_no_network)
                        errorMessage.text = it.error

                        refresh.isRefreshing = false
                        progress.visibility = View.GONE
                        error.visibility = View.VISIBLE
                        items.visibility = View.GONE
                    }
                }
                ApiState.Loading -> binding.progress.visibility = View.VISIBLE
                ApiState.Success -> {
                    with(binding) {
                        refresh.isRefreshing = false
                        progress.visibility = View.GONE
                        error.visibility = View.GONE
                        items.visibility = View.VISIBLE
                    }
                }
            }
        })
        viewModel.cats.observe(viewLifecycleOwner, {
            binding.items.adapter = CatListAdapter(it)
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}