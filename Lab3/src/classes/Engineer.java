package classes;

public class Engineer extends Worker {
    public Engineer(String surname, String name, String patronymic) {
        super(surname, name, patronymic, Post.Engineer);
    }
}
