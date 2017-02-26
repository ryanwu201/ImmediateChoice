package com.qzct.immediatechoice;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.itheima.immediatechoice.R;
import com.qzct.immediatechoice.domain.question;
import com.qzct.immediatechoice.fragment.RegisterFinallyFragment;
import com.qzct.immediatechoice.fragment.RegisterFirstFragment;
import com.qzct.immediatechoice.fragment.RegisterSecondFragment;
import com.qzct.immediatechoice.fragment.baseFragment;
import com.qzct.immediatechoice.util.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends FragmentActivity {

        FrameLayout fl_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fl_register = (FrameLayout) findViewById(R.id.fl_register);
        ImageView iv_back = (ImageView) findViewById(R.id.iv_register_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        Fragment fragment = (Fragment) Adapter.instantiateItem(fl_register, 0);
        Adapter.setPrimaryItem(fl_register, 0, fragment);
        Adapter.finishUpdate(fl_register);
    }

    FragmentStatePagerAdapter Adapter = new FragmentStatePagerAdapter(getSupportFragmentManager()) {

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int arg0) {
            baseFragment baseFragment = null;
            switch (arg0) {
                case 0:
                    baseFragment = new RegisterFirstFragment();
                    break;

                default:
                    break;
            }
            return baseFragment;
        }
    };

       public void setTitleColor(int code){
           TextView tv_fill_verification_code;
           switch (code){
                case 0:
                    TextView tv_fill_tel = (TextView)findViewById(R.id.tv_fill_tel);
                     tv_fill_verification_code = (TextView)findViewById(R.id.tv_fill_verification_code);
                    tv_fill_tel.setTextColor(getResources().getColor(R.color.gray));
                    tv_fill_verification_code.setTextColor(getResources().getColor(R.color.apporange));
                    break;
                case 1:
                     tv_fill_verification_code = (TextView)findViewById(R.id.tv_fill_verification_code);
                    tv_fill_verification_code.setTextColor(getResources().getColor(R.color.gray));
                    TextView tv_set_password = (TextView)findViewById(R.id.tv_set_password);
                    tv_set_password.setTextColor(getResources().getColor(R.color.apporange));
                    break;
                case 2:

                    break;

                default:

                    break;
            }
        }


}