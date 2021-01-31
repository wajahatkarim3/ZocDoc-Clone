package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import com.wajahatkarim3.zocdoc.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    lateinit var bi: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(bi.root)

        setupViews()
    }

    fun setupViews() {
        // Toolbar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Sign up"

        // Login
        bi.btnLogin.setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Signup
        bi.btnSignup.setOnClickListener {

            bi.inputEmail.error = null
            bi.inputPassword.error = null
            bi.inputName.error = null

            if (bi.txtEmail.text.toString().isEmpty()) {
                bi.inputEmail.error = "Email cannot be empty!"
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(bi.txtEmail.text.toString()).matches()) {
                bi.inputEmail.error = "Invalid Email!"
                return@setOnClickListener
            }

            if (bi.txtPassword.text.toString().isEmpty()) {
                bi.inputPassword.error = "Password cannot be empty!"
                return@setOnClickListener
            }

            if (bi.txtName.text.toString().isEmpty()) {
                bi.inputName.error = "Name cannot be empty!"
                return@setOnClickListener
            }

            var isDoctor = bi.radioDoctor.isChecked
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
        {
            setResult(RESULT_CANCELED)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}