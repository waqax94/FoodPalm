package com.foodpalm.foodpalm.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.User;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class LoginRegisterActivity extends AppCompatActivity {

    String usrEmail;
    String usrPsw;

    EditText euEmail;
    EditText eusrPsw;
    TextView errorMsg;
    Drawable rightDr;
    Drawable erDrawable;
    ProgressDialog pDialog;


    IpClass ipClass = new IpClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        euEmail = (EditText)findViewById(R.id.u_email);
        eusrPsw = (EditText)findViewById(R.id.psw);
        errorMsg = (TextView)findViewById(R.id.error_msg);
        erDrawable = getResources().getDrawable(R.drawable.false_fields);
        rightDr = getResources().getDrawable(R.drawable.fields);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);
    }

    public void openHome(View v){

        showpDialog();

        usrEmail =  euEmail.getText().toString();
        usrPsw = eusrPsw.getText().toString();
        euEmail.setBackground(rightDr);
        eusrPsw.setBackground(rightDr);
        errorMsg.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress)
                .addConverterFactory(GsonConverterFactory.create()).build();

        APIService service = retrofit.create(APIService.class);

        if(!usrEmail.equals("") && !usrPsw.equals("")){
            Call<User> userData = service.ProcessLogin(usrEmail,usrPsw);

            userData.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Response<User> response, Retrofit retrofit) {
                    hidepDialog();
                    User user = response.body();
                    if(response.body() != null){
                        Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_SHORT).show();
                        SharedPreferences loginData = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = loginData.edit();
                        editor.putString("userEmail", usrEmail);
                        editor.putString("userPassword", usrPsw);
                        editor.putString("userName",user.getCname());
                        editor.putString("userCity",user.getCity());
                        editor.apply();


                        Intent homePage = new Intent(getApplicationContext(),RestuarantActivity.class);
                        startActivity(homePage);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Throwable t) {
                    hidepDialog();
                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            hidepDialog();
            errorMsg.setVisibility(View.VISIBLE);
            euEmail.setBackground(erDrawable);
            eusrPsw.setBackground(erDrawable);
        }

    }

    public void openSignUpActivity(View v){

        Intent signupPage = new Intent(this,SignUpActivity.class);
        startActivity(signupPage);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}

/**/


/*hidepDialog();
                    Toast.makeText(getApplicationContext(),"Connection Error",Toast.LENGTH_SHORT).show();
                    */
