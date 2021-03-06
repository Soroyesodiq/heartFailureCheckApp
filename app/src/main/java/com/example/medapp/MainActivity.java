package com.example.medapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.sex_spinner) Spinner sexSpinner;
    @BindView(R.id.calculate)
    Button calculateButton;
    @BindView(R.id.sinus_checkbox)
    CheckBox sinusCheckbox;
    @BindView(R.id.arrhy_checkbox)
    CheckBox arrhyCheckbox;
    @BindView(R.id.conduction_checkbox)
    CheckBox conductionCheckbox;
    @BindView(R.id.left_checkbox)
    CheckBox leftCheckbox;
    int totalScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("MedApp");
        toolbar.setLogo(R.drawable.icons8_medical_doctor_48);

        setSupportActionBar(toolbar);

        String[] items = new String[]{"Male", "Female", "Binary"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        sexSpinner.setAdapter(adapter);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sinusCheckbox.isChecked() && !arrhyCheckbox.isChecked() && !conductionCheckbox.isChecked() && !leftCheckbox.isChecked()) {
                   //TODO Replace with a snackbar
                    Toast.makeText(MainActivity.this, "All fields can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(sinusCheckbox.isChecked()) {
                    totalScore+=2;
                }
                if(arrhyCheckbox.isChecked()) {
                    totalScore+=2;
                }
                if(conductionCheckbox.isChecked()) {
                    totalScore+=1;
                }
                if(leftCheckbox.isChecked()) {
                    totalScore+=1;
                }

                DisplayResult(totalScore);

            }
        });

    }
    //This display the heart failure percentage, summary and advise.
    //An AlertBuilder is used for the display
    private void DisplayResult(int result) {
        String summaryMessage = "";
        String resultPercentage = "";
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyleGreen);
        if(result == 0) {
            resultPercentage = "16.7%";
            summaryMessage = "Summary: The patient has a " + resultPercentage + " heart failure probability" + "\n" + "\n"
                    + "Advice: The patient is fine and does not have heart failure";
        }
        if(result == 1) {
            resultPercentage = "33.8%";
            summaryMessage = "Summary: The patient has a " + resultPercentage + " heart failure probability" + "\n" + "\n"
                    + "Advice: The patient is fine and does not have heart failure";
        }
        if(result == 2) {
            builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyleYellow);
            resultPercentage = "59.1%";
            summaryMessage = "Summary: The patient has a " + resultPercentage + " heart failure probability" + "\n" + "\n"
                    + "Advice: The patient is not fine and is advised to be refered to doctor";
        }
        if(result == 3) {
            builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyleYellow);
            resultPercentage = "73.8%";
            summaryMessage = "Summary: The patient has a " + resultPercentage + " heart failure probability" + "\n" + "\n"
                    + "Advice: The patient is not fine and is advised to be refered to doctor";
        }
        if(result == 4) {
            builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyleRed);
            resultPercentage = "95.4%";
            summaryMessage = "Summary: The patient has a " + resultPercentage + " heart failure probability" + "\n" + "\n"
                    + "Advice: The patient is not fine and has a very high percentage of heart failure";
        }
        if(result == 5 || result == 6) {
            builder = new AlertDialog.Builder(MainActivity.this, R.style.AlertDialogStyleRed);
            resultPercentage = "100%";
            summaryMessage = "Summary: The patient has a " + resultPercentage + " heart failure probability" + "\n" + "\n"
                    + "Advice: The patient is not fine and has a very high percentage of heart failure";
        }

        builder.setTitle("Heart Failure Diagnostic");
        builder.setMessage(summaryMessage);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.show();
        totalScore = 0;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_online:
                //addSomething();
                return true;
            case R.id.about_us:
                //startSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}