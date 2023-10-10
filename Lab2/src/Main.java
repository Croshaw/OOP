public class Main {
    public static void main(String[] args) {
        Box[] boxes = new Box[] { new Box(5,2,3), new Box(2,6,7), new Box(4,3,5) };
        float sumVolume = 0;
        for(Box box : boxes) {
            System.out.println(box.toString());
            sumVolume += box.getVolume();
        }
        System.out.printf("Суммарный объём коробок: %s", sumVolume);
    }
}