package com.whatsclone.giovanni.whatsclone.Activitys;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.whatsclone.giovanni.whatsclone.Config.ConfiguracaoFireBase;
import com.whatsclone.giovanni.whatsclone.Model.Usuario;
import com.whatsclone.giovanni.whatsclone.R;
import com.whatsclone.giovanni.whatsclone.helper.Base64Custom;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private EditText edtNomeNovo;
    private EditText edtEmailNovo;
    private EditText edtSenhaNovo;
    private Button btnCadastrar;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        RecuperarViews();
    }

    private void RecuperarViews(){
        edtNomeNovo  = findViewById(R.id.edtNomeNovoId);
        edtEmailNovo = findViewById(R.id.edtEmailNovoId);
        edtSenhaNovo = findViewById(R.id.edtSenhaNovoId);
        btnCadastrar = findViewById(R.id.btnCadastrarNovoId);
    }

    public void BtnCadastrarNovoOnClick(View view){
        usuario = new Usuario();

        usuario.setNome(edtNomeNovo.getText().toString());
        usuario.setEmail(edtEmailNovo.getText().toString());
        usuario.setSenha(edtSenhaNovo.getText().toString());
        CadastrarUsuario();

    }

    public void CadastrarUsuario(){
        ConfiguracaoFireBase.getFireBaseAuth().createUserWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           Toast.makeText(CadastroUsuarioActivity.this,"Sucesso ao cadastrar usuário.",Toast.LENGTH_LONG).show();
                           usuario.setId(Base64Custom.CodificarBase64(usuario.getEmail()));
                           usuario.Salvar();
                           finish();
                       }
                       else{
                           String excecao;
                           try{
                               throw task.getException();
                           }
                           catch (FirebaseAuthWeakPasswordException e){
                               excecao = "Erro. Digite uma senha mais forte, contendo caracteres numéricos e letras.";

                           }
                           catch (FirebaseAuthInvalidCredentialsException e){
                               excecao = "Erro. O e-mail digitado é inválido.";

                           }
                           catch (FirebaseAuthUserCollisionException e){
                               excecao = "Erro. Já existe um usuário cadastrado com este endereço de e-mail..";

                           }
                           catch (Exception e){
                               excecao = "Falha ao cadastrar usuário, tente novamente mais tarde..";
                               e.printStackTrace();

                           }
                           Toast.makeText(CadastroUsuarioActivity.this,excecao,Toast.LENGTH_LONG).show();
                       }
                    }
                });
    }
}
