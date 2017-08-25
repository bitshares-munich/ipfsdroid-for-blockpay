package com.example.developer03.test_ipfs;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.developer03.test_ipfs.model.AltcoinsArray;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

/**
 * Created by afnan on 9/27/16.
 */
public class AltcoinsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.altcoins_activity);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.llAltcoins);

        AltcoinsArray altcoinsArray =  (AltcoinsArray) Helper.fetchObjectSharePref(this,"altcoins");

        for(int i=0;i<altcoinsArray.key.size();i++){

            LinearLayout llAltcoins = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.altcoins,null);

            String image =  Helper.fetchStringSharePref(this ,altcoinsArray.key.get(i).logo);

            if(image!=null && !image.isEmpty()) {
                byte[] imageAsBytes = Base64.decode(image.getBytes(), Base64.DEFAULT);

                if (imageAsBytes != null) {


                    ImageView imageView = (ImageView) llAltcoins.findViewById(R.id.image);

                    imageView.setImageBitmap(BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length));
                }
            }

            TextView textView = (TextView) llAltcoins.findViewById(R.id.txtView);

            textView.setText(altcoinsArray.key.get(i).name);

            linearLayout.addView(llAltcoins);

        }


    }
}
