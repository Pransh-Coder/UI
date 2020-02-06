package com.example.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class MyTextView extends TextView {

    public MyTextView(Context context) {
        super(context);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //parseAttributes(context, attrs);
        init();
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //parseAttributes(context, attrs);
        init();
    }
    private void init() {
        setIncludeFontPadding(false);
        setPadding(0,0,0,0);
        setPaddingRelative(0,0,0,0);

    }
}
