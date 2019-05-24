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

public class PostTypeChecking extends Fragment {
    Button btn_create_checking_post,btn_class;
    EditText et_checking_subject;
    EditText et_checking_place;
    EditText et_checking_time;
    String class_name;
    EditText et_checking_teacher;
    DatabaseReference dr;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.post_type_checking, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        btn_create_checking_post = view.findViewById(R.id.btn_create_checking_post);
        et_checking_subject = view.findViewById(R.id.et_checking_subject);
        et_checking_place = view.findViewById(R.id.et_checking_place);
        et_checking_time = view.findViewById(R.id.et_checking_time);
        et_checking_teacher = view.findViewById(R.id.et_checking_teacher);
        btn_class=view.findViewById(R.id.btn_class);
        Calendar calendar = Calendar.getInstance();
        final int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        final int currentMinute = calendar.get(Calendar.MINUTE);

        et_checking_time.setOnClickListener(new View.OnClickListener() {
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
                        et_checking_time.setText(String.format("%02d:%02d", hourOfDay, minutes) + " " + amPm);
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
        btn_create_checking_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (et_checking_subject.getText().toString().isEmpty()||et_checking_teacher.getText().toString().isEmpty()||et_checking_time.getText().toString().isEmpty()||btn_class.getText().toString().equals("Select Class"))
                {
                    Toast.makeText(getActivity(), "Please, fill all the fields first!\n", Toast.LENGTH_SHORT).show();
                }
                else {
                    dr = FirebaseDatabase.getInstance().getReference();
                    Post post = new Post();
                    post.setField1("Checking");
                    post.setField2(et_checking_subject.getText().toString());
                    post.setField3(et_checking_place.getText().toString());
                    post.setField4(et_checking_time.getText().toString());
                    post.setField5(et_checking_teacher.getText().toString());

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
