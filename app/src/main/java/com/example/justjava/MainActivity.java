package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
    String choco = "";


    public void submitOrder(View view){
        CheckBox estaCheckChoco = (CheckBox) findViewById(R.id.checkboxChoco);

        CheckBox estaCheckChant = (CheckBox) findViewById(R.id.checkboxChant);

        EditText name = (EditText) findViewById(R.id.name);

        if(estaCheckChant.isChecked()){
            chantily = "Com adicional de chantilly";
        }else{
            chantily = "";
        }

        if (estaCheckChoco.isChecked()){
            choco = "Com adicional de chocolate";
        }else{
            choco = "";
        }

        if(quantityExpresso == 0){
            String message = name.getText() + " pediu:\n"
                    + quantityCapuccino + " capuccino(s)\n"
                    + "O total é de R$: " + calculate2(quantityCapuccino, estaCheckChant.isChecked(), estaCheckChoco.isChecked())
                    + "\n" +  chantily + "\n" + choco;
            sendEmail(message);
        }else if(quantityCapuccino == 0){
            String message = name.getText() + " pediu:\n"
                    + quantityExpresso + " expresso(s)\n"
                    + "O total é de R$: " + calculate1(quantityExpresso, estaCheckChant.isChecked(), estaCheckChoco.isChecked())
                    + "\n" + chantily + "\n" + choco;
            sendEmail(message);
        }else{
            String message = name.getText() + " pediu:\n"
                    + quantityExpresso + " café(s) expresso(s)\n"
                    + quantityCapuccino + " capuccino(s)\n"
                    + "O total é de R$: " + calculate3(quantityExpresso, quantityCapuccino, estaCheckChant.isChecked(), estaCheckChoco.isChecked())
                    + "\n" + chantily + "\n" + choco;
            sendEmail(message);
        }
    }

    public void sendEmail(String message){
        EditText name = (EditText) findViewById(R.id.name);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pedido de café de " + name.getText() );
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    private double calculate1(double quantityEx, boolean chant, boolean choco){
        if (chant == true && choco == true){
            double total = quantityEx * 3.5 + quantityEx * 4;
            return total;
        }else if (chant == true || choco == true){
            double total = quantityEx * 3.5 + quantityEx * 2;
            return total;
        }else{
            double total = quantityEx * 3.5;
            return total;
        }

    }

    private double calculate2(double quantityCap, boolean chant, boolean choco){
        if (chant == true && choco == true){
            double total = quantityCap * 5 + quantityCap * 4;
            return total;
        }else if (chant == true || choco == true){
            double total = quantityCap * 5 + quantityCap * 2;
            return total;
        }else{
            double total = quantityCap * 5;
            return total;
        }

    }

    private double calculate3(double quantityEx, double quantityCap, boolean chant, boolean choco){
        if (chant == true && choco == true){
            double total = (((quantityEx * 3.5) + (quantityEx * 4)) + ((quantityCap * 5) + (quantityCap * 4)));
            return total;
        }else if (chant == true || choco == true){
            double total = (((quantityEx * 3.5) + (quantityEx * 2)) + ((quantityCap * 5) + (quantityCap * 2)));
            return total;
        }else {
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
    }

    private void display(int number) {
        TextView quanTextView1 = (TextView) findViewById(R.id.quantity_text_view_expresso);
        quanTextView1.setText("" + number);
    }

    private void displayCapuccino(int number) {
        TextView quanTextView2 = (TextView) findViewById(R.id.quantity_text_view_capuccino);
        quanTextView2.setText("" + number);
    }

}