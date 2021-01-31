package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import com.wajahatkarim3.zocdoc.databinding.ActivityLoginBinding
import kotlin.random.Random

class LoginActivity : AppCompatActivity() {

    private lateinit var bi: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
    }

    fun setupViews() {
        // Toolbar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Log in"

        // Sign up
        bi.btnSignup.setOnClickListener {
            var intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }

        // Login
        bi.btnLogin.setOnClickListener {
            loginUser()
        }
    }

    fun loginUser() {

        bi.inputEmail.error = null
        bi.inputPassword.error = null

        if (bi.txtEmail.text.toString().isEmpty()) {
            bi.inputEmail.error = "Email cannot be empty!"
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(bi.txtEmail.text.toString()).matches()) {
            bi.inputEmail.error = "Invalid Email!"
            return
        }

        if (bi.txtPassword.text.toString().isEmpty()) {
            bi.inputPassword.error = "Password cannot be empty!"
            return
        }

        var isDoctor = Random.nextBoolean()
        if (isDoctor) {
            val i = Intent(applicationContext, DoctorMainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }
        else {
            val i = Intent(applicationContext, MainActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}