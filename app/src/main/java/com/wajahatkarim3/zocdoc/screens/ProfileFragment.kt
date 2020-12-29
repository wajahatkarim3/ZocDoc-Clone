package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    lateinit var bi: FragmentProfileBinding

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
    }

    fun setupViews() {
        bi.txtLogin.setOnClickListener {
            var intent = Intent(activity!!, LoginActivity::class.java)
            startActivity(intent)
        }

        bi.txtSignup.setOnClickListener {
            var intent = Intent(activity!!, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}