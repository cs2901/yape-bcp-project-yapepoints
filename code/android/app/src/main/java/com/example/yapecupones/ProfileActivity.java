package com.example.yapecupones;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //TextView _id = (TextView) findViewById(R.id._id);
        TextView title = (TextView) findViewById(R.id.title);
        TextView description = (TextView) findViewById(R.id.description);
        TextView image = (TextView) findViewById(R.id.image);
        TextView businessName = (TextView) findViewById(R.id.business_name);

        Intent intent = getIntent();
        //final String c_id = intent.getStringExtra(CouponsAdapter.KEY_ID);
        final String cTitle = intent.getStringExtra(CouponsAdapter.KEY_TITLE);
        final String cDescription = intent.getStringExtra(CouponsAdapter.KEY_DESCRIPTION);
        String cImage = intent.getStringExtra(CouponsAdapter.KEY_IMAGE);
        String bName = intent.getStringExtra(CouponsAdapter.KEY_BNAME);

        //_id.setText(c_id);
        title.setText(cTitle);
        description.setText(cDescription);
        //Picasso.with(this).load(cImage).into(image);
        image.setText(cImage);
        businessName.setText(bName);



    }
}
