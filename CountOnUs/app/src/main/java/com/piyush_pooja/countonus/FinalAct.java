package com.piyush_pooja.countonus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FinalAct extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button msendData;
    private EditText mName;
    private EditText mNum;

    String Name1,Numb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        msendData = (Button) findViewById(R.id.addData);
        mName =  (EditText) findViewById(R.id.name);
        mNum =   (EditText) findViewById(R.id.phone);

        Name1= mName.getText().toString().trim();
        Numb1 = mNum.getText().toString().trim();

        msendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Details details  = new Details();
                details.setName(Name1);
                details.setNumber(Numb1);

                String User_id = mDatabase.push().getKey();
                mDatabase.child("user").push().setValue(details);
                Toast.makeText(FinalAct.this,"Data Inserted",Toast.LENGTH_LONG);
            }
        });
    }
}
