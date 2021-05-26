package com.icb.icanbuy.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.icb.icanbuy.Cupones_automercado;
import com.icb.icanbuy.Cupones_maxipali;
import com.icb.icanbuy.ui.cupones.Cupones_masxmenos;
import com.icb.icanbuy.ui.cupones.Cupones_walmart;
import com.icb.icanbuy.R;
import com.icb.icanbuy.ui.ScannerQR2.ScannerQRActivity;

public class HomeFragment extends Fragment {
    private Button btn_Escanear;
    private ImageButton btn_cuponWalmart;
    private ImageButton btn_cuponAutomercado;
    private ImageButton btn_cuponMasxmenos;
    private ImageButton btn_cuponMaxipali;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        btn_Escanear=root.findViewById(R.id.btn_Escanear);
        btn_cuponWalmart=root.findViewById(R.id.btnwalmart);
        btn_cuponAutomercado=root.findViewById(R.id.automercado);
        btn_cuponMasxmenos=root.findViewById(R.id.masxmenos);
        btn_cuponMaxipali=root.findViewById(R.id.maxipali);

        btn_Escanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), ScannerQRActivity.class);
                startActivity(i);
            }
        });

        btn_cuponWalmart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Cupones_walmart.class);
                startActivity(i);
            }
        });

        btn_cuponAutomercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Cupones_automercado.class);
                startActivity(i);
            }
        });

        btn_cuponMasxmenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Cupones_masxmenos.class);
                startActivity(i);
            }
        });

        btn_cuponMaxipali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), Cupones_maxipali.class);
                startActivity(i);
            }
        });

        return root;
    }
}