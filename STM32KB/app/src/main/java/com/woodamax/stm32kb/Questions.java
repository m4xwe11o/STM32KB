package com.woodamax.stm32kb;

/**
 * Created by maxim on 14.05.2017.
 * Used to create the questions after the user is registered
 */

public class Questions {
    int ID;
    String question;

    public Questions(int ID, String question) {
        this.ID = ID;
        this.question = question;
    }

    public int getID() {

        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
