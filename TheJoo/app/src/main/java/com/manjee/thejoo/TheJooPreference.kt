package com.manjee.thejoo

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TheJooPreference @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        val TAG = TheJooPreference::class.simpleName
    }
}