package classes;

import java.util.Objects;

public class Administration implements ICompany {
    private String name;
    private final IPersonal personal;
    public Administration(String name) {
        this(name, new Personal());
    }

    public Administration(String name, IPersonal personal) {
        this.name = name;
        this.personal = personal;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        this.name = value;
    }

    @Override
    public IPersonal getPersonal() {
        return personal;
    }

    @Override
    public void work() {
        for(Worker worker : personal.getWorkers())
            worker.work();
    }

    @Override
    public String toString() {
        return "Администрация под названием %s\nСотрудники: %s".formatted(name, personal.toString());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Administration(name, personal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Administration that)) return false;
        return Objects.equals(name, that.name) && Objects.equals(personal, that.personal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, personal);
    }
}
