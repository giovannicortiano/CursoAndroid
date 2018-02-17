package com.whatsclone.giovanni.whatsclone.helper;

import android.util.Base64;

/**
 * Created by Giovanni on 11/02/2018.
 */

public class Base64Custom {

    public static String CodificarBase64(String valor){
        return Base64.encodeToString(valor.getBytes(),Base64.DEFAULT).replaceAll("(\\n|\\r)","");

    }

    public static  String DecodificarBase64(String valor){
       return new String( Base64.decode(valor,Base64.DEFAULT));
    }


}
