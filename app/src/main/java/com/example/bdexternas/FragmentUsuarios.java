package com.example.bdexternas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentUsuarios extends Fragment {

    FloatingActionButton fab;
    EditText nombre, nick;
    Usuarios usuarios;
    ProveedorServicios proveedorServicios;
    private static final String url = "http://xusa.iesdoctorbalmis.info/usuarios/";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usuarios, container, false);

        fab = v.findViewById(R.id.fab);
        nombre = v.findViewById(R.id.et_Nombre);
        nick = v.findViewById(R.id.et_Nick);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        proveedorServicios = retrofit.create(ProveedorServicios.class);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                añadirUsuario();
                nick.setText("");
                nombre.setText("");
            }
        });
        return v;

    }

    private void añadirUsuario()
    {
        usuarios = new Usuarios();
        usuarios.setNick(nick.getText().toString());
        usuarios.setNombre(nombre.getText().toString());

        proveedorServicios.insertarUsuario(usuarios).enqueue(new Callback<RespuestaJson>() {
            @Override
            public void onResponse(Call<RespuestaJson> call, Response<RespuestaJson> response) {
                Toast.makeText(getContext(), "Usuario Insertado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RespuestaJson> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
