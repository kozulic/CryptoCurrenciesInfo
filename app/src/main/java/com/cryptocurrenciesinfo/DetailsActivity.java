package com.cryptocurrenciesinfo;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    TextView nameTextView, valueTextView, marketCapTextView, asTextView, msTextView, volumeTextView,
        perc1TextView, perc24TextView, perc7TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setTitle(R.string.title);

        Intent intent = getIntent();
        CryptoCurr cryptoCurr = (CryptoCurr) intent.getExtras().getSerializable("KEY");

        nameTextView = (TextView) findViewById(R.id.c_name);
        nameTextView.setText(cryptoCurr.getName());

        valueTextView = (TextView) findViewById(R.id.c_value);
        valueTextView.setText("$ " + Double.toString(cryptoCurr.getPrice()));

        marketCapTextView = (TextView) findViewById(R.id.c_market_cap);
        marketCapTextView.setText(Long.toString(cryptoCurr.getMarketCap()));

        asTextView = (TextView) findViewById(R.id.c_av_supply);
        asTextView.setText(String.format("%.0f", cryptoCurr.getAvSupply()));

        msTextView = (TextView) findViewById(R.id.c_max_supply);
        msTextView.setText(String.format("%.0f", cryptoCurr.getTotSupply()));

        volumeTextView = (TextView) findViewById(R.id.c_volume);
        volumeTextView.setText(Long.toString(cryptoCurr.getVolume()));

        perc1TextView = (TextView) findViewById(R.id.c_perc_1h);
        if(cryptoCurr.getPc1() > 0) {
            perc1TextView.setText("+" + Double.toString(cryptoCurr.getPc1()) + "%");
            //perc1TextView.setTextColor(ContextCompat.getColor(getContext(), R.color.asc_clr));
        } else {
            perc1TextView.setText(Double.toString(cryptoCurr.getPc1()) + "%");
        }

        perc24TextView = (TextView) findViewById(R.id.c_perc_24h);
        if(cryptoCurr.getPc24() > 0) {
            perc24TextView.setText("+" + Double.toString(cryptoCurr.getPc24()) + "%");
        } else {
            perc24TextView.setText(Double.toString(cryptoCurr.getPc24()) + "%");
        }

        perc7TextView = (TextView) findViewById(R.id.c_perc_7d);
        if(cryptoCurr.getPcw() > 0) {
            perc7TextView.setText("+" + Double.toString(cryptoCurr.getPcw()) + "%");
        } else {
            perc7TextView.setText(Double.toString(cryptoCurr.getPcw()) + "%");
        }

    }
}
