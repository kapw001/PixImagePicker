package com.fxn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.request.RequestOptions;
import com.fxn.pixsample.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final RequestOptions options;
    private final RequestManager glide;
    private ArrayList<String> list = new ArrayList<>();
    private Context context;

    public MyAdapter(Context context) {
        this.context = context;
        options = new RequestOptions().transform(new CenterCrop()).transform(new FitCenter());
        glide = Glide.with(context);
    }

    public void addImage(ArrayList<String> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.image, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //Uri imageUri = Uri.fromFile(new File(list.get(position)));// For files on device
        //Log.e("hello", "- " + imageUri.toString());
        //  File f = new File(list.get(position));
        glide.load(list.get(position)).apply(options).into(((Holder) holder).iv);
       /* Bitmap d = new BitmapDrawable(context.getResources(), f.getAbsolutePath()).getBitmap();
        //Bitmap scaled = com.fxn.utility.Utility.getScaledBitmap(512, com.fxn.utility.Utility.getExifCorrectedBitmap(f));
        Bitmap scaled = com.fxn.utility.Utility.getScaledBitmap(512, d);
        ((Holder) holder).iv.setImageBitmap(scaled);*/
        // ((Holder) holder).iv.setImageURI(imageUri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public ImageView iv;


        public Holder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }
}
