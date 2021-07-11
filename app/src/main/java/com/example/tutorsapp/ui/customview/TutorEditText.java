package com.example.tutorsapp.ui.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.method.DigitsKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.tutorsapp.R;

import java.util.Objects;

public class TutorEditText extends ConstraintLayout {

    LayoutInflater mInflater;
    TextView hintLbl;
    EditText aksaEdtext;
    TypedArray typedArray;
    Resources resources;

    public TutorEditText(Context context) {
        super(context);
        mInflater = LayoutInflater.from(context);
        initViews();
    }

    public TutorEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        mInflater = LayoutInflater.from(context);
        initViews();
        parseAttributes(attrs);

    }

    public TutorEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        initViews();
        parseAttributes(attrs);

    }


    private void initViews() {
        View v = mInflater.inflate(R.layout.tutor_edittext_layout, this);
        hintLbl = v.findViewById(R.id.titleTv);
        aksaEdtext = v.findViewById(R.id.editText);
        bindViews();
    }

    private void bindViews() {
    }

    private void parseAttributes(AttributeSet attrs) {
        typedArray = getContext().obtainStyledAttributes(attrs,
                R.styleable.TutorEditText, 0, 0);
        resources = getContext().getResources();
        try {
            hintLbl.setText(typedArray.getString(R.styleable.TutorEditText_titletext));
            if (typedArray.getString(R.styleable.TutorEditText_editTxt) != null && !typedArray.getString(R.styleable.TutorEditText_editTxt).isEmpty())
                aksaEdtext.setText(typedArray.getString(R.styleable.TutorEditText_editTxt));
            else
                aksaEdtext.setHint(typedArray.getString(R.styleable.TutorEditText_editHint));
            aksaEdtext.setEnabled(typedArray.getBoolean(R.styleable.TutorEditText_edEnable, false));

            // imeoOptions Set hoye hain
            if (typedArray.getInt(R.styleable.TutorEditText_android_imeOptions, 0) != 0) {
                aksaEdtext.setImeOptions(typedArray.getInt(R.styleable.TutorEditText_android_imeOptions, 0));
            } else {
                aksaEdtext.setImeOptions(EditorInfo.IME_ACTION_DONE);
            }
            if (typedArray.getInt(R.styleable.TutorEditText_android_inputType, 0) - 1 == InputType.TYPE_TEXT_VARIATION_PERSON_NAME) {
                aksaEdtext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(typedArray.getInt(R.styleable.TutorEditText_ed_maxLenght, 20)), nameInputFilter});
            } else if (typedArray.getInt(R.styleable.TutorEditText_android_inputType, 0) == InputType.TYPE_CLASS_TEXT) {

                aksaEdtext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(typedArray.getInt(R.styleable.TutorEditText_ed_maxLenght, 20)), alphaNumricFilter});
            }else if (typedArray.getInt(R.styleable.TutorEditText_android_inputType, 0) == InputType.TYPE_CLASS_NUMBER) {

                aksaEdtext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(typedArray.getInt(R.styleable.TutorEditText_ed_maxLenght, 20)), numberOnly});
            }else {
                aksaEdtext.setFilters(new InputFilter[]{new InputFilter.LengthFilter(typedArray.getInt(R.styleable.TutorEditText_ed_maxLenght, 20))});

            }


            // max lenght
            if (typedArray.getInteger(R.styleable.TutorEditText_ed_maxLenght, 0) != 0) {

            }


            // inputType Set
            if (typedArray.getInt(R.styleable.TutorEditText_android_inputType, EditorInfo.TYPE_TEXT_VARIATION_NORMAL) != EditorInfo.TYPE_NULL) {
                aksaEdtext.setInputType(typedArray.getInt(R.styleable.TutorEditText_android_inputType, EditorInfo.TYPE_TEXT_VARIATION_NORMAL));
            } else {
                aksaEdtext.setInputType(InputType.TYPE_CLASS_TEXT);
            }


        } catch (Exception e) {
            Log.e("ex", Objects.requireNonNull(e.getMessage()));
        } finally {
            typedArray.recycle();
        }

    }

    public String getText() {
        return aksaEdtext.getText().toString();
    }

    public void setEditTextValue(String value) {
        aksaEdtext.setText(value);
    }

    InputFilter nameInputFilter = new InputFilter() {
        public CharSequence filter(CharSequence cs, int start,
                                   int end, Spanned dest, int dstart, int dend) {
            // TODO Auto-generated method stub
            if (cs.toString().equals("")) { // for backspace
                return cs;
            }
            if (cs.toString().matches("^[a-zA-Z ]+$")) {
                return cs;
            }
            return cs.toString().substring(0, cs.length() - 1);
        }
    };

    InputFilter alphaNumricFilter = new InputFilter() {
        public CharSequence filter(CharSequence cs, int start,
                                   int end, Spanned dest, int dstart, int dend) {
            // TODO Auto-generated method stub
            if (cs.toString().equals("")) { // for backspace
                return cs;
            }
            if (cs.toString().matches("^[A-Za-z0-9, ]+$")) {
                return cs;
            }
            return cs.toString().substring(0, cs.length() - 1);
        }
    };

    InputFilter numberOnly = new InputFilter() {
        public CharSequence filter(CharSequence cs, int start,
                                   int end, Spanned dest, int dstart, int dend) {
            // TODO Auto-generated method stub
            if (cs.toString().equals("")) { // for backspace
                return cs;
            }
            if (cs.toString().matches("^[0-9]+$")) {
                return cs;
            }
            return cs.toString().substring(0, cs.length() - 1);
        }
    };

    InputFilter emailOnly = new InputFilter() {
        public CharSequence filter(CharSequence cs, int start,
                                   int end, Spanned dest, int dstart, int dend) {
            // TODO Auto-generated method stub
            if (cs.toString().equals("")) { // for backspace
                return cs;
            }
            if (cs.toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+")) {
                return cs;
            }
            return cs.toString().substring(0, cs.length() - 1);
        }
    };
}
