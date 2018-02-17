package com.whatsclone.giovanni.whatsclone.Activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.whatsclone.giovanni.whatsclone.Adapter.TabAdapter;
import com.whatsclone.giovanni.whatsclone.Config.ConfiguracaoFireBase;
import com.whatsclone.giovanni.whatsclone.R;
import com.whatsclone.giovanni.whatsclone.helper.Base64Custom;
import com.whatsclone.giovanni.whatsclone.helper.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolBarId);
        toolbar.setTitle("WhatsApp");
        slidingTabLayout = findViewById(R.id.stlTabs);
        viewPager = findViewById(R.id.vpPagina);
        setSupportActionBar(toolbar);

        ///distribui o espaço do menu de abas de acordo com o tamanho da tela.
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this,R.color.colorAccent));


        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);
        slidingTabLayout.setViewPager(viewPager);
    }

    public void FazerLogoffUsuario(){
        ConfiguracaoFireBase.getFireBaseAuth().signOut();
        Intent intent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_sair){
            FazerLogoffUsuario();
            return true;
        }
        else if (item.getItemId() == R.id.action_adicionar) {
            return true;
        }
        else{
            return super.onOptionsItemSelected(item);
        }

    }

    private void AbrirCadastroContato(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Novo Contato");
        alertDialog.setMessage("E-mail do usuário");
        alertDialog.setCancelable(false);
        final EditText email = new EditText(MainActivity.this);
        alertDialog.setView(email);
        alertDialog.setPositiveButton("Cadastrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (email.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Informe o e-mail do usuário.",Toast.LENGTH_SHORT).show();
                }
                else{
                    ConfiguracaoFireBase.getFirabase().child("usuarios").child(Base64Custom.CodificarBase64(email.getText().toString()))
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.getValue() != null){

                                String EmailUsuario = ConfiguracaoFireBase.getFireBaseAuth().getCurrentUser().getEmail();
                                EmailUsuario = Base64Custom.CodificarBase64(EmailUsuario);

                                ConfiguracaoFireBase.getFirabase().child("contatos").child(EmailUsuario)
                                        .child(Base64Custom.CodificarBase64(email.getText().toString()));
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Usuário não possui cadastro.",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
            }

        });

        alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alertDialog.create();
        alertDialog.show();


    }
}
