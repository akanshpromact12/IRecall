package com.promact.akansh.irecall;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Akansh on 28-06-2017.
 */

public class SecondActivity extends AppCompatActivity
{
    private TextView Username;
    private TextView Email;
    private CircleImageView circleImageView;
    private Intent intent;
    private String idToken;
    private String email_intent;
    private String uname;
    private String photoUri;
    private final String TAG = "Second Activity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);

        intent = getIntent();
        Username = (TextView) findViewById(R.id.txtUname);
        Email = (TextView) findViewById(R.id.txtEmail);
        circleImageView = (CircleImageView) findViewById(R.id.circleprofile);

        idToken = intent.getStringExtra("idToken");
        uname = intent.getStringExtra("name");
        email_intent = intent.getStringExtra("email");
        photoUri = intent.getStringExtra("photoUri");

        /*Glide.with(this)
                .load(photoUri)
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .override(200,200)
                .centerCrop()
                .into(circleImageView);*/
        Glide.with(getApplicationContext()).load(photoUri).into(circleImageView);

        Username.setText(uname);
        Email.setText(email_intent);
    }
}
