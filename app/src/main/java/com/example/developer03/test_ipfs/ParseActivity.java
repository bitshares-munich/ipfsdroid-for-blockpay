package com.example.developer03.test_ipfs;

import android.content.Context;
import android.util.Log;

import com.example.developer03.test_ipfs.model.Altcoins;
import com.example.developer03.test_ipfs.model.AltcoinsArray;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by afnan on 9/27/16.
 */
public class ParseActivity {

    String altcoinsList[] = new String[]{
            "coinType", "name", "symbol", "walletSymbol",
            "walletType", "transactionFee", "precision", "backingCoinType",
            "supportsOutputMemos", "logo"};

    AltcoinsArray altcoinsArray = new AltcoinsArray();

    public ParseActivity(HashMap<Integer,String>map){

        Log.i("key",map+"");

        Log.i("key",map.get(1)+"");

        Log.i("key",map.get(2)+"");
    }

    public ParseActivity(Context context){

      // String Json =  loadJSONFromAsset(context).replaceAll(" ","");

       String Json =  Helper.fetchStringSharePref(context,"altcoins_content");

        Json = Json.replaceAll(" ","");

        Json = Json.replace("\n", "").replace("\r", "");

        Log.i("json",Json.replaceAll(" ",""));

        try {


            String altcoinsarray = ParseJsonObject(Json,"altcoins");

            int count = TotalArraysOfObj(altcoinsarray);

            Gson gson = new Gson();

           for(int i = 0; i<count ; i++){

               String altcoin = ParseObjectFromJsonArray(altcoinsarray,i);

               Altcoins altcoins = gson.fromJson(altcoin,Altcoins.class);

               altcoinsArray.key.add(altcoins);

           }
            Helper.storeObjectSharePref(context,"altcoins",altcoinsArray);

            MainActivity.altcoinsDelegate.altcoinsEvent(1);

        }catch (Exception e){

        }

        Log.i("json",Json.replaceAll(" ",""));

    }

    public String loadJSONFromAsset(Context context) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(context.getAssets().open("altcoins.json")));
            StringBuilder sb = new StringBuilder();
            String mLine = reader.readLine();
            while (mLine != null) {
                sb.append(mLine); // process line
                mLine = reader.readLine();
            }
            reader.close();
            return sb.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String ParseJsonObject(String Json , String Req){
        try {
            if(Json.contains(Req)){
                JSONObject myJson = new JSONObject(Json);
                return  myJson.getString(Req);}
        }catch (Exception e){}
        return "";
    }
    public static String ParseObjectFromJsonArray(String Json , int position){
        try {
            JSONArray myArray = new JSONArray(Json);
            if(myArray.length()>=position){
                return  myArray.get(position).toString();
            }
        }catch (Exception e){
        }
        return "";
    }
    public static int TotalArraysOfObj(String Json){
        try {
            JSONArray myArray = new JSONArray(Json);
            return myArray.length();
        }catch (Exception e){
        }
        return -1;
    }
}
