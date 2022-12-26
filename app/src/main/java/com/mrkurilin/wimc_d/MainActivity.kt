package com.mrkurilin.wimc_d

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.mrkurilin.wimc_d.data.repositories.firebase_repositories.DriversEmailsFirebaseRepository
import com.mrkurilin.wimc_d.presentation.screens.driver_screen.DriverScreenFragment
import com.mrkurilin.wimc_d.presentation.screens.user_screen.UserScreenFragment
import kotlinx.coroutines.launch

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
        setSupportActionBar(toolbar)

        if (FirebaseAuth.getInstance().currentUser == null) {
            val signInIntent = AuthUI.getInstance().createSignInIntentBuilder().build()
            signInLauncher.launch(signInIntent)
        } else {
            startUsage()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.wimc_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                return true
            }
            R.id.menu_log_out -> {
                FirebaseAuth.getInstance().signOut()
                val signInIntent = AuthUI.getInstance().createSignInIntentBuilder().build()
                signInLauncher.launch(signInIntent)
                true
            }
            else -> {
                throw java.lang.Exception()
            }
        }
    }

    private fun onSignIn(res: FirebaseAuthUIAuthenticationResult) {
        if (res.resultCode == RESULT_OK) {
            startUsage()
        } else {
            onRestart()
        }
    }

    private fun startUsage() = lifecycleScope.launch {
        val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email.toString()

        val IsCurrentUserDriver = DriversEmailsFirebaseRepository.isDriver(currentUserEmail)

        val fragment = if (IsCurrentUserDriver) {
            DriverScreenFragment()
        } else {
            UserScreenFragment()
        }

        supportFragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit()
    }
}