package co.com.etn.mvp_base.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import co.com.etn.mvp_base.R;
import co.com.etn.mvp_base.helper.CustomSharePreference;
import co.com.etn.mvp_base.models.Customers;
import co.com.etn.mvp_base.models.User;
import co.com.etn.mvp_base.presenter.GetCustomerPresenter;
import co.com.etn.mvp_base.presenter.UserPresenter;
import co.com.etn.mvp_base.views.activities.CreateCustomerActivity;
import co.com.etn.mvp_base.views.activities.IGetCustomerView;
import co.com.etn.mvp_base.views.activities.IUserView;
import co.com.etn.mvp_base.views.adapter.CustomerAdapter;

/**
 * co.com.etn.mvp_base.fragments
 * MVP_Base
 * Created by alexander.vasquez on 14/10/2017.10:01 AM
 */

public class PerfilFragment extends BaseFragments<UserPresenter> implements IUserView {

   // private FloatingActionButton activity_customers_fab;
    Spinner main_spinner_sync;
    CheckBox main_checkbox_notification;
    Button main_button_cerrar;
    private ImageView main_foto;
    private TextView main_user;
    private TextView main_likes;
    private TextView main_following;
    private TextView main_followers;
    private CustomSharePreference customSharePreference;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        View view  = inflater.inflate(R.layout.activity_perfil, container, false);
        setPresenter(new UserPresenter());
        getBaseActivity().createProgress();
        getActivity();
        getPresenter().inject(this, getValidateItnernet());
        customSharePreference = new CustomSharePreference(getActivity().getApplicationContext());
        //activity_customers_fab = (FloatingActionButton)view.findViewById(R.id.activity_customers_fab);
        loadView(view);

        return view;
    }


    private void loadView(View view) {
        main_foto = (ImageView)view.findViewById(R.id.main_foto);
        main_user = (TextView)view.findViewById(R.id.main_user);
        main_likes =  (TextView)view.findViewById(R.id.main_like);
        main_following =  (TextView)view.findViewById(R.id.main_following);
        main_followers =  (TextView)view.findViewById(R.id.main_followers);
    }

   /* private void loadEvents(View view) {
        activity_customers_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CreateCustomerActivity.class);
                startActivity(intent);
            }

        });
    }*/

    protected void onRestart() {
        getPresenter().getUser();

    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().getUser();
    }

    @Override
    public void showUserInfo() {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Transformation transformation = new RoundedTransformationBuilder()
                        .borderColor(Color.BLACK)
                        .borderWidthDp(3)
                        .cornerRadiusDp(30)
                        .oval(false)
                        .build();

                User user = customSharePreference.getObjectUser("user");
                //Picasso.with(getActivity()).load(user.getPhoto()).error(R.drawable.ic_close_24dp).fit().into(main_foto);
                //Picasso.with(getActivity()).load(user.getPhoto()).fit().transform(transformation).into(main_foto);
                Picasso.with(getActivity()).load(user.getPhoto()).into(main_foto);
                Picasso.with(getActivity()).setIndicatorsEnabled(true);

                main_user.setText(user.getName());
                main_likes.setText(String.valueOf(user.getLikes())+"-"+getResources().getString(R.string.main_textview_like));
                main_following.setText(String.valueOf(user.getFollowings())+"-"+getResources().getString(R.string.main_textview_follow));
                main_followers.setText(String.valueOf(user.getFollowers())+"-"+getResources().getString(R.string.main_textview_follower));
            }
        });
    }

    @Override
    public void showAlertDialog(final int id) {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                DialogInterface.OnClickListener diocl = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };
                getAlertDialog().showAlertDialog(R.string.dialog_error_title, id, false,R.string.positive_button, diocl );
            }
        });

    }



    @Override
    public void showAlertDialog(final String message) {
        getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogInterface.OnClickListener diocl = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                };

                getAlertDialog().showAlertDialog(R.string.dialog_error_title, message, false,R.string.positive_button, diocl );
            }
        });
    }

}
