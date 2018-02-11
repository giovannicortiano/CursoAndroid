package com.whatsclone.giovanni.whatsclone.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.whatsclone.giovanni.whatsclone.R;
import com.whatsclone.giovanni.whatsclone.helper.Permissao;
import com.whatsclone.giovanni.whatsclone.helper.Preferencias;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private EditText edtNome;
    private EditText edtCodigoPais;
    private EditText edtCodigoArea;
    private EditText edtTelefone;
    private Button   btnCadastrar;
    private String[] PermissoesNecessarias = new String[]{android.Manifest.permission.SEND_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Permissao.ValidarPermissoes(this,PermissoesNecessarias);
        RecuperarViews();
        DefinirMascaras();
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TelefoneCompleto = edtCodigoPais.getText().toString() +
                                          edtCodigoArea.getText().toString() +
                                          edtTelefone.getText().toString();

                TelefoneCompleto = TelefoneCompleto.replace("-","");

                int iToken = new Random().nextInt(9999-1000)+1000;
                String sToken = String.valueOf(iToken);
                Preferencias preferencias = new Preferencias(getApplicationContext());
                preferencias.SalvarUsuario(edtNome.getText().toString(),TelefoneCompleto,sToken);

                if (EnviarSms(TelefoneCompleto,"Código de validação WhatsClone: " + sToken)){
                    Intent intent = new Intent(LoginActivity.this,ValidacaoActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Problema ao enviar SMS. Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void RecuperarViews(){
        edtNome       = findViewById(R.id.edtNomeId);
        edtCodigoPais = findViewById(R.id.edtCodigoPaisId);
        edtCodigoArea = findViewById(R.id.edtCodigoAreaId);
        edtTelefone   = findViewById(R.id.edtTelefoneId);
        btnCadastrar  = findViewById(R.id.btnCadastrarId);
    }

    private void DefinirMascaras(){
        ///Mascaras
        SimpleMaskFormatter mascaraTelefone = new SimpleMaskFormatter("NNNNN-NNNN");
        MaskTextWatcher maskWatcherTelefone = new MaskTextWatcher(edtTelefone,mascaraTelefone);

        SimpleMaskFormatter mascaraCodigoPais = new SimpleMaskFormatter("+NN");
        MaskTextWatcher maskWatcherCodigoPais = new MaskTextWatcher(edtCodigoPais,mascaraCodigoPais);

        SimpleMaskFormatter mascaraCodigoArea = new SimpleMaskFormatter("NN");
        MaskTextWatcher maskWatcherCodigoArea = new MaskTextWatcher(edtCodigoArea,mascaraCodigoArea);

        edtTelefone.addTextChangedListener(maskWatcherTelefone);
        edtCodigoPais.addTextChangedListener(maskWatcherCodigoPais);
        edtCodigoArea.addTextChangedListener(maskWatcherCodigoArea);
    }

    private boolean EnviarSms(String sTelefone, String sMensagem){
        SmsManager Sms = SmsManager.getDefault();
        try{
            Sms.sendTextMessage(sTelefone,null,sMensagem,null,null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private  void AlertarValidacaoPermissao(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas!");
        builder.setMessage("Para usar o App é necesário aceitar as permissões.");
        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }


    public void onRequestPermissionsResult(int requestCode,String[]Permissions, int[]GrantResults){
        super.onRequestPermissionsResult(requestCode,Permissions,GrantResults );
        for (int resultado : GrantResults){
            if (resultado == PackageManager.PERMISSION_DENIED){
                AlertarValidacaoPermissao();
            }
        }
    }
}
