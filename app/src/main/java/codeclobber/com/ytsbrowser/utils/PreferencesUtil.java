package codeclobber.com.ytsbrowser.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wahib on 2/19/17.
 */

public class PreferencesUtil {

    private SharedPreferences mPreferences;

    public PreferencesUtil(Context context) {
        String FILE_NAME = "YtsTorrentsPref";
        mPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    // Save Methods
    public void saveString(String key, String value) {
        mPreferences.edit().putString(key, value).apply();
    }

    public void saveInt(String key, int value) {
        mPreferences.edit().putInt(key, value).apply();
    }

    public void saveBoolean(String key, boolean value) {
        mPreferences.edit().putBoolean(key, value).apply();
    }

    // Get Methods
    public String getString(String key) {
        return mPreferences.getString(key, null);
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mPreferences.getBoolean(key, defaultValue);
    }

}
