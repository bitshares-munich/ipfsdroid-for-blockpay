package com.example.developer03.test_ipfs

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.TextView
import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.Array

class IPFSBinaryController(val context: Context) {

    fun copy() {
        val calculateFilename = calculateFilename()
        if (calculateFilename != null) {
            doCopyForArch(calculateFilename)
        } else {
            AlertDialog.Builder(context).setMessage("no supported ABIs").show()
        }
    }

    private fun calculateFilename(): String? {
        if (Build.CPU_ABI.startsWith("x86")) {
            return "ipfs-android-16-386"
        } else if (Build.CPU_ABI.startsWith("arm")) {
            return "ipfs-android-16-arm"
        }
        return null
    }

    private fun doCopyForArch(s: String) {
        val out = FileOutputStream(getFile())
        context.assets.open(s).copyTo(out)
        out.close()
        getFile().setExecutable(true)
    }

    fun run(cmd: String): String {
        val env = arrayOf("IPFS_PATH=" + File(context.filesDir, ".ipfs").absoluteFile)
        val process = Runtime.getRuntime().exec(getFile().absolutePath + " " + cmd, env)

        process.waitFor()

        val err = process.errorStream.reader().readText()

        if (!err.isEmpty()) {
            return err
        }
        return process.inputStream.reader().readText()
    }
    fun getFileIPFS(command: String, prefKey: String) :Boolean{
        val env = arrayOf("IPFS_PATH=" + File(context.filesDir, ".ipfs").absoluteFile)
        val process1 = Runtime.getRuntime().exec(getFile().absolutePath + " " + command, env)
        process1.waitFor()
        val err = process1.errorStream.reader().readText()

        if (!err.isEmpty()) {
            Log.e("Error: ",err)
            return false
        }else {
            val result = process1.inputStream.reader().readText().toString()
            Helper.storeStringSharePref(context, prefKey, result)
            Log.d("Content: ", result)
            return true
        }
    }

    fun getImageIPFS(command: String, fileName: String) :Boolean{
        val env = arrayOf("IPFS_PATH=" + File(context.filesDir, ".ipfs").absoluteFile)
        val process1 = Runtime.getRuntime().exec(getFile().absolutePath + " " + command, env)
        process1.waitFor()
        val err = process1.errorStream.reader().readText()

        if (!err.isEmpty()) {
            Log.e("Error: ",err)
            return false
        }else {
            val result = process1.inputStream
            Helper.saveImageFile(result, fileName,context)
            Log.d("Content: ", result.toString())
            return true
        }
    }

    fun startDaemon(ctx: Context): Boolean {
        val env = arrayOf("IPFS_PATH=" + File(context.filesDir, ".ipfs").absoluteFile)
        val process = Runtime.getRuntime().exec(getFile().absolutePath + " " + "init", env)
        process.waitFor()
        val daemonProcess = Runtime.getRuntime().exec(getFile().absolutePath + " " + "daemon", env)
        Log.d("Content: ","Daemon Started")
        val handler = Handler()
        val runnableCode = object : Runnable {
            override fun run() {
                var found = getFileIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/fee_file.json","fee_content")
                if (!found) {
                    handler.postDelayed(this, 1000)
                }else{
                    getFileIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/nodesurl.json","nodes_content")
                    getFileIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/assets.json","assets_content")
                    getFileIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/altcoins.json","altcoins_content")
                    getFileIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/layout.json","layout_content")

                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/bts.png","bts.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/eth.png","eth.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/nbt.png","nbt.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/btc.png","btc.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/ltc.png","ltc.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/ppc.png","ppc.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/open.png","open.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/steem.png","steem.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/blocktrade_icon.png","blocktrade_icon.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/doge.png","doge.png")
                    getImageIPFS("cat /ipns/QmU5CYayWCzzVHxyLPHU6YHdpXqg1PNpRc2hTSbGaGaLW4/logo/dash.png","dash.png")

                    daemonProcess.destroy()
                    MainActivity.altcoinsDelegate.altcoinsEvent(2)
                }
            }
        }
        handler.post(runnableCode)
        return true;
    }

    fun getFile() = File(context.filesDir, "ipfs_bin")


    fun runWithAlert(ctx: Context, command: String) {
        val handler = Handler()
        val progressDialog = ProgressDialog(ctx)
        progressDialog.setMessage("executing ipfs " + command)
        progressDialog.show();

        Thread(Runnable {
            val run = run(command)
            handler.post {
                progressDialog.dismiss()
                AlertDialog.Builder(ctx)
                        .setMessage(run)
                        .setPositiveButton(android.R.string.ok, null)
                        .show()
            }
        }).start()

    }
}
