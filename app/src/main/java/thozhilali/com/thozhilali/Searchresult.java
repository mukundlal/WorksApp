package thozhilali.com.thozhilali;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Searchresult extends AppCompatActivity {
    Button srt;
    Button fltr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);
        fltr=(Button)findViewById(R.id.fltr);
        srt=(Button)findViewById(R.id.srt);
        srt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Searchresult.this,Sortby.class));
            }
        });
        fltr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Searchresult.this,Filter.class));
            }
        });

    }
}
