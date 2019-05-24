package earlylesson.sagar.com.earlylesson;


import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class PostTypeLecture extends Fragment {
    EditText et_lecture_time;
    EditText et_lecture_subject;
    EditText et_lecture_topic;
    EditText et_lecture_web;
    Button btn_class;
    String url,class_name;
    EditText et_lecture_teacher;
    DatabaseReference dr;
 //   private int countPosts = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_type_lecture, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        et_lecture_subject = view.findViewById(R.id.et_lecture_subject);
        et_lecture_topic = view.findViewById(R.id.et_lecture_topic);
        et_lecture_time = view.findViewById(R.id.et_lecture_time);
        et_lecture_web = view.findViewById(R.id.et_lecture_web);
        et_lecture_teacher = view.findViewById(R.id.et_lecture_teacher);
        btn_class=view.findViewById(R.id.btn_class);

        Calendar calendar = Calendar.getInstance();
        final int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        final int currentMinute = calendar.get(Calendar.MINUTE);

        et_lecture_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String amPm;
                        if (hourOfDay >= 12) {
                            hourOfDay = hourOfDay - 12;
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        et_lecture_time.setText(String.format("%02d:%02d", hourOfDay, minutes) + " " + amPm);
                    }
                }, currentHour, currentMinute, false);
                timePickerDialog.show();
            }
        });

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


        view.findViewById(R.id.btn_create_lecture_post).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_lecture_subject.getText().toString().isEmpty()||et_lecture_topic.getText().toString().isEmpty()||et_lecture_time.getText().toString().isEmpty()||btn_class.getText().toString().equals("Select Class"))
                {
                    Toast.makeText(getActivity(), "Please, fill all the fields first!\nAnd\nSelect class.", Toast.LENGTH_SHORT).show();
                }
                else if (et_lecture_web.getText().toString().isEmpty())
                {

                    dr = FirebaseDatabase.getInstance().getReference();
                    Post post = new Post();
                    post.setField1("Lecture");
                    post.setField2(et_lecture_subject.getText().toString());
                    post.setField3(et_lecture_topic.getText().toString());
                    post.setField4(et_lecture_time.getText().toString());
                    post.setField5(et_lecture_teacher.getText().toString());
                    post.setField6("https://www.google.com/search?q="+et_lecture_topic.getText());


                    switch (class_name) {
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
                else {

                    dr = FirebaseDatabase.getInstance().getReference();
                    Post post = new Post();
                    post.setField1("Lecture");
                    post.setField2(et_lecture_subject.getText().toString());
                    post.setField3(et_lecture_topic.getText().toString());
                    post.setField4(et_lecture_time.getText().toString());
                    post.setField5(et_lecture_teacher.getText().toString());
                    post.setField6(et_lecture_web.getText().toString());


                    switch (class_name) {
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



