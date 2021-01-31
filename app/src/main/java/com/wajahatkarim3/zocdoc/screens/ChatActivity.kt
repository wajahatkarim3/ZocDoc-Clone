package com.wajahatkarim3.zocdoc.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.ActivityChatBinding
import com.wajahatkarim3.zocdoc.model.DoctorModel

class ChatActivity : AppCompatActivity() {

    lateinit var bi: ActivityChatBinding
    var doctor: DoctorModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityChatBinding.inflate(layoutInflater)
        setContentView(bi.root)

        if (intent.extras != null && intent.hasExtra("doctor")) {
            doctor = intent.getParcelableExtra("doctor")
        } else {
            finish()
        }

        setupViews()
        initChat()
    }

    fun setupViews() {

    }

    fun initChat() {
        initEmoji()
        if (doctor != null) {

        }
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