package com.icb.icanbuy.ui.pago;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.icb.icanbuy.R;
import com.icb.icanbuy.Registro;

public class PagoFragment extends Fragment {


    public PagoFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pago, container, false);

        Button button_agregar_tarjeta = (Button) view.findViewById(R.id.button_agregar_tarjeta);
        button_agregar_tarjeta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent in = new Intent(getActivity(),TarjetaPago.class);
                in.putExtra("some","some data");
                startActivity(in);
            }
        });

        return view;
    }

}
