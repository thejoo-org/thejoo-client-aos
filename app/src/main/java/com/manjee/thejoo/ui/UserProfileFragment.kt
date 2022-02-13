package com.manjee.thejoo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.manjee.thejoo.databinding.FragmentUserProfileBinding
import com.manjee.thejoo.viewmodel.UserProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private val viewModel by viewModels<UserProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserProfileBinding.inflate(inflater, container, false).run {
            lifecycleOwner = this@UserProfileFragment

            root
        }
    }
}