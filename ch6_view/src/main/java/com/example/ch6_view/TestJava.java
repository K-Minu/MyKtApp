package com.example.ch6_view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ch6_view.databinding.CustomDialogBinding;

public class TestJava extends AppCompatActivity {

    CustomDialogBinding bind;

    protected void onCreate(Bundle bb){
        super.onCreate(bb);

        bind = CustomDialogBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());
    }
}
