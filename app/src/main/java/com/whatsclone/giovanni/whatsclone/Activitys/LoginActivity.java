package com.whatsclone.giovanni.whatsclone.Activitys;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.whatsclone.giovanni.whatsclone.Config.ConfiguracaoFireBase;
import com.whatsclone.giovanni.whatsclone.Model.Usuario;
import com.whatsclone.giovanni.whatsclone.R;
import com.whatsclone.giovanni.whatsclone.helper.Permissao;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtSenha;
    private TextView textCadastreSe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        VerificarSeUsuarioLogado();
        RecuperarViews();
    }

    private void RecuperarViews(){
        edtEmail       = findViewById(R.id.edtEmailId);
        edtSenha       = findViewById(R.id.edtSenhaId);;
        textCadastreSe = findViewById(R.id.textCadastre);
    }


    public void AbrirActivityCadastroUsuario(View view){
        Intent intent = new Intent(LoginActivity.this,CadastroUsuarioActivity.class);
        startActivity(intent);

    }

    public void ValidarLogin(View view) {
        ConfiguracaoFireBase.getFireBaseAuth().signInWithEmailAndPassword(edtEmail.getText().toString(), edtSenha.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            AbrirTelaPrincipal();
                        } else {
                            Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void VerificarSeUsuarioLogado(){
        if (ConfiguracaoFireBase.getFireBaseAuth().getCurrentUser() != null){
            AbrirTelaPrincipal();
        }
    }

    private void AbrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
