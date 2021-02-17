package com.icb.icanbuy.services;

import android.content.Context;
import android.os.AsyncTask;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icb.icanbuy.models.Usuario.Usuario;
import com.icb.icanbuy.models.Usuario.UsuarioList;
<<<<<<< HEAD
import com.icb.icanbuy.models.Usuario.UsuarioRecords;
import com.icb.icanbuy.models.Usuario.UsuarioRecord;
=======
import com.icb.icanbuy.models.Usuario.UsuarioRecord;
import com.icb.icanbuy.models.Usuario.UsuarioRecords;
>>>>>>> a7640fcbb7a06ec37802dcbee1cda216f9ea2582
import com.icb.icanbuy.utils.PropertiesConfig;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class DALService {
    private PropertiesConfig propertiesConfig;
    private Context context;
<<<<<<< HEAD
    private final static String PRODUCT_ERROR_MESSAGE= "No se pudieron cargar los productos";

    public DALService(Context context) {
        this.context= context;
        propertiesConfig = new PropertiesConfig(context);

        usuarioList= new UsuarioList();
        new HttpLoadUsuarios().execute();
    }


=======
    public DALService(Context context) {
        this.context= context;
        propertiesConfig = new PropertiesConfig(context);
        usuarioList= new UsuarioList();
        new HttpLoadUsuarios().execute();
    }
>>>>>>> a7640fcbb7a06ec37802dcbee1cda216f9ea2582
    private UsuarioList usuarioList;
    private final static String USUARIO_ERROR_MESSAGE= "No se pudieron cargar los usuarios";


    public Usuario getUsuarioByCode(String code){

        return usuarioList.getUsuario(code);
    }

<<<<<<< HEAD
    private class HttpLoadUsuarios extends AsyncTask<Void, Void, Integer>{
=======
    private class HttpLoadUsuarios extends AsyncTask<Void, Void, Integer> {
>>>>>>> a7640fcbb7a06ec37802dcbee1cda216f9ea2582
        @Override
        protected Integer doInBackground(Void... params) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
            mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
            MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
            messageConverter.setObjectMapper(mapper);
            List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
            converters.add(messageConverter);
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set("Authorization", "Bearer " + propertiesConfig.getAirtableApiKey());
            RestTemplate restTemplate = new RestTemplate(true);
            for (int i = 0; i < restTemplate.getMessageConverters().size(); i++) {
                if (!restTemplate.getMessageConverters().get(i).getClass().getName().equals(MappingJackson2HttpMessageConverter.class.getName())) {
                    converters.add(restTemplate.getMessageConverters().get(i));
                }
            }
            restTemplate.setMessageConverters(converters);
            restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
            HttpEntity<?> httpEntity = new HttpEntity<Object>("", requestHeaders);


            ResponseEntity<UsuarioRecords> result_usuario = restTemplate.exchange(propertiesConfig.getAirtableBaseUrl() +
                    "/Usuario", HttpMethod.GET, httpEntity, UsuarioRecords.class);
            for (UsuarioRecord r : result_usuario.getBody().getRecords()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(r.getFields().getIdUsuario());
                usuario.setNombre(r.getFields().getNombre());
                usuario.setApellido(r.getFields().getApellido());
                usuario.setFechaNac(r.getFields().getFechaNac());
                usuario.setCorreo((r.getFields().getCorreo()));
                usuario.setContrasena((r.getFields().getContrasena()));
                usuarioList.addUsuario(usuario);
            }
            return usuarioList.getUsuarioList().size();
        }
    }

}
