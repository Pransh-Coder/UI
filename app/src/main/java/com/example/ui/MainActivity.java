package com.example.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MyEditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.txtview);
        editText = findViewById(R.id.editText);

        setupAutoresize();
    }

    private void setupAutoresize() {

        textView.setText(" ", TextView.BufferType.EDITABLE);

        float txtVsize =  textView.getTextSize();
        Log.d("textsize in TextV", String.valueOf(txtVsize));

        float edtsize=editText.getTextSize();
        Log.d("editsize in editT", String.valueOf(edtsize));

        edtsize=txtVsize;
        Log.d("changed size editT", String.valueOf(edtsize));

        editText.setTextSize(/*textView.getTextSize()*/edtsize);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("func call","onTextChanged");
                String text;
                if (charSequence.length() == 0) {
                    textView.setText(" ");
                } else {
                    if(charSequence.charAt(charSequence.length()-1)=='\n'){
                        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,9);
                        Toast.makeText(MainActivity.this, "new line and size is "+editText.getTextSize(), Toast.LENGTH_SHORT).show();
                    }
                    textView.setText(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d("func call","afterTextChanged");
                if(editable.charAt(editable.length()-1)=='\n'){
                    editText.setTextSize(TypedValue.COMPLEX_UNIT_SP,40);
                    Log.d("func call inside "," text set ");
//                    Toast.makeText(MainActivity.this, "new line and size is "+editText.getTextSize(), Toast.LENGTH_SHORT).show();
                }
//                editText.setTextSize(textView.getTextSize());
            }
        });
    }

    private float autosizeText(float size) {
        Resources var10001 = this.getResources();
        //Intrinsics.checkExpressionValueIsNotNull(var10001, "resources");
        return size / (var10001.getDisplayMetrics().density + (float) 2);
    }
}