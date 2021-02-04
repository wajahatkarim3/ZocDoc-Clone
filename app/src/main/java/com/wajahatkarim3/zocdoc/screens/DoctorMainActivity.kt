package com.wajahatkarim3.zocdoc.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.cometchat.pro.uikit.Settings.UIKitSettings
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.ActivityDoctorMainBinding
import screen.CometChatConversationListScreen

class DoctorMainActivity : AppCompatActivity() {

    lateinit var bi: ActivityDoctorMainBinding
    private lateinit var viewPagerAdapter: DoctorMainViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityDoctorMainBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
    }

    fun setupViews() {
        // ViewPager
        viewPagerAdapter = DoctorMainViewPagerAdapter(supportFragmentManager)
        bi.viewPager.adapter = viewPagerAdapter

        // Tabs
        bi.tabLayout.setupWithViewPager(bi.viewPager)
        var icons = arrayOf(R.drawable.ic_baseline_search_24, R.drawable.ic_outline_mark_chat_unread_24, R.drawable.ic_baseline_person_24)
        for (i in 0 until bi.tabLayout.tabCount) {
            bi.tabLayout.getTabAt(i)?.setIcon(icons[i])
        }
    }

    inner class DoctorMainViewPagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount() = 3

        override fun getItem(position: Int): Fragment {
            var fragment = SearchFragment()
            if (position == 0) return SearchFragment()
            if (position == 1) return ConversationsFragment()
            if (position == 2) return ProfileFragment()
            return fragment
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return ""
        }
    }
}