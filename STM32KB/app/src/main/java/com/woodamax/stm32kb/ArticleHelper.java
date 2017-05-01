package com.woodamax.stm32kb;

/**
 * Created by maxim on 30.04.2017.
 */

public class ArticleHelper {
    int Id;
    boolean read;

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
