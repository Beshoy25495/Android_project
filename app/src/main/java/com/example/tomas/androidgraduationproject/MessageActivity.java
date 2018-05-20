package com.example.tomas.androidgraduationproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    AQuery aq;
    //Add the Messages objects to an ArrayList
    ArrayList<Message> MessageList = new ArrayList<>();
    ListView mListView =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aq = new AQuery(this);

        setContentView(R.layout.activit_listviewmessage);
        mListView = (ListView) findViewById(R.id.listView);



    }


    public void Btnsearch(View view) {

        EditText VID =(EditText)findViewById(R.id.textView6) ;

        if(VID.getText().toString() !="")
        {
            String url = SaveSettings.ServerURL +"UsersWS.asmx/ShowAllMessageToClient?VID="+ VID.getText().toString();

            aq.ajax(url, JSONObject.class, this, "jsonCallback");

        }else {
            Toast.makeText(this, "Please Enter Correct the VID ", Toast.LENGTH_LONG).show();
        }



    }



    public void jsonCallback(String url, JSONObject json, AjaxStatus status) throws JSONException {

        if(json != null){
            //successful ajax call
            //successful ajax call
            JSONArray contactslist = json.getJSONArray("contactslist");
            // String[] MessageArray = new String[contactslist.length()];
            for(int i=0;i<contactslist.length();i++)
            {
                JSONObject JScontactslist = contactslist.getJSONObject(i);
                MessageList.add(new Message(JScontactslist.getString("Name"),JScontactslist.getString("Cost"),JScontactslist.getString("Street")));

            }


            MessageListAdapter adapter = new MessageListAdapter(this, R.layout.adapter_d, MessageList);
            mListView.setAdapter(adapter);



        }else{
            //ajax error
        }

    }






}
