package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.thetechnocafe.gurleensethi.liteutils.RecyclerAdapterUtil
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.ActivityDoctorsSearchBinding
import com.wajahatkarim3.zocdoc.databinding.ItemDoctorSearchLayoutBinding
import com.wajahatkarim3.zocdoc.model.DoctorModel


class DoctorsSearchActivity : AppCompatActivity() {

    lateinit var bi: ActivityDoctorsSearchBinding
    lateinit var recyclerAdapter: RecyclerAdapterUtil<DoctorModel>
    val doctorsList = arrayListOf<DoctorModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityDoctorsSearchBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
        loadDummyDoctors()
    }

    fun setupViews() {
        // Toolbar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        // RecyclerView
        recyclerAdapter = RecyclerAdapterUtil(this, doctorsList, R.layout.item_doctor_search_layout)
        recyclerAdapter.addOnDataBindListener { itemView, item, position, _ ->
            var bbb = ItemDoctorSearchLayoutBinding.bind(itemView)
            bbb.apply {
                txtName.setText(item.name)
                txtAddress.setText(item.address)
                txtCategory.setText(item.category)
                ratingBar.setStar(item.rating)
                imgAvatar.load(item.imageUrl) {
                    crossfade(true)
                    placeholder(R.color.backgroundColor)
                    transformations(CircleCropTransformation())
                }

                var slotsAdapter = RecyclerAdapterUtil<String>(itemView.context, item.timeSlots, R.layout.item_time_slot_layout)
                slotsAdapter.addOnDataBindListener { itemView, item, position, _ ->
                    itemView.findViewById<TextView>(R.id.txtTime).setText(item)
                }
                recyclerTimeSlots.adapter = slotsAdapter
            }
        }
        recyclerAdapter.addOnClickListener { item, position ->
            var intent = Intent(this, DoctorDetailsActivity::class.java)
            intent.putExtra("doctor", item)
            startActivity(intent)
        }
        bi.recyclerDoctors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bi.recyclerDoctors.adapter = recyclerAdapter
    }

    fun loadDummyDoctors() {
        for (i in 0 until 6) {
            doctorsList.add(DoctorModel.random(i))
        }
        recyclerAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_doctors_search, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}