package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.MenuItem
import android.widget.Toast
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.wajahatkarim3.zocdoc.R
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

            var auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(bi.txtEmail.text.toString(), bi.txtPassword.text.toString())
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var firebaseUser = auth.currentUser

                        var cometUser = User()
                        cometUser.uid = firebaseUser!!.uid
                        cometUser.name = bi.txtName.text.toString()

                        CometChat.createUser(cometUser, getString(R.string.auth_key), object : CometChat.CallbackListener<User>() {
                            override fun onSuccess(u: User?) {
                                Log.d("createUser", u.toString());

                                CometChat.login(cometUser.uid, getString(R.string.auth_key), object : CometChat.CallbackListener<User>() {
                                    override fun onSuccess(u: User?) {
                                        setResult(RESULT_OK)
                                        finish()
                                    }

                                    override fun onError(ex: CometChatException?) {
                                        Snackbar.make(bi.root, ex?.localizedMessage ?: "Couldn't login CometChat user", Snackbar.LENGTH_SHORT).show()
                                    }
                                })
                            }

                            override fun onError(ex: CometChatException?) {
                                Snackbar.make(bi.root, ex?.localizedMessage ?: "Couldn't create CometChat user", Snackbar.LENGTH_SHORT).show()
                            }
                        })

                    } else {
                        Snackbar.make(bi.root, "Authentication failed!", Snackbar.LENGTH_SHORT).show()
                    }
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