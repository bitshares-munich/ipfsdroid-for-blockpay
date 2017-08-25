package com.example.developer03.test_ipfs;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.preference.PreferenceManager;
import android.util.Base64;

import com.example.developer03.test_ipfs.model.Altcoins;
import com.example.developer03.test_ipfs.model.AltcoinsArray;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by afnan on 9/27/16.
 */
public class Helper {
    public static void storeObjectSharePref(Context context, String key, Object object) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(object);
        prefsEditor.putString(key, json);
        prefsEditor.commit();

    }

    public static Object fetchObjectSharePref(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = preferences.getString(key, "");
        Object value = new Gson().fromJson(json, AltcoinsArray.class);
        if (value == null)
            throw new NullPointerException();
        return value;
    }
    public static String fetchStringSharePref(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String json = preferences.getString(key, "");
        return json;
    }
    public static void storeStringSharePref(Context context, String key, String object) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor prefsEditor = preferences.edit();
        prefsEditor.putString(key, object);
        prefsEditor.commit();

    }
    public static void saveImageFile(InputStream inputStream,String fileName, Context context){
        FileOutputStream out = null;
        Bitmap bmp = BitmapFactory.decodeStream(inputStream);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        String encoded = Base64.encodeToString(b, Base64.DEFAULT);
        Helper.storeStringSharePref(context,fileName,encoded);
    }
}
