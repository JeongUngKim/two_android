package com.example.two.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.two.MyReviewActivity;
import com.example.two.R;
import com.example.two.ReviewUpdateActivity;
import com.example.two.SearchContentActivity;
import com.example.two.model.Seach;
import com.example.two.model.reView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {


    Context context;

    ArrayList<reView> reViewArrayList;

    public ReviewAdapter(Context context, ArrayList<reView> reViewArrayList) {
        this.context = context;
        this.reViewArrayList = reViewArrayList;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_row,parent,false);

        return new ReviewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
        reView review = reViewArrayList.get(position);

        holder.txtTitle.setText(review.getTitle());
        holder.txtContent.setText(review.getContent());
        holder.contentRating.setText(String.valueOf(review.getUserRating()));
    }

    @Override
    public int getItemCount() {
        return reViewArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtContent;

        TextView contentRating;

        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
            cardView = itemView.findViewById(R.id.cardView);
            contentRating = itemView.findViewById(R.id.contentRating);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    reView review = reViewArrayList.get(index);
                    Intent intent = new Intent(context, ReviewUpdateActivity.class);
                    intent.putExtra("Id",reViewArrayList.get(index).getContentReviewUserId());
                    intent.putExtra("contentId",reViewArrayList.get(index).getContentId());
                    intent.putExtra("reviewId",reViewArrayList.get(index).getContentReviewId());
                    intent.putExtra("title",reViewArrayList.get(index).getTitle());
                    intent.putExtra("content",reViewArrayList.get(index).getContent());
                    intent.putExtra("rating",reViewArrayList.get(index).getUserRating());
                    context.startActivity(intent);
                }
            });





        }
    }
}
