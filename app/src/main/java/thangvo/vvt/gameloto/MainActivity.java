package thangvo.vvt.gameloto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnRandom ,btnAddnumber, btnChoilai;
    EditText edtMin , edtMax;
    ArrayList<Integer> mang = new ArrayList<>();
    Random random = new Random();
    TextView txtresult ;
    String mRandom = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        btnChoilai.setVisibility(View.INVISIBLE);

        btnAddnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sMin = edtMin.getText().toString();
                String sMax = edtMax.getText().toString();
                if(mang.size() >= 0){
                    mang.clear();
                }

                if(sMin.isEmpty() || sMax.isEmpty()){
                    Toast.makeText(MainActivity.this, "Bạn phải nhập đủ 2 số", Toast.LENGTH_SHORT).show();
                    return;
                }
                int numberMin = Integer.parseInt(sMin);
                int numberMax = Integer.parseInt(sMax);
                if(numberMin >= numberMax){
                    Toast.makeText(MainActivity.this, "Số Min không được bằng or lớn hơn số Max! Vui lòng nhập lại!", Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i = numberMin ; i<= numberMax ; i++){
                    mang.add(i);
                }
                Toast.makeText(MainActivity.this, "Bạn đã addNumber thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mang.size() == 0){
                    Toast.makeText(MainActivity.this, "Bạn chưa AddNumber! Vui lòng Addnumber", Toast.LENGTH_SHORT).show();
                    return;
                }
                int number =  random.nextInt(mang.size());
                int value = mang.get(number);
                if(mang.size() == 1)
                {
                    mRandom += value;
                    Toast.makeText(MainActivity.this, "Kết Thúc Trò Chơi", Toast.LENGTH_LONG).show();
                    txtresult.setText(mRandom);
                    mang.clear();
                    btnRandom.setVisibility(View.INVISIBLE);
                    btnAddnumber.setVisibility(View.INVISIBLE);
                    btnChoilai.setVisibility(View.VISIBLE);
                    return;
                }
                else {
                    mRandom += value +"-";
                }
                txtresult.setText(mRandom);
                mang.remove(number);
            }
        });

        btnChoilai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnChoilai.setVisibility(View.INVISIBLE);
                btnRandom.setVisibility(View.VISIBLE);
                btnAddnumber.setVisibility(View.VISIBLE);
                mRandom ="";
                txtresult.setText("");
            }
        });

    }


    private void AnhXa(){
        btnChoilai = (Button) findViewById(R.id.buttonChoilai);
        btnAddnumber = (Button) findViewById(R.id.buttonAddnumber);
        btnRandom = (Button) findViewById(R.id.buttonRandom);
        edtMin = (EditText) findViewById(R.id.edittextMin);
        edtMax = (EditText) findViewById(R.id.edittexMax);
        txtresult = (TextView) findViewById(R.id.textviewResult);
    }
}
