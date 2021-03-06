package com.wajahatkarim3.zocdoc.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cometchat.pro.core.CometChat
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.FragmentAppointmentsBinding

class AppointmentsFragment : Fragment() {

    lateinit var bi: FragmentAppointmentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bi = FragmentAppointmentsBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViews()
        setLoggedInUI()
    }

    fun setupViews() {
        bi.btnFindDoctor.setOnClickListener {
            (activity as? MainActivity)?.selectTab(0)
        }

        bi.btnLogin.setOnClickListener {
            (activity as? MainActivity)?.selectTab(4)
        }
    }

    fun setLoggedInUI() {
        if (CometChat.getLoggedInUser() != null) {
            bi.btnLogin.visibility = View.GONE
            bi.lblHaveAccount.visibility = View.GONE
        }
    }
}