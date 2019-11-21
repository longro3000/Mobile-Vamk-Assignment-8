package com.example.assignment_8;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ErrorActivity extends AppCompatActivity {

    TextView errorTextView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        errorTextView = findViewById(R.id.error);
        Button backButton = findViewById(R.id.btn_back);
        backButton.setOnClickListener(buttonClickListener);
        String error = "";
// Here we access the incoming Intent object
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            error = extras.getString("error");
// Here we set the hint value of the edit text to the data
// sent by calling activity
        errorTextView.append(" " + error);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//Here we initialize the content of the text view
            finish();
        }
    };
}
