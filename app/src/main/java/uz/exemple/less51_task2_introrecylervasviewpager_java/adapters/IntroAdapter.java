package uz.exemple.less51_task2_introrecylervasviewpager_java.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.List;

import uz.exemple.less51_task2_introrecylervasviewpager_java.MainActivity;
import uz.exemple.less51_task2_introrecylervasviewpager_java.R;
import uz.exemple.less51_task2_introrecylervasviewpager_java.models.IntroModel;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.ViewHolder> {
    private Context context;
    private List<IntroModel> intros;

    public IntroAdapter(Context context, List<IntroModel> intros) {
        this.context = context;
        this.intros = intros;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_intro,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IntroModel intro = intros.get(position);
        holder.title.setText(intro.getTitle());
        holder.desc.setText(intro.getDesc());
        holder.lottie.setAnimation(intro.getLottyName());
        /*if(position == 2){
            holder.btn_start.setVisibility(View.VISIBLE);
            holder.skip.setVisibility(View.GONE);
            holder.circle2.setBackgroundResource(R.drawable.circle_brushed);
            holder.circle0.setBackgroundResource(R.drawable.circle);
        }else if (position == 1){
            holder.circle0.setBackgroundResource(R.drawable.circle);
            holder.circle1.setBackgroundResource(R.drawable.circle_brushed);
        }
        else {
            holder.btn_start.setVisibility(View.GONE);
            holder.skip.setVisibility(View.VISIBLE);
        }

        holder.skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(holder.itemView.getContext());
            }
        });
        holder.btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity(holder.itemView.getContext());
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return intros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,desc;
        LottieAnimationView lottie;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title_f1);
            desc = itemView.findViewById(R.id.tv_desc_f1);
            lottie = itemView.findViewById(R.id.lottie_intro);
            context = itemView.getContext();
        }
    }


}
