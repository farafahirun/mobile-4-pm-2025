package com.example.tp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAccount extends AppCompatActivity {
    RecyclerView recyclerView;
    PostAdapter adapter;
    List<Post> postList;
    ImageView kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_main);

//        Tombol kembali ke activity_main
        kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Berpindah ke MainActivity
                Intent intent = new Intent(MainAccount.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postList = new ArrayList<>();
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "01 Apr 23",
                "PEACEMINUSONE x NIKE 'KIIIONDO1'COMING SOON🔥 #Kwondo1 #PEACEMINUSONE #NIKE #FreedomInFlow",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "954", "7,5K", "29,1K", "1,9M"));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "01 Apr 23",
                "[THE FIRST-EVER BMW XM]\nG-Dragon x BMW XM\n'BREAK THE E NORM'\n@BMWMotorsport",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "454", "6,4K", "23,4K", "1,7M"));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "01 Apr 23",
                "\uD83D\uDD52Act.III, AT3:00AM\uD83D\uDD52 seoul, South Korea \ninstagram.com/p/BYBlz-vAW2A/",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "229", "2,5K", "18K", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "25 Feb 18",
                "'Untitled, 2017' -2018.2.25 Jeju Shinhwa World",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "17,4K", "7,6K", "61,2kK", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "23 Nov 17",
                "#BIGBANGJAPANDOMETOUR2017 #LASTDANCE #FUKUOKA > #OSAKA instagram.com/p/Bb0c_dgg61_/",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "296", "3,8K", "23,1K", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "31 Okt 17",
                "BIGBANG 2017 CONCERT <LAST DANCE> IN SEOUL\n\n(Date & Time) : 2017.12.30 (SAT) 6PM -12.31...",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "274", "3,8K", "21,3K", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "03 Okt 17",
                "#CHANELSpringSummer grand Palais - RMN (Officiel)",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "148", "2,8K", "18,7K", ""));
        postList.add(new Post("G-DRAGON", "@IBGDRGN", "01 Mei 17",
                "#My8second instagram.com/p/BTi2xROg5NE/",
                R.drawable.fotoprofil, R.drawable.centangbiru,
                "58K", "1,7K", "9,6K", ""));

        adapter = new PostAdapter(this, postList);
        recyclerView.setAdapter(adapter);
    }
}
