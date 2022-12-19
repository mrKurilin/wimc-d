package com.mrkurilin.wimc_d.data.utils

import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

open class MyChildEventListener : ChildEventListener {
    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) = Unit

    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) = Unit

    override fun onChildRemoved(snapshot: DataSnapshot) = Unit

    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) = Unit

    override fun onCancelled(error: DatabaseError) = Unit
}