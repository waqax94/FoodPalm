package com.foodpalm.foodpalm.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.foodpalm.foodpalm.Adapters.CityAdapter;
import com.foodpalm.foodpalm.R;
import com.foodpalm.foodpalm.Service.APIService;
import com.foodpalm.foodpalm.models.IpClass;
import com.foodpalm.foodpalm.models.User;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class SignUpActivity extends AppCompatActivity {


    EditText ef_name;
    EditText el_name;
    EditText e_email;
    EditText e_psw;
    EditText ec_psw;
    Spinner city;

    Drawable rightDr;
    Drawable erDrawable;

    ProgressDialog pDialog;

    ArrayList<String> cityValues = new ArrayList<String>();

    IpClass ipClass = new IpClass();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cityValues.add("Lahore");
        cityValues.add("Karachi");
        cityValues.add("Islamabad");
        cityValues.add("Multan");
        cityValues.add("Sahiwal");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        erDrawable = getResources().getDrawable(R.drawable.false_fields);
        rightDr = getResources().getDrawable(R.drawable.fields);

        ef_name = (EditText) findViewById(R.id.f_name);
        el_name = (EditText) findViewById(R.id.l_name);
        e_email = (EditText) findViewById(R.id.usr_email);
        e_psw = (EditText) findViewById(R.id.usr_psw);
        ec_psw = (EditText) findViewById(R.id.c_user_psw);

        city = (Spinner) findViewById(R.id.city);

        CityAdapter cityAdapter = new CityAdapter(this,cityValues);

        city.setAdapter(cityAdapter);

        pDialog = new ProgressDialog(this);

        pDialog.setMessage("Please Wait...");
        pDialog.setCancelable(false);

    }


    public void signupProcessing(View v) {

        showpDialog();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipClass.ipAddress).
                        addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);

        String psw = e_psw.getText().toString();
        String cpsw = ec_psw.getText().toString();

        if(!ef_name.getText().toString().equals("") && !el_name.getText().toString().equals("") && !e_email.getText().toString().equals("") && !cityValues.get(Integer.parseInt(city.getSelectedItem().toString())).equals("") && !psw.equals("")){
            if(psw.equals(cpsw)){

                ec_psw.setBackground(rightDr);

                Call<String> userData = service.ProcessSignUp(e_email.getText().toString(),ef_name.getText().toString()+" "+el_name.getText().toString(),psw,cityValues.get(Integer.parseInt(city.getSelectedItem().toString())));

                userData.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Response<String> response, Retrofit retrofit) {
                        hidepDialog();
                        String message = response.body();
                        Toast.makeText(SignUpActivity.this,message,Toast.LENGTH_LONG).show();
                        Intent loginPage = new Intent(getApplicationContext(),LoginRegisterActivity.class);
                        startActivity(loginPage);
                        finish();
                    }
                    @Override
                    public void onFailure(Throwable t) {
                        hidepDialog();
                        Toast.makeText(SignUpActivity.this,"There is some error",Toast.LENGTH_LONG).show();
                    }
                });

            }
            else {
                hidepDialog();
                ec_psw.setBackground(erDrawable);
                Toast.makeText(this,"Password does not match",Toast.LENGTH_SHORT).show();
            }
        } else {
            hidepDialog();
            Toast.makeText(this,"All fields are required",Toast.LENGTH_SHORT).show();
        }
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
