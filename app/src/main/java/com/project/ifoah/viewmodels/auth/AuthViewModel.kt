package com.project.ifoah.viewmodels.auth

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.toObject
import com.project.ifoah.data.Event
import com.project.ifoah.data.UserData
import java.lang.Exception
import javax.inject.Inject

const val USERS = "UserMeta"


class AuthViewModel constructor(
    val auth: FirebaseAuth,
    val db : FirebaseFirestore,
) : ViewModel() {
    var isInProgress = mutableStateOf(false)
    var singIn = mutableStateOf(false)
    var userData = mutableStateOf<UserData?>(null)
    var popNotification = mutableStateOf<Event<String>?>(null)

    init {
        //auth.signOut()
        val currentUser = auth.currentUser
        singIn.value = currentUser != null
        currentUser?.uid?.let { uid ->
            Log.d("uid",uid)
            getUserData(uid)
        }
    }

    fun signOut(){
        auth.signOut()
        userData.value = null
        singIn.value = false
    }


    fun signUp(userName: String, email: String, pass: String,  name: String){
        if (userName.isEmpty() or email.isEmpty() or pass.isEmpty()) {
            handledException(customMessage = "Please fill in all fields")
            return
        }
        db.collection(USERS).whereEqualTo("UserMeta", userName.replace(" ","")).get()
            .addOnSuccessListener { documents ->
                if (documents.size() > 0) {
                    handledException(customMessage = "Username already exist")
                    isInProgress.value = false
                } else {
                    auth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("somewherehere?", "hello")
                                singIn.value = true
                                createOrUpdateProfile(userName = userName,
                                    name = name,
                                    email = email,
                                    )
                            } else {
                                handledException(customMessage = "signed failed")
                            }
                            isInProgress.value = false
                        }
                        .addOnFailureListener {  }
                }
            }
            .addOnFailureListener {  }

    }

    private fun createOrUpdateProfile(
        name: String? = null,
        userName: String? = null,
        email: String? = null,
        ) {
        val uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name ?: userData.value?.name,
            email = email ?: userData.value?.email,
            username = userName ?: userData.value?.username,
        )
        uid?.let {
            isInProgress.value = true
            db.collection(USERS).document(uid).get()
                .addOnSuccessListener {
                    if (it.exists()) {
                        it.reference.update(userData.toMap())
                            .addOnSuccessListener {
                                this.userData.value = userData
                                isInProgress.value = false
                            }
                            .addOnFailureListener {
                                handledException(customMessage = "Cannot Update user")
                                isInProgress.value =false

                            }
                    } else {
                        db.collection(USERS).document(uid).set(userData)
                        getUserData(uid)
                        isInProgress.value = false
                    }
                }
                .addOnFailureListener { exception ->
                    handledException(exception, "Cannot create user")
                    isInProgress.value = false
                }
        }
    }

    private fun getUserData(uid: String) {
        isInProgress.value = true
        db.collection(USERS).document(uid).get()
            .addOnSuccessListener {
                val user = it.toObject<UserData>()
                Log.d("myuser",it.toString())
                userData.value = user
                isInProgress.value = false
//                popNotification.value = Event("User data retrieve Successfully")
            }
            .addOnFailureListener { exception ->
                handledException(exception, "Cannot retrieve user data")
                isInProgress.value = false
            }
    }

    fun login(email: String, pass: String) {
        if (email.isEmpty() or pass.isEmpty()) {
            handledException(customMessage = "Please fill in all fields")
            return
        }
        isInProgress.value = true
        auth.signInWithEmailAndPassword(email, pass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    singIn.value = true
                    isInProgress.value = false
                    auth.currentUser?.uid?.let { uid ->
//                        handledException(customMessage = "Login success")
                        getUserData(uid)
                    }
                } else {
                    handledException(task.exception, "Login failed")
                    isInProgress.value = false
                }
            }
            .addOnFailureListener { exc ->
                handledException(exc, "Login failed")
                isInProgress.value = false
            }
    }

    private fun handledException(exception: Exception? = null, customMessage: String = "") {
        exception?.printStackTrace()
        val errorMsg =  exception?.message ?: ""
        val message = if (customMessage.isEmpty()) { errorMsg } else { "$customMessage: $errorMsg" }
        popNotification.value = Event(message)
    }
}