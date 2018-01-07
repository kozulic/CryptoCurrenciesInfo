package com.cryptocurrenciesinfo;

import android.content.Context;

import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CryptoCurrAdapter extends ArrayAdapter<CryptoCurr>{

    public CryptoCurrAdapter(Context context, List<CryptoCurr> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemVIew = convertView;
        if (listItemVIew == null) {
            listItemVIew = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        CryptoCurr curr = getItem(position);

        TextView nameView = (TextView) listItemVIew.findViewById(R.id.name);
        nameView.setText(curr.getName());

        TextView symbolView = (TextView) listItemVIew.findViewById(R.id.symbol);
        symbolView.setText(curr.getSymbol());

        TextView valueView = (TextView) listItemVIew.findViewById(R.id.value);
        valueView.setText("$ " + Double.toString(curr.getPrice()));

        TextView percentView = (TextView) listItemVIew.findViewById(R.id.percent);
        if(curr.getPc24() > 0) {
            percentView.setTextColor(ContextCompat.getColor(getContext(), R.color.asc_clr));
            percentView.setText("+" + Double.toString(curr.getPc24()) + "%");
        } else {
            percentView.setTextColor(ContextCompat.getColor(getContext(), R.color.desc_clr));
            percentView.setText(Double.toString(curr.getPc24()) + "%");
        }


        return listItemVIew;
    }
}
