package com.project.ifoah.viewmodels.skidata

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

const val SKIDATA = "SkiData"

class SkiDataViewModel constructor(
    val db: FirebaseFirestore,
    val uid: String?
) : ViewModel() {

    private val _skiData = getDataFromDB()
    val skiData: MutableMap<String, MutableList<SkiSession>>
        get() = _skiData


    fun getDataFromDB(): MutableMap<String, MutableList<SkiSession>> {

        var tmpFetchedSessions = mutableListOf<SkiSession>()
        var orderedSessions = mutableMapOf<String, MutableList<SkiSession>>()
        uid?.let {
            db.collection(SKIDATA).document(uid).get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        document.data?.forEach { docEntry ->
                            var tmpSkiSession = SkiSession();
                            val tempEntry = docEntry.value as Map<String, Any>
                            tempEntry.forEach { (key, sessionVal) ->
                                if (key == "startDate") {
                                    tmpSkiSession.date.time = Timestamp(sessionVal as Long * 1000).time
                                }
                                if (key == "turns") {
                                    val tmpData = sessionVal as Map<String, List<Number>>
                                    tmpData.forEach { (key, turnData) ->
                                        val tmpTurn =
                                            Turn(maxAngle = turnData[0], avgSpeed = turnData[1])
                                        tmpSkiSession.totalTurns.add(tmpTurn)
                                    }
                                }
                            }
                            //tmpSkiSession.calcAvgAngle()
                            tmpFetchedSessions.add(tmpSkiSession)
                        }
                    }
                    tmpFetchedSessions.forEach { session ->
                        val dateFormat = SimpleDateFormat("d-M-y")
                        val keyTime = dateFormat.format(session.date.time)
                        when (orderedSessions.containsKey(keyTime.toString())) {
                            false -> orderedSessions.set(keyTime.toString(), mutableListOf(session))
                            true -> orderedSessions[keyTime.toString()]?.add(session);
                        }
                    }

                }
        }

        return orderedSessions
    }

}




