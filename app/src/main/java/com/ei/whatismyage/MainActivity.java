package com.ei.whatismyage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText birthyear;
    TextView age;
    SharedPreferences sharedPreferences;
    int storedAgeint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        birthyear = findViewById(R.id.birthyear);
        age = findViewById(R.id.age);

        sharedPreferences = this.getSharedPreferences("com.ei.whatismyage", Context.MODE_PRIVATE);

        storedAgeint = sharedPreferences.getInt("storedAge",0);
        if(storedAgeint==0) {
            age.setText("Your age: ");
        }
        else {
            age.setText("Your age: "+storedAgeint);
        }
    }

    public void save(View view) {
        int by = Integer.parseInt(birthyear.getText().toString());

        int ageint = 2021-by;

        age.setText("Your age: " + ageint);

        sharedPreferences.edit().putInt("storedAge",ageint).apply();
    }

    public void delete(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        alert.setTitle("Delete");
        alert.setMessage("Are you sure you want to delete your age data?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(storedAgeint != 0) {
                    sharedPreferences.edit().remove("storedAge").apply();
                    age.setText("Your age: ");
                }

                Toast.makeText(MainActivity.this,"Deleted",Toast.LENGTH_LONG).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"Canceled",Toast.LENGTH_LONG).show();
            }
        });
        alert.show();
    }
}