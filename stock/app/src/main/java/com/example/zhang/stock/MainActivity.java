package com.example.zhang.stock;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText price ;  //股价
    EditText ziJinChengBen ;// 资金成本
    EditText guBen;        //股本
    EditText kouFeiJingLiRun; //抠非净利润
    TextView cashResult;   //现金流结果
    EditText jingLiRungGroup; // 净利润增加比
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=findViewById(R.id.button);
        ziJinChengBen=findViewById(R.id.ziJinChengBen);
        guBen=findViewById(R.id.guBen);
        kouFeiJingLiRun=findViewById(R.id.kouFeiJingLiRun);
        cashResult=findViewById(R.id.cashResult);
        price=findViewById(R.id.price);
        jingLiRungGroup = findViewById(R.id.jingLiRungGroup);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ziJinChengBenTest = ziJinChengBen.getText().toString();
                if(ziJinChengBenTest == null || ziJinChengBenTest.equals("")){
                    Toast toast = Toast.makeText(MainActivity.this,"资金成本内容为空",Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                String jingLiRungGroupTest = jingLiRungGroup.getText().toString();
                if(jingLiRungGroupTest == null || jingLiRungGroupTest.equals("")){
                    Toast toast = Toast.makeText(MainActivity.this,"净利润增加比内容为空",Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                String kouFeiJingLiRunTest = kouFeiJingLiRun.getText().toString();

                if(kouFeiJingLiRunTest == null || kouFeiJingLiRunTest.equals("")){
                    Toast toast = Toast.makeText(MainActivity.this,"扣非净利润内容为空",Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                String guBenTest = guBen.getText().toString();

                if(guBenTest == null || guBenTest.equals("")){
                    Toast toast = Toast.makeText(MainActivity.this,"股本内容为空",Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                String priceTest = price.getText().toString();

                if(priceTest == null || priceTest.equals("")){
                    Toast toast = Toast.makeText(MainActivity.this,"价格内容为空",Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                Double ziJinChengBenNum = Double.parseDouble(ziJinChengBenTest);
                Double jingLiRungGroupNum = Double.parseDouble(jingLiRungGroupTest);
                Double guBenNum = Double.parseDouble(guBenTest);
                Double kouFeiJingLiRunNum = Double.parseDouble(kouFeiJingLiRunTest);
                Double kouFeiJingLiRunAll = kouFeiJingLiRunNum;
                StringBuffer stringBuffer = new StringBuffer();
                double cost = 1+ziJinChengBenNum - jingLiRungGroupNum;
                for(int i=1;i<10;i++){
                    kouFeiJingLiRunNum =  kouFeiJingLiRunNum/cost;
                    kouFeiJingLiRunAll+= kouFeiJingLiRunNum;
                    stringBuffer.append("第"+(i+1)+"年的情况"+kouFeiJingLiRunNum +"\n");
                }
                stringBuffer.append("总值"+kouFeiJingLiRunAll+"\n");

                double presentPrice =kouFeiJingLiRunAll/guBenNum;
                stringBuffer.append("保守现价"+presentPrice +"\n");
                cashResult.setText(stringBuffer.toString());
            }
        });
    }
}
