package classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Personal implements IPersonal {
    private final ArrayList<Worker> workers;
    public Personal() {
        this(new ArrayList<Worker>());
    }
    public Personal(Worker[] workers) {
        this(new ArrayList<Worker>(Arrays.asList(workers)));
    }
    public Personal(ArrayList<Worker> workers) {
        this.workers = workers;
    }
    @Override
    public Worker[] getWorkers() {
        return workers.toArray(new Worker[0]);
    }

    @Override
    public Worker[] getWorkersByPost(Post post) {
        return workers.stream()
                .filter(x -> x.getPost().equals(post))
                .toArray(Worker[]::new);
    }

    @Override
    public Worker[] getWorkersBySurname(String surname) {
        return workers.stream()
                .filter(x -> x.getSurname().equals(surname))
                .toArray(Worker[]::new);
    }

    @Override
    public Worker[] getWorkersByName(String name) {
        return workers.stream()
                .filter(x -> x.getName().equals(name))
                .toArray(Worker[]::new);
    }

    @Override
    public Worker[] getWorkersByPatronymic(String patronymic) {
        return workers.stream()
                .filter(x -> x.getPatronymic().equals(patronymic))
                .toArray(Worker[]::new);
    }

    @Override
    public void hireWorker(Worker worker) {
        workers.add(worker);
    }

    @Override
    public void dismissWorker(Worker worker) {
        workers.remove(worker);
    }

    @Override
    public void dismissWorker(int id) {
        workers.remove(id);
    }

    @Override
    public String toString() {
        return Arrays.toString(getWorkers());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Personal(workers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Personal personal)) return false;
        return Objects.equals(workers, personal.workers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(workers);
    }
}
