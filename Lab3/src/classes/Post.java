package classes;

public enum Post {
    Engineer("Инженер"), SeniorEngineer("Старший инженер"), Manager("Менеджер"), GeneralManager("Главный менеджер"), Director("Директор");

    private String name;

    Post(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
