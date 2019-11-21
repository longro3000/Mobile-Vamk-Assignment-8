package com.example.assignment_8;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;


public class SecondActivity extends AppCompatActivity {
    EditText FirstNameEditText = null, LastNameEditText, PhoneNumberEditText, EducationEditText, HobbiesEditText, idEditText;
    TextView summaryTextView = null, timeTextView = null;
    LinearLayout.LayoutParams viewLayoutParams = null;
    Button addButton, getButton, getAllButton, deleteButton, updateButton;
    DBAdapter dbAdapter=null;

    // Here we create the layout
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbAdapter = new DBAdapter(getApplicationContext());

        // Here we define parameters for views
        viewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        viewLayoutParams.leftMargin = 40;
        viewLayoutParams.rightMargin = 40;
        viewLayoutParams.topMargin = 10;
        viewLayoutParams.bottomMargin = 10;
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);




//Here we initialize the SeekBar and EditText objects
        timeTextView = new TextView(this);
        timeTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(timeTextView);

        // Here we define a text view
        TextView IDTextView = new TextView(this);
        IDTextView.setText("ID");
        IDTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(IDTextView);
// Here we define the edit text
        idEditText= new EditText(this);
        idEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(idEditText);



// Here we define a text view
        TextView FirstNameTextView = new TextView(this);
        FirstNameTextView.setText("First Name");
        FirstNameTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(FirstNameTextView);
// Here we define the edit text
        FirstNameEditText = new EditText(this);
        FirstNameEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(FirstNameEditText);
        // Here we define a text view
        TextView LastNameTextView = new TextView(this);
        LastNameTextView.setText("Last Name");
        LastNameTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(LastNameTextView);
// Here we define the edit text
        LastNameEditText = new EditText(this);
        LastNameEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(LastNameEditText);
        // Here we define a text view
        TextView PhoneNumberTextView = new TextView(this);
        PhoneNumberTextView.setText("Phone Number");
        PhoneNumberTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(PhoneNumberTextView);
// Here we define the edit text
        PhoneNumberEditText = new EditText(this);
        PhoneNumberEditText.setInputType(InputType.TYPE_CLASS_PHONE);
        PhoneNumberEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(PhoneNumberEditText);
        // Here we define a text view
        TextView EducationTextView = new TextView(this);
        EducationTextView.setText("Education Level");
        EducationTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(EducationTextView);
// Here we define the edit text
        EducationEditText = new EditText(this);
        EducationEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(EducationEditText);
        // Here we define a text view
        TextView HobbiesTextView = new TextView(this);
        HobbiesTextView.setText("Hobbies");
        HobbiesTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(HobbiesTextView);
// Here we define the edit text
        HobbiesEditText = new EditText(this);
        HobbiesEditText.setLayoutParams(viewLayoutParams);


        linearLayout.addView(HobbiesEditText);

//Add new Data Button

        addButton = new Button(this);
        addButton.setText("Add New Contact");
        addButton.setLayoutParams(viewLayoutParams);
        addButton.setOnClickListener(clickListener);
        linearLayout.addView(addButton);

//Save Internal Button

        deleteButton = new Button(this);
        deleteButton.setText("Delete");
        deleteButton.setLayoutParams(viewLayoutParams);
        deleteButton.setOnClickListener(clickListener);
        linearLayout.addView(deleteButton);

//Load Internal Button
        getButton = new Button(this);
        getButton.setText("Get Contact");
        getButton.setLayoutParams(viewLayoutParams);
        getButton.setOnClickListener(clickListener);
        linearLayout.addView(getButton);

//Save External Button

        getAllButton = new Button(this);
        getAllButton.setText("Get All Contacts");
        getAllButton.setLayoutParams(viewLayoutParams);
        getAllButton.setOnClickListener(clickListener);
        linearLayout.addView(getAllButton);

//Load External Button
        updateButton = new Button(this);
        updateButton.setText("Update");
        updateButton.setLayoutParams(viewLayoutParams);
        updateButton.setOnClickListener(clickListener);
        linearLayout.addView(updateButton);

// Here we define a text view
        summaryTextView = new TextView(this);
        summaryTextView.setLayoutParams(viewLayoutParams);
        linearLayout.addView(summaryTextView);


// Here we define a text view
        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);


        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        scrollView.addView(linearLayout);
        this.addContentView(scrollView, layoutParams);

        String data = "";
// Here we access the incoming Intent object
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            data = extras.getString("data");

        if (data != "admin") {
            dbAdapter.open();
            //long id = Integer.parseInt(userName);
            Cursor cursor = dbAdapter.getContactByLastName(data);

            idEditText.setText(cursor.getString(0));
            FirstNameEditText.setText(cursor.getString(1));
            LastNameEditText.setText(cursor.getString(2));
            PhoneNumberEditText.setText(cursor.getString(3));
            EducationEditText.setText(cursor.getString(4));
            HobbiesEditText.setText(cursor.getString(5));

            String displayUser = "User ID: " + cursor.getString(0) + " --- Last Name: " + cursor.getString(2) + " --- Last Login: " + cursor.getString(6);
            timeTextView.setText(displayUser);
        }
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button clickedButton = (Button) v;
            if(clickedButton.getText().equals("Add New Contact")) {
                if(idEditText.getText().length()==0 || FirstNameEditText.getText().length()==0 || PhoneNumberEditText.getText().length()==0
                        || LastNameEditText.getText().length()==0 || HobbiesEditText.getText().length()==0 || EducationEditText.getText().length()==0){
                    Toast.makeText(getApplication(), getResources().getString(R.string.empty_fields_txt), Toast.LENGTH_LONG).show();
                } else {
                    //Here we add a new customer data
                    dbAdapter.open();
                    String date = "" + new Date();
                    long id = dbAdapter.addContact(Integer.parseInt(idEditText.getText().toString()),
                                            FirstNameEditText.getText().toString(),
                                            LastNameEditText.getText().toString(),
                                            PhoneNumberEditText.getText().toString(),
                                            EducationEditText.getText().toString(),
                                            HobbiesEditText.getText().toString(),
                                            date);
                    displayResult(id);
                    //Here we close database connection
                    dbAdapter.close();
                }
            } else if(clickedButton.getText().equals("Get All Contact")) {
                //Here we add a new customer data
                dbAdapter.open();
                Cursor cursor = dbAdapter.getAllContacts();
                if(cursor.moveToFirst()) {
                    do {
                        summaryTextView.append(displayData(cursor) + "\n");
                    } while(cursor.moveToNext());
                }
                //Here we close database connection
                dbAdapter.close();
            } else if(clickedButton.getText().equals("Get Contact")) {
                if(idEditText.getText().length()==0){
                    idEditText.setBackgroundColor(Color.rgb(100, 150, 150));
                    Toast.makeText(getApplication(), getResources().getString(R.string.no_id_txt), Toast.LENGTH_LONG).show();
                } else {
                    //Here we add a new customer data
                    dbAdapter.open();
                    long id= Integer.parseInt(idEditText.getText().toString());
                    Cursor cursor = dbAdapter.getContact(id);
                    if(cursor.moveToFirst()) {
                        summaryTextView.setText(displayData(cursor));
                    } else {
                        Toast.makeText(getApplication(), getResources().getString(R.string.customer_id_txt) + id + getResources().getString(R.string.not_found_txt), Toast.LENGTH_LONG).show();
                    }
                    //Here we close database connection
                    dbAdapter.close();
                }
            } else if(clickedButton.getText().equals("Update")) {
                if(idEditText.getText().length()==0 || FirstNameEditText.getText().length()==0 || PhoneNumberEditText.getText().length()==0
                        || LastNameEditText.getText().length()==0 || HobbiesEditText.getText().length()==0 || EducationEditText.getText().length()==0){
                    Toast.makeText(getApplication(), getResources().getString(R.string.empty_fields_txt), Toast.LENGTH_LONG).show();
                } else {
                    //Here we add a new customer data
                    dbAdapter.open();
                    long id= Integer.parseInt(idEditText.getText().toString());
                    String date = "" + new Date();
                    if(dbAdapter.updateContact(id,
                            FirstNameEditText.getText().toString(),
                            LastNameEditText.getText().toString(),
                            PhoneNumberEditText.getText().toString(),
                            EducationEditText.getText().toString(),
                            HobbiesEditText.getText().toString(),
                            date))
                        Toast.makeText(getApplication(), getResources().getString(R.string.update_txt) + id +getResources().getString(R.string.success_txt), Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplication(), getResources().getString(R.string.update_txt) + id + getResources().getString(R.string.failure_txt), Toast.LENGTH_LONG).show();
                    //Here we close database connection
                    dbAdapter.close();
                }
            } else if(clickedButton.getText().equals("Delete")) {
                if(idEditText.getText().length()==0){
                    idEditText.setBackgroundColor(Color.rgb(100, 150, 150));
                    Toast.makeText(getApplication(), getResources().getString(R.string.no_id_txt), Toast.LENGTH_LONG).show();
                } else {
                    //Here we add a new customer data
                    dbAdapter.open();
                    long id= Integer.parseInt(idEditText.getText().toString());
                    if(dbAdapter.deleteContact(id))
                        Toast.makeText(getApplication(),  getResources().getString(R.string.delete_txt) + id + getResources().getString(R.string.success_txt), Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(getApplication(), getResources().getString(R.string.delete_txt) + id + getResources().getString(R.string.failure_txt), Toast.LENGTH_LONG).show();
                    //Here we close database connection
                    dbAdapter.close();
                }
            }
        }
    };

    private String displayData(Cursor cursor){
        return "ID: " + cursor.getString(0)
                + " -- FirstName:"  + cursor.getString(1)
                + " -- LastName: " + cursor.getString(2)
                + " -- Phone Number: " + cursor.getString(3)
                + " -- Education Level: " + cursor.getString(4)
                + " -- Hobbies: " + cursor.getString(5)
                + " -- Last Login: "+ cursor.getString(6);
    }

    private void displayResult(long id) {
        Toast.makeText(getApplication(), getResources().getString(R.string.returned_value_txt)+ " " + id, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.open();
        long id= Integer.parseInt(idEditText.getText().toString());
        String date = "" + new Date();
        if(dbAdapter.updateContact(id,
                FirstNameEditText.getText().toString(),
                LastNameEditText.getText().toString(),
                PhoneNumberEditText.getText().toString(),
                EducationEditText.getText().toString(),
                HobbiesEditText.getText().toString(),
                date))
            Toast.makeText(getApplication(), getResources().getString(R.string.update_txt) + id +getResources().getString(R.string.success_txt), Toast.LENGTH_LONG).show();
        else
            Toast.makeText(getApplication(), getResources().getString(R.string.update_txt) + id + getResources().getString(R.string.failure_txt), Toast.LENGTH_LONG).show();
        //Here we close database connection
        dbAdapter.close();
    }
}
