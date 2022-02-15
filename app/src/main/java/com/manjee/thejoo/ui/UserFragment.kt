package com.manjee.thejoo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.manjee.thejoo.R
import com.manjee.thejoo.databinding.FragmentUserBinding
import com.manjee.thejoo.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {

    private val viewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserBinding.inflate(inflater, container, false).run {
            lifecycleOwner = this@UserFragment
            vm = viewModel

            observedVm()

            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserToken()
    }

    private fun FragmentUserBinding.observedVm() {
        with(viewModel) {
            createUerQrLiveData.observe(viewLifecycleOwner) {
                ivQrCode.setImageBitmap(it)
            }

            navigate.observe(viewLifecycleOwner) {
                requireView().findNavController()
                    .navigate(R.id.action_userFragment_to_userProfileFragment)
            }
        }
    }
}