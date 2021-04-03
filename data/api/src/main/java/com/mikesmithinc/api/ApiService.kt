package com.mikesmithinc.api

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ApiService
@Inject
constructor() {
    private val database = Firebase.database
    private val myRef = database.getReference("classified")
    private var counter = 1

    fun submit() {
        myRef.setValue("message ${counter++}")
    }

    sealed class EventResponse {
        data class Changed(val snapshot: DataSnapshot) : EventResponse()
        data class Cancelled(val error: DatabaseError) : EventResponse()
    }

    @ExperimentalCoroutinesApi
    fun getClassifieds(): Flow<EventResponse> {
        return myRef.valueEventFlow()
    }

    @ExperimentalCoroutinesApi
    private fun DatabaseReference.valueEventFlow(): Flow<EventResponse> = callbackFlow {
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot): Unit =
                sendBlocking(EventResponse.Changed(snapshot))

            override fun onCancelled(error: DatabaseError): Unit =
                sendBlocking(EventResponse.Cancelled(error))
        }
        addValueEventListener(valueEventListener)
        awaitClose {
            removeEventListener(valueEventListener)
        }
    }
}