package com.sybrin.interviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.identitydocumentsdk.SybrinSmartIdentification;
import com.example.identitydocumentsdk.SybrinSmartIdentificationInterface;
import com.example.identitydocumentsdk.models.IDModel;
import com.example.identitydocumentsdk.utils.DocTypeProvider;


public class MainActivity extends AppCompatActivity {

    private final static String SybrinLicense = "8+F9HvN7JNNOGf41YGJ0201juBwxwX27Zg1VUnVLCUS0gNOKK/q0g/DZQYQoFho/+6u2lswGpGYtDxRpoNoh6b8YR2pJIivdZSUncmgZLNtzMc\n" +
            "RC7B9y2Sp0UxBuSLopHUpy73DtGcFQ2BmG5gyBU4EqbxFaDpb2kz6VJn82RZDJSltVL4J0RK3UUHZLkyWxdif1jn11xVPOiiqJzdCmNZBGqVofhL\n" +
            "pE4gCXE/zyVZ/ZeJBUOh0kvlEigyFnm5gZHakF4bKVjLumRxgzMrS6kiDzkte10JFIJi3AIsG5NTAl2Pv6J6x9fzIowG9zXxPZ59UM4G6wwnn43by1D\n" +
            "FBU6IJ7S9ooUB1/pqJFt47zd2Hoa0jkwKjJHw9rYMtQJ5uU5wlOnlx0iVb/aGk6ujtNgQ==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SybrinSmartIdentification sybrinIdSCan =
                SybrinSmartIdentification.getInstance(MainActivity.this);
        sybrinIdSCan.setListener(new SybrinSmartIdentificationInterface() {
            @Override
            public void onDataReturned(IDModel idModel) {

            }
        });
        sybrinIdSCan.StartSmartIdentification(SybrinLicense, this,
                DocTypeProvider.IDCard, "South Africa");
    }
}