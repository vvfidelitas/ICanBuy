package com.icb.icanbuy.ui.pago;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.braintreepayments.cardform.view.CardForm;
import com.icb.icanbuy.R;

public class TarjetaPago extends AppCompatActivity {

    CardForm cardForm;
    Button buy;
    AlertDialog.Builder alertBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta_pago);


        Bundle bundle = getIntent().getExtras();
        if (bundle !=null){
            if(bundle.getString("some") != null);
            Toast.makeText(getApplicationContext(),
                    "data:" + bundle.getString("some"),
                    Toast.LENGTH_SHORT).show();

        }


        cardForm = findViewById(R.id.card_form);
        buy = findViewById(R.id.btnBuy);
        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .postalCodeRequired(true)
                .mobileNumberRequired(true)
                .mobileNumberExplanation("SMS es requerido")
                .setup(TarjetaPago.this);
        cardForm.getCvvEditText().setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cardForm.isValid()) {
                    alertBuilder = new AlertDialog.Builder(TarjetaPago.this);
                    alertBuilder.setTitle("Verifique sus datos");
                    alertBuilder.setMessage("Número: " + cardForm.getCardNumber() + "\n" +
                            "Fecha de vencimiento: " + cardForm.getExpirationDateEditText().getText().toString() + "\n" +
                            "CVV: " + cardForm.getCvv() + "\n" +
                            "Código postal: " + cardForm.getPostalCode() + "\n" +
                            "Número: " + cardForm.getMobileNumber());
                    alertBuilder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            Toast.makeText(TarjetaPago.this, "Su tarjeta ha sido agregada", Toast.LENGTH_LONG).show();
                        }
                    });
                    alertBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    AlertDialog alertDialog = alertBuilder.create();
                    alertDialog.show();

                } else {
                    Toast.makeText(TarjetaPago.this, "Por favor complete el formulario", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}