package com.whatsclone.giovanni.whatsclone.Model;

import com.google.firebase.database.Exclude;
import com.whatsclone.giovanni.whatsclone.Config.ConfiguracaoFireBase;

/**
 * Created by Giovanni on 11/02/2018.
 */

public class Usuario {
    private String Id;
    private String Email;
    private String Senha;
    private String Nome;

    public Usuario(){

    }

    public void Salvar() {
        if (getId() != "") {
            ConfiguracaoFireBase.getFirabase().child("Usuarios").child(getId()).setValue(this);
        }
    }

    @Exclude
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Exclude
    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }
}
