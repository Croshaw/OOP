import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Random rand = new Random();
    //region 1Zad
    public static void fillArray(int[][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length;j++) {
                array[i][j] = rand.nextInt(50-10)+10;
            }
        }
    }
    public static void printArray(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.printf("%s ", anInt);
            }
            System.out.print('\n');
        }
    }
    public static int[][] getArray() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите кол-во строк\n");
        int row = in.nextInt();
        System.out.print("Введите кол-во столбцов\n");
        int column = in.nextInt();
        int[][] array = new int[row][column];
        fillArray(array);
        return array;
    }
    public static int getColumnWithMaxAvg(int[][] array) {
        int columnInd = -1;
        double maxSum=0;
        double curSum=0;
        System.out.print("\n");
        for(int column = 0; column < array[0].length;column++) {
            curSum=0;
            for (int row = 0; row < array.length;row++) {
                curSum+=array[row][column];
            }
            curSum/= array.length;
            if(curSum>maxSum){
                maxSum = curSum;
                columnInd=column;
            }
            System.out.printf("%s ", Math.round(curSum));
        }
        System.out.print("\n");
        return columnInd;
    }
    public static void runFirstTask() {
        int[][] array = getArray();
        printArray(array);
        int columnWithMaxAvg = getColumnWithMaxAvg(array);
        System.out.printf("Столбец с максимальным среднем значением: %s", columnWithMaxAvg+1);
    }
    //endregion
    //region 2Zad
    public static List<String> getWords(String source) {
        return Arrays.stream(source.split(" ")).filter(x -> x.charAt(0)==x.charAt(x.length()-1)).toList();
    }
    public static void runSecTask() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите строку: ");
        String source = in.nextLine();
        List<String> result = getWords(source);
        System.out.print("Слова в строке у которых одинаковые первый и последний символ: \n");
        for (String s : result) {
            System.out.printf("%s\n", s);
        }
    }
    //endregion
    public static void main(String[] args) {
        runFirstTask();
        runSecTask();
    }
}