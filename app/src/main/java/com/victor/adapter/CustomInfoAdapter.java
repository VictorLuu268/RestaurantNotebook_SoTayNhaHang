package com.victor.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.victor.model.Restaurant;
import com.victor.sotaynhahang.R;

/**
 * Created by Victor on 05/07/2017.
 */

public class CustomInfoAdapter implements GoogleMap.InfoWindowAdapter {

    Activity context;
    Restaurant restaurant;

    public CustomInfoAdapter(Activity context, Restaurant restaurant){
        this.context = context;
        this.restaurant = restaurant;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(R.layout.infowindow_view,null);
        ImageView picResImgV = (ImageView) row.findViewById(R.id.picRes_ImageView);
        TextView restauNameTV = (TextView) row.findViewById(R.id.restauName_TextView);

        picResImgV.setImageBitmap(restaurant.getPicture());
        restauNameTV.setText(restaurant.getName());

        return row;
    }
}
