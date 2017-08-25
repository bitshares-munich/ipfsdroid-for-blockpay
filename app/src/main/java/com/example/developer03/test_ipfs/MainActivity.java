package com.example.developer03.test_ipfs;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.developer03.test_ipfs.delegates.AltcoinsDelegate;


public class MainActivity extends AppCompatActivity implements AltcoinsDelegate{
    IPFSBinaryController ipfsBinaryController;
    TextView tvNodes;
    TextView tvFees;
    Context context;
    static AltcoinsDelegate altcoinsDelegate;
    Button altcoinsBtn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNodes = (TextView) findViewById(R.id.tvNodes);
        tvFees = (TextView) findViewById(R.id.tvFees);
        altcoinsBtn  = (Button) findViewById(R.id.altcoins_btn);
        altcoinsBtn.setEnabled(false);

        context = this;

        altcoinsDelegate = this;


        ipfsBinaryController = new IPFSBinaryController(this);
        ipfsBinaryController.copy();
        startProgressDialog();
        ipfsBinaryController.startDaemon(this);

   //     new ParseActivity(this);


        final Button button = (Button) findViewById(R.id.fetch_btn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startProgressDialog();
                ipfsBinaryController.startDaemon(context);
            }
        });


        final Button button_coin = (Button) findViewById(R.id.bitcoin_btn);
        button_coin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SmartcoinsUIAActivity.class);
                startActivity(intent);
            }
        });

        final Button button_layout_modification = (Button) findViewById(R.id.layout_modification_btn1);
        button_layout_modification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AttributesModificationActivity.class);
                startActivity(intent);
            }
        });
    }

    public void altcoinsEvent(int in){
        if(in==1)
        altcoinsBtn.setEnabled(true);
        if (in == 2){
            progressDialog.dismiss();
            tvFees.setText(Helper.fetchStringSharePref(context,"fee_content"));
            tvNodes.setText(Helper.fetchStringSharePref(context,"nodes_content"));
            new ParseActivity(context);
        }
    }

    public void altcoinsClick(View view){
        startActivity(new Intent(this, AltcoinsActivity.class));
    }
    public void startProgressDialog(){
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Getting files from IPFS");
        progressDialog.show();
    }


}
