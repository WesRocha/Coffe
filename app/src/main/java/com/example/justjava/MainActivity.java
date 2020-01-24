package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import java.text.NumberFormat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantityExpresso = 0;
    int quantityCapuccino = 0;
    String chantily = "";

    public void submitOrder(View view){

        CheckBox estaCheck = (CheckBox) findViewById(R.id.checkboxChant);
        if(estaCheck.isChecked()){
            chantily = "Com adicional de chantilly";
        }else{
            chantily = "";
        }
        if(quantityExpresso == 0){
            String message = "Você pediu:\n"
                    + quantityCapuccino + " capuccino(s)\n"
                    + "O total é de R$: " + calculate2(quantityCapuccino, estaCheck.isChecked()) + "\n" +  chantily;
            displayMessage(message);
        }else if(quantityCapuccino == 0){
            String message = "Você pediu:\n"
                    + quantityExpresso + " expresso(s)\n"
                    + "O total é de R$: " + calculate1(quantityExpresso, estaCheck.isChecked()) + "\n" + chantily;
            displayMessage(message);
        }else{
            String message = "Você pediu:\n"
                    + quantityExpresso + " café(s) expresso(s)\n"
                    + quantityCapuccino + " capuccino(s)\n"
                    + "O total é de R$: " + calculate3(quantityExpresso, quantityCapuccino, estaCheck.isChecked()) + "\n" + chantily;
            displayMessage(message);
        }

    }

    private double calculate1(double quantityEx, boolean chant){
        if (chant == true){
            double total = quantityEx * 3.5 + 2;
            return total;
        }else{
            double total = quantityEx * 3.5;
            return total;
        }

    }

    private double calculate2(double quantityCap, boolean chant){
        if (chant == true){
            double total = quantityCap * 5 + 2;
            return total;
        }else{
            double total = quantityCap * 5;
            return total;
        }

    }

    private double calculate3(double quantityEx, double quantityCap, boolean chant){
        if (chant == true){
            double total = ((quantityEx * 3.5) + (quantityCap * 5)) + 2;
            return total;
        }else{
            double total = ((quantityEx * 3.5) + (quantityCap * 5));
            return total;
        }
    }

   public void subtrairExpresso(View view){
        quantityExpresso -= 1;
        if (quantityExpresso < 0){
            quantityExpresso = 0;
            display(quantityExpresso);
        }
        display(quantityExpresso);

   }

    public void somarExpresso(View view){
        quantityExpresso += 1;
        display(quantityExpresso);
    }

    public void subtrairCapuccino(View view){
        quantityCapuccino -= 1;
        if (quantityCapuccino < 0){
            quantityCapuccino = 0;
            displayCapuccino(quantityCapuccino);
        }
        displayCapuccino(quantityCapuccino);

    }

    public void somarCapuccino(View view){
        quantityCapuccino += 1;
        displayCapuccino(quantityCapuccino);
    }

    public void reset(View view){
        quantityExpresso = 0;
        quantityCapuccino = 0;
        display(quantityExpresso);
        displayCapuccino(quantityCapuccino);
        displayMessage("");
    }

    private void display(int number) {
        TextView quanTextView1 = (TextView) findViewById(R.id.quantity_text_view_expresso);
        quanTextView1.setText("" + number);
    }

    private void displayCapuccino(int number) {
        TextView quanTextView2 = (TextView) findViewById(R.id.quantity_text_view_capuccino);
        quanTextView2.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}