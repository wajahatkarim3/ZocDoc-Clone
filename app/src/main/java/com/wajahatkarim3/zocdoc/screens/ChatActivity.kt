package com.wajahatkarim3.zocdoc.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import androidx.fragment.app.Fragment
import com.cometchat.pro.constants.CometChatConstants
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.ActivityChatBinding
import com.wajahatkarim3.zocdoc.model.DoctorModel
import constant.StringContract
import screen.messagelist.CometChatMessageScreen

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
            CometChat.getLoggedInUser()?.let {

                CometChat.getUser(doctor!!.doctorId, object : CometChat.CallbackListener<User>() {
                    override fun onSuccess(doctorUser: User?) {
                        var bundle = Bundle()
                        var chatFragment: Fragment = CometChatMessageScreen()

                        bundle.apply {
                            putString(StringContract.IntentStrings.AVATAR, doctor!!.imageUrl)
                            putString(StringContract.IntentStrings.NAME, doctorUser!!.name)
                            putString(StringContract.IntentStrings.UID, doctorUser!!.uid)
                            putString(StringContract.IntentStrings.STATUS, doctorUser!!.status)
                            putString(StringContract.IntentStrings.TYPE, CometChatConstants.RECEIVER_TYPE_USER)
                        }

                        chatFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.chatFrame, chatFragment)
                            .commit()
                    }

                    override fun onError(p0: CometChatException?) {
                        finish()
                    }
                })
            }
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