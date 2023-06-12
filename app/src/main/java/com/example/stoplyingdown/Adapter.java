package com.example.stoplyingdown;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    Context mContext;
    List<item> mData;

    public Adapter(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.activity_card, parent, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.background_photo.setImageResource(mData.get(position).getBackground());
        holder.title.setText(mData.get(position).getActivityTitle());
        holder.description.setText(mData.get(position).getDescription());
        holder.item = mData.get(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView background_photo;
        TextView title, description;
        Button btn_finish;
        item item;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.background_photo = itemView.findViewById(R.id.activity_card_background);
            this.title = itemView.findViewById(R.id.activity_name);
            this.description = itemView.findViewById(R.id.description);
            this.btn_finish = itemView.findViewById(R.id.btn_finish);

            btn_finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!item.isFinished){
                        btn_finish.setText("✔︎");
                        btn_finish.setWidth(btn_finish.getHeight());
                        btn_finish.setBackgroundResource(R.drawable.good_button);
                        System.out.println(btn_finish.getWidth());
                        System.out.println(btn_finish.getHeight());
                        item.isFinished = !item.isFinished;
                    }
                    else{
                        btn_finish.setText("Выполнить");
                        btn_finish.setWidth(289);
                        btn_finish.setBackgroundResource(R.drawable.empty_button_card);
                        item.isFinished = !item.isFinished;
                    }
                }
            });
        }
    }
}
