package com.icb.icanbuy.ui.terminos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.icb.icanbuy.R;
import com.icb.icanbuy.Registro;

public class TermActivity extends AppCompatActivity {

    Button bt_aceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term);
        bt_aceptar = findViewById(R.id.bt_accept);

        bt_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Registro.class);
                startActivity(intent);
            }
        });
    }
}