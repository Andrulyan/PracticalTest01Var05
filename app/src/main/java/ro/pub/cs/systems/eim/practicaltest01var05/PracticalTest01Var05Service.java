package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class PracticalTest01Var05Service extends Service {

    public static final String ACTION_BROADCAST = "ro.pub.cs.systems.eim.practicaltest01var05.ACTION_BROADCAST";
    public static final String EXTRA_MESSAGE = "message";
    private static final int MESSAGE_INTERVAL = 5000; // 5 secunde
    private static final int PRAG = 10; // Prag pentru numărul maxim de mesaje
    private int counter = 0; // Contorul pentru numărul de mesaje
    private Handler handler;

    private Runnable messageRunnable = new Runnable() {
        @Override
        public void run() {
            if (counter < PRAG) {
                // Intent pentru difuzare protejată
                Intent intent = new Intent(ACTION_BROADCAST);
                intent.putExtra(EXTRA_MESSAGE, "Mesaj de difuzare numărul " + counter);

                // PendingIntent pentru a trimite broadcast protejat
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        PracticalTest01Var05Service.this,
                        0,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE
                );

                try {
                    pendingIntent.send();
                    Log.d("PracticalTest01Service", "Difuzare mesaj: Mesaj de difuzare numărul " + counter);
                } catch (PendingIntent.CanceledException e) {
                    Log.e("PracticalTest01Service", "Eroare la difuzarea mesajului.", e);
                }

                counter++;
                handler.postDelayed(this, MESSAGE_INTERVAL);
            } else {
                stopSelf();
                Log.d("PracticalTest01Service", "Serviciul a fost oprit deoarece contorul a depășit pragul.");
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        handler = new Handler();
        handler.postDelayed(messageRunnable, MESSAGE_INTERVAL);
        Log.d("PracticalTest01Service", "Serviciul a fost creat și începe difuzarea mesajelor.");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("PracticalTest01Service", "Serviciul a fost pornit.");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(messageRunnable);
        Log.d("PracticalTest01Service", "Serviciul a fost oprit manual.");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
