package co.com.etn.mvp_base;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import co.com.etn.mvp_base.helper.DataBase;
import co.com.etn.mvp_base.views.activities.LoginActivity;

/**
 * co.com.etn.mvp_base
 * MVP_Base
 * Created by alexander.vasquez on 30/09/2017.11:35 AM
 */

public class App extends Application {

    public static DataBase mdb;

    @Override
    public void onCreate(){
        super.onCreate();
        mdb = new DataBase(this);
        mdb = mdb.open();
        Log.e("SQL Open",":------------------------------->Si abriooooo????");
        //Configuracin de OneSignal
        OneSignal.startInit(this).autoPromptLocation(false)
                .setNotificationReceivedHandler( new NotificationReceivedHandler())
                .setNotificationOpenedHandler(new NotificationOpenedHandler())
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    @Override
    public void onTerminate(){
        mdb.close();
        super.onTerminate();
    }

    public class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {

        @Override
        public void notificationOpened(OSNotificationOpenResult openedResult) {
            OSNotification notification = openedResult.notification;
            JSONObject data = notification.payload.additionalData;
            OSNotificationAction.ActionType actionType = openedResult.action.type;

            String customKey = data.optString("customkey", null);
            if (actionType == OSNotificationAction.ActionType.ActionTaken) {
                Log.i("OneSignal", "Button pressed with id: " + openedResult.action.actionID);
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

            if (data != null)
                Log.d("OneSignal", "Full additionalData:\n" + data.toString());

            Log.d("OneSignal", "App in focus: " + notification.isAppInFocus);
        }
    }

    public class NotificationReceivedHandler implements OneSignal.NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {
            JSONObject data = notification.payload.additionalData;
            String notificationID = notification.payload.notificationID;
            String title = notification.payload.title;
            if (data != null) {
                Log.d("OneSignal", "Full additionalData:\n" + data.toString());
                String customKey = data.optString("customkey", null);
                if(customKey!=null){
                    Log.d("OneSignal", "customKey:\n" + customKey );

                }
            }

            Log.d("OneSignal", "App in focus: " + notification.isAppInFocus);
        }
    }
}
