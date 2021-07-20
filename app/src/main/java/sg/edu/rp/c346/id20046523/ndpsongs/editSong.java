package sg.edu.rp.c346.id20046523.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class editSong extends AppCompatActivity {

    EditText etID,etTitle, etSinger, etYear;
    RadioGroup rgeditStars;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;
    RadioButton rb1,rb2,rb3,rb4,rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        etID=findViewById(R.id.etID);
        etTitle=findViewById(R.id.etTitle);
        etSinger=findViewById(R.id.etSingers);
        etYear=findViewById(R.id.etYear);
        rgeditStars=findViewById(R.id.rgeditStars);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnDelete=findViewById(R.id.btnDelete);
        btnCancel=findViewById(R.id.btnCancel);
        rb1=findViewById(R.id.editrb1);
        rb2=findViewById(R.id.editrb2);
        rb3=findViewById(R.id.editrb3);
        rb4=findViewById(R.id.edit4);
        rb5=findViewById(R.id.editrb5);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setEnabled(false);
        etID.setHint(data.getId());
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getYear());
        int numStar = data.getStars();
        if(numStar == 1)
        {
            rb1.setChecked(true);
        }
        else if (numStar==2)
        {
            rb2.setChecked(true);
        }
        else if (numStar==3)
        {
            rb3.setChecked(true);
        }
        else if (numStar==4)
        {
            rb4.setChecked(true);
        }
        else if (numStar==5)
        {
            rb5.setChecked(true);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(editSong.this);
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSinger.getText().toString());
                int year =Integer.parseInt(etYear.getText().toString());
                data.setYear(year);
                data.setStars(rgeditStars.getCheckedRadioButtonId());

                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(editSong.this);
                dbh.deleteNote(data.getId());
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}