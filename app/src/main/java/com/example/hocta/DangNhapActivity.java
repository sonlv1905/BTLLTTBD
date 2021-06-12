package com.example.hocta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hocta.fragment.FragmentInfo;
import com.example.hocta.model.User;
import com.example.hocta.sqlite.SQLiteUserHelper;

public class DangNhapActivity extends AppCompatActivity {

    private EditText eUsername, ePassword;
    private Button btDN;
    private TextView tvQMK, tvDK;
    private User user;
    private SQLiteUserHelper SQLite;
//    private static final  int REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        initView();

        SQLite = new SQLiteUserHelper(this);
        tvDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapActivity.this, DangKiActivity.class);
               startActivity(intent);
            }
        });
        btDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eUsername.getText().toString().equalsIgnoreCase("")||
                ePassword.getText().toString().equalsIgnoreCase("")){
                    Toast.makeText(DangNhapActivity.this, "Bạn chưa điền UserName, Password", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean check = SQLite.checkUser(eUsername.getText().toString(), ePassword.getText().toString());
                    if (check){
                        user = new User();
                        user = SQLite.getUserByUserName(eUsername.getText().toString());
                        Intent intent = new Intent(DangNhapActivity.this, MainActivity.class);
                        intent.putExtra("user", user);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(DangNhapActivity.this, "UserName, Password chưa đúng!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void initView() {
        eUsername = findViewById(R.id.eUserName);
        ePassword = findViewById(R.id.ePassword);
        btDN = findViewById(R.id.btDN);
        tvQMK = findViewById(R.id.tvQMK);
        tvDK = findViewById(R.id.tvDK);
    }
}