package com.cryptocurrenciesinfo;


import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class CryptoCurrLoader extends AsyncTaskLoader<List<CryptoCurr>>{

    private String mUrl;

    public CryptoCurrLoader(Context context,String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<CryptoCurr> loadInBackground() {
        if(mUrl == null) {
            return null;
        }
        List<CryptoCurr> cryptoCurrs = QueryUtils.fetchData(mUrl);
        return cryptoCurrs;
    }
}
