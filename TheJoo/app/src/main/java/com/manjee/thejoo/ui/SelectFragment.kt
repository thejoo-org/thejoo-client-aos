package com.manjee.thejoo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manjee.thejoo.databinding.FragmentSelectBinding
import com.manjee.thejoo.viewmodel.SelectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectFragment : Fragment() {

    private val viewModel by viewModels<SelectViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentSelectBinding.inflate(inflater, container, false).run {
            lifecycleOwner = this@SelectFragment

            root
        }
    }
}