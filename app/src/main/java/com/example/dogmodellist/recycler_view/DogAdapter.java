package com.example.dogmodellist.recycler_view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.dogmodellist.retrofit.ImageLoad;
import com.example.dogmodellist.R;
import com.example.dogmodellist.retrofit.DataModel;

import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.DogviewHolder> {

    private Context ctx;
    private List<DataModel> dog;
    private DataModel dataModel;

    public DogAdapter(Context mctx, List<DataModel> dog){
        this.ctx =mctx;
        this.dog= dog;
    }

    @NonNull
    @Override
    public DogviewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.recycler_view, null);
        return new DogviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogviewHolder dogviewHolder, int i) {
        dataModel=dog.get(i);
        new ImageLoad(dataModel.getMessage(), dogviewHolder.iv).execute();
    }

    @Override
    public int getItemCount() {

        return dog.size();
    }

    public class DogviewHolder extends RecyclerView.ViewHolder {

        ImageView iv;
        public DogviewHolder(@NonNull View itemView) {
            super(itemView);
                iv = itemView.findViewById(R.id.iv);

        }
    }
}
