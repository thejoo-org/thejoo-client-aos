package com.manjee.thejoo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.manjee.thejoo.data.model.UserMembership
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

            initView()

            root
        }
    }

    private fun FragmentUserProfileBinding.initView() {
        userMembershipAdapter = UserMembershipAdapter {}

        rvMembership.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = userMembershipAdapter
        }

        val membershipList = arrayListOf<UserMembership>()
        for (i in 0..100) {
            membershipList.add(UserMembership(storeName = i.toString()))
        }
        userMembershipAdapter.submitList(membershipList as MutableList<UserMembership>)
    }
}