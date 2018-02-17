package com.whatsclone.giovanni.whatsclone.Config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Giovanni on 11/02/2018.
 */

///Final class não pode ser utilizada ser a super classe de outra, ninguém pode herdar dela
public final class ConfiguracaoFireBase {
    ///Satic faz com que a instancia do objeto seja sempre o mesmo quando for chamado, mesmo que em outras instancias dessa class
    private static DatabaseReference referenciaFireBase;
    private static FirebaseAuth autenticacao;

    public static  DatabaseReference getFirabase(){
        if (referenciaFireBase == null) {
            referenciaFireBase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFireBase;
    }

    public static FirebaseAuth getFireBaseAuth(){
        if (autenticacao == null) {
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }

}
