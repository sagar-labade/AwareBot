package earlylesson.sagar.com.earlylesson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import java.util.List;

public class SHomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private PostAdapter postAdapter;

    FirebaseDatabase fd;
    DatabaseReference dr;
    ChildEventListener childEventListener;
    List<Post> datalist;
    public String mclass;

    public SHomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.shome_fragment, container, false);
        mclass = this.getArguments().getString("data");

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        datalist = new ArrayList<>();
        fd = FirebaseDatabase.getInstance();
        switch (mclass) {
            case "First Year":
                dr = fd.getReference("Posts/First Year");
                break;
            case "Second Year":
                dr = fd.getReference("Posts/Second Year");
                break;
            case "Third Year":
                dr = fd.getReference("Posts/Third Year");
                break;
        }

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Post post = dataSnapshot.getValue(Post.class);
                datalist.add(0, post);

                postAdapter.notifyDataSetChanged();//refreshes data in recycler view
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Post post = dataSnapshot.getValue(Post.class);
                datalist.add(0, post);
                postAdapter.notifyDataSetChanged();//refreshes data in recycler view
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue(Post.class);
                datalist.add(0, post);
                postAdapter.notifyDataSetChanged();//refreshes data in recycler view
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Post post = dataSnapshot.getValue(Post.class);
                datalist.add(0, post);
                postAdapter.notifyDataSetChanged();//refreshes data in recycler view
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getActivity(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        dr.addChildEventListener(childEventListener);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        postAdapter = new PostAdapter(getActivity(), datalist);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(postAdapter);
        super.onViewCreated(view, savedInstanceState);
    }
}
