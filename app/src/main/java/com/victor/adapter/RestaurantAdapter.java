package com.victor.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.victor.model.Restaurant;
import com.victor.sotaynhahang.R;

import java.util.List;

/**
 * Created by Victor on 05/07/2017.
 */

public class RestaurantAdapter extends ArrayAdapter<Restaurant> {

    Activity context;
    int resource;
    List objects;

    public RestaurantAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View item = inflater.inflate(this.resource,null);

        Restaurant res = (Restaurant) this.objects.get(position);
        ImageView picImgV = (ImageView) item.findViewById(R.id.pic_ImageView);
        TextView nameTV = (TextView) item.findViewById(R.id.resName_TextView);

        picImgV.setImageBitmap(res.getPicture());
        nameTV.setText(res.getName());

        return item;
    }
}
