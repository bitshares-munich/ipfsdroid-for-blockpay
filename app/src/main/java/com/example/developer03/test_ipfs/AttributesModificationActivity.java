package com.example.developer03.test_ipfs;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AttributesModificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attributes_modification);

        final String values = (String) Helper.fetchStringSharePref(this,"layout_content");


        TextView Tv1 = (TextView) findViewById(R.id.tvFirst);
        TextView Tv2 = (TextView) findViewById(R.id.tvSecond);
        TextView Tv3 = (TextView) findViewById(R.id.tvThird);
        List<TextView> textViews = new ArrayList<>();
        textViews.add(Tv1);
        textViews.add(Tv2);
        textViews.add(Tv3);
        try {
            JSONObject json = new JSONObject(values);

            String textviewlist[] = new String[]{
                    "tvFirst", "tvSecond", "tvThird"};
            for(int i=0;i<json.length(); i++){
//                JSONObject jsonas = Tv1.getJSONObject("i");
                JSONObject Tv= json.getJSONObject(textviewlist[i]);
                String bg_color = Tv.getString("backgroundColor");
                String font_color = Tv.getString("fontColor");
                String font_size = Tv.getString("fontSize");
                Float f_font_size= Float.parseFloat(font_size.replaceAll("[^\\d.]", ""));


                textViews.get(i).setBackgroundColor(Color.parseColor(bg_color));
                textViews.get(i).setTextColor(Color.parseColor(font_color));
                textViews.get(i).setTextSize(TypedValue.COMPLEX_UNIT_SP, f_font_size);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


            }
}
