package com.dexter.redis;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;

/**
 * Created by shikhar on 30/07/17.
 */

public class Redis {

    SharedPreferences pref;
    private static Redis instance  = null;
    SharedPreferences.Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "dextercache";

    public void set(String key, String value){
        editor.putString(key, value);
        editor.commit();
    }

    public String get(String key){
        return pref.getString(key,null);
    }

    // time will be in ms (milli seconds)

    public void set(final String key, String value, int time){
        set(key,value);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                clear(key);
            }
        }, time);
    }

    public static synchronized Redis getInstance(Context context){
        if (null == instance) instance = new Redis(context);
        return instance;
    }

    public Redis(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void clear(){
        editor.clear();
        editor.commit();
    }

    public void clear(String key){
        editor.putString(key, null);
        editor.commit();
    }
}
