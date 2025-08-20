package parkhausSimulator.app.src.main.java.ch.javamilesiii;

public class SpotsDisplay {
    private int freeSpaces;

    public void setFreeSpaces(int freeSpaces) {
        this.freeSpaces = freeSpaces;
    }

    private void updateDisplay() {
        System.out.println("Display: " + freeSpaces + " spaces left");
    }

}
