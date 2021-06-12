package com.example.hocta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hocta.model.TuMoi;
import com.example.hocta.sqlite.SQLiteTuMoiHelper;

public class UpdateRemoveTuMoiActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eTu, eNghia;
    private Spinner spin;
    private Button btUpdate, btCancel;
    private int id;

    private SQLiteTuMoiHelper SQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_remove_tu_moi);

        initView();
        spin = findViewById(R.id.spin);
        String[] sp = getResources().getStringArray(R.array.loaitu);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spin_item, sp);
        adapter.setDropDownViewResource(R.layout.spin_item);
        spin.setAdapter(adapter);

        SQLite = new SQLiteTuMoiHelper(this);
        Intent intent = getIntent();
        TuMoi tuMoi = (TuMoi)intent.getSerializableExtra("tumoi");
        id=tuMoi.getId();
        eTu.setText(tuMoi.getTu());
        eNghia.setText(tuMoi.getNghia());
        String loai = tuMoi.getLoaiTu();
        int m=0;
        for(int i=0 ; i<sp.length ; i++){
            if (sp[i].equalsIgnoreCase(loai)){
                m=i;
            }
        }
        spin.setSelection(m);

        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);

    }

    private void initView() {
        eTu = findViewById(R.id.eTu);
        eNghia = findViewById(R.id.eNghia);
        btUpdate = findViewById(R.id.btUpdate);
        btCancel = findViewById(R.id.btCancel);
    }

    @Override
    public void onClick(View v) {
        if (v==btUpdate){
            String tu = eTu.getText().toString();
            if(SQLite.getTu(tu)){
                Toast.makeText(this, tu+" đã tồn tại", Toast.LENGTH_LONG).show();
            }
            else {
                TuMoi tuMoi = new TuMoi(id, eTu.getText().toString(), spin.getSelectedItem().toString(),
                        eNghia.getText().toString());
                SQLite.updateTM(tuMoi);
                finish();
            }
        }
        if(v==btCancel){
            finish();
        }
    }
}