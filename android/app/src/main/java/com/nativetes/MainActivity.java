package com.nativetes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;

import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.nativetes.chromecast.CastManager;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler {
  private ReactRootView mReactRootView;
  private ReactInstanceManager mReactInstanceManager;
  private CastManager castManager = new CastManager();

  protected String getMainComponentName() {
    return "NativeTEs";
  }


  @Override

  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    return castManager.onCreateOptionsMenu(this, menu);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    castManager.onCreate(this);


    mReactRootView = new ReactRootView(this);
    mReactInstanceManager = ReactInstanceManager.builder()
          .setApplication(getApplication())
          .setBundleAssetName("index.android.bundle")
          .setJSMainModuleName("index.android")
          .addPackage(new MainReactPackage())
          .setUseDeveloperSupport(BuildConfig.DEBUG)
          .setInitialLifecycleState(LifecycleState.RESUMED)
          .build();

    mReactInstanceManager.registerAdditionalPackages(Arrays.<ReactPackage>asList(
          new ChromeCastPackage()
    ));
    mReactRootView.startReactApplication(mReactInstanceManager, getMainComponentName(), null);

    setContentView(mReactRootView);
  }

  @Override
  public void invokeDefaultOnBackPressed() {
    super.onBackPressed();
  }

  @Override
  protected void onPause() {
    super.onPause();
    castManager.onPause();
    if (mReactInstanceManager != null) {
      mReactInstanceManager.onHostPause(this);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    castManager.onResume(this);
    if (mReactInstanceManager != null) {
      mReactInstanceManager.onHostResume(this, this);
    }
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (mReactInstanceManager != null) {
      mReactInstanceManager.onHostDestroy();
    }
  }

  @Override
  public void onBackPressed() {
    if (mReactInstanceManager != null) {
      mReactInstanceManager.onBackPressed();
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
      mReactInstanceManager.showDevOptionsDialog();
      return true;
    }
    return super.onKeyUp(keyCode, event);
  }


  @Override
  public boolean dispatchKeyEvent(KeyEvent event) {
    return castManager.dispatchKeyEvent(event);
  }
}




