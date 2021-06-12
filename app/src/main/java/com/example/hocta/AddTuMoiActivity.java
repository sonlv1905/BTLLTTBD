package com.example.hocta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hocta.model.TuMoi;
import com.example.hocta.sqlite.SQLiteTuMoiHelper;


public class AddTuMoiActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CHANNEL_ID = "channel";
    private EditText eTu, eNghia;
    private Spinner spin;
    private Button btAdd, btCancel;
    private SQLiteTuMoiHelper SQLite;
    private NotificationManagerCompat managerCompat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tu_moi);
        initView();
        managerCompat = NotificationManagerCompat.from(this);
        createNotificationChannel();
        SQLite = new SQLiteTuMoiHelper(this);
        btAdd.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, "c 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("This is channel");
            NotificationManager manager = this.getSystemService(
                    NotificationManager.class
            );
            manager.createNotificationChannel(channel);
        }
    }

    private void senOnChannel(){
        String title = "HocTA";
        String content = "Bạn đã thêm thành công từ mới";
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID).setSmallIcon(R.drawable.ic_notifications)
                .setContentTitle(title).setContentText(content).setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_CALL).build();
        int id = 1;
        managerCompat.notify(id,notification);
    }

    private void initView() {
        eTu = findViewById(R.id.eTu);
        eNghia = findViewById(R.id.eNghia);

        spin = findViewById(R.id.spin);
        String[] sp = getResources().getStringArray(R.array.loaitu);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spin_item, sp);
        adapter.setDropDownViewResource(R.layout.spin_item);
        spin.setAdapter(adapter);

        btAdd = findViewById(R.id.btAdd);
        btCancel = findViewById(R.id.btCancel);
    }

    @Override
    public void onClick(View v) {
        if (btAdd == v){
            String tu = eTu.getText().toString();
            if(SQLite.getTu(tu)){
                Toast.makeText(this, tu+" đã tồn tại", Toast.LENGTH_LONG).show();
            }
            else{
                TuMoi tuMoi = new TuMoi(tu,
                        spin.getSelectedItem().toString(),eNghia.getText().toString());
                SQLite.addTM(tuMoi);
                senOnChannel();
                finish();
            }
        }
        if(btCancel == v){
            finish();
        }

    }
}