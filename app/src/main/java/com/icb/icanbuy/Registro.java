package com.icb.icanbuy;

<<<<<<< HEAD
=======
import androidx.appcompat.app.AppCompatActivity;

>>>>>>> a7640fcbb7a06ec37802dcbee1cda216f9ea2582
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class Registro extends AppCompatActivity {
    private EditText txtCorreo;
    private EditText edtNombre;
    private EditText edtApellido;
    private EditText edtFechaNac;
    private Button btnRegistro;

=======
import android.net.Uri;

public class Registro extends AppCompatActivity {
    private EditText txtCorreo;
>>>>>>> a7640fcbb7a06ec37802dcbee1cda216f9ea2582
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
<<<<<<< HEAD
=======


>>>>>>> a7640fcbb7a06ec37802dcbee1cda216f9ea2582
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
<<<<<<< HEAD
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
=======
>>>>>>> a7640fcbb7a06ec37802dcbee1cda216f9ea2582
}