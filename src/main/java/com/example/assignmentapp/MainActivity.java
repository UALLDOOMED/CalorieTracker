package com.example.assignmentapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    private  ImageView show;
    private  ImageView hide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText username = (EditText) findViewById(R.id.userText);
        final EditText password = (EditText) findViewById(R.id.password);
        show = (ImageView) findViewById(R.id.showpassword);
        hide = (ImageView) findViewById(R.id.hidepassword);
        Button loginbtn = (Button) findViewById(R.id.loginbtn);
        TextView signup_link = (TextView) findViewById(R.id.link_signup);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        password.setSelection(password.getText().toString().length());
        signup_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
        hide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                password.setSelection(password.getText().toString().length());
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide.setVisibility(View.VISIBLE);
                show.setVisibility(View.INVISIBLE);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                password.setSelection(password.getText().toString().length());
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username1 = username.getText().toString();
                String password1 = password.getText().toString();
                try {
                    // Create MessageDigest instance for MD5
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    //Add password bytes to digest
                    md.update(password1.getBytes());
                    //Get the hash's bytes
                    byte[] bytes = md.digest();
                    //This bytes[] has bytes in decimal format;
                    //Convert it to hexadecimal format
                    StringBuilder sb = new StringBuilder();
                    for(int i=0; i< bytes.length ;i++)
                    {
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                    }
                    //Get complete hashed password in hex format
                    password1 = sb.toString();
                }
                catch (NoSuchAlgorithmException e)
                {
                    e.printStackTrace();
                }
                if(username1.length() == 0 || password1.length() == 0){
                    Toast.makeText(getBaseContext(), "Please enter username and password", Toast.LENGTH_LONG).show();
                }
                else {
                    LoginAsyncTask login = new LoginAsyncTask();
                    login.execute(username1, password1);
                    GoogleGeocoding googleGeocoding = new GoogleGeocoding();
                    googleGeocoding.execute();
                }
            }
        });
    }

    private class LoginAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String result =  RESTclient.findCredentialByUandP(params[0],params[1]);
            String username = null;
            String firstName = null;
            String sureName = null;
            String email = null;
            String dob = null;
            Integer height = 0;
            Integer weight = 0;
            String gender = null;
            String address = null;
            String postcode = null;
            Integer loa = 0 ;
            Integer steps = 0;
            Integer userid = 0;
            try {
                JSONArray userList = new JSONArray(result);
                JSONObject userinfo = userList.getJSONObject(0);
                if (userinfo != null) {
                    username = userinfo.getString("username");
                    JSONObject user = userinfo.getJSONObject("userid");
                    userid = user.getInt("userid");
                    firstName = user.getString("name");
                    sureName = user.getString("surename");
                    email = user.getString("email");
                    dob = user.getString("dob");
                    height = user.getInt("height");
                    weight = user.getInt("weight");
                    gender = user.getString("gender");
                    address = user.getString("address");
                    postcode = user.getString("postcode");
                    loa = user.getInt("levelOfActivity");
                    steps = user.getInt("stepsPerMile");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            SharedPreferences preferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("userid", userid);
            editor.putString("username", username);
            editor.putString("firstName", firstName);
            editor.putString("sureName", sureName);
            editor.putString("email", email);
            editor.putString("dob", dob);
            editor.putInt("height",height);
            editor.putInt("weight",weight);
            editor.putString("gender", gender);
            editor.putString("address", address);
            editor.putString("postcode", postcode);
            editor.putInt("loa",loa);
            editor.putInt("steps",steps);

            editor.commit();
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            if(!result.equals("[]")){
                Intent intent=new Intent(MainActivity.this,FragmentMenuActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Login Successful", Toast.LENGTH_LONG).show();
                finish();
            }
            else  Toast.makeText(getBaseContext(), "Wrong username or password!", Toast.LENGTH_LONG).show();
        }
    }
    private class GoogleGeocoding extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            SharedPreferences sharedPreferences = getSharedPreferences("userinfo", 0);
            Integer userid = sharedPreferences.getInt("userid", 0);
            String userinfo = RESTclient.findUserByID(userid);
            String address = null;
            String postcode = null;
            String fullAddress = null;
            try {
                JSONObject jsonObject =new JSONObject(userinfo);
                if (jsonObject != null) {
                    address = jsonObject.getString("address");
                    postcode = jsonObject.getString("postcode");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            fullAddress = postcode + " " + address;
            String response = GoogleMapAPI.showPlace(fullAddress);
            return  response;
        }
        @Override
        protected void onPostExecute(String result){
            try{
                JSONObject jsonObject = new JSONObject(result);
                if (jsonObject != null) {
                    String lat = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat").toString();
                    String lng = ((JSONArray)jsonObject.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng").toString();
                    SharedPreferences sharedPreferences = getSharedPreferences("geocode", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("latitude", lat);
                    editor.putString("longitude", lng);
                    editor.commit();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
