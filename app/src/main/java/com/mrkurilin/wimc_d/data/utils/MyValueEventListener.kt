package com.mrkurilin.wimc_d.data.utils

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

open class MyValueEventListener : ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) = Unit

    override fun onCancelled(error: DatabaseError) = Unit
}