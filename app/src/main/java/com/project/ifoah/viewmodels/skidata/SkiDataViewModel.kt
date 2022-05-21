package com.project.ifoah.viewmodels.skidata

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.project.ifoah.viewmodels.auth.USERS
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

const val SKIDATA = "SkiData"

class SkiDataViewModel constructor(
    val db: FirebaseFirestore,
    val uid: String?
) : ViewModel() {

    private val _skiData = mutableListOf<SkiSession>()
    val skiData: List<SkiSession>
        get() = _skiData

    fun getDataFromDB() {
        var tmpSkiSession = SkiSession();
        uid?.let {
            db.collection(SKIDATA).document(uid).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        //Log.d("getData", it.data
                        document.data?.forEach { docEntry ->
                            val tempEntry = docEntry.value as Map<String, Any>
                            tempEntry.forEach { (key, sessionVal) ->
                               if(key == "startDate") {
                                   tmpSkiSession.date.time = Timestamp(sessionVal as Long).time
                               }
                                if (key == "turns") {
                                    val tmpData = sessionVal as Map<String, List<Number>>
                                    tmpData.forEach { (key, turnData) ->
                                        val tmpTurn = Turn(maxAngle = turnData[0], avgSpeed = turnData[1])
                                        Log.d("stuff", tmpTurn.toString())
                                        tmpSkiSession.totalTurns.add(tmpTurn)
                                    }
                                }
                            }
                            /* after docEntry */
                           _skiData.add(tmpSkiSession)
                        }
                    }
                }
        }
    }


}




