package com.articles.newsfeed.model.data;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.articles.newsfeed.R;
import com.articles.newsfeed.model.pojo.Article;
import com.articles.newsfeed.view.LoadWebviewActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class LoadArticlesAdapter extends RecyclerView.Adapter<LoadArticlesAdapter.ArticleViewHolder> implements Filterable {
    private List<Article> articleList;
    private Context mContext;
    private List<Article> mFilteredList;

    public LoadArticlesAdapter(List<Article> articleList,Context context) {
        this.articleList = articleList;
        mFilteredList=articleList;
        this.mContext=context;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_layout, viewGroup, false);
        return new ArticleViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull final ArticleViewHolder articleViewHolder, final int position) {


      /*  if(articleViewHolder.isSelected)
        {
            articleViewHolder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.lightGrey));
            articleViewHolder.articleTitle.setTextColor(mContext.getResources().getColor(R.color.darkGrey));
        }
        else
        {
            articleViewHolder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.windowBackground));
            articleViewHolder.articleTitle.setTextColor(mContext.getResources().getColor(R.color.textColor));
        }*/
        articleViewHolder.articleTitle.setText(mFilteredList.get(position).getTitle());
       /* articleViewHolder.articleTitle.setTextColor(mContext.getResources().getColor(R.color.textColor));
        articleViewHolder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.appBartextColor));*/

        articleViewHolder.articleDescription.setText(mFilteredList.get(position).getDescription());
        articleViewHolder.articleURL.setText(mFilteredList.get(position).getUrl());

        articleViewHolder.articleURL.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //greying out the read articles
                articleViewHolder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.lightGrey));
                articleViewHolder.articleTitle.setTextColor(mContext.getResources().getColor(R.color.darkGrey));
               // notifyItemChanged(position);
                //notifyDataSetChanged();
                articleViewHolder.isSelected=true;
                Intent intent = new Intent(mContext, LoadWebviewActivity.class);
                intent.putExtra("url", mFilteredList.get(position).getUrl());
                mContext.startActivity(intent);
            }});

        Picasso.get()
                .load(mFilteredList.get(position).getUrlToImage())
                .fit()
                .into(articleViewHolder.articlePhoto);


    }

    @Override
    public int getItemCount() {
        return mFilteredList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = articleList;
                } else {

                    ArrayList<Article> filteredList = new ArrayList<>();

                    for (Article article : articleList) {

                        if (article.getTitle().toLowerCase().contains(charString)) {

                            filteredList.add(article);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<Article>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }




    static class ArticleViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView articleTitle;
        TextView articleDescription;
        TextView articleURL;
        ImageView articlePhoto;
        boolean isSelected=false;

        ArticleViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            articleTitle = itemView.findViewById(R.id.textViewTitle);
            articleDescription = itemView.findViewById(R.id.textViewDesc);
            articleURL = itemView.findViewById(R.id.textViewURL);
            articlePhoto = itemView.findViewById(R.id.imageArticle);
        }
    }

}