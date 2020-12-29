package com.wajahatkarim3.zocdoc

import android.app.Application
import android.util.Log
import com.cometchat.pro.core.AppSettings
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException

class ZocdocApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initCometChat()
    }

    fun initCometChat() {
        val appID = getString(R.string.app_id) // Replace with your App ID
        val region = getString(R.string.region) // Replace with your App Region ("eu" or "us")

        val appSettings: AppSettings = AppSettings.AppSettingsBuilder().subscribePresenceForAllUsers().setRegion(region).build()

        CometChat.init(this, appID, appSettings, object : CometChat.CallbackListener<String?>() {
            override fun onSuccess(successMessage: String?) {
                Log.d("ZocdocApp", "Initialization completed successfully")
            }

            override fun onError(e: CometChatException) {
                Log.d("ZocdocApp", "Initialization failed with exception: " + e.localizedMessage)
            }
        })
    }
}