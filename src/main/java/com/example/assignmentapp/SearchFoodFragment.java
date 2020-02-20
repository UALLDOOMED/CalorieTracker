package com.example.assignmentapp;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchFoodFragment extends Fragment {
    View vSearchFood;
    private EditText editText, amount;
    private Button btnSearch, addToEat, addToDB;
    private Spinner categorySpinner;
    private Spinner foodNameSpinner;
    private TextView foodCal, foodFat, servingUnit, showName;
    String foodName;
    String calorie;
    String fat;
    String serving;
    String foodID;
    String serve_amount;
    String category;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        vSearchFood = inflater.inflate(R.layout.fragment_search_food, container, false);
        Toast.makeText(vSearchFood.getContext(), "Loading information...", Toast.LENGTH_SHORT).show();
        editText = (EditText) vSearchFood.findViewById(R.id.editText);
        foodCal =  (TextView) vSearchFood.findViewById(R.id.foodCal);
        foodFat = (TextView) vSearchFood.findViewById(R.id.foodFat);
        showName = (TextView) vSearchFood.findViewById(R.id.searchedFoodName);
        servingUnit = (TextView) vSearchFood.findViewById(R.id.serving_unit);
        amount = (EditText) vSearchFood.findViewById(R.id.amount);
        addToEat = (Button) vSearchFood.findViewById(R.id.btnADD);
        addToDB = (Button) vSearchFood.findViewById(R.id.btnAddToDB);
        amount.setInputType(InputType.TYPE_CLASS_NUMBER);
        FindCategoryAsyncTask findCategoryAsyncTask = new FindCategoryAsyncTask();
        findCategoryAsyncTask.execute();
        btnSearch = (Button) vSearchFood.findViewById(R.id.btnFind);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(vSearchFood.getContext(), "Please wait, it may take a few seconds...", Toast.LENGTH_SHORT).show();
                String keyword = editText.getText().toString();
                SearchFoodFragment.SearchAsyncTask searchAsyncTask = new SearchFoodFragment.SearchAsyncTask();
                SearchFoodFragment.GoogleSearchAsyncTask googleAsyncTask = new SearchFoodFragment.GoogleSearchAsyncTask();
                SearchFoodFragment.DownloadImageFromInternet imageSearch = new SearchFoodFragment.DownloadImageFromInternet((ImageView)vSearchFood.findViewById(R.id.imageView));
                searchAsyncTask.execute(keyword);
                googleAsyncTask.execute(keyword);
                imageSearch.execute(keyword);
            }
        });
        addToEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!amount.getText().toString().isEmpty()){

                    AddConsumptionAsyncTask addConsumptionAsyncTask = new AddConsumptionAsyncTask();
                    addConsumptionAsyncTask.execute();
                }
                else  Toast.makeText(vSearchFood.getContext(), "The amount cannot be 0", Toast.LENGTH_SHORT).show();
            }
        });
        addToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFoodAsyncTask addFoodAsyncTask = new AddFoodAsyncTask();
                addFoodAsyncTask.execute();
            }
        });
        return vSearchFood;
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
    private class SearchAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return SearchFoodAPI.search(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            foodName = SearchFoodAPI.getFoodName(result);
            foodName = foodName.replace(","," ");
            calorie = SearchFoodAPI.getFoodCalorie(result);
            calorie = calorie.substring(0,calorie.indexOf("."));
            fat = SearchFoodAPI.getFoodFat(result);
            fat = fat.substring(0,fat.indexOf("."));
            editText.setText("");
            foodCal.setText("CALORIE : " + calorie);
            foodFat.setText("FAT : " + fat);
            servingUnit.setText("100g");
            showName.setText("NAME : " + foodName);
        }
    }

    private class GoogleSearchAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            return SearchFoodAPI.googleSearch(params[0], new String[]{"num"}, new
                    String[]{"1"});
        }

        @Override
        protected void onPostExecute(String result) {
            TextView tvGoogle = (TextView) vSearchFood.findViewById(R.id.tvGoogle);
            amount.setText("");
            tvGoogle.setText(SearchFoodAPI.getSnippet(result));
        }
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView = imageView;
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL = SearchFoodAPI.getImage(SearchFoodAPI.imageSearch(urls[0], new String[]{"num"}, new
                    String[]{"1"}));
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }

        protected void onPostExecute(Bitmap result) {
            if(result != null) {
                ImageView imageView = (ImageView) vSearchFood.findViewById(R.id.imageView);
                imageView.setImageBitmap(result);
            }
            else {
                Toast.makeText(vSearchFood.getContext(), "No Images found", Toast.LENGTH_SHORT).show();
            }
        }

        }
    private class FindCategoryAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            categorySpinner = (Spinner) vSearchFood.findViewById(R.id.category_spinner);
            return RESTclient.findAllCategory();
        }

        @Override
        protected void onPostExecute(String result) {
            final String[] cateList = result.split(",");
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, cateList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            categorySpinner.setAdapter(spinnerAdapter);
            categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    FindFoodAsyncTask findFoodAsyncTask = new FindFoodAsyncTask();
                    findFoodAsyncTask.execute(cateList[position]);
                    category = cateList[position];
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }
    }
    private class FindFoodAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            foodNameSpinner = (Spinner) vSearchFood.findViewById(R.id.foodName_spinner);
            return RESTclient.findFoodByCategory(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            foodName = RESTclient.getFoodName(result);
            final String[] foodNameList = foodName.split(",");
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getActivity(), R.layout.spinner_item, foodNameList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            foodNameSpinner.setAdapter(spinnerAdapter);
            foodNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        GoogleSearchAsyncTask googleSearchAsyncTask = new GoogleSearchAsyncTask();
                        googleSearchAsyncTask.execute(foodNameList[position]);
                        showName.setText("NAME : " + foodNameList[position]);
                        foodName = foodNameList[position];
                        FindFoodInfoAsyncTask findFoodInfoAsyncTask = new FindFoodInfoAsyncTask();
                        findFoodInfoAsyncTask.execute(foodName);
                        DownloadImageFromInternet downloadImageFromInternet = new DownloadImageFromInternet((ImageView)vSearchFood.findViewById(R.id.imageView));
                        downloadImageFromInternet.execute(foodName);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }
    private class FindFoodInfoAsyncTask extends AsyncTask<String, Void, String>{
        @Override
        protected String doInBackground(String... params) {
            return RESTclient.findFoodByFoodName(params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            calorie = RESTclient.getCalorie(result);
            fat = RESTclient.getFat(result);
            serving = RESTclient.getServingUnit(result);
            foodCal.setText("CALORIE : " + calorie);
            foodFat.setText("FAT : " + fat);
            servingUnit.setText(serving);
            serve_amount = RESTclient.getAmount(result);
            foodID = RESTclient.getFoodID(result);
        }
    }
    private class AddConsumptionAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            Date date = new Date();
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userinfo",0);
            Integer userid = sharedPreferences.getInt("userid",0);
            String firstName = sharedPreferences.getString("firstName", String.valueOf(0));
            String sureName = sharedPreferences.getString("sureName", String.valueOf(0));
            String email = sharedPreferences.getString("email", String.valueOf(0));
            String dob = sharedPreferences.getString("dob", String.valueOf(0));
            Integer height = sharedPreferences.getInt("height", 0);
            Integer weight = sharedPreferences.getInt("weight", 0);
            String gender = sharedPreferences.getString("gender", String.valueOf(0));
            String address = sharedPreferences.getString("address", String.valueOf(0));
            String postcode = sharedPreferences.getString("postcode", String.valueOf(0));
            Integer loa = sharedPreferences.getInt("loa", 0);
            Integer steps = sharedPreferences.getInt("steps", 0);
            java.util.Date birthday = StrTransDate(dob);
            String valid = "ok";
            if( !RESTclient.findFoodByFoodName(foodName).equals("[]")){
            Userinfo userinfo1 = new Userinfo(userid,firstName,sureName,email,birthday,height,weight, gender,
                    address,postcode, loa,steps);
                Consumption consumption = new Consumption(null,date,Integer.valueOf(amount.getText().toString()));
                Food food = new Food(Integer.valueOf(foodID),foodName,category,Integer.valueOf(calorie),serving,Double.valueOf(serve_amount),Integer.valueOf(fat));
                consumption.setFoodid(food);
                consumption.setUserid(userinfo1);
                RESTclient.createConsumption(consumption);
            }
            else {
                valid = "no";
            }
            return valid;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("ok")) {
                Toast.makeText(vSearchFood.getContext(), "The Food Added To Eat", Toast.LENGTH_SHORT).show();
                amount.setText("");
            }
            else  Toast.makeText(vSearchFood.getContext(), "The food doesn't exist in the database", Toast.LENGTH_SHORT).show();
        }
    }
    private class AddFoodAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String valid = "no";
            String result = RESTclient.findFoodByFoodName(foodName);
            if(result.equals("[]")) {
                String foodinfo = SearchFoodAPI.search(foodName);
                String category = SearchFoodAPI.getFoodCategory(foodinfo);
                category = category.replace(" ","");
                Food food = new Food(null,foodName,category,Integer.valueOf(calorie),"g",100.0,Integer.valueOf(fat));
                RESTclient.createFood(food);
                valid = "ok";
            }
            return valid;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result.equals("ok"))
            {
                Toast.makeText(vSearchFood.getContext(), "The Food Added To Database", Toast.LENGTH_SHORT).show();
            }
            else Toast.makeText(vSearchFood.getContext(), "The Food Exists In The Database", Toast.LENGTH_SHORT).show();
        }
    }
}
