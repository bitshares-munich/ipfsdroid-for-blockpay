package com.example.developer03.test_ipfs;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SmartcoinsUIAActivity extends AppCompatActivity {

    ListView listView ;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitcoins);

        String encoded = Helper.fetchStringSharePref(this,"bts.png");
        byte[] imageAsBytes = Base64.decode(encoded.getBytes(),Base64.DEFAULT);
        ivLogo = (ImageView) findViewById(R.id.ivLogo);
        ivLogo.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String values = (String) Helper.fetchStringSharePref(this,"assets_content");
        ArrayList<String> smartcoinsArray = new ArrayList<String>();

        try {
            JSONObject json = new JSONObject(values);
            JSONArray smartcoins= json.getJSONArray("smartcoins");

            Log.d("smartcoins",smartcoins.toString());
            for(int i=0;i<smartcoins.length(); i++){
                JSONObject jsonas = smartcoins.getJSONObject(i);
                String symbol = jsonas.getString("symbol")+" - "+jsonas.getString("description");
                smartcoinsArray.add(symbol);
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, smartcoinsArray);


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }

        });
    }
}
