package com.sybrin.interviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.identitydocumentsdk.SybrinSmartIdentification;
import com.example.identitydocumentsdk.SybrinSmartIdentificationInterface;
import com.example.identitydocumentsdk.models.IDModel;
import com.example.identitydocumentsdk.utils.DocTypeProvider;
import com.sybrin.interviewtest.model.KeyValue;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private Button mGreenBookScanButton;
    private Button mIDCardScanButton;
    private Button mPassportScanButton;
    private ListView mIDDataListView;

    private SybrinSmartIdentification mSybrinIdScan;

    private final static String SYBRIN_LICENSE = "8+F9HvN7JNNOGf41YGJ0201juBwxwX27Zg1VUnVLCUS0gNOKK/q0g/DZQYQoFho/+6u2lswGpGYtDxRpoNoh6b8YR2pJIivdZSUncmgZLNtzMc\n" +
            "RC7B9y2Sp0UxBuSLopHUpy73DtGcFQ2BmG5gyBU4EqbxFaDpb2kz6VJn82RZDJSltVL4J0RK3UUHZLkyWxdif1jn11xVPOiiqJzdCmNZBGqVofhL\n" +
            "pE4gCXE/zyVZ/ZeJBUOh0kvlEigyFnm5gZHakF4bKVjLumRxgzMrS6kiDzkte10JFIJi3AIsG5NTAl2Pv6J6x9fzIowG9zXxPZ59UM4G6wwnn43by1D\n" +
            "FBU6IJ7S9ooUB1/pqJFt47zd2Hoa0jkwKjJHw9rYMtQJ5uU5wlOnlx0iVb/aGk6ujtNgQ==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGreenBookScanButton = findViewById(R.id.green_book_scan);
        mIDCardScanButton = findViewById(R.id.smart_id_scan);
        mPassportScanButton = findViewById(R.id.passport_scan);

        mIDDataListView = findViewById(R.id.list_view);

        initializeSybrin();

        mGreenBookScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDocument(mSybrinIdScan, DocTypeProvider.GreenBookID);
            }
        });

        mIDCardScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDocument(mSybrinIdScan, DocTypeProvider.IDCard);
            }
        });

        mPassportScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanDocument(mSybrinIdScan, DocTypeProvider.Passport);
            }
        });
    }

    private void scanDocument(SybrinSmartIdentification sybrinIdScan, String type) {
        if (sybrinIdScan == null) {
            return;
        }
        sybrinIdScan.StartSmartIdentification(SYBRIN_LICENSE, this,
                type, "South Africa");
    }

    private void initializeSybrin() {
        mSybrinIdScan =
                SybrinSmartIdentification.getInstance(MainActivity.this);
        mSybrinIdScan.setListener(new SybrinSmartIdentificationInterface() {
            @Override
            public void onDataReturned(IDModel idModel) {
                displayData(idModel);
            }
        });
    }

    private void displayData(IDModel idModel) {
        List<KeyValue> keyValues = getData(idModel);
        System.out.println(keyValues);

        updateUI(keyValues);
    }

    private void updateUI(List<KeyValue> keyValues){

    }


    private List<KeyValue> getData(IDModel idModel) {

        List<KeyValue> keyValues = new ArrayList<>();
        if(idModel == null){
            return keyValues;
        }
        KeyValue keyValue;

        if (validData(idModel.Surname)) {
            keyValue = new KeyValue("Surname", idModel.Surname);
            keyValues.add(keyValue);
        }

        if (validData(idModel.Names)) {
            keyValue = new KeyValue("Names", idModel.Names);
            keyValues.add(keyValue);
        }

        if (validData(idModel.GivenNames)) {
            keyValue = new KeyValue("GivenNames", idModel.GivenNames);
            keyValues.add(keyValue);
        }

        if (validData(idModel.Sex)) {
            keyValue = new KeyValue("Sex", idModel.Sex);
            keyValues.add(keyValue);
        }

        if (validData(idModel.Nationality)) {
            keyValue = new KeyValue("Nationality", idModel.Nationality);
            keyValues.add(keyValue);
        }

        if (validData(idModel.IdNumber)) {
            keyValue = new KeyValue("IdNumber", idModel.IdNumber);
            keyValues.add(keyValue);
        }

        if (validData(idModel.DocumentNumber)) {
            keyValue = new KeyValue("DocumentNumber", idModel.DocumentNumber);
            keyValues.add(keyValue);
        }

        if (validData(idModel.DateOfBirth)) {
            keyValue = new KeyValue("DateOfBirth", idModel.DateOfBirth);
            keyValues.add(keyValue);
        }

        if (validData(idModel.CountryOfBirth)) {
            keyValue = new KeyValue("CountryOfBirth", idModel.CountryOfBirth);
            keyValues.add(keyValue);
        }

        if (validData(idModel.ResidenceStatus)) {
            keyValue = new KeyValue("ResidenceStatus", idModel.ResidenceStatus);
            keyValues.add(keyValue);
        }

        if (validData(idModel.DateOfIssue)) {
            keyValue = new KeyValue("DateOfIssue", idModel.DateOfIssue);
            keyValues.add(keyValue);
        }

        if (validData(idModel.CardNo)) {
            keyValue = new KeyValue("CardNo", idModel.CardNo);
            keyValues.add(keyValue);
        }

        if (validData(idModel.DocType)) {
            keyValue = new KeyValue("DocType", idModel.DocType);
            keyValues.add(keyValue);
        }

        if (validData(idModel.PersonalNumber)) {
            keyValue = new KeyValue("PersonalNumber", idModel.PersonalNumber);
            keyValues.add(keyValue);
        }

        if (validData(idModel.RSACode)) {
            keyValue = new KeyValue("RSACode", idModel.RSACode);
            keyValues.add(keyValue);
        }

        return keyValues;
    }

    private boolean validData(String data) {
        return data != null && !data.isEmpty();
    }
}