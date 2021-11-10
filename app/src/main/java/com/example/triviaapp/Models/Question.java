package com.example.triviaapp.Models;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String image = "";
    private List<String> answers = new ArrayList<>();

    public Question() {
    }

    public String getImage() {
        return image;
    }

    public Question setImage(String image) {
        this.image = image;
        return this;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public Question setAnswers(List<String> answers) {
        this.answers = answers;
        return this;
    }
}
