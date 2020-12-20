package com.chilik1020.framework.local

import android.content.SharedPreferences
import com.chilik1020.data.models.UserDataModel
import com.chilik1020.data.sources.UserLocalDataSource
import com.chilik1020.framework.utils.PREFERENCE_TOKEN_KEY
import com.chilik1020.framework.utils.PREFERENCE_USER_CREATED_AT_KEY
import com.chilik1020.framework.utils.PREFERENCE_USER_EMAIL_KEY
import com.chilik1020.framework.utils.PREFERENCE_USER_FIRSTNAME_KEY
import com.chilik1020.framework.utils.PREFERENCE_USER_ID_KEY
import com.chilik1020.framework.utils.PREFERENCE_USER_LASTNAME_KEY
import com.chilik1020.framework.utils.PREFERENCE_USER_NAME_KEY
import com.chilik1020.framework.utils.PREFERENCE_USER_PHONENUMBER_KEY
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val preferences: SharedPreferences
) : UserLocalDataSource {

    override suspend fun saveAccessToken(token: String) {
        preferences.edit().putString(PREFERENCE_TOKEN_KEY, token).apply()
    }

    override suspend fun getSavedToken(): String? {
        return preferences.getString(PREFERENCE_TOKEN_KEY, null)
    }

    override suspend fun saveUserDetails(userDataModel: UserDataModel) {
        val editor: SharedPreferences.Editor = preferences.edit()
        with(editor) {
            putLong(PREFERENCE_USER_ID_KEY, userDataModel.id).apply()
            putString(PREFERENCE_USER_NAME_KEY, userDataModel.username).apply()
            putString(PREFERENCE_USER_FIRSTNAME_KEY, userDataModel.firstname).apply()
            putString(PREFERENCE_USER_LASTNAME_KEY, userDataModel.lastname).apply()
            putString(PREFERENCE_USER_EMAIL_KEY, userDataModel.email).apply()
            putString(PREFERENCE_USER_PHONENUMBER_KEY, userDataModel.phoneNumber).apply()
            putString(PREFERENCE_USER_CREATED_AT_KEY, userDataModel.createdAt).apply()
        }
    }

    override suspend fun getSavedUserDetails(): UserDataModel {
        with(preferences) {
            return UserDataModel(
                getLong(PREFERENCE_USER_ID_KEY, 0),
                getString(PREFERENCE_USER_NAME_KEY, null) ?: "",
                getString(PREFERENCE_USER_FIRSTNAME_KEY, null),
                getString(PREFERENCE_USER_LASTNAME_KEY, null),
                getString(PREFERENCE_USER_EMAIL_KEY, null),
                getString(PREFERENCE_USER_PHONENUMBER_KEY, null),
                getString(PREFERENCE_USER_CREATED_AT_KEY, null)
            )
        }
    }
}