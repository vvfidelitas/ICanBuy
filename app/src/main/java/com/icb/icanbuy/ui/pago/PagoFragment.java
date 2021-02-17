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


<<<<<<< HEAD
=======
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

>>>>>>> a7640fcbb7a06ec37802dcbee1cda216f9ea2582
    public PagoFragment() {

    }

<<<<<<< HEAD
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
=======


    // TODO: Rename and change types and number of parameters
    public static PagoFragment newInstance(String param1, String param2) {
        PagoFragment fragment = new PagoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pago, container, false);
>>>>>>> a7640fcbb7a06ec37802dcbee1cda216f9ea2582
    }

}
