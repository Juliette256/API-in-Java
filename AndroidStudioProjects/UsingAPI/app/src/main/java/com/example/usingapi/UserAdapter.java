package com.example.usingapi;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {
    private static final String TAG = "UserAdapter";
    Activity context;
    ArrayList<User> arrayList;

    public UserAdapter(Activity context1, ArrayList<User>arrayList1){
        this.context=context1;
        this.arrayList=arrayList1; }

    Bitmap bitmap=null;
    ImageView imageView;

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int i) {
            return i;    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView ==  null) {
            convertView=LayoutInflater.from(context).inflate(R.layout.row, parent,false);
        }


        final TextView id = convertView.findViewById(R.id.id);
        final TextView title = convertView.findViewById(R.id.title);
        final TextView body = convertView.findViewById(R.id.body);
        imageView = convertView.findViewById(R.id.imageViewer);


        int ifd=arrayList.get(position).getId();
        String f_id= String.valueOf(ifd);
        String f_title=arrayList.get(position).getTitle();
        String f_body=arrayList.get(position).getBody();
        String img=arrayList.get(position).getUri();

        id.setText(f_id);
        title.setText(f_title);
        body.setText(f_body);
        Log.d(TAG, "getView: image"+img);
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);


            Glide.with(context).load(img).apply(options).into(imageView);

            

        return convertView;    }

}
