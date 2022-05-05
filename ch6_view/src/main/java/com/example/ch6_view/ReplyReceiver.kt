package com.example.ch6_view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReplyReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent != null) {
            if(intent.getAction().equals(Intent.ACTION_APP_ERROR)){

            }else {
                Log.d("kkkmmmuuu","context : $context, intent : ${intent.data}")
            }
        }
    }

}