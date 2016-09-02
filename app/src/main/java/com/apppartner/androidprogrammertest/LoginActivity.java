package com.apppartner.androidprogrammertest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.apppartner.androidprogrammertest.controller.RetrofitManager;
import com.apppartner.androidprogrammertest.models.LoginResponse;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// 08/31 added Retrofit library to make network connection
// 08/31 AlertDialog to display login response
// 09/01 added error handling to login
// 09/01 added butterknife injection

public class LoginActivity extends AppCompatActivity
{
    private static final String TAG = "Dialogbox" ;

    @Bind(R.id.userName)
    EditText mUserName;

    @Bind(R.id.password)
    EditText mPassword;

    @Bind(R.id.toolbar)
    Toolbar mtoolbar;

    @Bind(R.id.loginImageButton)
    ImageButton msubmitButton;

    @Bind(R.id.toolBarText)
    TextView mToolBarText;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mToolBarText.setText("Login");
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/Jelloween - Machinato ExtraLight.ttf");
        mToolBarText.setTypeface(myTypeface);
        mtoolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_arrow_back_white_24dp));
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
                //What to do on back clicked
            }
        });

        Typeface LoginTypeface = Typeface.createFromAsset(getAssets(), "fonts/Jelloween - Machinato.ttf");
        mUserName.setTypeface(LoginTypeface);
        mPassword.setTypeface(LoginTypeface);

        msubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login(view.getContext());
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void Login(final Context context) {

        String userName = mUserName.getText().toString();
        String password = mPassword.getText().toString();

        RetrofitManager retrofitManager = new RetrofitManager();
        Call<LoginResponse> call = retrofitManager.getLoginService().getLoginData(userName, password);

        call.enqueue(new Callback<LoginResponse>() {
            long startTime = System.currentTimeMillis();

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response.isSuccessful()) {

                    float t=  response.body().getResponseTime();
                    //retrofit response not returning elapsed time in response-body
                    Log.d("Elapsed time: ",""+t);

                    long elapsedTime = System.currentTimeMillis() - startTime;
                    //Getting response time manually by using current system time.
                    CreateDialog(context, response,elapsedTime);
                }
                else{
                    Toast.makeText(LoginActivity.this, response.message() +"\nPlease enter valid credentials", Toast.LENGTH_SHORT).show();
                    //For getting error message
                    Log.d("Error message",response.message());
                    //For getting error code.
                    Log.d("Error code", String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Login failed.  Please check your internet connection.", Toast.LENGTH_SHORT).show();
            }


        });
    }


    public void CreateDialog(Context context, final Response<LoginResponse> response, long time) {
        Log.i(TAG, "RESPONSE_CODE: "+response.body().getCode());
        Log.i(TAG, "RESPONSE_MESSAGE "+response.body().getMessage());
        new AlertDialog.Builder(context)
                .setTitle("LOGIN RESPONSE")
                .setMessage("Code: " + response.body().getCode() + "\nMessage: " + response.body().getMessage()+ "\nElapsed time: "+time+ " milliseconds" )
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (response.body().getCode().equals("Success")) {
                            Intent intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            dialog.dismiss();
                        }
                    }
                }).show();

    }
}
