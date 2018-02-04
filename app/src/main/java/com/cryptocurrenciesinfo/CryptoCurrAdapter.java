package com.cryptocurrenciesinfo;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CryptoCurrAdapter extends ArrayAdapter<CryptoCurr> implements Filterable{

    private ArrayList<CryptoCurr> entryList;
    private ArrayList<CryptoCurr> filterList;
    private ListActivity listActivity;
    private CCFilter ccFilter;

    public CryptoCurrAdapter(Context context, List<CryptoCurr> list, ListActivity listActivity) {
        super(context, 0, list);
        entryList = (ArrayList<CryptoCurr>) list;
        filterList = (ArrayList<CryptoCurr>) list;
        this.listActivity = listActivity;
    }

    @Override
    public int getCount() {
        return filterList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public CryptoCurr getItem(int position) {
        return filterList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /* View listItemVIew = convertView;

        CryptoCurr curr = getItem(position);
        if (listItemVIew == null) {
            listItemVIew = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

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


        return listItemVIew; */

        final CryptoCurr cryptoCurr = (CryptoCurr) getItem(position);

        if(convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) listActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);

            TextView nameView = (TextView) convertView.findViewById(R.id.name);
            nameView.setText(cryptoCurr.getName());

            TextView symbolView = (TextView) convertView.findViewById(R.id.symbol);
            symbolView.setText(cryptoCurr.getSymbol());

            TextView valueView = (TextView) convertView.findViewById(R.id.value);
            valueView.setText("$ " + Double.toString(cryptoCurr.getPrice()));

            TextView percentView = (TextView) convertView.findViewById(R.id.percent);
            if(cryptoCurr.getPc24() > 0) {
                percentView.setTextColor(ContextCompat.getColor(getContext(), R.color.asc_clr));
                percentView.setText("+" + Double.toString(cryptoCurr.getPc24()) + "%");
            } else {
                percentView.setTextColor(ContextCompat.getColor(getContext(), R.color.desc_clr));
                percentView.setText(Double.toString(cryptoCurr.getPc24()) + "%");
            }

        }
        return convertView;
    }

    @Override
    public Filter getFilter() {
        if(ccFilter == null) {
            ccFilter = new CCFilter();
        }
        return ccFilter;
    }

    private class CCFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();
            if(constraint != null && constraint.length()>0) {
                ArrayList<CryptoCurr> temp = new ArrayList<CryptoCurr>();
                for(CryptoCurr cc : entryList) {
                    if(cc.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            temp.add(cc);
                    }
                }
                filterResults.count = temp.size();
                filterResults.values = temp;
            } else {
                filterResults.count = entryList.size();
                filterResults.values = entryList;
            }
            return filterResults;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                filterList = (ArrayList<CryptoCurr>) results.values;
                notifyDataSetChanged();
        }
    }

}
