package com.example.developer03.test_ipfs;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;

public class IPFSDaemonService extends IntentService {

    public IPFSDaemonService() {
        super("IPFSDaemonService");
    }

    @Override
    protected void onHandleIntent(final Intent intent) {
        new IPFSBinaryController(getBaseContext()).run("daemon");
        final Handler handler=new Handler();
        Runnable r = new Runnable(){
            public void run() {
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(r);
    }
}
