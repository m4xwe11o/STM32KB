package com.woodamax.stm32kb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxim on 14.05.2017.
 * Used to create the questions after the user is registered
 */

public class Questions {
    int ID;
    List<String> questions = new ArrayList<>();
    List<String> answers = new ArrayList<>();

    public List<String> getQuestion() {
        return questions;
    }

    public void setQuestion(List<String> question) {
        this.questions = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> question) {
        this.answers = question;
    }


    public int getID() {

        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    String q1;
    String q2;
    String q3;
    String q4;

    boolean q1a1, q1a2, q1a3, q1a4;
    boolean q2a1, q2a2, q2a3, q2a4;
    boolean q3a1, q3a2, q3a3, q3a4;
    boolean q4a1, q4a2, q4a3, q4a4;

    public String getQ1() {
        return q1;
    }

    public void setQ1(String q1) {
        this.q1 = q1;
    }

    public String getQ2() {
        return q2;
    }

    public void setQ2(String q2) {
        this.q2 = q2;
    }

    public String getQ3() {
        return q3;
    }

    public void setQ3(String q3) {
        this.q3 = q3;
    }

    public String getQ4() {
        return q4;
    }

    public void setQ4(String q4) {
        this.q4 = q4;
    }

    public boolean isQ1a1() {
        return q1a1;
    }

    public void setQ1a1(boolean q1a1) {
        this.q1a1 = q1a1;
    }

    public boolean isQ1a2() {
        return q1a2;
    }

    public void setQ1a2(boolean q1a2) {
        this.q1a2 = q1a2;
    }

    public boolean isQ1a3() {
        return q1a3;
    }

    public void setQ1a3(boolean q1a3) {
        this.q1a3 = q1a3;
    }

    public boolean isQ1a4() {
        return q1a4;
    }

    public void setQ1a4(boolean q1a4) {
        this.q1a4 = q1a4;
    }

    public boolean isQ2a1() {
        return q2a1;
    }

    public void setQ2a1(boolean q2a1) {
        this.q2a1 = q2a1;
    }

    public boolean isQ2a2() {
        return q2a2;
    }

    public void setQ2a2(boolean q2a2) {
        this.q2a2 = q2a2;
    }

    public boolean isQ2a3() {
        return q2a3;
    }

    public void setQ2a3(boolean q2a3) {
        this.q2a3 = q2a3;
    }

    public boolean isQ2a4() {
        return q2a4;
    }

    public void setQ2a4(boolean q2a4) {
        this.q2a4 = q2a4;
    }

    public boolean isQ3a1() {
        return q3a1;
    }

    public void setQ3a1(boolean q3a1) {
        this.q3a1 = q3a1;
    }

    public boolean isQ3a2() {
        return q3a2;
    }

    public void setQ3a2(boolean q3a2) {
        this.q3a2 = q3a2;
    }

    public boolean isQ3a3() {
        return q3a3;
    }

    public void setQ3a3(boolean q3a3) {
        this.q3a3 = q3a3;
    }

    public boolean isQ3a4() {
        return q3a4;
    }

    public void setQ3a4(boolean q3a4) {
        this.q3a4 = q3a4;
    }

    public boolean isQ4a1() {
        return q4a1;
    }

    public void setQ4a1(boolean q4a1) {
        this.q4a1 = q4a1;
    }

    public boolean isQ4a2() {
        return q4a2;
    }

    public void setQ4a2(boolean q4a2) {
        this.q4a2 = q4a2;
    }

    public boolean isQ4a3() {
        return q4a3;
    }

    public void setQ4a3(boolean q4a3) {
        this.q4a3 = q4a3;
    }

    public boolean isQ4a4() {
        return q4a4;
    }

    public void setQ4a4(boolean q4a4) {
        this.q4a4 = q4a4;
    }
}
