package com.example.tp1login.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1login.model.Usuario;
import com.example.tp1login.request.ApiClient;

public class RegistroActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Usuario> user;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getUser() {
        if(user == null){
            user= new MutableLiveData<>();
        }
        return user;
    }

    public void registrar(Long dni, String mail, String apellido, String nombre, String password){

        Usuario usuario = new Usuario(dni,mail,apellido,nombre,password);

        ApiClient api= new ApiClient();

        api.guardar(context,usuario);

    }

    public  void logeado(Boolean bool){
        //consultar al usuario logeado

        if(bool == true){
            ApiClient api= new ApiClient();
            Usuario usuario = new Usuario();
            usuario = api.leer(context);

            user.setValue(usuario);
        }

    }

}
