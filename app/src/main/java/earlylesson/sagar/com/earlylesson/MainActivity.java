package earlylesson.sagar.com.earlylesson;

import java.util.Random;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvShow;
    TextView tvQuote;
    Button btnStudent, btnTeacher;
    int counter;
    String quote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStudent = findViewById(R.id.btnStudent);
        btnTeacher = findViewById(R.id.btnTeacher);
        tvQuote = findViewById(R.id.tvQuote);
        tvShow = findViewById(R.id.tvShow);

        tvShow.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/f1.otf"));
        tvQuote.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/f2.ttf"));
        Random r = new Random();
        counter = r.nextInt(20) + 1;
        generateQuote();
        tvQuote.setText(quote);

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PassS = new Intent(MainActivity.this, ActivityLoginS.class);
                startActivity(PassS);
            }
        });

        btnTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PassT = new Intent(MainActivity.this, ActivityLoginT.class);
                startActivity(PassT);
            }
        });


    }

    public void generateQuote() {
        switch (counter) {

            case 1:
                quote = "It always seems impossible until it’s done.";
                break;
            case 2:
                quote = "Start where you are. Use what you have. Do what you can.";
                break;
            case 3:
                quote = " Strive for progress, not perfection.";
                break;
            case 4:
                quote = "There are no shortcuts to any place worth going.";
                break;
            case 5:
                quote = "Life has two rules: 1) Never quit. 2) Always remember Rule #1.";
                break;
            case 6:
                quote = "It’s not going to be easy, but it’s going to be worth it.";
                break;
            case 7:
                quote = "You don’t drown by falling in the water; you drown by staying there";
                break;
            case 8:
                quote = "Keep going. Be all in.";
                break;
            case 9:
                quote = "Dream big. Pray bigger.";
                break;
            case 10:
                quote = "Stay hungry. Stay foolish.";
                break;
            case 11:
                quote = "Your life is your argument.";
                break;
            case 12:
                quote = "Fight till the last gasp.";
                break;
            case 13:
                quote = "If you want it, work for it.";
                break;
            case 14:
                quote = "Whatever you are, be a good one.";
                break;
            case 15:
                quote = "Take the risk or lose the chance.";
                break;
            case 16:
                quote = "Be faithful to that which exists within yourself. ";
                break;
            case 17:
                quote = "Procrastination makes easy things hard, hard things harder.";
                break;
            case 18:
                quote = "It’s not the time to look for excuses.";
                break;
            case 19:
                quote = "If it’s important to you, you’ll find a way. If it’s not, you’ll find excuses.";
                break;
            case 20:
                quote = "Your positive action combined with positive thinking results in success.";
                break;
        }
    }

}
