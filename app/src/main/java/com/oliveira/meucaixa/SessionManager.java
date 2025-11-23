package com.oliveira.meucaixa;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {

    private static final String USER_ID_KEY = "last_user_id";
    private final SharedPreferences sharedPreferences;

    public SessionManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public long getLoggedInUserId() {
        // Retorna -1 se nenhum usuário estiver logado
        return sharedPreferences.getLong(USER_ID_KEY, -1);
    }
}
