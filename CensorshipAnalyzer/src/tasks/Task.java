package tasks;


import java.util.TimerTask;

public class Task extends TimerTask {


    int count = 1;

    // run is a abstract method that defines task performed at scheduled time.
    public void run() {
        System.out.println(count+" : Mahendra Singh");
        count++;
    }
}