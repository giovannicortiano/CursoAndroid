package com.whatsclone.giovanni.whatsclone.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Giovanni on 08/02/2018.
 */

public class Permissao {

    public static boolean ValidarPermissoes(Activity activity, String[]Permissoes){
        if (Build.VERSION.SDK_INT >= 23){
            List<String> listaDeItensNaoPermitidos = new ArrayList<String>();
            for (String permissao : Permissoes){
                if (ContextCompat.checkSelfPermission(activity,permissao) != PackageManager.PERMISSION_GRANTED){
                    listaDeItensNaoPermitidos.add(permissao);
                }
            }

            String [] ArrayDeItensNaoPermitidos = new String[listaDeItensNaoPermitidos.size()];
            listaDeItensNaoPermitidos.toArray(ArrayDeItensNaoPermitidos);
            if (! listaDeItensNaoPermitidos.isEmpty()){
                ActivityCompat.requestPermissions(activity,ArrayDeItensNaoPermitidos,1);
                return true;
            }
        }
        return true;
    }
}
