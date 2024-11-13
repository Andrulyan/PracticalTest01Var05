package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private TextView displayTextView;
    private Button buttonTopLeft, buttonTopRight, buttonCenter, buttonBottomLeft, buttonBottomRight, navigateButton, verifyActivityButton;
    private int buttonPressCount = 0;

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

        // Lansăm PracticalTest01Var05SecondaryActivity cu un request code pentru a primi rezultatul
        verifyActivityButton.setOnClickListener(v -> {
            Intent intent = new Intent(PracticalTest01Var05MainActivity.this, PracticalTest01Var05SecondaryActivity.class);
            startActivityForResult(intent, 1);
        });
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

    // Gestionăm rezultatul primit de la PracticalTest01Var05SecondaryActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                String result = data.getStringExtra("result");
                Toast.makeText(this, "Rezultat primit: " + result, Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Rezultat primit: cancel", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
