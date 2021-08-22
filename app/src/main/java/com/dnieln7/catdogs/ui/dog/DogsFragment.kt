package com.dnieln7.catdogs.ui.dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dnieln7.catdogs.databinding.DogsFragmentBinding

class DogsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DogsFragmentBinding.inflate(inflater, container, false)
        val viewModel = ViewModelProvider(this).get(DogsViewModel::class.java)

        return binding.root
    }
}