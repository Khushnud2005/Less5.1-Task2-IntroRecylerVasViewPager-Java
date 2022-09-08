package uz.exemple.less51_task2_introrecylervasviewpager_java.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import uz.exemple.less51_task2_introrecylervasviewpager_java.MainActivity;
import uz.exemple.less51_task2_introrecylervasviewpager_java.R;
import uz.exemple.less51_task2_introrecylervasviewpager_java.adapters.IntroAdapter;
import uz.exemple.less51_task2_introrecylervasviewpager_java.models.IntroModel;

public class IntroActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView recyclerView;
    private TabLayout tabLayout;
    LinearLayoutManager linearLayoutManager;
    List<IntroModel> introModels;
    TextView tv_skip;
    Button btn_start;

    private boolean isUserScrolling = false;
    private boolean isListGoingUp = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initViews();
        introModels = getIntros();
        refreshAdapter(introModels);
    }

    void initViews(){
        recyclerView = findViewById(R.id.rv_intro);
        tabLayout = findViewById(R.id.tab_layout);
        btn_start = findViewById(R.id.btn_start);
        linearLayoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        /*recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int activePosition = layoutManager.findFirstVisibleItemPosition();
                if (activePosition == RecyclerView.NO_POSITION) return;
                Log.d("@@@","Active Position" + activePosition);
            }
        });*/

        ViewGroup tabs = (ViewGroup) tabLayout.getChildAt(0);

        for (int i = 0;i<3;i++) {
            tabLayout.addTab(tabLayout.newTab());
            View v = tabs.getChildAt(i);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            params.rightMargin = 25;
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                isUserScrolling = false ;
                int position = tab.getPosition();
                if(position==0){
                    recyclerView.smoothScrollToPosition(0);
                    btnOf();
                }else if(position==1){
                    recyclerView.smoothScrollToPosition(1);
                    btnOf();
                }else if(position==2){
                    recyclerView.smoothScrollToPosition(2);
                    btnOn();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    isUserScrolling = true;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int itemPosition = linearLayoutManager.findFirstVisibleItemPosition();


                if(isUserScrolling){
                    if (itemPosition == 0) { //  item position of uses
                        TabLayout.Tab tab = tabLayout.getTabAt(0);
                        tab.select();
                        btnOf();
                    } else if (itemPosition == 1) {//  item position of side effects
                        TabLayout.Tab tab = tabLayout.getTabAt(1);
                        tab.select();
                        btnOf();
                    } else if (itemPosition == 2) {//  item position of how it works
                        TabLayout.Tab tab = tabLayout.getTabAt(2);
                        tab.select();
                        btnOn();
                    }
                }



            }
        });



        tv_skip = findViewById(R.id.tv_skip_f1);
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.smoothScrollToPosition(2);
                TabLayout.Tab tab = tabLayout.getTabAt(2);
                tab.select();
                btnOn();
            }
        });
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });
    }
    private void refreshAdapter(List<IntroModel> intros){
        IntroAdapter adapter = new IntroAdapter(context,intros);
        recyclerView.setAdapter(adapter);
    }

    private List<IntroModel> getIntros(){
        List<IntroModel> intros = new ArrayList<>();
        intros.add(new IntroModel("Say Hello to Global Top - Up","Send mobile top-up to more then 500 networks in over 140 countries.","hello_business.json"));
        intros.add(new IntroModel("Safe, Trusted & Fully Secure","Encrypted transactions mean your payments & Privacy and protected.","safe_money.json"));
        intros.add(new IntroModel("Easy To Use","Pick a number, choose an amount, send your Top-up. Simple","easy_use.json"));

        return intros;
    }
    void btnOn(){
        tv_skip.setVisibility(View.GONE);
        btn_start.setVisibility(View.VISIBLE);
    }
    void btnOf(){
        tv_skip.setVisibility(View.VISIBLE);
        btn_start.setVisibility(View.GONE);
    }
    void openMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}