package com.promact.akansh.irecall;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;

/**
 * Created by Akansh on 29-06-2017.
 */

public class SaveSharedPref
{
    static final String PREF_ID_TOKEN = "idToken";
    static final String PREF_USERNAME = "username";
    static final String PREF_EMAIL = "email";
    static final String PREF_PHOTO_URI = "photoUri";

    static SharedPreferences getSharedPreferences(Context context)
    {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setPrefs(Context context, String idToken, String username, String email, String photoUri)
    {
        Editor editor = getSharedPreferences(context).edit();
        editor.putString(PREF_ID_TOKEN, idToken);
        editor.putString(PREF_USERNAME, username);
        editor.putString(PREF_EMAIL, email);
        editor.putString(PREF_PHOTO_URI, photoUri);
        editor.commit();
    }

    public static String getToken(Context context)
    {
        return getSharedPreferences(context).getString(PREF_ID_TOKEN, "");
    }

    public static String getUsername(Context context)
    {
        return getSharedPreferences(context).getString(PREF_USERNAME, "");
    }

    public static String getEmail(Context context)
    {
        return getSharedPreferences(context).getString(PREF_EMAIL, "");
    }

    public static String getPhotoUri(Context context)
    {
        return getSharedPreferences(context).getString(PREF_PHOTO_URI, "");
    }
}
