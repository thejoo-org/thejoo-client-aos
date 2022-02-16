package com.manjee.thejoo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.manjee.thejoo.databinding.FragmentUserProfileBinding
import com.manjee.thejoo.viewmodel.UserProfileViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserProfileFragment : Fragment() {

    private val viewModel by viewModels<UserProfileViewModel>()

    private lateinit var userMembershipAdapter: UserMembershipAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentUserProfileBinding.inflate(inflater, container, false).run {
            lifecycleOwner = this@UserProfileFragment
            vm = viewModel

            initView()
            observedVm()

            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserProfile()
        viewModel.getUserMembership()
    }

    private fun FragmentUserProfileBinding.initView() {
        userMembershipAdapter = UserMembershipAdapter {}

        rvMembership.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = userMembershipAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.getUserMembership()
                    }
                }
            })
        }
    }

    private fun FragmentUserProfileBinding.observedVm() {
        viewModel.userMembershipLiveData.observe(viewLifecycleOwner) {
            userMembershipAdapter.submitList(it)
        }
    }
}