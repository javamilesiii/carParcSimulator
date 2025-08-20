package parkhausSimulator.app.src.main.java.ch.javamilesiii;

public class Barrier {
    private String barrierName;
    public Barrier(String name) {
        this.barrierName = name;
    }

    public void passThroughBarrier() throws InterruptedException {
        this.openBarrier();
        System.out.println("...");
        Thread.sleep(2000);
        this.closeBarrier();
    }
    public void openBarrier() {
        System.out.println(this.barrierName + " is now open.");
    }
    public void closeBarrier() {
        System.out.println(this.barrierName + " is now closed.");
    }
}
