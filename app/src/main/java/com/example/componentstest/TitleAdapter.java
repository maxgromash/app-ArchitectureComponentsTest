package com.example.componentstest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.componentstest.data.Title;

import java.util.ArrayList;
import java.util.List;

public class TitleAdapter extends RecyclerView.Adapter<TitleAdapter.Titleholder> {

    private List<Title> titleList;
    private OnItemClickListener onItemClickListener;

    public TitleAdapter() {
        titleList = new ArrayList<Title>();
    }

    public void setTitles(List<Title> titleList){
        this.titleList = titleList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }

    @NonNull
    @Override
    public Titleholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.titile_item, parent, false);
        return new Titleholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Titleholder holder, int position) {
        Title title = titleList.get(position);
        holder.text.setText(title.getText());
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

    public class Titleholder extends RecyclerView.ViewHolder{
        private TextView text;

        public Titleholder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null)
                        onItemClickListener.onClick(getAdapterPosition());
                }
            });
        }
    }
}
