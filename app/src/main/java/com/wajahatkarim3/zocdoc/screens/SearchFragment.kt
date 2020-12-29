package com.wajahatkarim3.zocdoc.screens

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {

    lateinit var bi: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bi = FragmentSearchBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViews()
    }

    fun setupViews() {
        // Looping Green Shape
        val imageViewAnimator = ObjectAnimator.ofFloat(bi.imgGreenShape,View.ROTATION, 360f)
        imageViewAnimator.repeatCount = Animation.INFINITE
        imageViewAnimator.repeatMode = ObjectAnimator.RESTART
        imageViewAnimator.duration = 30_000
        imageViewAnimator.start()

        // Find
        bi.btnFind.setOnClickListener {
            var intent = Intent(context, DoctorsSearchActivity::class.java)
            startActivity(intent)
        }
    }
}