package com.icb.icanbuy.ui.ScannerQR2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.icb.icanbuy.MenuActivity;
import com.icb.icanbuy.R;
import com.icb.icanbuy.models.ConexionCarrito.ConexionCarrito;
import com.icb.icanbuy.models.ConexionCarrito.ConexionCarritoRecord;
import com.icb.icanbuy.models.ConexionCarrito.ConexionCarritoRecords;
import com.icb.icanbuy.models.Usuario.Usuario;
import com.icb.icanbuy.ui.pago.Constants;
import com.icb.icanbuy.ui.pago.TarjetaPago;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class ScannerQRActivity extends AppCompatActivity implements AsyncTaskCallback{
    private CameraSource cameraSource;
    private SurfaceView cameraView;
    private final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private String token = "";
    private String tokenanterior = "";
    private static FirebaseUser user;
    private static DatabaseReference reference;
    private static String userID;
    private static String nombreString;
    private String idtablet;
    private String totalApagar;
    private int estado;
    private Button btnIdentificarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner_qr);

        btnIdentificarse=(Button)findViewById(R.id.btnIdentificarse);

        btnIdentificarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user= FirebaseAuth.getInstance().getCurrentUser();
                reference= FirebaseDatabase.getInstance().getReference("Users");
                userID=user.getUid();


                reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        try {
                            for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                                Usuario userProfile = snapshot.getValue(Usuario.class);

                                if (userProfile != null) {
                                    nombreString= (userProfile.getNombre()).concat(" ".concat(userProfile.getApellido()));


                                }
                            }
                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                        new PostAPI().execute();
                        Toast.makeText(getApplicationContext(),"Conexión exitosa", Toast.LENGTH_SHORT).show();
                        (new Handler()).postDelayed(this::irMenu, 2000);
                    }//on data change

                    private void irMenu() {
                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });//listener for single value event

            }//onclick

        });//onclick listener


        cameraView = (SurfaceView) findViewById(R.id.camera_view);
        initQR();

    }

    public void initQR() {

        // creo el detector qr
        BarcodeDetector barcodeDetector =
                new BarcodeDetector.Builder(this)
                        .setBarcodeFormats(Barcode.ALL_FORMATS)
                        .build();

        // creo la camara
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1600, 1024)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        // listener de ciclo de vida de la camara
        cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                // verifico si el usuario dio los permisos para la camara
                if (ActivityCompat.checkSelfPermission(ScannerQRActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        // verificamos la version de ANdroid que sea al menos la M para mostrar
                        // el dialog de la solicitud de la camara
                        if (shouldShowRequestPermissionRationale(
                                Manifest.permission.CAMERA)) ;
                        requestPermissions(new String[]{Manifest.permission.CAMERA},
                                MY_PERMISSIONS_REQUEST_CAMERA);
                    }
                    return;
                } else {
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException ie) {
                        Log.e("CAMERA SOURCE", ie.getMessage());
                    }
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        // preparo el detector de QR
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }


            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();

                if (barcodes.size() > 0) {

                    // obtenemos el token
                    token = barcodes.valueAt(0).displayValue.toString();

                    // verificamos que el token anterior no se igual al actual
                    // esto es util para evitar multiples llamadas empleando el mismo token
                    if (!token.equals(tokenanterior)) {

                        // guardamos el ultimo token proceado
                        tokenanterior = token;
                        Log.i("token", token);

                        if (URLUtil.isValidUrl(token)) {
                            // si es una URL valida abre el navegador
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(token));
                            startActivity(browserIntent);
                        } else if(token.equals("ICBTABLET01")){
                            Intent i = new Intent(getApplicationContext(), MenuActivity.class);
                            startActivity(i);
                        }

                        else {
                            // comparte en otras apps
                            Intent shareIntent = new Intent();
                            shareIntent.setAction(Intent.ACTION_SEND);
                            shareIntent.putExtra(Intent.EXTRA_TEXT, token);
                            shareIntent.setType("text/plain");
                            startActivity(shareIntent);
                        }

                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    synchronized (this) {
                                        wait(5000);
                                        // limpiamos el token
                                        tokenanterior = "";
                                    }
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    Log.e("Error", "Waiting didnt work!!");
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }
                }
            }
        });

    }//initQR

    @Override
    public void onPostExecute(Object result) {

    }


    public class PostAPI extends AsyncTask<Void, Void, ConexionCarritoRecord>{

        @Override
        protected ConexionCarritoRecord doInBackground(Void... params) {
            URL url2 = null;
            try{

            url2 = new URL(ConstantsScan.AIRTABLE_BASE_URL+"ConexionCarrito");

            HttpURLConnection http = (HttpURLConnection)url2.openConnection();
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            http.setRequestProperty("Authorization", "Bearer "+ ConstantsScan.AIRTABLE_API_KEY);
            http.setRequestProperty("Content-Type", "application/json");


            JSONObject jsonObjectFields = new JSONObject();
            try{
                jsonObjectFields.put("fields",datosC());
            }catch (Exception exception){
                exception.printStackTrace();
            }

           /* String data = "{\n  \"fields\": {\n   " +
                    " \"IDUsuario\": \"2\",\n    " +
                    "\"NombreUsuario\": \"Juan Perez\",\n    " +
                    "\"Estado\": 0,\n    " +
                    "\"IDTablet\": \"1\",\n    " +
                    "\"totalAPagar\": \"0.00\"\n  }\n}";*/

            byte[] out = jsonObjectFields.toString().getBytes(StandardCharsets.UTF_8);

            OutputStream stream = http.getOutputStream();
            stream.write(out);

            System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
            http.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

            return null;
        }
    }//class


    private JSONObject datosC() throws JSONException {

        JSONObject params = new JSONObject();
        params.put( "IDUsuario", ScannerQRActivity.obtenerID());
        params.put( "NombreUsuario", nombreString);
        params.put( "Estado", 1);
        params.put( "IDTablet", "1");
        params.put( "totalAPagar", "0.00");
        return params;
    }
/*
    public static String obtenerNombre(){
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    for (DataSnapshot childSnapshot : snapshot.getChildren()) {
                        Usuario userProfile = snapshot.getValue(Usuario.class);

                        if (userProfile != null) {
                            String fullname = (userProfile.getNombre()).concat(" ".concat(userProfile.getApellido()));

                            nombreString=(userProfile.getNombre()).concat(" ".concat(userProfile.getApellido()));

                        }
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return nombreString;
    }*/
    public static String obtenerID(){
        user= FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users");
        userID=user.getUid();
        return userID;
    }

}//activity