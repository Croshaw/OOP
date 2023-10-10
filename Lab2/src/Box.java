public class Box {
    private int length;
    private int height;
    private int width;
    public Box(int length, int height, int width) {
        this.length = length;
        this.height = height;
        this.width = width;
    }
    public float getVolume() {
        return length*height*width;
    }
    public int getLength() {
        return length;
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public String toString() {
        return "Длина: %s Высота: %s Ширина: %s".formatted(length, height, width);
    }
}
