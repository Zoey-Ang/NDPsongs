package sg.edu.rp.c346.id20046523.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSinger, etYear;
    RadioGroup rgRatings;
    Button btnInsert, btnShow;
    ArrayList<Song> allSongList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle);
        etSinger=findViewById(R.id.etSingers);
        etYear= findViewById(R.id.etYear);
        rgRatings=findViewById(R.id.rgRatings);
        btnInsert=findViewById(R.id.btnInsert);
        btnShow=findViewById(R.id.btnShowList);

        allSongList = new ArrayList<Song>();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer =etSinger.getText().toString();
                String year = etYear.getText().toString();

                String star;
                int checkRadioId = rgRatings.getCheckedRadioButtonId();
                if(checkRadioId == R.id.rb1)
                {
                    star="1";
                }
                else if (checkRadioId == R.id.rb2)
                {
                    star="2";
                }
                else if (checkRadioId == R.id.rb3)
                {
                    star="3";
                }
                else if (checkRadioId == R.id.rb4)
                {
                    star="4";
                }
                else
                {
                    star="5";
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted = dbh.insertSong(title,singer,
                        year, star);

                if (inserted != -1)
                {
                    allSongList.clear();
                    allSongList.addAll(dbh.getAllSongs());
                    Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,NDPSongList.class);
                startActivity(i);
            }
        });

    }
}