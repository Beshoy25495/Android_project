package com.example.tomas.androidgraduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ControlPanel extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_panel);
        SaveSettings sv=new SaveSettings(this);
        sv.LoadData();
    }

    public void camera(View view) {
        Intent MyHomeActivity=new Intent(ControlPanel.this,CameraActivity.class);
        startActivity(MyHomeActivity);

    }


    public void search(View view) {

        Intent MyHomeActivity=new Intent(ControlPanel.this,MessageActivity.class);
        startActivity(MyHomeActivity);


    }


    public void ADD(View view) {
        Intent MyHomeActivity=new Intent(ControlPanel.this,AddInfractionActivity.class);
        startActivity(MyHomeActivity);


    }




    public void SearchAll(View view) {
        Intent MyHomeActivity=new Intent(ControlPanel.this,MessagesClientActivity.class);
        startActivity(MyHomeActivity);

    }

    public void Modify(View view) {
        Intent MyHomeActivity=new Intent(ControlPanel.this,ModifyInfractionActivity.class);
       startActivity(MyHomeActivity);

    }

    public void Delete(View view) {
        Intent MyHomeActivity=new Intent(ControlPanel.this,DeleteInfractionActivity.class);
        startActivity(MyHomeActivity);

    }



}
