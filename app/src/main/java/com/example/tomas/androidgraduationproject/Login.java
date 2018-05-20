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

public class Login extends AppCompatActivity {
    AQuery aq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        aq=new  AQuery(this);
    }

    public void buloginckic(View view) {

        EditText mail=(EditText)findViewById(R.id.EDTUserName);
        EditText Password=(EditText)findViewById(R.id.EDTpassword);
        if((mail.getText().length()<2 )||(Password.getText().length()<2 ) ) {

            Operations.DisplayMessage(this, getResources().getString(R.string.AddAllinfo));

            return;
        }

        String url=SaveSettings.ServerURL +"UsersWS.asmx/Login?mail="+ mail.getText().toString() +"&password="+ Password.getText().toString();
        aq.ajax(url, JSONObject.class, this, "jsonCallback");
    }



    public void jsonCallback(String url, JSONObject json, AjaxStatus status) throws JSONException {

        if(json != null){
            //successful ajax call
            //successful ajax call

            int Of_ID=json.getInt("Of_ID");
            if(Of_ID!=0){
                SaveSettings.Of_ID=String.valueOf(Of_ID);
                SaveSettings sv=new SaveSettings(this);
                sv.SaveData();

                Intent intent=new Intent(this,ControlPanel.class);
                startActivity(intent);

            }
            else {
                String msg=json.getString("Messages");
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        }else{
            //ajax error
        }

    }

    public void burigsterclick(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
