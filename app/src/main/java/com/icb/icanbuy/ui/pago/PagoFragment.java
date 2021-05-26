package com.icb.icanbuy.ui.pago;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.icb.icanbuy.R;

public class PagoFragment extends Fragment {


    public PagoFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pago, container, false);

        Button button_agregar_tarjeta = (Button) view.findViewById(R.id.button_pagar_orden);
        button_agregar_tarjeta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), PayPalActivity.class);
                startActivity(in);
            }
        });

        return view;
    }
}
