package com.whatsclone.giovanni.whatsclone.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.whatsclone.giovanni.whatsclone.Fragment.ContatosFragment;
import com.whatsclone.giovanni.whatsclone.Fragment.ConversasFragment;

/**
 * Created by Giovanni on 11/02/2018.
 */

public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] TituloAbas = {"CONVERSAS","CONTATOS"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragmento = null;
        if (position == 0){
            fragmento = new ConversasFragment();
        }
        else{
            fragmento = new ContatosFragment();
        }
        return fragmento;
    }

    @Override
    public int getCount() {
        return TituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TituloAbas[position];
    }
}
