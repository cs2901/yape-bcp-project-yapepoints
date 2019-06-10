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

import org.w3c.dom.Text;

import java.util.List;

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder> {

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_BNAME = "bname";

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
        //holder._id.setText(couponsList.get_id());
        holder.description.setText(couponsList.getDescription());
        holder.title.setText(couponsList.getTitle());
        Picasso.with(context).load(couponsList.getImage_path()).into(holder.image_path);
        holder.business_name.setText(couponsList.getBusiness_name());



        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CouponsList couponsList1 = couponsLists.get(position);
                Intent skipIntent = new Intent(v.getContext(), ProfileActivity.class);
                //skipIntent.putExtra(KEY_ID, couponsList1.get_id());
                skipIntent.putExtra(KEY_DESCRIPTION, couponsList1.getDescription());
                skipIntent.putExtra(KEY_TITLE, couponsList1.getTitle());
                skipIntent.putExtra(KEY_IMAGE, couponsList1.getImage_path());
                skipIntent.putExtra(KEY_BNAME, couponsList1.getBusiness_name());
                v.getContext().startActivity(skipIntent);
            }
        });

    }

    @Override
    public int getItemCount() { return couponsLists.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //public TextView _id;
        public TextView description;
        public TextView title;
        public ImageView image_path;
        public TextView business_name;
        public LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            //_id = (TextView) itemView.findViewById(R.id._id);
            description = (TextView) itemView.findViewById(R.id.description);
            title = (TextView) itemView.findViewById(R.id.title);
            image_path = (ImageView) itemView.findViewById(R.id.image);
            business_name = (TextView) itemView.findViewById(R.id.business_name);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }

}
