package com.example.tomas.androidgraduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

public class DeleteInfractionActivity extends AppCompatActivity {
    AQuery aq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_infraction);
        aq = new AQuery(this);
    }

    public void BtnDel(View view) {
        EditText VID =(EditText)findViewById(R.id.textView6);
        if(VID.getText().toString() !="")
        {
            String url = SaveSettings.ServerURL +"UsersWS.asmx/DeleteMessageFromClient?VID="+ VID.getText().toString();

            aq.ajax(url, JSONObject.class, this, "jsonCallback");

        }else {
            Toast.makeText(this, "Please Enter Correct the VID ", Toast.LENGTH_LONG).show();
        }


    }



    public void jsonCallback(String url, JSONObject json, AjaxStatus status) throws JSONException {

        if(json != null){
            //successful ajax call
            //successful ajax call
            int Isdel=json.getInt("roweffected");
            if(Isdel==1) {
                Toast.makeText(this, "Delete the infraction successfully..", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ControlPanel.class);
                startActivity(intent);
            }
        }else{
            //ajax error
        }

    }





}
