package com.example.assignmentapp;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class StepsFragment extends Fragment {
    List<HashMap<String, String>> stepListArray;
    SimpleAdapter myListAdapter;
    ListView stepList;
    String[] colHEAD = new String[]{"Time","Steps"};
    int[] dataCell = new int[]{R.id.ctime, R.id.steps};
    Button addButton;
    EditText addEditText;
    HashMap<String, String> map;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_steps, container, false);
        stepList = v.findViewById(R.id.listView);
        addButton = v.findViewById(R.id.addButton);
        addEditText = v.findViewById(R.id.addEditText);
        addEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        stepListArray = new ArrayList<>();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        addButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                if(addEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(getActivity(), "ENTER A NUMBER FIRST",Toast.LENGTH_LONG).show();
                }
                else {
                    String steps = addEditText.getText().toString();
                    Date date = new Date();
                    String date1 = simpleDateFormat.format(date);
                    map = new HashMap<String, String>();
                    map.put("Time", date1);
                    map.put("Steps", steps);
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("steps",Integer.valueOf(steps));
                    addMap(map);
                    addEditText.setText("");
                }
            }
        });
        return v;
    }
    protected void addMap (HashMap map){
        stepListArray.add(map);
        myListAdapter = new SimpleAdapter(getActivity(), stepListArray, R.layout.list_view, colHEAD, dataCell);
        stepList.setAdapter(myListAdapter);
    }
}