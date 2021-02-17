package com.icb.icanbuy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class Registro extends AppCompatActivity {
    private EditText txtCorreo;
    private EditText edtNombre;
    private EditText edtApellido;
    private EditText edtFechaNac;
    private Button btnRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtCorreo = findViewById(R.id.editTextTextEmailAddress);
        String edttxt = txtCorreo.getText().toString();

        final Button send = (Button) this.findViewById(R.id.button3);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                try {
                    GMailSender sender = new GMailSender(edttxt, "password");
                    sender.sendMail("This is Subject",
                            "This is Body",
                            "user@gmail.com",
                            edttxt);
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

            }
        });
    }
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}