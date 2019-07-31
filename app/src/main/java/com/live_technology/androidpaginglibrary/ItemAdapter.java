package com.live_technology.androidpaginglibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ItemAdapter extends PagedListAdapter<StackApiResponse.Item, ItemAdapter.ItemViewHolder> {
    private Context mCtx;

    ItemAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }


    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_users, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        StackApiResponse.Item item = getItem(position);

        if (item != null) {
            holder.textView.setText(item.owner.display_name);
            Glide.with(mCtx)
                    .load(item.owner.profile_image)
                    .into(holder.imageView);
        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<StackApiResponse.Item> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<StackApiResponse.Item>() {
                @Override
                public boolean areItemsTheSame(StackApiResponse.Item oldItem, StackApiResponse.Item newItem) {
                    return oldItem.question_id == newItem.question_id;
                }

                @SuppressLint("DiffUtilEquals")
                @Override
                public boolean areContentsTheSame(StackApiResponse.Item oldItem, StackApiResponse.Item newItem) {
                    return oldItem.equals(newItem);
                }
            };


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textViewName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}

