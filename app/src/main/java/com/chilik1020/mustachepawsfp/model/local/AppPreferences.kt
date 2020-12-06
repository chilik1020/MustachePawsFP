package com.chilik1020.mustachepawsfp.model.local

import android.content.SharedPreferences
import com.chilik1020.mustachepawsfp.model.entities.UserVO
import javax.inject.Inject

class AppPreferences {

    @Inject
    lateinit var preferences: SharedPreferences

    val accessToken: String?
        get() = preferences.getString("ACCESS_TOKEN", null)

    fun storeAccessToken(accessToken: String) {
        preferences.edit().putString("ACCESS_TOKEN", accessToken).apply()
    }

    val userDetails: UserVO
        get() {
            with(preferences) {
                return UserVO(
                    getLong("ID", 0),
                    getString("USERNAME", null),
                    getString("FIRSTNAME", null),
                    getString("LASTNAME", null),
                    getString("EMAIL", null),
                    getString("PHONENUMBER", null),
                    getString("CREATED_AT", null)
                )
            }
        }

    fun storeUserDetails(user: UserVO) {
        val editor: SharedPreferences.Editor = preferences.edit()
        with(editor) {
            putLong("ID", user.id).apply()
            putString("USERNAME", user.username).apply()
            putString("FIRSTNAME", user.firstname).apply()
            putString("LASTNAME", user.lastname).apply()
            putString("EMAIL", user.email).apply()
            putString("PHONENUMBER", user.phoneNumber).apply()
            putString("CREATED_AT", user.createdAt).apply()
        }
    }

    fun clear() {
        val editor = preferences.edit()
        editor.clear()
        editor.apply()
    }
}