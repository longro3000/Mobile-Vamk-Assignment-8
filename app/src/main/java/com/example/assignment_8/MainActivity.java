package com.example.assignment_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button buttonLogin;
    EditText username, password;
    DBAdapter dbAdapter=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter = new DBAdapter(getApplicationContext());

        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        buttonLogin=(Button)findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                validate(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void validate(String userName, String userPassword) {
        if(username.getText().length()==0){
            username.setBackgroundColor(Color.rgb(100, 150, 150));
            Toast.makeText(getApplication(), getResources().getString(R.string.no_id_txt), Toast.LENGTH_LONG).show();
        } else {
            if ((userName.equals("admin")) && (userPassword.equals("123456"))) {
                startActivity("admin");
            } else {
                //Here we add a new customer data
                dbAdapter.open();
                //long id = Integer.parseInt(userName);
                Cursor cursor = dbAdapter.getContactByLastName(userName);

                if (cursor.moveToFirst()) {
                    //Here we close database connection and start user page
                    dbAdapter.close();
                    startActivity(userName);
                } else {
                    //Here we close database connection and start error page
                    dbAdapter.close();
                    Intent intent = new Intent(getApplication(), ErrorActivity.class);
                    intent.putExtra("error", "User Not Found !! Try to Log In with admin instead");
                    startActivity(intent);
                }
            }
        }
    }

    private void startActivity(String userName) {
        Intent intent = new Intent(getApplication(), SecondActivity.class);
        intent.putExtra("data", userName);
        startActivity(intent);
    }
}