package com.example.tutorsapp.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tutorsapp.R;
import com.example.tutorsapp.helper.Constants;

public class ContactDetailsDialog extends Dialog {
    EditText contactOneEd, contactTwoEd;
    Button saveBtn;
    Numbers numbers;

    public ContactDetailsDialog(@NonNull Context context, Numbers numbers) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        setCancelable(true);
        setContentView(R.layout.contact_details_dialog);
        this.numbers = numbers;
        saveBtn = findViewById(R.id.saveBtn);
        contactOneEd = findViewById(R.id.contactOneEd);
        contactTwoEd = findViewById(R.id.contactTwoEd);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contactOneEd.getText().toString().isEmpty() || !Constants.phoneRegex(contactOneEd.getText().toString())) {
                    contactOneEd.setError("Please enter valid mobile number");
                    contactOneEd.requestFocus();
                } else {
                    numbers.numbers(contactTwoEd.getText().toString().isEmpty() ? contactOneEd.getText().toString() :
                            contactOneEd.getText().toString() + "," + contactTwoEd.getText().toString());
                    dismiss();
                }
            }
        });

    }

    public interface Numbers {
        public void numbers(String numbers);
    }
}
