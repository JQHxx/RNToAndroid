package com.hjq.mall;

import android.os.Bundle;
import android.view.KeyEvent;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactPackage;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.hjq.cardview.RNCardviewPackage;
import com.hjq.mall.reactnative.ReactNativePackage;
import com.reactnativecommunity.webview.RNCWebViewPackage;
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class BaseRnActivity extends AppCompatActivity implements DefaultHardwareBackBtnHandler  {

    private ReactRootView mReactRootView;
    private ReactInstanceManager mReactInstanceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReactRootView = new ReactRootView(this);
        ReactInstanceManagerBuilder builder = ReactInstanceManager.builder();
        builder.setApplication(getApplication());
        builder.setBundleAssetName("index.android.bundle");
        builder.setJSMainModulePath("index");
        builder.addPackages(
                    Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new ReactNativePackage(),
                    new RNGestureHandlerPackage(),
                    new RNCardviewPackage(),
                    new RNCWebViewPackage())
        );
        builder.setUseDeveloperSupport(false);
        builder.setInitialLifecycleState(LifecycleState.BEFORE_RESUME);
        mReactInstanceManager = builder.build();
        //这里的AndroidRnDemoApp必须对应“index.js”中的“AppRegistry.registerComponent()”的第一个参数
        mReactRootView.startReactApplication(mReactInstanceManager, "RNHighScores", null);
        //加载ReactRootView到布局中
        setContentView(mReactRootView);

        // mReactRootView.setAppProperties();
    }

    @Override
    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }
    /**
     * ReactInstanceManager生命周期同activity
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostPause(this);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostResume(this, this);
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onHostDestroy(this);
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
}

