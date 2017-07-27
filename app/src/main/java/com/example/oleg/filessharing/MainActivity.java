package com.example.oleg.filessharing;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;

import static android.support.v4.content.FileProvider.getUriForFile;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String filename = "file.txt";
        String string = "Hello world!";
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        File newFile = new File(getFilesDir(), "file.txt");
        Uri contentUri = getUriForFile(this, "com.example.oleg.filessharing", newFile);

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent .setType("vnd.android.cursor.dir/email");
        emailIntent .putExtra(Intent.EXTRA_EMAIL, "kasoleg1@gmail.com");
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        startActivity(emailIntent);
    }
}
