package com.livingfiction.gottagetdownonfriday01;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class MainActivity extends AppCompatActivity {

    private TextView info;
    private TextView result;
    private LoginButton loginButton;
    private Button doButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_main);
        info = (TextView)findViewById(R.id.info);
        result = (TextView)findViewById(R.id.result);
        doButton = (Button) findViewById(R.id.doSomething);
        loginButton = (LoginButton)findViewById(R.id.login_button);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>()
        {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText(R.string.login_success);
            }

            @Override
            public void onCancel() {
                info.setText(R.string.login_cancel);
            }

            @Override
            public void onError(FacebookException e) {
                info.setText(R.string.login_fail);
            }
        });

        doButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                result.setText("I don't know, what do YOU want to do tonight?");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
