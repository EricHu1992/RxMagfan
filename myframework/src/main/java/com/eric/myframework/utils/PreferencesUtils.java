package com.eric.myframework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * 储存配置信息工具
 * Created by Eric on 16/5/4.
 */
public class PreferencesUtils {

    private Context context;
    private SharedPreferences.Editor editor;
    private SharedPreferences sp;

    public PreferencesUtils(Context context){
        this.context = context;
        sp = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sp.edit();
    }

    public PreferencesUtils(Context context,String preferenceName){
        this.context = context;
        sp = context.getSharedPreferences(preferenceName,Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sp.getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean state) {

        editor.putBoolean(key, state);
        editor.commit();
    }

    public String getString(String key, String defValue) {
        return sp.getString(key, defValue);
    }

    public void putString(String key, String value) {
        editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public int getInt(String key, int defValue) {
        return sp.getInt(key, defValue);
    }

    public void putInt(String key, int value) {
        editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public void putLong(String key, long value) {
        editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key, Long defValue) {
        return  sp.getLong(key, defValue);
    }

    public boolean contains(String key){
        return sp.contains(key);
    }

    public void remove(String key){
        editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public void putStringSet(String key, Set<String> values){
        editor = sp.edit();
        editor.putStringSet(key, values);
        editor.apply();
    }

    public Set<String> getStringSet(String key, Set<String> values) {
        return  sp.getStringSet(key, values);
    }

}
