package com.example.tomas.androidgraduationproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;



public class SplashActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private RadioButton rb_admin, rb_officer, rb_client;
    String theChoice = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        whoAreYou();
    }



    public void whoAreYou()
    {
        radioGroup = (RadioGroup) findViewById(R.id.rg_users);



        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {



            @Override

            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected

                if(checkedId == R.id.rb_officer) {
                    theChoice = "officer" ;
                    Toast.makeText(getApplicationContext(), "choice: Officer", Toast.LENGTH_SHORT).show();

                } else if(checkedId == R.id.rb_client) {
                    theChoice = "client" ;
                    Toast.makeText(getApplicationContext(), "choice: Client", Toast.LENGTH_SHORT).show();

                }

            }




        });




    }



    public void  login(View view)
    {



       if(theChoice.equalsIgnoreCase("officer"))
        {
           /*
            Intent loginUser = new Intent(this,LoginActivity.class) ;
            loginUser.putExtra("theChoice" , theChoice);
            startActivity(loginUser);
            */
        }
        else  if(theChoice.equalsIgnoreCase("client"))
        {
            /*
            Intent loginUser = new Intent(this,LoginActivity.class) ;
            loginUser.putExtra("theChoice" , theChoice);
            startActivity(loginUser);
            */
        }

    }


    public void  getStarted(View view)
    {




       if(theChoice.equalsIgnoreCase("officer"))
        {
           /*
            Intent signUser = new Intent(this,RegisterActivity.class) ;
            signUser.putExtra("theChoice" , theChoice);
            startActivity(signUser);
            */
        }
        else  if(theChoice.equalsIgnoreCase("client"))
        {
            /*
            Intent signUser = new Intent(this,RegisterActivity.class) ;
            signUser.putExtra("theChoice" , theChoice);
            startActivity(signUser);
            */
        }




    }

}
