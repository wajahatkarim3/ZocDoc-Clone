package com.wajahatkarim3.zocdoc

import android.app.Application
import android.util.Log
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import com.cometchat.pro.core.AppSettings
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.uikit.Settings.UIKitSettings

class ZocdocApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initEmoji()
        initCometChat()
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

    fun initCometChat() {
        val appID = getString(R.string.app_id) // Replace with your App ID
        val region = getString(R.string.region) // Replace with your App Region ("eu" or "us")

        val appSettings: AppSettings = AppSettings.AppSettingsBuilder().subscribePresenceForAllUsers().setRegion(
            region
        ).build()

        CometChat.init(this, appID, appSettings, object : CometChat.CallbackListener<String?>() {
            override fun onSuccess(successMessage: String?) {
                Log.d("ZocdocApp", "Initialization completed successfully")
            }

            override fun onError(e: CometChatException) {
                Log.d("ZocdocApp", "Initialization failed with exception: " + e.localizedMessage)
            }
        })

        // Setting Colors
        UIKitSettings.setColor(resources.getString(R.color.colorPrimaryVariant))
    }
}