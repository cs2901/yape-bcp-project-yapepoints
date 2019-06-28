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

        //TextView _id = findViewById(R.id._id);
        TextView title = findViewById(R.id.title);
        TextView description = findViewById(R.id.description);
        TextView cost = findViewById(R.id.cost);
        ImageView image = findViewById(R.id.image);
        TextView businessName = findViewById(R.id.business_name);
        TextView businessRegion = findViewById(R.id.business_region);
        TextView businessAddress = findViewById(R.id.business_address);

        Intent intent = getIntent();
        //final String c_id = intent.getStringExtra(CouponsAdapter.KEY_ID);
        final String cTitle = intent.getStringExtra(CouponsAdapter.KEY_TITLE);
        final String cDescription = intent.getStringExtra(CouponsAdapter.KEY_DESCRIPTION);
        String cCost = intent.getStringExtra(CouponsAdapter.KEY_COST);
        String cImage = intent.getStringExtra(CouponsAdapter.KEY_IMAGE);
        String bName = intent.getStringExtra(CouponsAdapter.KEY_BNAME);
        String bRegion = intent.getStringExtra(CouponsAdapter.KEY_BREGION);
        String bAddress = intent.getStringExtra(CouponsAdapter.KEY_BAD);

        //_id.setText(c_id);
        title.setText(cTitle);
        description.setText(cDescription);
        cost.setText(cCost);
        Picasso.with(this).load(cImage).into(image);
        //image.setText(cImage);
        businessName.setText(bName);
        businessRegion.setText(bRegion);
        businessAddress.setText(bAddress);



    }
}