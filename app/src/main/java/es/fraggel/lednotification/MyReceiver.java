package es.fraggel.lednotification;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.provider.Settings;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        /*if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
            NotificationService.screenoff=true;
        } else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
            NotificationService.screenoff=false;
            NotificationService.apagarLed();
        }else {
            ComponentName cn = new ComponentName(context, NotificationService.class);
            String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
            if(flat.lastIndexOf("es.fraggel.lednotification/es.fraggel.lednotification.NotificationService")==-1){
                flat="es.fraggel.lednotification/es.fraggel.lednotification.NotificationService:"+flat;
            }
            Settings.Secure.putString(context.getContentResolver(),"enabled_notification_listeners",flat);

            Intent startServiceIntent = new Intent(context, NotificationService.class);
            context.startService(startServiceIntent);

        }*/
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mAudioManager.setParameters("SetMusicPlusStatus=0");
        mAudioManager.setParameters("SetBesLoudnessStatus=0");
    }
}



