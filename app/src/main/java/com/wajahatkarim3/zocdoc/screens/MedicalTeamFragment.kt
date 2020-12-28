package com.wajahatkarim3.zocdoc.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.FragmentMedicalTeamBinding

class MedicalTeamFragment : Fragment() {

    lateinit var bi: FragmentMedicalTeamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bi = FragmentMedicalTeamBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViews()
    }

    fun setupViews() {
        bi.btnFindDoctor.setOnClickListener {
            (activity as? MainActivity)?.selectTab(0)
        }

        bi.btnLogin.setOnClickListener {
            (activity as? MainActivity)?.selectTab(4)
        }
    }
}