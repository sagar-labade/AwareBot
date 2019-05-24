package earlylesson.sagar.com.earlylesson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityLoginT extends AppCompatActivity {
    EditText etPassT;
    Button btnLogT;
    FirebaseDatabase fd;
    DatabaseReference drPasswords;
    String teacher;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logint);

        etPassT = (EditText) findViewById(R.id.etPassT);
        btnLogT = (Button) findViewById(R.id.btnLogT);
        fd = FirebaseDatabase.getInstance();
        drPasswords = fd.getReference("Passwords");
        drPasswords.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 teacher = String.valueOf(dataSnapshot.child("Teacher").getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ActivityLoginT.this, "DataConnection Error", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPassT.getText().toString().equals(teacher)) {

                    Intent logt = new Intent(ActivityLoginT.this, Teacher.class);
                    startActivity(logt);
                } else if (etPassT.getText().toString().equals("")) {
                    Toast.makeText(ActivityLoginT.this, "Fill the fields first!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ActivityLoginT.this, "You entered wrong password!\nThis section is for Teachers.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}