package classes;

public interface IPeople {
    String getSurname();
    String getName();
    String getPatronymic();
    void setSurname(String value);
    void setName(String value);
    void setPatronymic(String value);
    String toString();
    String toShortString();
}
