package io.ican.hgl.share;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import io.ican.hgl.share.databinding.ActivityReceiveBinding;


public class ReceiveActivity extends AppCompatActivity {

    ShareEntity shareEntity;

    private static final String TAG = "ReceiveActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityReceiveBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_receive);
        shareEntity = new ShareEntity();
        resolveIntent();
        binding.setEntity(shareEntity);
    }

    private void resolveIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getAction().equals(Intent.ACTION_SEND)) {
                if (intent.getType().equals("text/*"))
                    setText(intent.getStringExtra(Intent.EXTRA_TEXT));
                if (intent.getType().equals("image/*"))
                    setImage(intent.getParcelableExtra(Intent.EXTRA_STREAM));
            }
            if (intent.getAction().equals(Intent.ACTION_SEND_MULTIPLE)) {
                if (intent.getType().equals("text/*"))
                    setText(intent.getStringArrayListExtra(Intent.EXTRA_TEXT));
                if (intent.getType().equals("image/*"))
                    setImage(intent.getParcelableArrayExtra(Intent.EXTRA_STREAM));
            }
        }
    }

    private void setImage(Parcelable[] parcelableArrayExtra) {
        if (parcelableArrayExtra != null) {
            ArrayList<Uri> uris = new ArrayList<>();
            for (Parcelable uri :
                    parcelableArrayExtra) {
                uris.add((Uri) uri);
            }
            shareEntity.setImageUris(uris);
        }
    }

    private void setText(ArrayList<String> stringArrayListExtra) {
        if (stringArrayListExtra != null) {
            shareEntity.setTextContents(stringArrayListExtra);
        }
    }

    private void setImage(Parcelable parcelableExtra) {
        if (parcelableExtra != null) {
            Log.i(TAG, parcelableExtra.toString());
            shareEntity.setImageUri((Uri) parcelableExtra);
        }
    }

    public void setText(String text) {
        if (text != null)
            shareEntity.setTextContent(text);
    }
}
