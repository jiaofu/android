package com.example.zhang.secret;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    EditText editText;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (text == null || text.equals("")) {
                    Toast toast = Toast.makeText(MainActivity.this, "内容为空", Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }
                text = text.trim();
                Log.d("TAG", "text:" + text);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(text);
                stringBuffer.append("水流");
                stringBuffer.append("黄河之水天上来，奔流到海不复回");
                stringBuffer.append("49");
                String encryption = stringBuffer.toString();
                String substring = MD5Util.getMD5String64(encryption).substring(0, 7);
                Log.d("TAG", "MD5:" + substring);
                String showText = createString(substring);
                Log.d("TAG", "create:" + showText);
                textView.setText(showText);
            }
        });
    }

    //规则第一个应该如果是
    private String createString(String intiStr) {

        Boolean isDidit = false;
        Boolean isLower = false;
        Boolean isUpper = false;
        int num = 0;
        StringBuffer stringBuffer = new StringBuffer();
        String str = intiStr.substring(0, 5);
        String completion = intiStr.substring(5, intiStr.length());
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            num = num + ch;
            if (Character.isDigit(ch) && !isDidit) {
                stringBuffer.append(ch);
                isDidit = true;
                continue;
            } else if (Character.isLowerCase(ch) && !isLower) {
                isLower = true;
                stringBuffer.append(ch);
                continue;
            } else if (Character.isUpperCase(ch) && !isUpper) {
                isUpper = true;
                stringBuffer.append(ch);
                continue;
            }
        }

        //ASCII在 65-90 之间是大写
        //97-122 是小写
        if (!isDidit) {
            int getNum = num % 10;
            stringBuffer.append(getNum);
        }
        if (!isLower) {
            char upperNum = (char) (num % 26 + 97);
            stringBuffer.append(upperNum);
        }
        if (!isUpper) {
            char upperNum = (char) (num % 26 + 65);
            stringBuffer.append(upperNum);
        }
        stringBuffer.append(completion);
        return stringBuffer.toString();

    }


}
