package com.manjee.thejoo.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.manjee.thejoo.data.repository.TestRepository
import com.manjee.thejoo.util.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val testRepository: TestRepository
) : ViewModel() {

    private val _createUerQrLiveData = SingleLiveData<Bitmap>()
    val createUerQrLiveData: LiveData<Bitmap> get() = _createUerQrLiveData

    fun getUserToken() {
        viewModelScope.launch(Dispatchers.IO) {
            testRepository.createAuthToken(0,
                success = {
                    Log.d(TAG, "success createUerToken $it")
                },
                fail = {
                    Log.e(TAG, "fail createUserToken $it")
                })
        }
    }

    fun createUserQrCode(qrData: String) {
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(qrData, BarcodeFormat.QR_CODE, 400, 400)

            _createUerQrLiveData.postValue(bitmap)
        } catch (e: Exception) {
            Log.e(TAG, "fail createUserQrCode $e")
        }
    }

    companion object {
        val TAG = UserViewModel::class.simpleName
    }
}