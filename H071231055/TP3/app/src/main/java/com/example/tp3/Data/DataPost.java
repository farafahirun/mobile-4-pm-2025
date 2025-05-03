package com.example.tp3.Data;

import com.example.tp3.Models.Post;
import com.example.tp3.Models.Upload;
import com.example.tp3.R;

import java.util.ArrayList;
import java.util.List;

public class DataPost {

    public static ArrayList<Post> posts = generateDummyPosts();
    public static List<Upload> uploadList = new ArrayList<>();

    private static ArrayList<Post> generateDummyPosts() {
        ArrayList<Post> posts = new ArrayList<>();

        List<Integer> zainab = new ArrayList<>();
        zainab.add(R.drawable.zainab1_1);
        zainab.add(R.drawable.zainab1_2);
        zainab.add(R.drawable.zainab1_3);

        posts.add(new Post(R.drawable.zaynabzhaa10, zainab, "zaynabzhaa10", "143", "26", "",  "zaynabzhaa10  29 Agustus 2015 - 20 Juli 2023\n" +
                "1 Windu sudah,\n" +
                "Bukan waktu yang singkat\n" +
                "namun berlalu begitu cepat.\n" +
                "\n" +
                "Kisah-kisah lama akan menjadi kenangan.\n" +
                "Berpadu dalam angan dan harapan.\n" +
                "Meminta pertemuan disegerakan.\n" +
                "Walau terus bertanya entah kapan.\n" +
                "\n" +
                "Tangisku memecah keheningan malam.\n" +
                "Menabrak arus angin yang kian mencekam.\n" +
                "Masih banyak yang ingin kulakukan di pulau ini.\n" +
                "Namun, tanah kelahiran menarikku untuk mengabdi.\n" +
                "\n" +
                "Akhirnya, kuucapkan \"Sampai jumpa di lain hari.\"\n" +
                "Hingga Allah Izinkan bersua lagi.\n" +
                "\n" +
                "Surabaya,\n" +
                "20 Juli 2023.\n" +
                "\n" +
                "#anakrantau #anakrantaupunyacerita #storyofmylife", "3", "1.095", "686", "\uD835\uDCE9\uD835\uDCEA\uD835\uDCF2\uD835\uDCF7\uD835\uDCEA\uD835\uDCEB \uD835\uDCDC\uD835\uDCFE\uD835\uDCEC\uD835\uDCF1\uD835\uDCFC\uD835\uDCF2\uD835\uDCF7\uD835\uDCF2\uD835\uDCF7", "✏\uFE0F @penaqu.cans✏\uFE0F\n" +
                "✨ An Admirer of Ibn Battuta & Ibn Katsir ✨\n" +
                "\uD83C\uDF13 Pengembara waktu, merangkai kisah di setiap persimpangan \uD83C\uDF13", zainab, zainab));


        List<Integer> ervin = new ArrayList<>();
        ervin.add(R.drawable.ervin1_1);
        ervin.add(R.drawable.ervin1_2);
        ervin.add(R.drawable.ervin1_3);
        ervin.add(R.drawable.ervin1_4);
        ervin.add(R.drawable.ervin1_5);
        ervin.add(R.drawable.ervin1_6);
        ervin.add(R.drawable.ervin1_7);
        ervin.add(R.drawable.ervin1_8);
        ervin.add(R.drawable.ervin1_9);

        posts.add(new Post(R.drawable.ervinhsn, ervin, "ervinhsn", "67", "2", "6",  "ervinhsn  Child", "4", "583", "435", "Ervink", "@house.pace", zainab, zainab));

        List<Integer> suci = new ArrayList<>();
        suci.add(R.drawable.suci1_1);
        suci.add(R.drawable.suci1_2);
        suci.add(R.drawable.suci1_3);
        suci.add(R.drawable.suci1_4);
        suci.add(R.drawable.suci1_5);
        suci.add(R.drawable.suci1_6);
        suci.add(R.drawable.suci1_7);

        posts.add(new Post(R.drawable.suci, suci, "suci.ss_", "", "15", "",  "suci.ss_  get this princess outfit \uD801\uDE5A ˖ ݁\uD81A\uDD54 ݁\n" +
                "\n" +
                "\uD83D\uDC57 : @trinzqueen.maker\n" +
                "\uD83D\uDCF8 : @rifdaarafaah", "3", "1.704", "1.282", "거룩한\uD83D\uDC07", "@haechanahceah loved.\n", zainab, zainab));

        return posts;
    }
}