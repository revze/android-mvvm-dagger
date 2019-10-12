package id.revan.mvvmwithdagger.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.revan.mvvmwithdagger.R;
import id.revan.mvvmwithdagger.model.Post;
import id.revan.mvvmwithdagger.ui.postdetail.PostDetailActivity;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> posts;

    public PostAdapter(List<Post> posts) {
        this.posts = posts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final PostViewHolder holder, int position) {
        final Post post = posts.get(position);
        final Context context = holder.itemView.getContext();

        holder.tvTitle.setText(post.getTitle());
        holder.tvBody.setText(post.getBody());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent postDetailIntent = new Intent(context, PostDetailActivity.class);
                postDetailIntent.putExtra(PostDetailActivity.ID, post.getId());
                context.startActivity(postDetailIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class PostViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvBody;

        PostViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvBody = itemView.findViewById(R.id.tv_body);
        }
    }
}
