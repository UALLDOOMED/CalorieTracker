package com.example.assignmentapp;



import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SearchFoodAPI {
    private static final String APP_KEY = "1c4e9c162fb88c1c894b04ea7cba1f21";
    private static final String APP_ID = "8fd2c12a";
    private static final String API_KEY = "AIzaSyCQoGRtSUTJwTHmLpiMl192yyL7qL34bXk";
    private static final String SEARCH_ID_cx = "013776133436378424954:lpbn94nhkyk";

    public static String search(String keyword) {
        keyword = keyword.replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        try {
            url = new URL("https://api.edamam.com/api/food-database/parser?ingr=" + keyword + "&app_id=" + APP_ID + "&app_key=" + APP_KEY);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return textResult;
    }

    public static String googleSearch(String keyword, String[] params, String[] values) {
        keyword = keyword.replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        String query_parameter = "";
        if (params != null && values != null) {
            for (int i = 0; i < params.length; i++) {
                query_parameter += "&";
                query_parameter += params[i];
                query_parameter += "=";
                query_parameter += values[i];
            }
        }
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key=" +
                    API_KEY + "&cx=" + SEARCH_ID_cx + "&q=" + keyword + query_parameter);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return textResult;
    }

    public static String imageSearch(String keyword, String[] params, String[] values) {
        keyword = keyword.replace(" ", "+");
        URL url = null;
        HttpURLConnection connection = null;
        String textResult = "";
        String query_parameter = "";
        if (params != null && values != null) {
            for (int i = 0; i < params.length; i++) {
                query_parameter += "&";
                query_parameter += params[i];
                query_parameter += "=";
                query_parameter += values[i];
            }
        }
        try {
            url = new URL("https://www.googleapis.com/customsearch/v1?key=" +
                    API_KEY + "&cx=" + SEARCH_ID_cx + "&searchType=image&q=" + keyword + query_parameter);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            Scanner scanner = new Scanner(connection.getInputStream());
            while (scanner.hasNextLine()) {
                textResult += scanner.nextLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return textResult;
    }

    public static String getFoodName(String result) {
        String foodName = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("parsed");
            if (jsonArray != null && jsonArray.length() > 0) {
                foodName = jsonArray.getJSONObject(0).getJSONObject("food").getString("label");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return foodName;
    }
    public static String getFoodCalorie(String result) {
        String calorie = null;
        String category = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("parsed");
            if (jsonArray != null && jsonArray.length() > 0) {
                calorie = jsonArray.getJSONObject(0).getJSONObject("food").getJSONObject("nutrients").getString("ENERC_KCAL");
                category = jsonArray.getJSONObject(0).getString("category");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calorie;
    }

    public static String getFoodCategory(String result) {
        String category = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("parsed");
            if (jsonArray != null && jsonArray.length() > 0) {
                category = jsonArray.getJSONObject(0).getJSONObject("food").getString("category");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }

    public static String getFoodFat(String result) {
        String fat = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("parsed");
            if (jsonArray != null && jsonArray.length() > 0) {
                fat = jsonArray.getJSONObject(0).getJSONObject("food").getJSONObject("nutrients").getString("FAT");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fat;
    }


    public static String getSnippet(String result) {
        String snippet = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            if (jsonArray != null && jsonArray.length() > 0) {
                snippet = jsonArray.getJSONObject(0).getString("snippet");
                for(int i = 0;i < snippet.length();i++){
                    if (snippet.charAt(i) == '.') {
                        snippet = snippet.substring(0,i+1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            snippet = "NO INFO FOUND";
        }
        return snippet;
    }

    public static String getImage(String result) {
        String url = null;
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            if (jsonArray != null && jsonArray.length() > 0) {
                url = jsonArray.getJSONObject(0).getString("link");
            }
        } catch (Exception e) {
            e.printStackTrace();
            url = "NO IMAGE FOUND";
        }
        return url;
    }
}

