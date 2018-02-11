package com.whatsclone.giovanni.whatsclone.helper;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;

/**
 * Created by Giovanni on 07/02/2018.
 */

public class Preferencias {
    private Context contexto;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "WhatsClonePreferences";
    private SharedPreferences.Editor editor;

    public Preferencias(Context contextoParamaetro){
        contexto = contextoParamaetro;
        preferences = contexto.getSharedPreferences(NOME_ARQUIVO,Context.MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void SalvarUsuario(String nome, String telefone,String token){
        editor.putString("Nome",nome);
        editor.putString("Telefone",telefone);
        editor.putString("Token",token);
        editor.commit();
    }

    public HashMap<String,String> RecuperarDadosUsuario(){
        HashMap<String,String> dadosUsuario = new HashMap<>();
        dadosUsuario.put("Nome",preferences.getString("Nome",""));
        dadosUsuario.put("Telefone",preferences.getString("Telefone",""));
        dadosUsuario.put("Token",preferences.getString("Token",""));
        return dadosUsuario;
    }
}
