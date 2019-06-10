package com.example.yapecupones;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder> {

    public static final String KEY_NAME = "name";
    public static final String KEY_BDESCRIPTION = "bdescription";
    public static final String KEY_MAP = "map";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";

    private List<CouponsList> couponsLists;
    private Context context;

    public CouponsAdapter(List<CouponsList> couponsLists, Context context) {
        this.couponsLists = couponsLists;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coupons_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final CouponsList couponsList = couponsLists.get(position);
        holder.business_name.setText(couponsList.getBusiness_name());
        holder.business_description.setText(couponsList.getBusiness_description());
        holder.map_url.setText(couponsList.getBusiness_map_url());
        holder.title.setText(couponsList.getTitle());
        holder.description.setText(couponsList.getDescription());

        Picasso.with(context).load(couponsList.getImage_path()).into(holder.image_path);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CouponsList couponsList1 = couponsLists.get(position);
                Intent skipIntent = new Intent(v.getContext(), ProfileActivity.class);
                skipIntent.putExtra(KEY_NAME, couponsList1.getBusiness_name());
                skipIntent.putExtra(KEY_BDESCRIPTION, couponsList1.getBusiness_description());
                skipIntent.putExtra(KEY_MAP, couponsList1.getBusiness_map_url());
                skipIntent.putExtra(KEY_TITLE, couponsList1.getTitle());
                skipIntent.putExtra(KEY_DESCRIPTION, couponsList1.getDescription());
                skipIntent.putExtra(KEY_IMAGE, couponsList1.getImage_path());
                v.getContext().startActivity(skipIntent);
            }
        });

    }

    @Override
    public int getItemCount() { return couponsLists.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView business_name;
        public TextView business_description;
        public TextView map_url;
        public TextView title;
        public TextView description;
        public ImageView image_path;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            business_name = (TextView) itemView.findViewById(R.id.business_name);
            business_description = (TextView) itemView.findViewById(R.id.business_description);
            map_url = (TextView) itemView.findViewById(R.id.map_url);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            image_path = (ImageView) itemView.findViewById(R.id.image);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }

}
