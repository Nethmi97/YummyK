package com.example.yummyk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Conversion extends AppCompatActivity {
    EditText GramValue;
    RadioButton toOunce, toMg;
    Button convertGram;
    TextView gramConv;

    EditText MililitreValue;
    RadioButton toPint, toCup;
    Button convertMl;
    TextView mlConv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        GramValue = (EditText) findViewById(R.id.gram_value);
        toOunce = (RadioButton) findViewById(R.id.toOunce);
        toMg = (RadioButton) findViewById(R.id.toMg);
        convertGram = (Button) findViewById(R.id.convBtnGram);
        gramConv = (TextView) findViewById(R.id.gramConvDis);

        MililitreValue = (EditText) findViewById(R.id.ml_value);
        toPint = (RadioButton) findViewById(R.id.toPints);
        toCup = (RadioButton) findViewById(R.id.toCups);
        convertMl = (Button) findViewById(R.id.convBtnMl);
        mlConv = (TextView) findViewById(R.id.MlConvDis);

        convertGram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(GramValue.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),"Please enter a gram value", Toast.LENGTH_SHORT).show();
                    return;
                }
                float inputVal = Float.parseFloat(GramValue.getText().toString());

                if(toOunce.isChecked()){
                    gramConv.setText("Result in Ounce: " + String.valueOf(convertToOunce(inputVal))+ " oz");
                    toOunce.setChecked(true);
                    toMg.setChecked(false);
                }else{
                    gramConv.setText("Result in Milligram: " + String.valueOf(convertToMilligram(inputVal))+ " mg");
                    toOunce.setChecked(false);
                    toMg.setChecked(true);
                }
            }
        });

        convertMl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MililitreValue.getText().length() == 0){
                    Toast.makeText(getApplicationContext(),"Please enter a millilitre value", Toast.LENGTH_SHORT).show();
                    return;
                }
                float inputVal1 = Float.parseFloat(MililitreValue.getText().toString());

                if(toPint.isChecked()){
                    mlConv.setText("Result in Ounce: " + String.valueOf(convertToPint(inputVal1))+ " pint");
                    toPint.setChecked(true);
                    toCup.setChecked(false);
                }else{
                    mlConv.setText("Result in Cups: " + String.valueOf(convertToCups(inputVal1))+ " cups");
                    toPint.setChecked(false);
                    toCup.setChecked(true);
                }
            }
        });
    }
    public static float convertToOunce(float number){
        return (float) (number / 28.35);
    }

    public static double convertToMilligram(float number){
        return (number*1000);
    }

    public static float convertToPint(float number){
        return (float) (number / 473);
    }

    public static double convertToCups(float number){
        return (float) (number / 240);
    }
}