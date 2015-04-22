package es.fraggel.lednotification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.service.notification.NotificationListenerService;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class NotificationService extends NotificationListenerService{
    Context context;
    public static boolean screenoff=false;
    @Override

    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        BroadcastReceiver mReceiver = new MyReceiver();
        registerReceiver(mReceiver, filter);
        /*context = getApplicationContext();
        nm = ( NotificationManager ) getSystemService( context.NOTIFICATION_SERVICE );*/
    }
    static boolean apagado=false;
    static NotificationManager nm =null;
    int NOTIFICATION_ID=6565;
    Timer timmer=new Timer("led",true);
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        if(screenoff){
            encenderLed();
        }

        /*String pack = sbn.getPackageName();
        String ticker = sbn.getNotification().tickerText.toString();
        Bundle extras = sbn.getNotification().extras;
        String title = extras.getString("android.title");
        String text = extras.getCharSequence("android.text").toString();
        NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
        Notification notif = new Notification();
        notif.defaults=0;
        notif.priority=Notification.PRIORITY_DEFAULT;
        notif.ledARGB = 0xFFff0000;
        notif.flags = Notification.FLAG_SHOW_LIGHTS;
        notif.ledOnMS = 1;
        notif.ledOffMS = 0;
        nm.notify(6565, notif);*/


    }
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        //NotificationManager nm = ( NotificationManager ) getSystemService( NOTIFICATION_SERVICE );
        //nm.cancel( 6565 );
        apagarLed();

    }
    public static void encenderLed(){
        try {

            Process su = Runtime.getRuntime().exec("su");
            OutputStream outputStream = su.getOutputStream();
            outputStream.write("echo 1 > /sys/devices/platform/leds-mt65xx/leds/green/brightness".getBytes());
            outputStream.flush();
            outputStream.close();
            apagado=false;
            final Handler handler = new Handler();
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                public void run() {
                    handler.post(new Runnable() {
                        public void run() {
                            try {
                                if(!apagado) {
                                    FileReader f=new FileReader(new File("/sys/devices/platform/leds-mt65xx/leds/green/brightness"));
                                    int read = f.read();
                                    f.close();
                                    Process su = Runtime.getRuntime().exec("su");
                                    OutputStream outputStream = su.getOutputStream();
                                    if(read==1){
                                        outputStream.write("echo 0 > /sys/devices/platform/leds-mt65xx/leds/green/brightness".getBytes());
                                    }else {
                                        outputStream.write("echo 1 > /sys/devices/platform/leds-mt65xx/leds/green/brightness".getBytes());
                                    }
                                    outputStream.flush();
                                    outputStream.close();
                                }
                            }catch(Exception e){}
                        }
                    });
                }
            }, 2000,120000);
            if(apagado){
                t.cancel();
            }
        }catch(Exception e){}
    }
    public static void apagarLed(){
        try {
            Process su = Runtime.getRuntime().exec("su");
            OutputStream outputStream = su.getOutputStream();
            outputStream.write("echo 0 > /sys/devices/platform/leds-mt65xx/leds/green/brightness".getBytes());
            outputStream.flush();
            outputStream.close();
            apagado=true;
        }catch(Exception e){}
    }

}
