package parkhausSimulator.app.src.main.java.ch.javamilesiii;

public class Display {
    private int freeSpaces;

    public void setFreeSpaces(int freeSpaces) {
        this.freeSpaces = freeSpaces;
    }
    public void updateDisplay() {
        System.out.println("Display: " + freeSpaces + " spaces left");
    }
}
