package com.nativetes.chromecast;

import android.content.Context;
import android.support.v7.app.MediaRouteButton;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nativetes.R;


public class ChromeCastButton extends LinearLayout {
    private MediaRouteButton mButton;
    private TextView routes;

    public ChromeCastButton(Context context) {
        super(context);
        init();
    }

    public ChromeCastButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChromeCastButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.sample_chrome_cast_button, this);
        this.mButton = (MediaRouteButton) findViewById(R.id.media_route_button);
        this.routes = (TextView) findViewById(R.id.routes);

        CastDeviceScanner.registerButton(getContext().getApplicationContext(), mButton);
        routes.setText(mButton.getRouteSelector().toString());
    }



}