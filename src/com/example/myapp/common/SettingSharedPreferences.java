package com.example.myapp.common;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.DefaultInt;
import org.androidannotations.annotations.sharedpreferences.DefaultString;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

@SharedPref
public interface SettingSharedPreferences {
    @DefaultInt(0)
    int lastReadPosition();

    @DefaultBoolean(true)
    boolean daily();

    @DefaultBoolean(true)
    boolean sound();

    @DefaultBoolean(false)
    boolean imageOnlyWifi();

    @DefaultBoolean(false)
    boolean offline();

    @DefaultInt(1)
    int fontSize();

    @DefaultBoolean(false)
    boolean renRen();

    @DefaultString("")
    String lastUpdate();

    @DefaultString("")
    String lastOfflineUpdate();

	@DefaultBoolean(true)
	boolean isIndexStarter();

    @DefaultBoolean(true)
    boolean isContentStarter();

    @DefaultString("")
    String city();

    @DefaultString("")
    String ignoreVersion();

    @DefaultBoolean(false)
    boolean isNight();
}

