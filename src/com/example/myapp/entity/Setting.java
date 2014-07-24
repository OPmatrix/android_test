package com.example.myapp.entity;


import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import com.example.myapp.common.SettingSharedPreferences_;
import org.acra.ACRA;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@EBean
public class Setting {

    private static Setting_ _instance;

    private final static Object LOCK = new Object();

    public static Setting_ getInstance(Context context) {
        if (_instance == null) {
            synchronized (LOCK) {
                if (_instance == null) {
                    _instance = Setting_.getInstance_(context);
                }
            }
        }


        return _instance;
    }

    public static Setting_ getInstance() {
        return _instance;
    }

    public void addChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        pref.getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
    }

    public void removeChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        pref.getSharedPreferences().unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Pref
    SettingSharedPreferences_ pref;
    private static DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void setLastOfflineUpdate(Date value) {
        pref.lastUpdate().put(format1.format(value));
    }


    public void setIsNight(boolean value) {
        pref.isNight().put(value);
    }

    public boolean getIsNight() {
        return pref.isNight().get();
    }

    public void clearLastOfflineUpdate() {
        pref.lastUpdate().put("");
    }

    public boolean getContentStarter() {
        return pref.isContentStarter().get();
    }

    public boolean getIndexStarter() {
        return pref.isIndexStarter().get();
    }

    public void setContentStarted() {
        pref.edit().isContentStarter().put(false).apply();
    }

    public void setIndexStarted() {
        pref.edit().isIndexStarter().put(false).apply();
    }

    public Date getLastOfflineUpdate() {
        String config = pref.lastUpdate().get();
        if (TextUtils.isEmpty(config)) {
            return null;
        }

        Date lastUpdate;
        try {
            lastUpdate = format1.parse(config);
            return lastUpdate;
        } catch (ParseException e) {
        }

        return null;
    }

    public void setCity(String city) {
        pref.city().put(city);
    }

    public String getCity() {
        return pref.city().get();
    }

    public boolean getOffline() {
        return pref.offline().get();
    }


    private static String ANDROID_ID;

    public String getAndroidId(Context context) {
        try {
            if (TextUtils.isEmpty(ANDROID_ID)) {
                ANDROID_ID = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
            }

            return ANDROID_ID;
        } catch (Exception e) {
            ACRA.getErrorReporter().handleException(e);
        }


        return "";
    }

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            Log.e("settings", "settings  ex:" + e);
        } catch (UnsupportedEncodingException e) {
            Log.e("settings", "settings  ex:" + e);
        }
        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {

            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.substring(8, 24).toString().toUpperCase();
    }
}
