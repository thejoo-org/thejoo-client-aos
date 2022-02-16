package com.manjee.thejoo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manjee.thejoo.data.model.UserMembership
import com.manjee.thejoo.data.model.UserProfile
import com.manjee.thejoo.data.repository.MeRepository
import com.manjee.thejoo.util.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val meRepository: MeRepository
) : ViewModel() {

    private val _apiError = SingleLiveData<String>()
    val apiError: LiveData<String> get() = _apiError
    private val _userProfileLiveData = SingleLiveData<UserProfile>()
    val userProfileLiveData: LiveData<UserProfile> get() = _userProfileLiveData
    private val _userMembershipLiveData = SingleLiveData<ArrayList<UserMembership>>()
    val userMembershipLiveData: LiveData<ArrayList<UserMembership>> get() = _userMembershipLiveData

    private var lastPage = false
    private var membershipPage = 0

    fun getUserProfile() {
        viewModelScope.launch(Dispatchers.IO) {
            meRepository.getUserProfile(
                success = {
                    _userProfileLiveData.postValue(it)
                },
                fail = {
                    _apiError.postValue(it.message)
                }
            )
        }
    }

    fun getUserMembership() {
        if (lastPage) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            meRepository.getUserMembership(membershipPage,
                success = {
                    val list = arrayListOf<UserMembership>()

//                    for (i in 0..30) {
//                        list.add(UserMembership(storeName = i.toString()))
//                    }
//
//                    _userMembershipLiveData.value?.let { currentList -> list.addAll(currentList) }
                    list.addAll(it.content)
                    _userMembershipLiveData.postValue(list)

                    membershipPage++
                    lastPage = it.last
                },
                fail = {
                    _apiError.postValue(it.message)
                })
        }
    }

    companion object {
        val TAG = UserProfileViewModel::class.simpleName
    }
}