package com.example.assignmentapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {
    private TextView DOB;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private ImageView show;
    private ImageView hide;
    private EditText firstname;
    private EditText surename;
    private EditText email;
    private EditText address;
    private EditText postcode;
    private EditText steps;
    private RadioGroup gender;
    private EditText height;
    private EditText weight;
    String genderText = "male";
    Integer LOA = 0;
    private static final Integer[] list = {1, 2, 3, 4, 5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        final EditText username = (EditText) findViewById(R.id.userText);
        final EditText password = (EditText) findViewById(R.id.password);
        final Spinner loa = (Spinner) findViewById(R.id.LOA_spinner);
        show = (ImageView) findViewById(R.id.showpassword);
        hide = (ImageView) findViewById(R.id.hidepassword);
        DOB = (TextView) findViewById(R.id.DOB);
        firstname = (EditText) findViewById(R.id.firstName);
        surename = (EditText) findViewById(R.id.sureName);
        email = (EditText) findViewById(R.id.email);
        address = (EditText) findViewById(R.id.address);
        postcode = (EditText) findViewById(R.id.postCode);
        steps = (EditText) findViewById(R.id.stepsPerMile);
        gender = (RadioGroup) findViewById(R.id.radioSex);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        Button signup = (Button) findViewById(R.id.signupbtn);
        RadioButton male = (RadioButton) findViewById(R.id.radioMale);
        RadioButton female = (RadioButton) findViewById(R.id.radioFemale);
        TextView login = (TextView) findViewById(R.id.link_login);
        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        password.setSelection(password.getText().toString().length());
        height.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        height.setInputType(InputType.TYPE_CLASS_NUMBER);
        weight.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
        weight.setInputType(InputType.TYPE_CLASS_NUMBER);
        steps.setInputType(InputType.TYPE_CLASS_NUMBER);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectRadioBtn();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password1 = password.getText().toString();
                PostAsyncTask postAsyncTask = new PostAsyncTask();
                if (!(username.getText().toString().isEmpty())&& !(password.getText().toString().isEmpty())
                        && !(LOA.toString().isEmpty()) && !(firstname.getText().toString().isEmpty())
                        && !(surename.getText().toString().isEmpty()) && !(email.getText().toString().isEmpty())
                        && !(address.getText().toString().isEmpty()) && !(postcode.getText().toString().isEmpty())
                        && !(steps.getText().toString().isEmpty()) && !(genderText.isEmpty())
                        && !(height.getText().toString().isEmpty()) && !(weight.getText().toString().isEmpty())
                        && !(DOB.getText().toString().isEmpty())) {
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
                        for (int i = 0; i < bytes.length; i++) {
                            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                        }
                        //Get complete hashed password in hex format
                        password1 = sb.toString();
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
                        email.setError("enter a valid email address");
                    }
                    else {
                        postAsyncTask.execute(firstname.getText().toString(), surename.getText().toString(), email.getText().toString(), DOB.getText().toString(),
                                height.getText().toString(), weight.getText().toString(), genderText, address.getText().toString(),
                                postcode.getText().toString(), LOA.toString(), steps.getText().toString(), username.getText().toString(), password1);
                    }
                } else
                    Toast.makeText(getBaseContext(), "Please complete the form", Toast.LENGTH_LONG).show();
            }
        });
        final ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter(this
                , R.layout.spinner_item, list);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loa.setAdapter(spinnerAdapter);
        loa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LOA = parent.getSelectedItemPosition() + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(SignUpActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        dateSetListener, year, month, day);
                dialog.show();
            }
        });
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                month = month + 1;
                if (year >= cal.get(Calendar.YEAR))
                {
                    Toast.makeText(getApplicationContext(), "Invalid Date", Toast.LENGTH_SHORT).show();
                }
                else {
                    String date = year + "-" + month + "-" + dayOfMonth;
                    DOB.setText(date);
                }
            }
        };
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
    }
    public static java.util.Date StrTransDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date nowDate = null;
        try {
            nowDate = simpleDateFormat.parse(date);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nowDate;
    }
    private void selectRadioBtn() {
        RadioButton rb = (RadioButton) SignUpActivity.this.findViewById(gender.getCheckedRadioButtonId());
        genderText = rb.getText().toString();
    }
    private class PostAsyncTask extends AsyncTask<String, Void, String> {
        java.util.Date current = new Date();
        String birth = DOB.getText().toString() + " 00:00:00";
        java.util.Date dob = StrTransDate(birth);

        @Override
        protected String doInBackground(String... params) {
            String sameEmail = RESTclient.findUserByEmail(params[2]);
            String sameUsername = RESTclient.findCredentialByUsername(params[11]);
            String valid = "no";
            if (sameEmail.equals("[]") && sameUsername.equals("[]")) {
                Userinfo userinfo = new Userinfo(null, params[0], params[1], params[2], dob, Integer.valueOf(params[4]), Integer.valueOf(params[5]), params[6],
                        params[7], params[8], Integer.valueOf(params[9]), Integer.valueOf(params[10]));
                RESTclient.createUser(userinfo);
                Credential credential = new Credential(null, params[11], params[12], current);
                String test = RESTclient.findUserByEmail(userinfo.getEmail());
                String userid = RESTclient.getID(test);
                userinfo.setUserid(Integer.valueOf(userid));
                credential.setUserid(userinfo);
                RESTclient.createCredential(credential);
                valid = "ok";
            }
            return valid;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result == "ok") {
                Toast.makeText(getApplicationContext(), "Processing...", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            } else
                Toast.makeText(getApplicationContext(), "Username or email exists", Toast.LENGTH_SHORT).show();
        }
    }
}


