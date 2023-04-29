package com.test.testdeeplink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();

        if (action.equals(Intent.ACTION_VIEW) && data != null) {
            String path = data.getPath();
            if (path.equals("/action/view")) {
                // Perform the appropriate action in your app
            }
        }
    }
}