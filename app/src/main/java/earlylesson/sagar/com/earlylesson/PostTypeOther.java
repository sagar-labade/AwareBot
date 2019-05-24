package earlylesson.sagar.com.earlylesson;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostTypeOther extends Fragment {
    EditText et_other_title;
    EditText et_other_description;
    EditText et_other_teacher;
    EditText et_other_web;
    String url,class_name;
    Button btn_create_other_post,btn_class;
    DatabaseReference dr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_type_other, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        et_other_title = view.findViewById(R.id.et_other_title);
        et_other_description = view.findViewById(R.id.et_other_description);
        et_other_teacher = view.findViewById(R.id.et_other_teacher);
        et_other_web=view.findViewById(R.id.et_other_web);
        btn_create_other_post = view.findViewById(R.id.btn_create_other_post);
        btn_class=view.findViewById(R.id.btn_class);

        btn_class.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getActivity(), btn_class);

                popup.getMenuInflater().inflate(R.menu.type_class, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        btn_class.setText(item.getTitle());
                        class_name=btn_class.getText().toString();
                        return true;
                    }
                });
                popup.show();
            }

        });


        btn_create_other_post.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (et_other_title.getText().toString().isEmpty()||et_other_description.getText().toString().isEmpty()||et_other_teacher.getText().toString().isEmpty()||btn_class.getText().toString().equals("Select Class"))
                {
                    Toast.makeText(getActivity(), "Please, fill all the fields first!\n", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    dr = FirebaseDatabase.getInstance().getReference();
                    Post post = new Post();
                    post.setField1(et_other_title.getText().toString());
                    post.setField2("Other");
                    post.setField3(et_other_description.getText().toString());
                    post.setField5(et_other_teacher.getText().toString());
                    post.setField6(et_other_web.getText().toString());

                    switch (class_name)
                    {
                        case "First Year":
                            dr.child("Posts/First Year").push().setValue(post);
                            break;
                        case "Second Year":
                            dr.child("Posts/Second Year").push().setValue(post);
                            break;
                        case "Third Year":
                            dr.child("Posts/Third Year").push().setValue(post);
                            break;
                    }
                        Toast.makeText(getActivity(), "Post Created.", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

}
