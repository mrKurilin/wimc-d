package com.mrkurilin.wimc_d

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_DRIVERS_KEY
import com.mrkurilin.wimc_d.data.utils.MyValueEventListener
import com.mrkurilin.wimc_d.presentation.screens.driver_screen.DriverScreenFragment
import com.mrkurilin.wimc_d.presentation.screens.user_screen.UserScreenFragment

class MainActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignIn(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.inflateMenu(R.menu.wimc_menu)
        setSupportActionBar(toolbar)

        if (FirebaseAuth.getInstance().currentUser == null) {
            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .build()
            signInLauncher.launch(signInIntent)
        } else {
            startUsage()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_settings -> {
                return true
            }
            R.id.menu_log_out -> {
                FirebaseAuth.getInstance().signOut()
                val signInIntent = AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .build()
                signInLauncher.launch(signInIntent)
                return true
            }
            else -> {
                throw java.lang.Exception()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.wimc_menu, menu)
        return true
    }

    private fun onSignIn(res: FirebaseAuthUIAuthenticationResult) {
        if (res.resultCode == RESULT_OK) {
            startUsage()
        } else {
            finish()
        }
    }

    private fun startUsage() {
        Firebase.database.reference.child(REF_DRIVERS_KEY)
            .addListenerForSingleValueEvent(object : MyValueEventListener() {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val driversString = snapshot.getValue<String>() ?: ""
                    val userMail = FirebaseAuth.getInstance().currentUser?.email.toString()
                    val fragment = if (driversString.contains(userMail)) {
                        DriverScreenFragment()
                    } else {
                        UserScreenFragment()
                    }
                    supportFragmentManager.beginTransaction().replace(
                        R.id.main_container,
                        fragment
                    ).commit()
                }
            })
    }
}