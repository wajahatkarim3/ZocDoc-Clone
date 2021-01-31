package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.FragmentProfileBinding
import kotlin.random.Random

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
        setLoggedInUI()
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

        bi.txtSignout.setOnClickListener {
            val i = Intent(activity, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            activity?.finish()
        }
    }

    fun setLoggedInUI() {
        var isLoggedIn = Random.nextBoolean()
        if (isLoggedIn) {
            bi.txtSignup.visibility = View.VISIBLE
            bi.txtLogin.visibility = View.VISIBLE
            bi.txtSignout.visibility = View.GONE
            bi.txtName.setText(getString(R.string.hello_name, "Guest"))
        } else {
            bi.txtSignup.visibility = View.GONE
            bi.txtLogin.visibility = View.GONE
            bi.txtSignout.visibility = View.VISIBLE
            bi.txtName.setText(getString(R.string.hello_name, "UserName"))
        }
    }
}