package vn.iotstar.baitap01;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class cau5  extends AppCompatActivity {
    private EditText inputText;
    private Button reverseButton;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cau5);

        inputText = findViewById(R.id.inputText);
        reverseButton = findViewById(R.id.reverseButton);
        outputText = findViewById(R.id.outputText);

        reverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();
                String reversed = reverseString(input);
                String uppercased = reversed.toUpperCase();

                outputText.setText(uppercased);
                Toast.makeText(cau5.this, "Chuỗi đảo ngược: " + uppercased, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String reverseString(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }
}
