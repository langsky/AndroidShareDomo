package io.ican.hgl.share;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import io.ican.hgl.share.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setHandler(handler);
    }


    public ClickHandler handler = new ClickHandler() {
        @Override
        public void onClick(View view) {
            if (view.getTag().equals("shareText"))
                shareText();
            if (view.getTag().equals("shareEmail"))
                shareEmail();
            if (view.getTag().equals("shareBinary"))
                shareBinary();
            if (view.getTag().equals("shareMultiple"))
                shareMultiData();
        }
    };


    //share plain text
    public void shareText() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, "This is my text to share");
        i.setType("text/plain");
        startActivity(Intent.createChooser(i, "This is a Chooser title"));
    }

    public void shareEmail() {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_EMAIL, "langsky001@hotmail.com");
        i.setType("text/email");
        startActivity(Intent.createChooser(i, "This is a Chooser title"));
    }

    //share binary data
    public void shareBinary() {
        Uri uri = Uri.parse("android.resource://" + getApplication().getPackageName() + "/" + R.mipmap.ic_launcher);
        Intent i = new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_STREAM, uri);
        i.setType("image/png");
        startActivity(Intent.createChooser(i, "This is a Chooser title"));
    }

    //share multiple data
    public void shareMultiData() {
        Uri uri = Uri.parse("android.resource://" + getApplication().getPackageName() + "/" + R.mipmap.ic_launcher);
        Uri uri1 = Uri.parse("android.resource://" + getApplication().getPackageName() + "/" + R.drawable.ic_accessible);
        ArrayList<Uri> contents = new ArrayList<>();
        contents.add(uri);
        contents.add(uri1);
        Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
        i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, contents);
        i.setType("image/*");
        startActivity(Intent.createChooser(i, "This is a Chooser title"));
    }
}
