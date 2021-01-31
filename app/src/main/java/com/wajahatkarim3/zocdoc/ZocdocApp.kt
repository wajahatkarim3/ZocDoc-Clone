package com.wajahatkarim3.zocdoc

import android.app.Application
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig

class ZocdocApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initEmoji()
    }

    fun initEmoji() {
        var fontRequest = FontRequest(
            "com.google.android.gms.fonts",
            "com.google.android.gms",
            "Noto Color Emoji Compat",
            R.array.com_google_android_gms_fonts_certs
        )
        val config: EmojiCompat.Config = FontRequestEmojiCompatConfig(this, fontRequest)
        EmojiCompat.init(config)
    }
}