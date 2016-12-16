package com.example.simple;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import android.util.Log;

import java.util.Hashtable;

import org.json.JSONArray;

public class SimplePlugin extends CordovaPlugin {
	private Hashtable<String, String> store = new Hashtable<String, String>();

	public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) {
		Log.e("~~~~>","~~~~> SimplePlugin execute " + action + " with args: " + args.toString());
		if (action.equals("set")) {
			cordova.getThreadPool().submit(new Runnable() {
				@Override
				public void run() {
					String key = args.optString(0);
					String value = args.optString(1);
					Log.e("~~~~>", "~~~~> setting key " + key + " to value " + value);
					store.put(key, value);
					callbackContext.success();
				}
			});
		} else if (action.equals("get")) {
			cordova.getThreadPool().submit(new Runnable() {
				@Override
				public void run() {
					String key = args.optString(0);
					Log.e("~~~~>", "~~~~> getting value for key: " + key);
					String stored = store.get(key);
					Log.e("~~~~>", "~~~~> stored value: " + stored);
					callbackContext.success(stored);
				}
			});
		} else if (action.equals("getOnMainThread")) {
			String key = args.optString(0);
			Log.e("~~~~>", "~~~~> main thread getting value for key: " + key);
			String stored = store.get(key);
			Log.e("~~~~>", "~~~~> stored value: " + stored);
			callbackContext.success(stored);
		} else {
			return false;
		}
		return true;
	}
}