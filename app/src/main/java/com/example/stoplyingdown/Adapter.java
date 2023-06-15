package com.example.stoplyingdown;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    Context mContext;
    List<item> mData;

    SharedPreferences sPref;

    public Adapter(Context mContext, List<item> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    public List<item> getmData() {
        return this.mData;
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
        holder.data = mData;
        holder.pos = holder.getAdapterPosition();

        if (holder.item.isFinished){
            holder.btn_finish.setText("✔︎");
            holder.btn_finish.setWidth(126);
            holder.btn_finish.setBackgroundResource(R.drawable.good_button);
        }
        else{
            holder.btn_finish.setText("Выполнить");
            holder.btn_finish.setWidth(289);
            holder.btn_finish.setBackgroundResource(R.drawable.empty_button_card);
        }
        holder.delete.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        ImageView background_photo;
        TextView title, description;
        Button btn_finish;
        Button delete;
        List<item> data;
        item item;

        Integer pos;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            this.background_photo = itemView.findViewById(R.id.activity_card_background);
            this.title = itemView.findViewById(R.id.activity_name);
            this.description = itemView.findViewById(R.id.description);
            this.btn_finish = itemView.findViewById(R.id.btn_finish);
            this.delete = itemView.findViewById(R.id.deleteButton);


            btn_finish.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!item.isFinished){
                        btn_finish.setText("✔︎");
                        System.out.println(btn_finish.getHeight() + " " + btn_finish.getWidth());
                        btn_finish.setWidth(btn_finish.getHeight());
                        btn_finish.setBackgroundResource(R.drawable.good_button);
                    }
                    else{
                        btn_finish.setText("Выполнить");
                        btn_finish.setWidth(289);
                        System.out.println(btn_finish.getHeight() + " " + btn_finish.getWidth());
                        btn_finish.setBackgroundResource(R.drawable.empty_button_card);
                    }
                    item.isFinished = !item.isFinished;
                    ChangeBoolean(pos);
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!item.isStock){
                        Integer position = getAdapterPosition();
                        sPref = mContext.getSharedPreferences("activities", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sPref.edit();
                        Integer all = getItemCount();
                        for (Integer i = position+1; i < all; i++){
                            sPref.edit().remove(Integer.toString(position+1));
                            sPref.edit().putString(Integer.toString(position+1), sPref.getString(Integer.toString(position+2), "0")).apply();
                        }
                        sPref.edit().remove(Integer.toString(all)).apply();
                    }
                }
            });
        }

        private void ChangeBoolean(Integer position){
            String resPos = ((Integer)(position+1)).toString();
            sPref = mContext.getSharedPreferences("activities", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sPref.edit();
            String itemString = sPref.getString(resPos, "0");
            String[] itemSplited = itemString.split("%");
            itemSplited[3] = itemSplited[3].equals("false") ? "true" : "false";
            String result = itemSplited[0]+"%"+itemSplited[1]+"%"+itemSplited[2]+"%"+itemSplited[3]+"%"+itemSplited[4];
            editor.putString(resPos, result);
            editor.apply();
        }
    }
}