package com.wajahatkarim3.zocdoc.screens

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.google.android.material.snackbar.Snackbar
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var bi: FragmentProfileBinding
    val RC_SIGNUP = 1213
    val RC_LOGIN = 12135

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bi = FragmentProfileBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViews()
        setLoggedInUI()
    }

    fun setupViews() {
        bi.txtLogin.setOnClickListener {
            var intent = Intent(activity!!, LoginActivity::class.java)
            startActivityForResult(intent, RC_LOGIN)
        }

        bi.txtSignup.setOnClickListener {
            var intent = Intent(activity!!, SignupActivity::class.java)
            startActivityForResult(intent, RC_SIGNUP)
        }

        bi.txtSignout.setOnClickListener {
            CometChat.logout(object : CometChat.CallbackListener<String>() {
                override fun onSuccess(p0: String?) {
                    setLoggedInUI()
                }

                override fun onError(ex: CometChatException?) {
                    Snackbar.make(bi.root, ex?.localizedMessage ?: "Couldn't logout CometChat user", Snackbar.LENGTH_SHORT).show()
                }
            })
        }
    }

    fun setLoggedInUI() {
        if (CometChat.getLoggedInUser() == null) {
            bi.txtSignup.visibility = View.VISIBLE
            bi.txtLogin.visibility = View.VISIBLE
            bi.txtSignout.visibility = View.GONE
        } else {
            bi.txtSignup.visibility = View.GONE
            bi.txtLogin.visibility = View.GONE
            bi.txtSignout.visibility = View.VISIBLE
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == RC_SIGNUP || requestCode == RC_LOGIN) && resultCode == RESULT_OK) {
            setLoggedInUI()
        }
    }
}