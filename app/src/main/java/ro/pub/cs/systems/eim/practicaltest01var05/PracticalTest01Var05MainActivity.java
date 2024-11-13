package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private TextView displayTextView;
    private Button buttonTopLeft, buttonTopRight, buttonCenter, buttonBottomLeft, buttonBottomRight;
    private Button navigateButton, verifyActivityButton, startServiceButton, stopServiceButton;
    private int buttonPressCount = 0;

    private BroadcastReceiver messageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(PracticalTest01Var05Service.ACTION_BROADCAST)) {
                String message = intent.getStringExtra(PracticalTest01Var05Service.EXTRA_MESSAGE);
                Toast.makeText(context, "Mesaj primit: " + message, Toast.LENGTH_SHORT).show();
                Log.d("PracticalTest01Var05MainActivity", "Mesaj de difuzare primit: " + message);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        displayTextView = findViewById(R.id.displayTextView);
        buttonTopLeft = findViewById(R.id.buttonTopLeft);
        buttonTopRight = findViewById(R.id.buttonTopRight);
        buttonCenter = findViewById(R.id.buttonCenter);
        buttonBottomLeft = findViewById(R.id.buttonBottomLeft);
        buttonBottomRight = findViewById(R.id.buttonBottomRight);
        navigateButton = findViewById(R.id.navigateButton);
        verifyActivityButton = findViewById(R.id.verifyActivityButton);
        startServiceButton = findViewById(R.id.startServiceButton);
        stopServiceButton = findViewById(R.id.stopServiceButton);

        buttonTopLeft.setOnClickListener(v -> {
            displayTextView.append("Top Left ");
            buttonPressCount++;
        });

        buttonTopRight.setOnClickListener(v -> {
            displayTextView.append("Top Right ");
            buttonPressCount++;
        });

        buttonCenter.setOnClickListener(v -> {
            displayTextView.append("Center ");
            buttonPressCount++;
        });

        buttonBottomLeft.setOnClickListener(v -> {
            displayTextView.append("Bottom Left ");
            buttonPressCount++;
        });

        buttonBottomRight.setOnClickListener(v -> {
            displayTextView.append("Bottom Right ");
            buttonPressCount++;
        });

        navigateButton.setOnClickListener(v -> {
            Intent intent = new Intent(PracticalTest01Var05MainActivity.this, PracticalTest01Var05SecondaryActivity.class);
            startActivity(intent);
        });

        verifyActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(PracticalTest01Var05MainActivity.this, PracticalTest01Var05SecondaryActivity.class);
            startActivityForResult(intent, 1);
        });

        startServiceButton.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(PracticalTest01Var05MainActivity.this, PracticalTest01Var05Service.class);
            startService(serviceIntent);
            Toast.makeText(this, "Serviciul a fost pornit", Toast.LENGTH_SHORT).show();
        });

        stopServiceButton.setOnClickListener(v -> {
            Intent serviceIntent = new Intent(PracticalTest01Var05MainActivity.this, PracticalTest01Var05Service.class);
            stopService(serviceIntent);
            Toast.makeText(this, "Serviciul a fost oprit", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter();
        filter.addAction(PracticalTest01Var05Service.ACTION_BROADCAST);
        registerReceiver(messageReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(messageReceiver);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("savedText", displayTextView.getText().toString());
        outState.putInt("buttonPressCount", buttonPressCount);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            displayTextView.setText(savedInstanceState.getString("savedText"));
            buttonPressCount = savedInstanceState.getInt("buttonPressCount");

            Toast.makeText(this, "Număr total de apăsări restaurat: " + buttonPressCount, Toast.LENGTH_SHORT).show();
        }
    }
}
