package com.devazt.carrito;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nekszer on 14/11/2015.
 */
public class ShopItemAdapter extends ArrayAdapter<ShopItem> {

    public ShopItemAdapter(Context context, ArrayList<ShopItem> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        ShopItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.shop_item, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvArticleName);
        TextView tvHome = (TextView) convertView.findViewById(R.id.tvCost);
        // Populate the data into the template view using the data object
        tvName.setText(item.getCode_bar());
        tvHome.setText(item.getPrice());
        // Return the completed view to render on screen
        return convertView;
    }
}
