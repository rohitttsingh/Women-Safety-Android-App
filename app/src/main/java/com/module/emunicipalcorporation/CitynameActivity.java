package com.module.emunicipalcorporation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CitynameActivity extends AppCompatActivity {
private Button submit;
private EditText cityname;
TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cityname);
        cityname = findViewById(R.id.cityname);
        submit=findViewById(R.id.submit);
        title=findViewById(R.id.title);

        cityname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              title.setText("City: "+charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CitynameActivity.this, ""+cityname.getText(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CitynameActivity.this,DashboardActivity.class));
            }
        });
        //
    }
}