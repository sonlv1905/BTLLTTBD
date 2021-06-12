package com.example.hocta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hocta.model.User;
import com.example.hocta.sqlite.SQLiteUserHelper;

import java.util.Calendar;

public class DangKiActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText eHoTen, eNgaySinh, eGioiTinh, eUsername, ePassword, eConfirmPassword;
    private Button btDk, btDate;
    private SQLiteUserHelper SQLite;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        initView();
        SQLite = new SQLiteUserHelper(this);
        btDate.setOnClickListener(this);
        btDk.setOnClickListener(this);
    }

    private void initView() {
        eHoTen = findViewById(R.id.eHoTen);
        eNgaySinh = findViewById(R.id.eNgaySinh);
        eGioiTinh = findViewById(R.id.eGioiTinh);
        eUsername = findViewById(R.id.eUserName);
        ePassword = findViewById(R.id.ePassword);
        eConfirmPassword = findViewById(R.id.eConfirmPassword);
        btDk = findViewById(R.id.btDK);
        btDate = findViewById(R.id.btDate);
    }

    @Override
    public void onClick(View v) {
        if (v==btDate){
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    eNgaySinh.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },y,m,d);
            dialog.show();
        }
        if (v==btDk){
            String username = eUsername.getText().toString();
            String password = ePassword.getText().toString();
            String cfpassword = eConfirmPassword.getText().toString();
            String hoten = eHoTen.getText().toString();
            String ngay = eNgaySinh.getText().toString();
            String gt = eGioiTinh.getText().toString();
            if(username.equalsIgnoreCase("")||password.equalsIgnoreCase("")
                    ||cfpassword.equalsIgnoreCase("")){
                Toast.makeText(DangKiActivity.this, "Bạn chưa điền hết các trường!", Toast.LENGTH_LONG).show();
            }
            else if(!password.equalsIgnoreCase(cfpassword)){
                Toast.makeText(DangKiActivity.this, "Mật khẩu phải giống nhau!", Toast.LENGTH_LONG).show();
            }
            else{
                boolean check = SQLite.checkUser(username);
                if (check == true){
                    Toast.makeText(DangKiActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_LONG).show();
                }
                else{
                    user = new User(hoten, username, password, ngay, gt);
                    SQLite.addUser(user);
                    Toast.makeText(DangKiActivity.this, "Đăng ký thành công!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DangKiActivity.this, DangNhapActivity.class);
                    startActivity(intent);
                }
            }
        }
    }
}