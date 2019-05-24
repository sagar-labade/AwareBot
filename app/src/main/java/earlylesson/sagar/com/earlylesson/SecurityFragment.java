package earlylesson.sagar.com.earlylesson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SecurityFragment extends Fragment {

    EditText etTypePass1;
    EditText etTypePass2;
    EditText etTypePass3;
    TextView tv_show_passwords1;
    TextView tv_show_passwords2;
    TextView tv_show_passwords3;
    Button btn_set_password1,btn_set_password2,btn_set_password3;
    String Student1,Student2,Student3;
    DatabaseReference drPasswords;
    FirebaseDatabase fd;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.security_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etTypePass1=view.findViewById(R.id.etTypePass1);
        etTypePass2=view.findViewById(R.id.etTypePass2);
        etTypePass3=view.findViewById(R.id.etTypePass3);

        tv_show_passwords1=view.findViewById(R.id.tv_show_passwords1);
        tv_show_passwords2=view.findViewById(R.id.tv_show_passwords2);
        tv_show_passwords3=view.findViewById(R.id.tv_show_passwords3);


        fd = FirebaseDatabase.getInstance();
        drPasswords = fd.getReference("Passwords");





        drPasswords.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String Student1 = String.valueOf(dataSnapshot.child("Student1").getValue());
                String Student2 = String.valueOf(dataSnapshot.child("Student2").getValue());
                String Student3 = String.valueOf(dataSnapshot.child("Student3").getValue());
                tv_show_passwords1.setText("First Year: "+Student1);
                tv_show_passwords2.setText("Second Year: "+Student2);
                tv_show_passwords3.setText("Third Year: "+Student3);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}});


        view.findViewById(R.id.btn_set_password1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pass1 = etTypePass1.getText().toString();
                drPasswords.child("Student1").setValue(Pass1);
                Toast.makeText(getActivity(), "Password set successfully.", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.btn_set_password2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pass2 = etTypePass2.getText().toString();
                drPasswords.child("Student2").setValue(Pass2);
                Toast.makeText(getActivity(), "Password set successfully.", Toast.LENGTH_SHORT).show();
            }
        });
        view.findViewById(R.id.btn_set_password3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Pass3 = etTypePass3.getText().toString();
                drPasswords.child("Student3").setValue(Pass3);
                Toast.makeText(getActivity(), "Password set successfully.", Toast.LENGTH_SHORT).show();
            }
        });


    }



}
