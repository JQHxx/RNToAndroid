package com.hjq.mall.reactnative;

import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * toast
 */
public class ToastJavaModule extends ReactContextBaseJavaModule {

    public static final String LONG_TIME = "LONG";
    public static final String SHORT_TIME = "SHORT";
    public static final String MESSAGE = "MESSAGE";

    public ToastJavaModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    /**
     * 该方法的返回值是在RN中需要这个值来调用该类的方法
     * @return
     */
    @Override
    public String getName() {
        return "ToastForAndroid";
    }
    @Override
    public boolean canOverrideExistingModule() {
        return true;
    }

    /* 第一种
    import { NativeModules} from 'react-native';
    NativeModules.ToastForAndroid.show(1000);
    */

    @ReactMethod
    public void  show(int duration){
        Toast.makeText(getReactApplicationContext(), "message:"+duration,duration).show();
    }

    /* 第二种 callback
    import { NativeModules} from 'react-native';
    NativeModules.ToastForAndroid.showCallbackMethod("HelloJack",(result)=>{
        this.setState({text2:result});
    })
    */
    @ReactMethod
    public void showCallbackMethod(String msg, Callback callback){

        Toast.makeText(getReactApplicationContext(), msg, Toast.LENGTH_LONG).show();
        callback.invoke("abc");
    }

    /* 第三种 Promise
    import { NativeModules} from 'react-native';
    NativeModules.ToastForAndroid.showPromiseMethod("abcx").then((result)=>{
    }).catch((error)=>{
    })
    */
    @ReactMethod
    public void showPromiseMethod(String msg, Promise promise){
        Toast.makeText(getReactApplicationContext(), msg, Toast.LENGTH_SHORT).show();
        String result = "高娟娟";
        promise.resolve(result);
    }


    /* 第四种 原生代码
    NativeModules.ToastForAndroid.sendEvent();
      constructor(props){
        super(props);
        this.componenWillMount();
    }
    componenWillMount(){
        DeviceEventEmitter.addListener('EventName',function (msg) {
            console.log(msg);
            let rest = NativeModules.ToastForAndroid.MESSAGE;
            ToastAndroid.show("DeviceEventEmitter收到的消息："+"\n"+rest,ToastAndroid.SHORT);
        });
    }
    _onPressButton(){
        NativeModules.ToastForAndroid.sendEvent();
    }
    */

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        //让js那边能够使用这些常量
        Map<String,Object> constans = new HashMap<>();
        constans.put(LONG_TIME, Toast.LENGTH_LONG);
        constans.put(SHORT_TIME, Toast.LENGTH_SHORT);
        constans.put(MESSAGE, "这是MESSAGE的值");
        return constans;
    }

    private void sendEvent(ReactApplicationContext reactContext, String eventName, WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName,params);
    }
}
