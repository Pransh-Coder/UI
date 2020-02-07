package com.example.ui;

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
    private ArrayList<Integer> positions= new ArrayList<>();        // would contain the positions at which lines break and start
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      textView = findViewById(R.id.txtview);
        editText = findViewById(R.id.editText);
        positions.add(0);
        setupAutoresizeDim();
        DisplayScreen();
    }

    @SuppressLint("LongLogTag")
    private void DisplayScreen() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        Toast.makeText(this, "width is "+width+" and height is "+height, Toast.LENGTH_SHORT).show();
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
//                Log.d(TAG,"current textSize is "+editText.getTextSize()/getResources().getDisplayMetrics().scaledDensity);
//                maxLines=((6)*(editText.getTextSize()/getResources().getDisplayMetrics().scaledDensity))/80;
//                Log.d(TAG," max chars at one line are "+maxLines);
                float ratio =charSequence.length()/maxChars_in_1_line;//editText.getSelectionStart()/maxLines;
                String text;
                if (charSequence.length() == 0) {
//                    textView.setText(" ");
                } else {
                    if(charSequence.charAt(charSequence.length()-1)=='\n'){
                        CharSequence s = editText.getText().subSequence(0,charSequence.length()-1);
                        Log.d("value of char",s.toString().charAt(0)+"");
                        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
                        Toast.makeText(MainActivity.this, "new line and size is "+editText.getTextSize(), Toast.LENGTH_SHORT).show();
                    }
                }
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
        double newSize=48- (64*ratio) + 32;         // 48 and 32 used to make textsize 80sp when value of (64*ratio)=0
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,(float) newSize*2);//
        Log.d(TAG,"setting the text size as "+newSize+" received ratio as "+ratio );
    }
}