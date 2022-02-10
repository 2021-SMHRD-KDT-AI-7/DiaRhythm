package com.example.projecttest1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class OptionCodeTypeDialog extends Dialog {

    private Context context;

    public OptionCodeTypeDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.option_codetype_dialog);
    }
}