package com.example.peraltar.converter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;
import static java.lang.Integer.toHexString;

public class MainActivity extends AppCompatActivity {
    private EditText dec;
    private EditText bin;
    private EditText hex;
    private String input;
    private Button btnConvert;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dec = (EditText) findViewById(R.id.decimal);
        bin = (EditText) findViewById(R.id.binary);
        hex = (EditText) findViewById(R.id.hexadecimal);
        btnConvert = (Button) findViewById(R.id.convert);
        btnClear = (Button) findViewById(R.id.clear);

        dec.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    bin.setText("");
                    hex.setText("");
                    input = "dec";
                }
            }
        });

        bin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    dec.setText("");
                    hex.setText("");
                    input = "bin";
                }
            }
        });

        hex.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    bin.setText("");
                    dec.setText("");
                    input = "hex";
                }
            }
        });

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                String decStr = "" + dec.getText();
                String binStr = "" + bin.getText();
                String hexStr = "" + hex.getText();
                int k = 0;

                if (input == "dec") {
                    if (decStr.length() == 0) {
                        Toast toast = Toast.makeText(MainActivity.this, "Please enter a value.", Toast.LENGTH_LONG);
                        toast.show();
                    } else {
                        k = parseInt(decStr, 10);
                        bin.setText(toBinaryString(k));
                        hex.setText(toHexString(k));
                    }
                }

                if (input == "bin" ) {
                    if(binStr.length() == 0){
                        Toast toast = Toast.makeText(MainActivity.this, "Please enter a value.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else if(!binaryEval()){
                        Toast toast = Toast.makeText(MainActivity.this, "Please enter a binary value.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else{
                        k = parseInt("" + bin.getText(),2);
                        dec.setText("" + k);
                        hex.setText("" + toHexString(k));
                    }
                }
                if (input == "hex" ) {
                    if(hexStr.length() == 0){
                        Toast toast = Toast.makeText(MainActivity.this, "Please enter a value.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else if(!hexEval()){
                        Toast toast = Toast.makeText(MainActivity.this, "Please enter a hexadecimal value.", Toast.LENGTH_LONG);
                        toast.show();
                    }
                    else {
                        k = parseInt("" + hex.getText(),16);
                        dec.setText("" + k);
                        bin.setText("" + toBinaryString(k));
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                dec.setText("");
                bin.setText("");
                hex.setText("");
            }
        });
    }


    public boolean binaryEval(){
        boolean isBinary = false;
        int r = 0;
        int c = 0;
        int binNum = Integer.parseInt(bin.getText().toString());
        while (binNum > 0) {
            if ((binNum % 10 == 0) || (binNum % 10 == 1))
                c++;
            r++;
            binNum = binNum / 10;
        }
        if (c == r) {
            isBinary = true;
        } else
            isBinary = false;
        return isBinary;
    }

    public boolean hexEval(){
        String hexStr = hex.getText().toString();
        char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f', 'A', 'B', 'C', 'D', 'E', 'F' };
        for (char symbol : hexStr.toCharArray()) {
            boolean isHex = false;
            for (char digit : digits) {
                if (symbol == digit) {
                    isHex = true;
                    break;
                }
            }
            if(!isHex)
                return false;
        }
        return true;
    }

}