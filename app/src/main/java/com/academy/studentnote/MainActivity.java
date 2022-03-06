package com.academy.studentnote;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSubject, btnAuthor, btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSubject=findViewById(R.id.buttonSubject);
        btnAuthor=findViewById(R.id.buttonAuthor);
        btnExit=findViewById(R.id.buttonExit);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogExit();
            }
        });

        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActivitySubject.class);
                startActivity(intent);
            }
        });

        btnAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAuthor();
            }
        });
    }

    private void dialogAuthor() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_author);
        dialog.show();
    }

    private void dialogExit() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_exit);
        dialog.setCanceledOnTouchOutside(true);
        Button btnYes=dialog.findViewById(R.id.buttonExitYes);
        Button btnNo=dialog.findViewById(R.id.buttonExitNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Thoát app ra ngoài
                MainActivity.this.finish();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}