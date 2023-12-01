package com.example.vize2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


import java.util.List;

public class Convertor extends AppCompatActivity {

    private EditText girilenDeger1,girilenDeger2,girilenDeger3;
    private TextView sonuc1,sonuc2,sonuc3;
    private Spinner spinner1,spinner2;
    private RadioGroup radioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convertor);

        girilenDeger1 = findViewById(R.id.textInput1);
        spinner1=findViewById(R.id.spinner1);
        sonuc1=findViewById(R.id.result1);

        List<String> kategori = new ArrayList<>();
        kategori.add("Seçim yapınız :");
        kategori.add("binary");
        kategori.add("octal");
        kategori.add("hexadecimal");

        ArrayAdapter<String> verilistesi1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kategori);
        verilistesi1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(verilistesi1);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = kategori.get(position);
                String girilenDegerValue1 = girilenDeger1.getText().toString();
                convertAndDisplayResult(girilenDegerValue1, selectedCategory);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        girilenDeger2=findViewById(R.id.textInput2);
        spinner2 = findViewById(R.id.spinner2);
        sonuc2=findViewById(R.id.result2);

        List<String> kategori2 = new ArrayList<>();
        kategori2.add("Seçim yapınız :");
        kategori2.add("Kilo Byte");
        kategori2.add("Byte");
        kategori2.add("Kibi Byte");
        kategori2.add("Bit");

        ArrayAdapter<String> verilistesi2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kategori2);
        verilistesi2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(verilistesi2);

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory2 = kategori2.get(position);
                String girilenDegerValue2 = girilenDeger2.getText().toString();
                convertAndDisplayResult2(girilenDegerValue2, selectedCategory2);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        girilenDeger3=findViewById(R.id.textInput3);
        sonuc3 = findViewById(R.id.result3);
        radioGroup=findViewById(R.id.radioGrup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selectedButton = findViewById(radioGroup);
                String converiontype = selectedButton.getText().toString();
                String girilenDegerValue3 = girilenDeger2.getText().toString();
                convertAndDisplayResult3(girilenDegerValue3, selectedButton);
            }
        });
    }

    private  void convertAndDisplayResult(String girilenDegerValue1, String selectedCategory) {
        if (girilenDegerValue1.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Lütfen bir sayı girin:", Toast.LENGTH_SHORT);
            return;
        }

        try {
            int decimalNumber = Integer.parseInt(girilenDegerValue1);
            String result1 = "";
            switch (selectedCategory){
                case "Binary":
                    result1 = Integer.toBinaryString(decimalNumber);
                    break;
                case "OCTAL":
                    result1=Integer.toOctalString(decimalNumber);
                    break;
                case "hexadecimal":
                    result1 = Integer.toHexString(decimalNumber);
                    break;
            }
            sonuc1.setText(result1);
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"Geçerli Bir Sayı Giriniz :",Toast.LENGTH_SHORT);
        }
    }

    private  void convertAndDisplayResult2(String girilenDegerValue2, String selectedCategory2){
        if (girilenDegerValue2.isEmpty()){
            Toast.makeText(getApplicationContext(),"Lütfen bir sayı giriniz :",Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            int decimalNumber2 = Integer.parseInt(girilenDegerValue2);
            String result2 = "";
            switch (selectedCategory2){
                case "Kilo byte" :
                    double kilobyte = decimalNumber2 * 1000;
                    result2=String.valueOf(kilobyte);
                    break;
                case "Byte" :
                    double bytevalue = decimalNumber2 * 1024 * 1024;
                    result2=String.valueOf(bytevalue);
                    break;
                case "Kibi byte" :
                    double kibiByte = decimalNumber2 * 1024 *1024 *8;
                    result2=String.valueOf(kibiByte);
                    break;
                case "Bit" :
                    double bit = decimalNumber2 * 1024;
                    result2=String.valueOf(bit);
                    break;
            }
            sonuc2.setText(result2);
        }catch (NumberFormatException e){
            Toast.makeText(getApplicationContext(),"lütfen geçerli bir sayı girin :",Toast.LENGTH_SHORT).show();
        }
    }

    private  void convertAndDisplayResult3(String girilenDegerValue3, String conversionType){
        if (girilenDegerValue3.isEmpty()){
            Toast.makeText(getApplicationContext(),"lutfen bir sıcaklık degeri girin :",Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double girilenDeger3 = Double.parseDouble(girilenDegerValue3);
            String result3="";

            if (conversionType.equals("celcius to fahrenayt")){
                double fahrenayt = (girilenDeger3*9/5)*32;
                result3=String.valueOf(fahrenayt);
            } else if (conversionType.equals("celcius to kelvin")) {
                double kelvin = girilenDeger3 * 273.15;
                result3=String.valueOf(kelvin);
            }
            sonuc3.setText(result3);
        }catch(NumberFormatException){
            Toast.makeText(getApplicationContext(),"lutfen gecerli bir sayı giriniz :",Toast.LENGTH_SHORT).show();
        }
    }
}