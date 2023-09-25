package com.cs407.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText number1EditText, number2EditText;
    private Button addButton, subtractButton, mulitplyButton, divideButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets the values of the buttons
        number1EditText = findViewById(R.id.number1EditText);
        number2EditText = findViewById(R.id.number2EditText);
        addButton = findViewById(R.id.addButton);
        subtractButton = findViewById(R.id.subtractButton);
        mulitplyButton = findViewById(R.id.multiplyButton);
        divideButton = findViewById(R.id.divideButton);


        // Onclick listeners for the operation type wanted
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate('+');
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate('-');
            }
        });

        mulitplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate('*');
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculate('/');
            }
        });
    }

    private void calculate(char mathOp){
        String num1str = number1EditText.getText().toString();
        String num2str = number2EditText.getText().toString();

        //If there is either input missing
        if(num1str.isEmpty() || num2str.isEmpty()){
            Toast.makeText(MainActivity.this, "You are missing one or more inputs",Toast.LENGTH_LONG).show();
        }

        //Get values for the calculation
        double num1 = Double.parseDouble(num1str);
        double num2 = Double.parseDouble(num2str);
        double result = 0;

        //Calculate using the given operator --> make sure you cannot divide by 0
        switch(mathOp){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result  = num1 - num2;
                break;
            case '*':
                result  = num1*num2;
                break;
            case '/':
                if(num2 == 0){
                    Toast.makeText(MainActivity.this,
                            "You cannot divide by 0", Toast.LENGTH_LONG).show();
                    return;
                }
                result = num1/num2;
                break;
            }

        //Create intent to switch to ResultActivity Screen
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        intent.putExtra("result", result);
        startActivity(intent);
    }
}