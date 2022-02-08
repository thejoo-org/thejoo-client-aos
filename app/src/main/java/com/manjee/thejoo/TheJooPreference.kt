package com.manjee.thejoo

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TheJooPreference @Inject constructor(@ApplicationContext context: Context) {

    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun setUserToken(token: String) {
        prefs.edit().putString("userToken", token).apply()
    }

    fun getUserToken(): String {
        return prefs.getString("userToken", "").toString()
    }

    companion object {
        val TAG = TheJooPreference::class.simpleName
    }
}