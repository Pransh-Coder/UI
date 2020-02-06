package com.example.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditText extends EditText {

    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        //parseAttributes(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //parseAttributes(context, attrs);
        init();
    }
    private void init() {
        setIncludeFontPadding(false);
        setPadding(0,0,0,0);
        setPaddingRelative(0,0,0,0);
    }
    /*private void parseAttributes(Context context, AttributeSet attrs) {
        TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.Simplified);

        int typefaceValue = values.getInt(R.styleable.Simplified_typeface, 0);
        values.recycle();

        setTypeface(MyFontUtil.obtaintTypeface(context, typefaceValue));
    }*/

}
