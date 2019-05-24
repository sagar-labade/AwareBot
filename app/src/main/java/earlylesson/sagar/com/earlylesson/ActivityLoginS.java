package earlylesson.sagar.com.earlylesson;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ActivityLoginS extends AppCompatActivity {
    EditText etPassS;
    Button btnLogS, btnClass;
    FirebaseDatabase fd;
    DatabaseReference drPasswords;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        etPassS = findViewById(R.id.etPassS);
        btnLogS = findViewById(R.id.btnLogS);
        btnClass = findViewById(R.id.btnClass);

        fd = FirebaseDatabase.getInstance();
        drPasswords = fd.getReference("Passwords");

        btnClass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(ActivityLoginS.this, btnClass);

                popup.getMenuInflater().inflate(R.menu.type_class, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        btnClass.setText(item.getTitle());


                        return true;
                    }
                });
                popup.show();
            }

        });
        btnLogS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drPasswords.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String Student1 = String.valueOf(dataSnapshot.child("Student1").getValue());
                        String Student2 = String.valueOf(dataSnapshot.child("Student2").getValue());
                        String Student3 = String.valueOf(dataSnapshot.child("Student3").getValue());

                        Intent logs = new Intent(ActivityLoginS.this, Student.class);

                        if (btnClass.getText().equals("First Year") && etPassS.getText().toString().equals(Student1)) {

                            Toast.makeText(ActivityLoginS.this, "Correct password!", Toast.LENGTH_SHORT).show();
                            logs.putExtra("className", "First Year");
                            startActivity(logs);
                        }
                        else if (btnClass.getText().equals("Second Year") && etPassS.getText().toString().equals(Student2)) {
                            Toast.makeText(ActivityLoginS.this, "Correct password!", Toast.LENGTH_SHORT).show();

                            logs.putExtra("className", "Second Year");
                            startActivity(logs);
                        }
                        else if (btnClass.getText().equals("Third Year") && etPassS.getText().toString().equals(Student3)) {
                            Toast.makeText(ActivityLoginS.this, "Correct password!", Toast.LENGTH_SHORT).show();

                            logs.putExtra("className", "Third Year");
                            startActivity(logs);
                        }
                        else if (btnClass.getText().toString().equals("Select Class") || etPassS.getText().toString().trim().equals("")) {
                            Toast.makeText(ActivityLoginS.this, "First, select your class and then\nEnter password!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(ActivityLoginS.this, "Wrong password!\nPlease contact your teachers.", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(ActivityLoginS.this, "Connection Failed.", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}