package com.woodamax.stm32kb;

/**
 * Created by maxim on 15.05.2017.
 * Needed for error handling and maybe other stuff
 */

public class BackgroundWorkerHelper {
    int count;
    int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
