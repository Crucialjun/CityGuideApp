package com.example.cityguideapp

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context, sessionName: String) {

    val IS_LOGIN = "IsLoggedIn"
    val SESSION_USER_SESSION = "userSession"
    val SESSION_REMEMBER_ME = "rememberMe"
    val KEY_FULLNAME = "fullname"
    private val KEY_USERNAME = "fullname"
    private val KEY_EMAIL = "email"
    val KEY_PHONENUMBER = "phonenumber"
    private val KEY_PASSWORD = "password"
    private val KEY_DATE = "date"
    private val KEY_GENDER = "gender"
    private val IS_REMEMBER_ME = "IsRememberMe"
    private val KEY_SESSION_PHONENUMBER = "phonenumber"
    private val KEY_SESSION_PASSWORD = "password"

    private val userSession: SharedPreferences =
        context.getSharedPreferences(sessionName, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = userSession.edit()

    constructor(context: Context) : this(context, "")


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

    fun createRememberMeSession(
        phoneno: String,
        password: String,
    ) {
        editor.putBoolean(IS_REMEMBER_ME, true)
        editor.putString(KEY_SESSION_PHONENUMBER, phoneno)
        editor.putString(KEY_SESSION_PASSWORD, password)

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

    fun getRememberMeDetails(): HashMap<String, String?> {
        val userData = HashMap<String, String?>()

        userData[KEY_SESSION_PASSWORD] = userSession.getString(KEY_SESSION_PHONENUMBER, null)
        userData[KEY_SESSION_PASSWORD] = userSession.getString(KEY_SESSION_PASSWORD, null)

        return userData
    }

    fun isLoggedIn(): Boolean {
        return userSession.getBoolean(IS_LOGIN, true)
    }

    fun checkRememberMe(): Boolean {
        return userSession.getBoolean(IS_REMEMBER_ME, true)
    }

    fun logOutsession() {
        editor.clear()
        editor.commit()
    }
}