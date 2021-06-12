package com.example.hocta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.example.hocta.fragment.FragmentReview;
import com.example.hocta.model.CauHoi;
import com.example.hocta.model.TraLoi;
import com.example.hocta.model.TuMoi;
import com.example.hocta.sqlite.SQLiteTuMoiHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ReviewActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvCH, tvTL1, tvTL2, tvTL3, tvTL4;
    private SQLiteTuMoiHelper SQLite;

    private List<CauHoi> mListCH;
    private int current = 0;
    private CauHoi mCauHoi;
    private int TLDung=0;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        initView();
        mListCH = getListCH();
        if (mListCH.isEmpty()){
            return;
        }
        setDataCH(mListCH.get(current));

    }

    private void setDataCH(CauHoi cauHoi) {
        if(cauHoi == null){
            return;
        }
        mCauHoi = cauHoi;
        tvTL1.setBackgroundResource(R.drawable.bg_hoc);
        tvTL2.setBackgroundResource(R.drawable.bg_hoc);
        tvTL3.setBackgroundResource(R.drawable.bg_hoc);
        tvTL4.setBackgroundResource(R.drawable.bg_hoc);


        tvCH.setText(cauHoi.getContent());
        tvTL1.setText(cauHoi.getList().get(0).getContent());
        tvTL2.setText(cauHoi.getList().get(1).getContent());
        tvTL3.setText(cauHoi.getList().get(2).getContent());
        tvTL4.setText(cauHoi.getList().get(3).getContent());

        tvTL1.setOnClickListener(this);
        tvTL2.setOnClickListener(this);
        tvTL3.setOnClickListener(this);
        tvTL4.setOnClickListener(this);

    }

    private void initView() {
        tvCH = findViewById(R.id.tvCH);
        tvTL1 = findViewById(R.id.tvTL1);
        tvTL2 = findViewById(R.id.tvTL2);
        tvTL3 = findViewById(R.id.tvTL3);
        tvTL4 = findViewById(R.id.tvTL4);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<CauHoi> getListCH(){
        List<CauHoi> list = new ArrayList<>();
        SQLite = new SQLiteTuMoiHelper(this);
        List<TuMoi> list1 = SQLite.getAll();
        Random random = new Random();
        for(int i=0 ; i<10 ; i++){
            List<Integer> rand = random.ints(1,list1.size()).distinct().limit(4).boxed().collect(Collectors.toList());
            List<TraLoi> list2 = new ArrayList<>();
            list2.add(new TraLoi(SQLite.getById(rand.get(0)).getNghia(),true));
            list2.add(new TraLoi(SQLite.getById(rand.get(1)).getNghia(),false));
            list2.add(new TraLoi(SQLite.getById(rand.get(2)).getNghia(),false));
            list2.add(new TraLoi(SQLite.getById(rand.get(3)).getNghia(),false));
            Collections.shuffle(list2);
            list.add(new CauHoi((i+1), SQLite.getById(rand.get(0)).getTu(), list2));
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tvTL1:
                tvTL1.setBackgroundResource(R.drawable.bg_cam);
                checkTL(tvTL1, mCauHoi, mCauHoi.getList().get(0));
                break;
            case R.id.tvTL2:
                tvTL2.setBackgroundResource(R.drawable.bg_cam);
                checkTL(tvTL2, mCauHoi, mCauHoi.getList().get(1));
                break;
            case R.id.tvTL3:
                tvTL3.setBackgroundResource(R.drawable.bg_cam);
                checkTL(tvTL3, mCauHoi, mCauHoi.getList().get(2));
                break;
            case R.id.tvTL4:
                tvTL4.setBackgroundResource(R.drawable.bg_cam);
                checkTL(tvTL4, mCauHoi, mCauHoi.getList().get(3));
                break;
        }
    }

    private void checkTL(TextView textView, CauHoi cauHoi, TraLoi traLoi){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (traLoi.isCorrect()){
                    textView.setBackgroundResource(R.drawable.bg_xanh);
                    TLDung++;
                    nextCauHoi();
                }else{
                    textView.setBackgroundResource(R.drawable.bg_do);
                    showTLDung(cauHoi);
                    nextCauHoi();
                }
            }
        },1000);
    }

    private void showTLDung(CauHoi cauHoi) {
        if(cauHoi == null || cauHoi.getList() ==null|| cauHoi.getList().isEmpty()){
            return;
        }
        if (cauHoi.getList().get(0).isCorrect()){
            tvTL1.setBackgroundResource(R.drawable.bg_xanh);
        }else if (cauHoi.getList().get(1).isCorrect()){
            tvTL2.setBackgroundResource(R.drawable.bg_xanh);
        }else if (cauHoi.getList().get(2).isCorrect()){
            tvTL3.setBackgroundResource(R.drawable.bg_xanh);
        }else if (cauHoi.getList().get(3).isCorrect()){
            tvTL4.setBackgroundResource(R.drawable.bg_xanh);
        }
    }

    private void nextCauHoi(){
        if(current == mListCH.size()-1){
            showDialog("Bạn đã trả lời đúng "+TLDung+"/10 câu");
        }else{
            current++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setDataCH(mListCH.get(current));
                }
            },1000);
        }
    }
    private void showDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                current =  0;
                mListCH = getListCH();
                setDataCH(mListCH.get(current));
                TLDung = 0;
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Intent intent = new Intent(getApplication(), MainActivity.class);
//                startActivity(intent);
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}