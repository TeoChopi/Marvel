package com.example.marvel.utils

import android.view.View
import com.example.marvel.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest


object Common {

    var ts = getTimeStamp()
    const val publicKey = BuildConfig.PUBLIC_KEY
    private const val privateKey = BuildConfig.PRIVATE_KEY

    private fun getTimeStamp(): String {
        return System.currentTimeMillis().toString()
    }

    fun getHash(input: String = ts + privateKey + publicKey): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun View.gone() {
        visibility = View.GONE
    }

    fun View.visible() {
        visibility = View.VISIBLE
    }
}