package com.example.assignmentapp;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainFragment extends Fragment {
    View vMain;
    private EditText inputGoal;
    private Button accept;
    private TextView userWelcome, goalText, calorie;
    private static String goal1 = "";

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        SharedPreferences preferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String firstName = preferences.getString("firstName", String.valueOf(0));
        vMain = inflater.inflate(R.layout.fragment_main, container, false);
        userWelcome = (TextView) vMain.findViewById(R.id.userWelcomeText);
        goalText = (TextView) vMain.findViewById(R.id.textGoal);
        inputGoal = (EditText) vMain.findViewById(R.id.inputGoal);
        inputGoal.setInputType(InputType.TYPE_CLASS_NUMBER);
        calorie = (TextView) vMain.findViewById(R.id.calorieGoal);
        accept = (Button) vMain.findViewById(R.id.accept);
        if(!goal1.isEmpty()){
            goalText.setText("Your Today's Calorie Goal is ");
            calorie.setText(goal1);
        }
        userWelcome.setText("Hi! " + firstName);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputGoal.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity().getBaseContext(), "Please enter a number", Toast.LENGTH_LONG).show();
                }
                else if (Integer.valueOf(inputGoal.getText().toString()) == 0) {
                    goalText.setText("You Haven't Set Your Goals Today");
                    calorie.setText("");
                    inputGoal.setText("");
                    goal1 = "";

                } else {
                    goal1 = Integer.valueOf(inputGoal.getText().toString()) + " cal";
                    SharedPreferences preferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    String calorieGoal = inputGoal.getText().toString();
                    String param = goal1;
                    editor.putString("CalorieGoal", calorieGoal);
                    editor.putString("Param",goal1);
                    editor.commit();
                    goalText.setText("Your Today's Calorie Goal is ");
                    calorie.setText(goal1);
                    inputGoal.setText("");
                }
            }
        });
        return vMain;
    }

}
