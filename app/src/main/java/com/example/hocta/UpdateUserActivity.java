package com.example.hocta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hocta.model.User;
import com.example.hocta.sqlite.SQLiteUserHelper;

import java.util.Calendar;

public class UpdateUserActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvId, tvUserName, tvPassword;
    private EditText eHoten, eDate, eGioiTinh;
    private Button btUpdate, btHuy, btDate;

    private SQLiteUserHelper SQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        initView();
        SQLite = new SQLiteUserHelper(this);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("user1");
        tvId.setText(user.getId()+"");
        tvUserName.setText(user.getUsername());
        tvPassword.setText(user.getPassword());
        eHoten.setText(user.getHoten());
        eDate.setText(user.getNgaysinh());
        eGioiTinh.setText(user.getGioitinh());
        btUpdate.setOnClickListener(this);
        btHuy.setOnClickListener(this);
        btDate.setOnClickListener(this);

    }

    private void initView() {
        tvId = findViewById(R.id.tvId);
        tvUserName = findViewById(R.id.tvUserName);
        tvPassword = findViewById(R.id.tvPass);
        eHoten = findViewById(R.id.eHoTen);
        eDate = findViewById(R.id.eDate);
        eGioiTinh = findViewById(R.id.eGioiTinh);
        btUpdate = findViewById(R.id.btUpdate);
        btHuy = findViewById(R.id.btHuy);
        btDate = findViewById(R.id.btDate);
    }

    @Override
    public void onClick(View v) {
        if(v== btDate){
            Calendar c = Calendar.getInstance();
            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    eDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                }
            },y,m,d);
            dialog.show();
        }
        if(v==btUpdate){
            User user = new User(Integer.parseInt(tvId.getText().toString()), tvUserName.getText().toString(), tvPassword.getText().toString(),
                    eHoten.getText().toString(), eDate.getText().toString(), eGioiTinh.getText().toString());
            SQLite.updateUser(user);
            finish();
        }
        if (v==btHuy){
            finish();
        }
    }
}