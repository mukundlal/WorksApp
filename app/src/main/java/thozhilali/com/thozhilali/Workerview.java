package thozhilali.com.thozhilali;

import android.app.FragmentManager;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import static thozhilali.com.thozhilali.R.id.tab;

public class Workerview extends AppCompatActivity {
    TabLayout mTabLayout;
    FrameLayout frameLayout;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workerview);
        mTabLayout = (TabLayout) findViewById(tab);
        frameLayout=(FrameLayout)findViewById(R.id.l1);
        fm=getFragmentManager();
        fm.beginTransaction().replace(R.id.l1,new Details()).commit();
        setupTabLayout();
    }
    private void setupTabLayout() {





        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                onTabTapped(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                onTabTapped(tab.getPosition());

            }
        });
    }

    private void onTabTapped(int position) {

        switch (position) {

            case 0:

                fm.beginTransaction().replace(R.id.l1,new Details()).commit();

                break;
            case 1:

                fm.beginTransaction().replace(R.id.l1,new Reviews()).commit();
                break;
            default:
                Toast.makeText(this,"Tapped2 " + position, Toast.LENGTH_SHORT).show();
        }
    }
}
