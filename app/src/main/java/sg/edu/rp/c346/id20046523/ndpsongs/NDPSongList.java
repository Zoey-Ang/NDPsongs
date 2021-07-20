package sg.edu.rp.c346.id20046523.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class NDPSongList extends AppCompatActivity {

    Button btn5star;
    ListView lvSongs;
    Song inserted;
    ArrayList<Song> allSongList;
    ArrayList <Song> fiveStarList;
    ArrayAdapter<Song> aa;
    ArrayAdapter<Song> aa5;

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(NDPSongList.this);
        if(!btn5star.isSelected())
        {
            aa.clear();
            aa.addAll(dbh.getAllSongs());
        }
        else
        {
            fiveStarList.clear();
            fiveStarList.addAll(dbh.getAllSongs());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_d_p_song_list);

        btn5star=findViewById(R.id.btn5stars);
        lvSongs=findViewById(R.id.lvSongs);

        Intent i =getIntent();
        inserted = (Song) i.getSerializableExtra("inserted");

        //allSongList = new ArrayList<Song>();
        ArrayList<Song> allSongList = (ArrayList<Song>) getIntent().getSerializableExtra("allSongs");
        aa = new ArrayAdapter<Song>(NDPSongList.this, android.R.layout.simple_list_item_1,allSongList);
        lvSongs.setAdapter(aa);

        fiveStarList =new ArrayList<Song>();

        aa5 =new ArrayAdapter<Song>(NDPSongList.this, android.R.layout.simple_list_item_1,fiveStarList);
        lvSongs.setAdapter(aa5);

        btn5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(NDPSongList.this);
                for(int i=0; i<allSongList.size(); i++)
                {
                    if(allSongList.get(i).getStars()== 5)
                    {
                        fiveStarList.add(dbh.getAllSongs().get(i));
                        aa5.notifyDataSetChanged();
                    }
                    else
                    {
                        allSongList.add(dbh.getAllSongs().get(i));
                    }
                }
            }
        });

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song data = allSongList.get(position);
                Intent i = new Intent(NDPSongList.this,editSong.class);
                i.putExtra("data",data);
                startActivity(i);
            }
        });

    }
}