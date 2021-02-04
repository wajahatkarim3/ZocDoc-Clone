package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import coil.load
import coil.transform.CircleCropTransformation
import com.cometchat.pro.core.CometChat
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.ActivityDoctorDetailsBinding
import com.wajahatkarim3.zocdoc.model.DoctorModel

class DoctorDetailsActivity : AppCompatActivity() {

    lateinit var bi: ActivityDoctorDetailsBinding
    var doctor: DoctorModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityDoctorDetailsBinding.inflate(layoutInflater)
        setContentView(bi.root)

        if (intent.extras != null && intent.hasExtra("doctor")) {
            doctor = intent.getParcelableExtra("doctor")
        }

        setupViews()
        initDoctorDetails()
    }

    fun setupViews() {
        // Toolbar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = doctor!!.name

        // Chat
        bi.btnChat.setOnClickListener {
            var intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("doctor", doctor!!)
            startActivity(intent)
        }
    }

    fun initDoctorDetails() {
        doctor?.let {
            bi.apply {
                txtName.setText(it.name)
                txtAddress.setText(it.address.replace("\n", " "))
                txtCategory.setText(it.category)
                ratingBar.setStar(it.rating)
                imgAvatar.load(it.imageUrl) {
                    crossfade(true)
                    placeholder(R.color.backgroundColor)
                    transformations(CircleCropTransformation())
                }

                var slotsAdapter = RecyclerAdapterUtil<String>(this@DoctorDetailsActivity, it.timeSlots, R.layout.item_time_slot_layout)
                slotsAdapter.addOnDataBindListener { itemView, item, position, _ ->
                    itemView.findViewById<TextView>(R.id.txtTime).setText(item)
                }
                recyclerTimeSlots.adapter = slotsAdapter

                // Chat with Doctor button
                if (CometChat.getLoggedInUser() == null) {
                    btnChat.visibility = View.GONE
                    lblLoginToChat.visibility = View.VISIBLE
                } else {
                    btnChat.visibility = View.VISIBLE
                    lblLoginToChat.visibility = View.GONE
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}