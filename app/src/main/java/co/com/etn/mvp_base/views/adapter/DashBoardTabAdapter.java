package co.com.etn.mvp_base.views.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.fragments.CustomesFragment;
import co.com.etn.mvp_base.fragments.PerfilFragment;
import co.com.etn.mvp_base.fragments.ProductFragment;
import co.com.etn.mvp_base.helper.Constants;

/**
 * co.com.etn.mvp_base.views.adapter
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.8:57 AM
 */

public class DashBoardTabAdapter extends FragmentStatePagerAdapter {

    private final Context context;

    public DashBoardTabAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProductFragment();
            case 1:
                return new CustomesFragment();
            case 2:
                return new PerfilFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                 return context.getResources().getString(R.string.title_product);

            case 1:
                return context.getResources().getString(R.string.title_contact);
            case 2:
                return context.getResources().getString(R.string.title_perfil);
            default:
                return Constants.EMPTY;
        }
    }


}
