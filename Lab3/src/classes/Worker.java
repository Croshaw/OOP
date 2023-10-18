package classes;

import java.util.Objects;

public class Worker extends People implements IWorker {
    private Post post;
    public Worker(String surname, String name, String patronymic, Post post) {
        super(surname, name, patronymic);
        this.post = post;
    }
    @Override
    public Post getPost() {
        return post;
    }
    @Override
    public void setPost(Post value) {
        this.post = value;
    }

    @Override
    public String getInfo() {
        return super.getInfo() + " Должность: %s".formatted(post);
    }

    @Override
    public void work() {
        System.out.printf("%s работает\n", this.toString());
    }

    @Override
    public String toString() {
        return "%s %s".formatted(super.toString(), post);
    }

    @Override
    public String toShortString() {
        return "%s %s".formatted(super.toShortString(), post);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Worker(getSurname(), getName(), getPatronymic(), post);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Worker worker)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(post, worker.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), post);
    }
}
