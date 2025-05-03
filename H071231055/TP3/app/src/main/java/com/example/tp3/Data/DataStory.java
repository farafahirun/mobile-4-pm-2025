package com.example.tp3.Data;

import com.example.tp3.Models.Feed;
import com.example.tp3.Models.Story;
import com.example.tp3.R;

import java.util.ArrayList;
import java.util.List;

public class DataStory {
    public static ArrayList<Story> stories = generateDummyStories();

    private static ArrayList<Story> generateDummyStories() {
        ArrayList<Story> stories = new ArrayList<>();

        List<Integer> h1 = new ArrayList<>();
        h1.add(R.drawable.h1_1);
        h1.add(R.drawable.h1_2);
        h1.add(R.drawable.h1_3);
        h1.add(R.drawable.h1_4);
        stories.add(new Story(R.drawable.h1, "amazing", h1));

        List<Integer> h2 = new ArrayList<>();
        h2.add(R.drawable.h2_1);
        h2.add(R.drawable.h2_2);
        stories.add(new Story(R.drawable.h2, "w o w", h2));

        List<Integer> h3 = new ArrayList<>();
        h3.add(R.drawable.h3_1);
        h3.add(R.drawable.h3_2);
        h3.add(R.drawable.h3_3);
        h3.add(R.drawable.h3_4);
        h3.add(R.drawable.h3_5);
        stories.add(new Story(R.drawable.h3, "<3", h3));

        List<Integer> h4 = new ArrayList<>();
        h4.add(R.drawable.h4_1);
        stories.add(new Story(R.drawable.h4, "sky", h4));

        List<Integer> h5 = new ArrayList<>();
        h5.add(R.drawable.h5_1);
        h5.add(R.drawable.h5_2);
        stories.add(new Story(R.drawable.h5, "butterfly", h5));

        return stories;
    }
}
