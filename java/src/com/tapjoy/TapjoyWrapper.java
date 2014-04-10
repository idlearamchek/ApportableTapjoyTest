package com.tapjoy;

import android.app.Activity;
import android.widget.Toast;

import android.util.Log;

import java.util.Random;

import android.os.Handler;
import android.view.Gravity;

import java.util.HashSet;
import java.util.Set;
import java.io.File;
import java.util.Hashtable;
import java.lang.StringBuilder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tapjoy.TJError;
import com.tapjoy.TJEvent;
import com.tapjoy.TJEventCallback;
import com.tapjoy.TJEventRequest;
import com.tapjoy.TapjoyAwardPointsNotifier;
import com.tapjoy.TapjoyConnect;
import com.tapjoy.TapjoyConnectFlag;
import com.tapjoy.TapjoyConnectNotifier;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyDisplayAdNotifier;
import com.tapjoy.TapjoyEarnedPointsNotifier;
import com.tapjoy.TapjoyFullScreenAdNotifier;
import com.tapjoy.TapjoyLog;
import com.tapjoy.TapjoyNotifier;
import com.tapjoy.TapjoyOffersNotifier;
import com.tapjoy.TapjoySpendPointsNotifier;
import com.tapjoy.TapjoyVideoNotifier;
import com.tapjoy.TapjoyViewNotifier;
import com.tapjoy.TapjoyViewType;
import com.tapjoy.TJCOffersWebView;

import java.net.URL;

import java.io.InputStream;
import java.io.IOException;
import java.util.Enumeration;

import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;

import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.Set;


public class TapjoyWrapper implements TapjoyNotifier
{
    private static final String TAG = "ApportableTapjoyTest";
    
    private Activity appActivity;
    
    public TapjoyWrapper( Activity activity )
    {
        appActivity = activity;

        Log.i(TAG, "Constructor");
    }
    
    public void setupTapjoy()
    {
        logMessage("exec: setupTapjoy");
        
        Hashtable<String, Object> connectFlags = new Hashtable<String, Object>();
        connectFlags.put(TapjoyConnectFlag.ENABLE_LOGGING, "true");
        
        //AppId and secret are valids
        String tapjoyAppID = "cf09234a-645d-47be-a91b-71881e6cd5c2"; //TEST
        String tapjoySecretKey = "NzcasOi5dYTOKMMvJxOQ"; //TEST
        
        
        Log.i("BEFORE", "requestTapjoyConnect");
        
                                                    //.getBaseContext()
        TapjoyConnect.requestTapjoyConnect(appActivity, tapjoyAppID, tapjoySecretKey
        , connectFlags, new TapjoyConnectNotifier(){
        	@Override
        	public void connectSuccess() {
        		onConnectSuccess();
        	}
              
        	@Override
        	public void connectFail() {
        		onConnectFail();
        	}
        });
        
        Log.i("AFTER", "requestTapjoyConnect");
    }
    
    public void onConnectSuccess()
	{
		Log.i(TAG, "CONNECTED TO TAPJOY");
        
		TapjoyConnect.getTapjoyConnectInstance().setEarnedPointsNotifier(new TapjoyEarnedPointsNotifier()
                                                                         {
			@Override
			public void earnedTapPoints(int amount){}
		});
		
		// Get notifications when Tapjoy views open or close.
		TapjoyConnect.getTapjoyConnectInstance().setTapjoyViewNotifier(new TapjoyViewNotifier()
                                                                       {
			@Override
			public void viewWillOpen(int viewType){}
			
			@Override
			public void viewWillClose(int viewType){}
			
			@Override
			public void viewDidOpen(int viewType){}
			
			@Override
			public void viewDidClose(int viewType){}
		});
		
		// Get notifications on video start, complete and error
		TapjoyConnect.getTapjoyConnectInstance().setVideoNotifier(new TapjoyVideoNotifier() {
            
			@Override
			public void videoStart() {
				Log.i(TAG, "video has started");
			}
            
			@Override
			public void videoError(int statusCode) {
				Log.i(TAG, "there was an error with the video: " + statusCode);
			}
            
			@Override
			public void videoComplete() {
				Log.i(TAG, "video has completed");
			}
			
		});
	}
	
	public void onConnectFail()
	{
		Log.e(TAG, "Tapjoy connect call failed.");
	}
    
    //================================================================================
	// TapjoyNotifier Methods
	//================================================================================
	@Override
	public void getUpdatePointsFailed(String error){}
	
	@Override
	public void getUpdatePoints(String currencyName, int pointTotal){}


    public void logMessage( String message )
    {
        Toast.makeText( appActivity, message, Toast.LENGTH_SHORT ).show();
    }
}
