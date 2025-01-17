package vn.iotstar.baitap01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class cau4 extends AppCompatActivity {
    private static final String TAG = "ArrayListMainActivity";
    private ArrayList<Integer> numbers;
    private EditText inputEditText;
    private Button processButton;
    private TextView result;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cau4);
        // Ánh xạ các view
        bindingView();
        numbers = new ArrayList<>();
        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy chuỗi từ EditText và xử lý
                String input = inputEditText.getText().toString().trim();
                parseInputAndProcessNumbers(input);
            }
        });
    }

    // Hàm xử lý chuỗi input và phân loại số
    private void parseInputAndProcessNumbers(String input) {
        numbers.clear(); // Xóa dữ liệu cũ
        ArrayList<Integer> evenNumbers = new ArrayList<>();
        ArrayList<Integer> oddNumbers = new ArrayList<>();
        // Tách chuỗi thành mảng các số
        String[] numberStrings = input.split("\\s+");
        // Chuyển đổi từng phần tử thành số và phân loại
        try {
            for (String numStr : numberStrings) {
                int num = Integer.parseInt(numStr);
                if (isPrime(num)) {
                    numbers.add(num);
                }
                if (num % 2 == 0) {
                    evenNumbers.add(num);
                } else {
                    oddNumbers.add(num);
                }
            }
            // In ra các số nguyên tố
            result.setText("Số nguyên tố: " + numbers.toString() + "\n" +
                    "Số chẵn: " + evenNumbers.toString() + "\n" +
                    "Số lẻ: " + oddNumbers.toString());
            // In ra Log
            printPrimeNumbers(numbers);
            printEvenOddNumbers(evenNumbers, oddNumbers);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập các số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }

    // Hàm kiểm tra số nguyên tố
    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // Hàm in ra các số nguyên tố từ ArrayList
    private void printPrimeNumbers(ArrayList<Integer> list) {
        for (Integer num : list) {
            Log.d(TAG, "Số nguyên tố: " + num);
        }
    }

    // Hàm in ra số chẵn và số lẻ từ ArrayList
    private void printEvenOddNumbers(ArrayList<Integer> evenNumbers, ArrayList<Integer> oddNumbers) {
        for (Integer num : evenNumbers) {
            Log.d(TAG, "Số chẵn: " + num);
        }
        for (Integer num : oddNumbers) {
            Log.d(TAG, "Số lẻ: " + num);
        }
    }

    // Ánh xạ các view
    private void bindingView() {
        inputEditText = (EditText) findViewById(R.id.arrayInput);
        processButton = (Button) findViewById(R.id.processButton);
        result = (TextView) findViewById(R.id.result);
    }
}
