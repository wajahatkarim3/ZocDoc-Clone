package com.wajahatkarim3.zocdoc.screens

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.FragmentRecommendationsBinding
import java.util.*

class RecommendationsFragment : Fragment() {

    lateinit var bi: FragmentRecommendationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        bi = FragmentRecommendationsBinding.inflate(inflater, container, false)
        return bi.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupViews()
    }

    fun setupViews() {
        bi.txtDoB.setOnClickListener {
            context?.let {
                var calendar = Calendar.getInstance()
                var dateDialog = DatePickerDialog(it, R.style.DatePickerDialogTheme, { datePicker, year, month, day ->
                    var mon = month + 1
                    bi.txtDoB.setText("$day-$mon-$year")
                    bi.btnViewRecommendations.isEnabled = true
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                dateDialog.show()
            }
        }
    }
}