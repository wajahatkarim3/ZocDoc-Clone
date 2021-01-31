package com.wajahatkarim3.zocdoc.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wajahatkarim3.zocdoc.databinding.FragmentConversationsBinding
import kotlin.random.Random

class ConversationsFragment : Fragment() {

    lateinit var bi: FragmentConversationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bi = FragmentConversationsBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViews()
        setLoggedInUI()
    }

    fun setupViews() {

    }

    fun setLoggedInUI() {
        var isLoggedIn = Random.nextBoolean()
        if (isLoggedIn) {
            // Load Conversations
        }
    }
}