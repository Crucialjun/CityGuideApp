package com.example.cityguideapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    val IS_LOGIN = "IsLoggedIn"
    val KEY_FULLNAME = "fullname"
    private val KEY_USERNAME = "fullname"
    private val KEY_EMAIL = "email"
    val KEY_PHONENUMBER = "phonenumber"
    private val KEY_PASSWORD = "password"
    private val KEY_DATE = "date"
    private val KEY_GENDER = "gender"

    private val userSession: SharedPreferences =
        context.getSharedPreferences("userSession", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = userSession.edit()


    fun createLoginSession(
        fullname: String,
        username: String,
        email: String,
        phoneno: String,
        password: String,
        age: String,
        gender: String
    ) {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_FULLNAME, fullname)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_PHONENUMBER, phoneno)
        editor.putString(KEY_DATE, age)
        editor.putString(KEY_PASSWORD, password)
        editor.putString(KEY_GENDER, gender)
        editor.putString(KEY_USERNAME, username)

        editor.commit()

    }

    fun getUserDetails(): HashMap<String, String?> {
        val userData = HashMap<String, String?>()

        userData[KEY_FULLNAME] = userSession.getString(KEY_FULLNAME, null)
        userData[KEY_USERNAME] = userSession.getString(KEY_USERNAME, null)
        userData[KEY_EMAIL] = userSession.getString(KEY_EMAIL, null)
        userData[KEY_PHONENUMBER] = userSession.getString(KEY_PHONENUMBER, null)
        userData[KEY_DATE] = userSession.getString(KEY_DATE, null)
        userData[KEY_PASSWORD] = userSession.getString(KEY_PASSWORD, null)
        userData[KEY_GENDER] = userSession.getString(KEY_GENDER, null)

        return userData
    }

    fun isLoggedIn(): Boolean {
        return userSession.getBoolean(IS_LOGIN, true)
    }

    fun logOutsession() {
        editor.clear()
        editor.commit()
    }
}