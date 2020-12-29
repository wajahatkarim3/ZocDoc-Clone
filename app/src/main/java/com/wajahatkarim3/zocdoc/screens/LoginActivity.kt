package com.wajahatkarim3.zocdoc.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.MenuItem
import com.cometchat.pro.core.CometChat
import com.cometchat.pro.exceptions.CometChatException
import com.cometchat.pro.models.User
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.thetechnocafe.gurleensethi.liteutils.info
import com.wajahatkarim3.zocdoc.R
import com.wajahatkarim3.zocdoc.databinding.ActivityLoginBinding

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

        var auth = FirebaseAuth.getInstance()
        auth.signInWithEmailAndPassword(bi.txtEmail.text.toString(), bi.txtPassword.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    var firebaseUser = auth.currentUser
                    CometChat.login(firebaseUser!!.uid, getString(R.string.auth_key), object : CometChat.CallbackListener<User>() {
                        override fun onSuccess(u: User?) {
                            setResult(RESULT_OK)
                            finish()
                        }

                        override fun onError(ex: CometChatException?) {
                            Snackbar.make(bi.root, ex?.localizedMessage ?: "Couldn't login CometChat user", Snackbar.LENGTH_SHORT).show()
                        }
                    })

                } else {
                    Snackbar.make(bi.root, "Couldn't login Firebase user", Snackbar.LENGTH_SHORT).show()
                }
            }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}