package com.example.bdexternas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentMensajes extends Fragment {

    ProveedorServicios proveedorServicios;
    private static final String url = "http://xusa.iesdoctorbalmis.info/usuarios/";
    private Spinner spinner;
    private List<Usuarios> lista;
    private Mensajes mensajes;
    private EditText descripcion;
    private FloatingActionButton fab;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mensajes, container, false);
        lista = new ArrayList<>();
        spinner = v.findViewById(R.id.Spinner);
        descripcion = v.findViewById(R.id.et_Descripcion);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        proveedorServicios = retrofit.create(ProveedorServicios.class);

        fab = v.findViewById(R.id.fab_mensajes);

        addUsers();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertMessage();
                descripcion.setText("");
            }
        });


        return v;
    }

    private void addUsers()
    {
        proveedorServicios.getUsuarios().enqueue(new Callback<List<Usuarios>>() {
            @Override
            public void onResponse(Call<List<Usuarios>> call, Response<List<Usuarios>> response) {
                lista = response.body();
                List<String> nicks = new ArrayList<>();
                for (Usuarios us:lista) {
                    nicks.add(us.getNick());
                }
                spinner.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1,nicks));
            }

            @Override
            public void onFailure(Call<List<Usuarios>> call, Throwable t) {
                Toast.makeText(getContext(), "No se han insertado en el array", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void insertMessage()
    {
        mensajes = new Mensajes();
        mensajes.setNick((String)spinner.getSelectedItem());
        mensajes.setMensaje(descripcion.getText().toString());

        proveedorServicios.insertarMensaje(mensajes).enqueue(new Callback<RespuestaJson>() {
            @Override
            public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                Toast.makeText(getContext(), "Mensaje Insertado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RespuestaJson> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
