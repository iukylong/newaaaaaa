package com.example.longlongtran.compehtml;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ArticleAdapter  extends RecyclerView.Adapter<ArticleAdapter.ArticleHolder> {
    private final Activity activity;
    private final ArrayList<Article> listAticle;

    public ArticleAdapter(Activity activity, ArrayList<Article> listAticle) {
        this.activity = activity;
        this.listAticle = listAticle;
    }

    @NonNull
    @Override
    public ArticleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        return new ArticleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArticleHolder holder, int position) {
        final Article article = listAticle.get(position);
        holder.tvTitle.setText(article.getTitle());
        Glide.with(activity)
                .load(article.getThumnail())
                .asBitmap()
                .atMost()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .animate(android.R.anim.fade_in)
                .approximate()
                .into(holder.imgThumnal);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity,DetailArticleActivity.class).putExtra("Article",article));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listAticle.size();
    }

    class ArticleHolder extends RecyclerView.ViewHolder{

        private final ImageView imgThumnal;
        private final TextView tvTitle;
        ArticleHolder(View itemView) {
            super(itemView);
            imgThumnal =  itemView.findViewById(R.id.img_thumbnail);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}