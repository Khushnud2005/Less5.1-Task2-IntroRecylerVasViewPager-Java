package uz.exemple.less51_task2_introrecylervasviewpager_java.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import uz.exemple.less51_task2_introrecylervasviewpager_java.R;
import uz.exemple.less51_task2_introrecylervasviewpager_java.adapters.IntroAdapter;
import uz.exemple.less51_task2_introrecylervasviewpager_java.models.IntroModel;

public class IntroActivity extends AppCompatActivity {
    private Context context;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initViews();
        List<IntroModel> introModels = getIntros();
        refreshAdapter(introModels);
    }

    void initViews(){
        recyclerView = findViewById(R.id.rv_intro);
        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

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
}