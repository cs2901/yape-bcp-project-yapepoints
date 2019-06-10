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

        TextView businessName = (TextView) findViewById(R.id.business_name);
        TextView title = (TextView) findViewById(R.id.title);
        ImageView image = (ImageView) findViewById(R.id.image);
        TextView description = (TextView) findViewById(R.id.description);
        TextView mapUrl = (TextView) findViewById(R.id.map_url);

        Intent intent = getIntent();
        final String bName = intent.getStringExtra(CouponsAdapter.KEY_NAME);
        final String cTitle = intent.getStringExtra(CouponsAdapter.KEY_TITLE);
        String cImage = intent.getStringExtra(CouponsAdapter.KEY_IMAGE);
        final String cDescription = intent.getStringExtra(CouponsAdapter.KEY_DESCRIPTION);
        final String cMapUrl = intent.getStringExtra(CouponsAdapter.KEY_MAP);


        businessName.setText(bName);
        title.setText(cTitle);
        Picasso.with(this).load(cImage).into(image);
        description.setText(cDescription);
        mapUrl.setText(cMapUrl);

        mapUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = cMapUrl;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });





    }
}
