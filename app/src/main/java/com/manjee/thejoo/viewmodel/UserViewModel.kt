package com.manjee.thejoo.viewmodel

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.auth0.android.jwt.JWT
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.manjee.thejoo.TheJooPreference
import com.manjee.thejoo.data.repository.MeRepository
import com.manjee.thejoo.data.repository.TestRepository
import com.manjee.thejoo.util.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.concurrent.timer

@HiltViewModel
class UserViewModel @Inject constructor(
    private val testRepository: TestRepository,
    private val meRepository: MeRepository,
    private val theJooPreference: TheJooPreference
) : ViewModel() {

    private val _createUerQrLiveData = SingleLiveData<Bitmap>()
    val createUerQrLiveData: LiveData<Bitmap> get() = _createUerQrLiveData
    private val _remainQrTime = SingleLiveData<Long>()
    val remainQrTime: LiveData<Long> get() = _remainQrTime
    private val _navigate = SingleLiveData<Int>()
    val navigate: LiveData<Int> get() = _navigate

    // 유저 인증 토큰 생성
    fun getUserToken() {
        viewModelScope.launch(Dispatchers.IO) {
            testRepository.createAuthToken(1,
                success = {
                    Log.d(TAG, "success createUerToken $it")
                    theJooPreference.setUserToken(it)
                    getUserQrToken()
                },
                fail = {
                    Log.e(TAG, "fail createUserToken $it")
                })
        }
    }

    // 유저 QR 토큰 생성
    private fun getUserQrToken() {
        viewModelScope.launch(Dispatchers.IO) {
            meRepository.getUserToken(success = {
                Log.d(TAG, "success getUerQrToken $it")

                val jwt = JWT(it)
                Log.d(TAG, "token info: iat = ${jwt.issuedAt} | exp = ${jwt.expiresAt}")

                startQrTimer(jwt.expiresAt!!)
                createUserQrCode(it)
            },
                fail = {
                    Log.e(TAG, "fail getUerQrToken $it")
                })
        }
    }

    // 유저 QR 코드 생성
    private fun createUserQrCode(qrData: String) {
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(qrData, BarcodeFormat.QR_CODE, 400, 400)

            _createUerQrLiveData.postValue(bitmap)
        } catch (e: Exception) {
            Log.e(TAG, "fail createUserQrCode $e")
        }
    }

    // QR 유효 시간 보여주기
    private fun startQrTimer(exp: Date) {
        var remain: Long
        timer(period = 1000, initialDelay = 1000) {
            remain = (exp.time - System.currentTimeMillis()) / 1000
            CoroutineScope(Dispatchers.Main).launch {
                _remainQrTime.postValue(remain)
            }

            if (remain == 0L) {
                cancel()
                getUserQrToken()
            }
        }
    }

    fun navigate(id: Int) {
        _navigate.postValue(id)
    }

    companion object {
        val TAG = UserViewModel::class.simpleName
    }
}