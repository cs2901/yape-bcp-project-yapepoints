package com.example.yapecupones;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder> {

    //public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_COST = "cost";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_BNAME = "bname";
    public static final String KEY_BREGION = "bregion";
    public static final String KEY_BAD = "baddress";

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
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final CouponsList couponsList = couponsLists.get(position);
        //holder._id.setText(couponsList.get_id());        final CouponsList couponsList = couponsLists.get(position);
        //holder._id.setText(couponsList.get_id());
        holder.title.setText(couponsList.getTitle());
        holder.description.setText(couponsList.getDescription());
        holder.cost.setText(couponsList.getCost());
        Picasso.with(context).load(couponsList.getImage_path()).into(holder.image_path);
        //holder.image_path.setText(couponsList.getImage_path());
        holder.business_name.setText(couponsList.getBusiness_name());
        holder.business_region.setText(couponsList.getBusiness_region());
        holder.business_address.setText(couponsList.getBusiness_address());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CouponsList couponsList1 = couponsLists.get(position);
                Intent skipIntent = new Intent(v.getContext(), ProfileActivity.class);
                //skipIntent.putExtra(KEY_ID, couponsList1.get_id());
                skipIntent.putExtra(KEY_TITLE, couponsList1.getTitle());
                skipIntent.putExtra(KEY_DESCRIPTION, couponsList1.getDescription());
                skipIntent.putExtra(KEY_COST, couponsList1.getCost());
                skipIntent.putExtra(KEY_IMAGE, couponsList1.getImage_path());
                skipIntent.putExtra(KEY_BNAME, couponsList1.getBusiness_name());
                skipIntent.putExtra(KEY_BREGION,couponsList1.getBusiness_region());
                skipIntent.putExtra(KEY_BAD, couponsList1.getBusiness_address());
                v.getContext().startActivity(skipIntent);
            }
        });

        holder.title.setText(couponsList.getTitle());
        holder.description.setText(couponsList.getDescription());
        holder.cost.setText(couponsList.getCost());
        Picasso.with(context).load(couponsList.getImage_path()).into(holder.image_path);
        //holder.image_path.setText(couponsList.getImage_path());
        holder.business_name.setText(couponsList.getBusiness_name());
        holder.business_region.setText(couponsList.getBusiness_region());
        holder.business_address.setText(couponsList.getBusiness_address());



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CouponsList couponsList1 = couponsLists.get(position);
                Intent skipIntent = new Intent(v.getContext(), ProfileActivity.class);
                //skipIntent.putExtra(KEY_ID, couponsList1.get_id());
                skipIntent.putExtra(KEY_TITLE, couponsList1.getTitle());
                skipIntent.putExtra(KEY_DESCRIPTION, couponsList1.getDescription());
                skipIntent.putExtra(KEY_COST, couponsList1.getCost());
                skipIntent.putExtra(KEY_IMAGE, couponsList1.getImage_path());
                skipIntent.putExtra(KEY_BNAME, couponsList1.getBusiness_name());
                skipIntent.putExtra(KEY_BREGION, couponsList1.getBusiness_region());
                skipIntent.putExtra(KEY_BAD, couponsList1.getBusiness_address());
                v.getContext().startActivity(skipIntent);
            }
        });

    }

    @Override
    public int getItemCount() { return couponsLists.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //public TextView _id;
        public TextView title;
        public TextView description;
        public TextView cost;
        public ImageView image_path;
        public TextView business_name;
        public TextView business_region;
        public TextView business_address;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            //_id = (TextView) itemView.findViewById(R.id._id);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            cost = (TextView) itemView.findViewById(R.id.cost);
            image_path = (ImageView) itemView.findViewById(R.id.image);
            business_name = (TextView) itemView.findViewById(R.id.business_name);
            business_region = (TextView) itemView.findViewById(R.id.business_region);
            business_address = (TextView) itemView.findViewById(R.id.business_address);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }

}