package com.dnieln7.catdogs.ui.dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnieln7.catdogs.databinding.DogsFragmentBinding

class DogsFragment : Fragment() {
    private var _binding: DogsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DogsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DogsFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(DogsViewModel::class.java)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}