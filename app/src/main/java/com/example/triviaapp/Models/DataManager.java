package com.example.triviaapp.Models;

import java.util.ArrayList;
import java.util.Arrays;

public class DataManager {
    public static ArrayList<Question> generateQuestion(){
        ArrayList<Question> questions = new ArrayList<>();

        questions.add(new Question()
                .setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Domestic_Cat_Face_Shot.jpg/1920px-Domestic_Cat_Face_Shot.jpg")
                .setAnswers(Arrays.asList("Cat","Dog","Cow","Fish"))
        );
        questions.add(new Question()
                .setImage("https://upload.wikimedia.org/wikipedia/commons/7/73/Lion_waiting_in_Namibia.jpg")
                .setAnswers(Arrays.asList("Lion","Tiger","Sloth","Fox"))
        );
        questions.add(new Question()
                .setImage("https://upload.wikimedia.org/wikipedia/commons/thumb/6/62/Chital_%288458215435%29.jpg/1920px-Chital_%288458215435%29.jpg")
                .setAnswers(Arrays.asList("Deer","Gorilla","Bear","Monkey"))
        );

        return questions;
    }
}
