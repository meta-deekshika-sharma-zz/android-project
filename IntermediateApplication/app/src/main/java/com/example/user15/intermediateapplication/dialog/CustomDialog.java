package com.example.user15.intermediateapplication.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user15.intermediateapplication.R;
import com.example.user15.intermediateapplication.activities.DialogShowActivity;

public class CustomDialog{

    private Dialog dialog;
    private EditText dialogEditText;
    private Button sendButton, cancelButton;

    public void showDialog(final Activity activity) {
        dialog = new Dialog(activity, R.style.Dialog);
        LayoutInflater layoutInflater = activity.getLayoutInflater();

        View view = layoutInflater.inflate(R.layout.custom_dialog, null);
        dialog.setContentView(view);

        dialogEditText = view.findViewById(R.id.dialogEditText);
        sendButton = view.findViewById(R.id.sendButton);
        cancelButton = view.findViewById(R.id.cancelButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = dialogEditText.getText().toString();
                if (!"".equals(message)) {
                    Intent i = new Intent(activity, DialogShowActivity.class);
                    i.putExtra("message", message);
                    activity.startActivity(i);
                    dialog.dismiss();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}
