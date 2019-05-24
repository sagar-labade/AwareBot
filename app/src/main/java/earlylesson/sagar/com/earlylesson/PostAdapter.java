package earlylesson.sagar.com.earlylesson;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    private List<Post> mpost;
    private Context mContext;


    public PostAdapter(Context context, List<Post> post) {
        mpost = post;
        mContext = context;
    }

    @NonNull
    @Override
    public PostAdapter.PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post, parent, false);
        return new PostViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {

        final Post currentPost = mpost.get(position);
        holder.field1.setText(currentPost.getField1());
        holder.field2.setText(currentPost.getField2());
        holder.field3.setText(currentPost.getField3());
        holder.field4.setText(currentPost.getField4());
        holder.field5.setText(currentPost.getField5());
        if (currentPost.getField1().equals("Checking") || currentPost.getField1().equals("Submission")) {
            holder.field6.setVisibility(View.GONE);
        }
        if (currentPost.getField1().equals("Notice")) {
            holder.field6.setVisibility(View.GONE);
            holder.field4.setVisibility(View.GONE);
        }
        try {
            if (currentPost.getField2().equals("Other")) {
                holder.field2.setVisibility(View.GONE);
                holder.field4.setVisibility(View.GONE);
                if (currentPost.getField6().trim().equals("") || currentPost.getField6().trim().isEmpty()) {
                    holder.field6.setVisibility(View.GONE);
                }
            }
        } catch (NullPointerException e) {}
        holder.field6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, mWebView.class);
                intent.putExtra("mUrl", currentPost.getField6());
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {

        if (mpost.size() >= 100) return 100;
        return mpost.size();

    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        TextView field1;
        TextView field2;
        TextView field3;
        TextView field4;
        TextView field5;
        Button field6;

        public PostViewHolder(View itemView) {
            super(itemView);
            field1 = itemView.findViewById(R.id.field1);
            field2 = itemView.findViewById(R.id.field2);
            field3 = itemView.findViewById(R.id.field3);
            field4 = itemView.findViewById(R.id.field4);
            field5 = itemView.findViewById(R.id.field5);
            field6 = itemView.findViewById(R.id.field6);
        }
    }
}