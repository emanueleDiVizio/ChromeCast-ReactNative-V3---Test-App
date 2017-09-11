package com.nativetes;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.nativetes.chromecast.ChromeCastButton;

/**
 * Created by Emanuele on 11/09/2017.
 */

public class ChromeCastManager extends SimpleViewManager<ChromeCastButton> {

    public static final String REACT_CLASS = "RCTChromeCastButton";
    private static final int HOOK_UP = 1;


    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ChromeCastButton createViewInstance(ThemedReactContext reactContext) {
        return new ChromeCastButton(reactContext);
    }


    @ReactProp(name = "progress", defaultInt = 0)
    public void setProgress(ChromeCastButton view, int progress) {

//      view.setProgress(progress);
    }



}
