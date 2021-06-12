package com.example.hocta;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hocta.model.CH;
import com.example.hocta.model.TuMoi;
import com.example.hocta.sqlite.SQLiteTuMoiHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class VietAnhActivity extends AppCompatActivity {


    private TextView tvTong, tvCH;
    private EditText eTL;
    private Button btTT;
    private List<CH> mListCH;
    private int current = 0;
    private CH mCH;
    private int tlDung=0;

    private SQLiteTuMoiHelper SQLite;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viet_anh);
        initView();

        mListCH = getListCH();
        if(mListCH==null){
            return;
        }
        tvTong.setText("Câu số: "+(current+1)+"/20");
        setDataCH(mListCH.get(current));
        btTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTL(mCH);
            }
        });
    }

    private void initView() {
        tvTong = findViewById(R.id.tvTong);
        tvCH = findViewById(R.id.tvCH);
        eTL = findViewById(R.id.eTL);
        btTT = findViewById(R.id.btTT);
    }

    private void setDataCH(CH ch){
        if (ch==null){
            return;
        }
        mCH = ch;
        tvCH.setText("Từ có nghĩa là "+ch.getQuestion()+ "là: ");
        eTL.setText("");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private List<CH> getListCH(){
        List<CH> list = new ArrayList<>();
        SQLite = new SQLiteTuMoiHelper(this);
        List<TuMoi>list1 = SQLite.getAll();
        Random random = new Random();
        List<Integer> list2 = random.ints(1,list1.size()).distinct().boxed().limit(20).collect(Collectors.toList());
        for(int i=0 ; i<20 ; i++){
            TuMoi tu = SQLite.getById(list2.get(i));
            list.add(new CH((i+1), tu.getNghia(), tu.getTu()));
        }
        return list;
    }

    private void checkTL(CH ch){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(eTL.getText().toString().trim().equalsIgnoreCase(ch.getAnswer())){
                    Toast.makeText(VietAnhActivity.this, "Bạn đã trả lời đúng!", Toast.LENGTH_LONG).show();
                    tlDung++;
                    nextCH();
                }else{
                    Toast.makeText(VietAnhActivity.this, "Sai rồi! " +ch.getQuestion()+" có nghĩa là "+ch.getAnswer(),
                            Toast.LENGTH_LONG).show();
                    nextCH();
                }
            }
        },1000);
    }
    private void nextCH(){
        if(current == mListCH.size()-1){
            showDialog("Bạn đã trả lời đúng "+tlDung+"/20 câu");
        }else{
            current++;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    tvTong.setText("Câu số: "+(current+1)+"/20");
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
                tlDung = 0;
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