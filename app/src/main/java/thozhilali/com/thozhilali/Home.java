package thozhilali.com.thozhilali;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static thozhilali.com.thozhilali.R.id.textView;

public class Home extends AppCompatActivity {
    EditText et1;
    EditText et2;
    Button bt1;
    Button bt2;
    Button bt3;
    TextView txv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         et1=(EditText)findViewById(R.id.ed);
         et2=(EditText)findViewById(R.id.ed2);
         bt1=(Button)findViewById(R.id.bt1);
         bt2=(Button)findViewById(R.id.bt2);
         bt3=(Button)findViewById(R.id.bt3);
        txv=(TextView)findViewById(R.id.textView2);
         bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,WorkerReg.class));
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Login.class));
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Searchresult.class));
            }
        });



     }

}

