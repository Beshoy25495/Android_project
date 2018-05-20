package com.example.tomas.androidgraduationproject;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MessagesClientActivity extends AppCompatActivity {
    AQuery aq;
    //Add the Messages objects to an ArrayList
    ArrayList<Messages> MessageList = new ArrayList<>();
    ListView mListView =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aq = new AQuery(this);

        setContentView(R.layout.activity_messages_client);
         mListView = (ListView) findViewById(R.id.LVNews);

        String url = SaveSettings.ServerURL +"UsersWS.asmx"+"/ShowAllMessageToAllClient";

        aq.ajax(url, JSONObject.class, this, "jsonCallback");


//        //Add the Messages objects to an ArrayList
//        ArrayList<Messages> MessageList = new ArrayList<>();



//
//        MessagesListAdapter adapter = new MessagesListAdapter(this, R.layout.adapter_view_layout, MessageList);
//        mListView.setAdapter(adapter);




    }

    public void btnDelete(View view) {
//        Intent MyHomeActivity=new Intent(ControlPanel.this,AddInfractionActivity.class);
//        startActivity(MyHomeActivity);
    }

    public void buAddInf(View view) {
        Intent MyHomeActivity=new Intent(this,AddInfractionActivity.class);
        startActivity(MyHomeActivity);
    }

    public void bunhomepage(View view) {
        Intent MyHomeActivity=new Intent(this,ControlPanel.class);
        startActivity(MyHomeActivity);
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
                MessageList.add(new Messages(JScontactslist.getString("Number") ,JScontactslist.getString("Name"),JScontactslist.getString("Cost"),JScontactslist.getString("Street"),JScontactslist.getString("CID")));

            }


            MessagesListAdapter adapter = new MessagesListAdapter(this, R.layout.adapter_view_layout, MessageList);
            mListView.setAdapter(adapter);



        }else{
            //ajax error
        }

    }




    SearchView searchView;
    Menu myMenu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.control_menu, menu);
        myMenu=menu;
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //final Context co=this;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return true;
    }










}
