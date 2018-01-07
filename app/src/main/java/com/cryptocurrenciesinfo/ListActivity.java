package com.cryptocurrenciesinfo;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<CryptoCurr>> {

    private static final String REQUEST_URL = "https://api.coinmarketcap.com/v1/ticker/?limit=30";

    private static final int LOADER_ID = 1;

    private CryptoCurrAdapter adapter;

    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView = (ListView) findViewById(R.id.list);
        emptyTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(emptyTextView);

        adapter = new CryptoCurrAdapter(this, new ArrayList<CryptoCurr>());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CryptoCurr cryptoCurr = adapter.getItem(position);
                Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
                intent.putExtra("KEY", cryptoCurr);
                startActivity(intent);
            }
        });
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_progress_bar);
            loadingIndicator.setVisibility(View.GONE);
            emptyTextView.setText(R.string.no_internet);
        }
    }

    @Override
    public Loader<List<CryptoCurr>> onCreateLoader(int id, Bundle args) {
        Uri uri = Uri.parse(REQUEST_URL);
        return new CryptoCurrLoader(this, uri.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<CryptoCurr>> loader, List<CryptoCurr> data) {
        View view = findViewById(R.id.loading_progress_bar);
        view.setVisibility(View.GONE);
        emptyTextView.setText(R.string.no_currencies);
        adapter.clear();
        if(data != null && !data.isEmpty()) {
            adapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<CryptoCurr>> loader) {
        adapter.clear();
    }
}
