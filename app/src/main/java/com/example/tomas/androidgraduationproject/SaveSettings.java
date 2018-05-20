package com.example.tomas.androidgraduationproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.security.PublicKey;


public class SaveSettings {

    Context context;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs3" ;
    public static   String Of_ID = "0";

    public static String ServerURL="http://kokowebservice.somee.com/";    //used

    public  SaveSettings(Context context) {
        this.context=context;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

    }
    public void SaveData()  {

        try

        {

            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString("UserID",String.valueOf(Of_ID));
            editor.commit();
            LoadData( );
        }

        catch( Exception e){}
    }
    public   void LoadData( ) {

        String TempUserID=sharedpreferences.getString("Of_ID","empty");
        if(!TempUserID.equals("empty"))
            Of_ID=TempUserID;// load user name
        else {
            Intent intent=new Intent(context,Login.class);
                   context.startActivity(intent);
        }
    }
}
