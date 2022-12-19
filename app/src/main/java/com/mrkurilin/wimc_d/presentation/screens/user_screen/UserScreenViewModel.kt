package com.mrkurilin.wimc_d.presentation.screens.user_screen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.mrkurilin.wimc_d.data.Constants.Companion.REF_CARS_KEY

class UserScreenViewModel(app: Application) : AndroidViewModel(app) {



    fun provideCarsReference(): DatabaseReference {
        return Firebase.database.reference.child(REF_CARS_KEY)
    }
}