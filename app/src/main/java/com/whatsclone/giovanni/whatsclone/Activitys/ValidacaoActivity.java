package com.whatsclone.giovanni.whatsclone.Activitys;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;
import com.whatsclone.giovanni.whatsclone.R;
import com.whatsclone.giovanni.whatsclone.helper.Preferencias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class ValidacaoActivity extends AppCompatActivity {

    private EditText edtCodigoDigitado;
    private Button btnValidar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validacao);
        edtCodigoDigitado = findViewById(R.id.edtCodigoDigitadoId);
        btnValidar = findViewById(R.id.btnValidarId);

        SimpleMaskFormatter mascaraCodigoDigitado = new SimpleMaskFormatter("NNNN");
        MaskTextWatcher maskWatcherCodigoDigitado = new MaskTextWatcher(edtCodigoDigitado,mascaraCodigoDigitado);
        edtCodigoDigitado.addTextChangedListener(maskWatcherCodigoDigitado);

        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferencias preferencias = new Preferencias(ValidacaoActivity.this);
                HashMap<String,String> usuario = preferencias.RecuperarDadosUsuario();
                String TokenGerado = usuario.get("Token");
                String TokenDigitado = edtCodigoDigitado.getText().toString();

                if (TokenDigitado.equals(TokenGerado)){
                    Toast.makeText(ValidacaoActivity.this,"Validado",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(ValidacaoActivity.this,"Não Validado",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
