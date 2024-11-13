package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private TextView displayTextView;
    private Button buttonTopLeft, buttonTopRight, buttonCenter, buttonBottomLeft, buttonBottomRight, navigateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);

        // Inițializarea elementelor UI
        displayTextView = findViewById(R.id.displayTextView);
        buttonTopLeft = findViewById(R.id.buttonTopLeft);
        buttonTopRight = findViewById(R.id.buttonTopRight);
        buttonCenter = findViewById(R.id.buttonCenter);
        buttonBottomLeft = findViewById(R.id.buttonBottomLeft);
        buttonBottomRight = findViewById(R.id.buttonBottomRight);
        navigateButton = findViewById(R.id.navigateButton);

        // Setarea funcționalității butoanelor
        buttonTopLeft.setOnClickListener(v -> displayTextView.append("Top Left "));
        buttonTopRight.setOnClickListener(v -> displayTextView.append("Top Right "));
        buttonCenter.setOnClickListener(v -> displayTextView.append("Center "));
        buttonBottomLeft.setOnClickListener(v -> displayTextView.append("Bottom Left "));
        buttonBottomRight.setOnClickListener(v -> displayTextView.append("Bottom Right "));

        // Setarea funcționalității pentru butonul de navigare
        navigateButton.setOnClickListener(v -> {
            Intent intent = new Intent(PracticalTest01Var05MainActivity.this, SecondaryActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("savedText", displayTextView.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            displayTextView.setText(savedInstanceState.getString("savedText"));
        }
    }

}
