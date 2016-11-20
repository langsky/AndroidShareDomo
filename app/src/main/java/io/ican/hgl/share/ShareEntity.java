package io.ican.hgl.share;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by swd1 on 16-11-20.
 */

public class ShareEntity {

    private String textContent;

    private Uri imageUri;

    private ArrayList<String> textContents;

    private ArrayList<Uri> imageUris;


    public ArrayList<Uri> getImageUris() {
        return imageUris;
    }

    public void setImageUris(ArrayList<Uri> imageUris) {
        this.imageUris = imageUris;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public ArrayList<String> getTextContents() {
        return textContents;
    }

    public void setTextContents(ArrayList<String> textContents) {
        this.textContents = textContents;
    }

    @BindingAdapter({"bind:uri"})
    public static void convertUri(ImageView view, Uri uri) {
        try {
            InputStream stream = view.getContext().getContentResolver().openInputStream(uri);
            Drawable drawable = Drawable.createFromStream(stream, uri.toString());
            view.setImageDrawable(drawable);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
