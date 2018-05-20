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

public class ModifyInfractionActivity extends AppCompatActivity {
    AQuery aq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_infraction);
        aq = new AQuery(this);
    }

    public void buMod(View view) {


        EditText Name=(EditText)findViewById(R.id.EDTToolName);
        EditText Description=(EditText)findViewById(R.id.EDToolDes);
        EditText Street=(EditText)findViewById(R.id.EDTToolStreat);
        EditText City=(EditText)findViewById(R.id.EDTToolCity);
        EditText Cost=(EditText)findViewById(R.id.EDTToolPrice);
        EditText Number=(EditText)findViewById(R.id.EDTToolNumber);
        EditText CID=(EditText)findViewById(R.id.EDTToolCID);


        if(Name.getText().toString() !="" && Description.getText().toString() !="" &&Street.getText().toString() !="" && City.getText().toString() !="" && Cost.getText().toString() !="" && Number.getText().toString() !="" && CID.getText().toString() !="")
        {

            String url = SaveSettings.ServerURL +"UsersWS.asmx/UpdateMessageForClient?Name="+ Name.getText().toString()+"&Des="+ Description.getText().toString()+"&Street="+Street.getText().toString()+"&City="+City.getText().toString()+"&Cost="+Cost.getText().toString()+"&Number="+Number.getText().toString()+"&CID="+Integer.parseInt(CID.getText().toString());

            aq.ajax(url, JSONObject.class, this, "jsonCallback");

        }
        else {
            Toast.makeText(this, "Please Enter All Information", Toast.LENGTH_LONG).show();
        }



    }



    public void jsonCallback(String url, JSONObject json, AjaxStatus status) throws JSONException {

        if(json != null){
            //successful ajax call
            //successful ajax call
            String msg=json.getString("Messages");
            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            int Ismod=json.getInt("IsAdded");
            if(Ismod==1) {
                Intent intent = new Intent(this, ControlPanel.class);
                startActivity(intent);
            }
        }else{
            //ajax error
        }





    }










}
