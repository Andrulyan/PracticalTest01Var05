package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Button verifyButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);

        // Inițializăm elementele din layout
        inputEditText = findViewById(R.id.inputEditText);
        verifyButton = findViewById(R.id.verifyButton);
        cancelButton = findViewById(R.id.cancelButton);

        // Setăm listenerul pentru butonul Verify
        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "verify");
                setResult(RESULT_OK, resultIntent);
                finish(); // Închidem activitatea și trimitem rezultatul către activitatea principală
            }
        });

        // Setăm listenerul pentru butonul Cancel
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result", "cancel");
                setResult(RESULT_CANCELED, resultIntent);
                finish(); // Închidem activitatea și trimitem rezultatul către activitatea principală
            }
        });
    }
}
