package classes;

import java.util.Objects;

public class People implements IPeople {
    private String surname;
    private String name;
    private String patronymic;
    public People(String surname, String name, String patronymic) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    @Override
    public String getSurname() {
        return surname;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getPatronymic() {
        return patronymic;
    }
    @Override
    public void setSurname(String value) {
        this.surname = value;
    }
    @Override
    public void setName(String value) {
        this.name = value;
    }
    @Override
    public void setPatronymic(String value) {
        this.patronymic = value;
    }
    @Override
    public String toString() {
        return "%s %s %s".formatted(surname, name, patronymic);
    }
    @Override
    public String toShortString() {
        return "%s %s %s".formatted(surname, name.charAt(0), patronymic.charAt(0));
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new People(surname, name, patronymic);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof People people)) return false;
        return Objects.equals(surname, people.surname) && Objects.equals(name, people.name) && Objects.equals(patronymic, people.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic);
    }

}
