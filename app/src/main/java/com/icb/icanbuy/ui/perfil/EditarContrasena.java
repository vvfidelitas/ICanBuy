package com.icb.icanbuy.ui.perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.icb.icanbuy.ForgotPassword;
import com.icb.icanbuy.MainActivity;
import com.icb.icanbuy.R;

import java.util.regex.Pattern;

import static androidx.camera.core.CameraX.getContext;

public class EditarContrasena extends AppCompatActivity {

    Button editarBoton;
    EditText Correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_contrasena);

        editarBoton = findViewById(R.id.btn_editarContrasena);
        Correo = findViewById(R.id.edt_Correo);
        editarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    public void validate(){
        String email = Correo.getText().toString().trim();

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Correo.setError("Correo invalido");
            return;
        }
        sendEmail(email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(EditarContrasena.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void sendEmail(String email){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String emailAddress = email;

        auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(EditarContrasena.this, "Correo enviado!",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditarContrasena.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(EditarContrasena.this, "Correo invalido",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}


