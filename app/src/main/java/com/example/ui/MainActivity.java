package com.example.ui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    final String  TAG="Main Activity monitoring";
//    TextView textView;
    MyEditText editText;
    float maxChars_in_1_line=11;// experimented these are max chars in a line
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.custom_background2));
            actionBar.setTitle("Resizable EditText");
        }

//      textView = findViewById(R.id.txtview);
        editText = findViewById(R.id.editText);
        setupAutoresizeDim();
        DisplayScreen();
    }

    @SuppressLint("LongLogTag")
    private void DisplayScreen() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        //Toast.makeText(this, "width is "+width+" and height is "+height, Toast.LENGTH_SHORT).show();
    }

    private void setupAutoresizeDim() {

        final float edtsize=editText.getTextSize();
        Log.d("editsize in editT", String.valueOf(edtsize));

        Log.d("changed size editT", String.valueOf(edtsize));

        editText.setTextSize(/*textView.getTextSize()*/edtsize);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @SuppressLint("LongLogTag")
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                float ratio =charSequence.length()/maxChars_in_1_line;//editText.getSelectionStart()/maxLines;
                Log.d(TAG," on text changed Edit text szize is "+editText.getTextSize() /getResources().getDisplayMetrics().scaledDensity);

                /*if(charSequence.charAt(charSequence.length()-1)=='\n'){
                        CharSequence s = editText.getText().subSequence(0,charSequence.length()-1);
                        Log.d("value of char",s.toString().charAt(0)+"");
                        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
                        Toast.makeText(MainActivity.this, "new line and size is "+editText.getTextSize(), Toast.LENGTH_SHORT).show();
                    }*/

                if(ratio>=1) ratio=1;
//                changed here
                resizeTheEditText(editText,ratio);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    @SuppressLint("LongLogTag")
    private void resizeTheEditText(MyEditText editText, double ratio) {
        ratio=ratio/1.1;
        double newSize=48 - (64*ratio) + 32;         // 48 and 32 used to make textsize 80sp when value of (64*ratio)=0
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,(float) newSize*2);//
        Log.d(TAG,"setting the text size as "+newSize+" received ratio as "+ratio );
    }
}