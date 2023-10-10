import classes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Administration administration = new Administration("Оренбург");
        Administration administration1 = new Administration("Оренбург1");
        Administration administration2 = new Administration("Оренбург");
        Worker bezr = new Worker("Безруков", "Кирилл", "Русланович", Post.Director);
        Worker abram = new Worker("Абрамян", "Ерванд", "Маратович", Post.Engineer);
        Worker illar = new Engineer("Илларионов", "Дмитрий", "Русланович");
        Worker scvorec = new Worker("Скворцов", "Александр", "Дмитриевич", Post.Manager);
        administration.getPersonal().hireWorker(bezr);
        administration.getPersonal().hireWorker(abram);
        administration.getPersonal().hireWorker(illar);
        administration.getPersonal().hireWorker(scvorec);

        administration1.getPersonal().hireWorker(illar);

        administration2.getPersonal().hireWorker(bezr);
        administration2.getPersonal().hireWorker(abram);
        administration2.getPersonal().hireWorker(illar);
        administration2.getPersonal().hireWorker(scvorec);
        System.out.println(administration.toString());
        System.out.println("-----------------------------------------------");
        System.out.println(Arrays.toString(administration.getPersonal().getWorkersByPost(Post.Engineer)));
        System.out.println("-----------------------------------------------");
        System.out.println(administration.equals(administration.clone()));
    }
}