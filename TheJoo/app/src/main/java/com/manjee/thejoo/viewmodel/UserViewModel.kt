package com.manjee.thejoo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manjee.thejoo.data.repository.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val testRepository: TestRepository
) : ViewModel() {

    fun getUserToken() {
        viewModelScope.launch(Dispatchers.IO) {
            testRepository.createUserToken(1206,
                success = {
                    Log.d(TAG, "success createUerToken $it")
                },
                fail = {
                    Log.e(TAG, "fail createUserToken $it")
                })
        }
    }

    companion object {
        val TAG = UserViewModel::class.simpleName
    }
}