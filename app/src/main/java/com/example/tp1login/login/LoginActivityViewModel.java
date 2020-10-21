package com.example.tp1login.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp1login.MainActivity;
import com.example.tp1login.model.Usuario;
import com.example.tp1login.registro.RegistroActivity;
import com.example.tp1login.request.ApiClient;

public class LoginActivityViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<String> error;

    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

    public LiveData<String> getError() {
        if(error == null){
            error = new MutableLiveData<>();
        }
        return error;
    }

    public void autenticacion(String u, String c){



        ApiClient api= new ApiClient();

        Usuario usuario = null;
        usuario = api.login(context,u,c);


        if (usuario !=null) {

            Intent intent = new Intent(context, RegistroActivity.class);
            intent.putExtra("logueado",true);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            error.setValue("Usuario y/o o contraseña incorrectos");
        }
    }

}
