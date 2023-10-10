package classes;

public interface IPersonal {
    Worker[] getWorkers();
    Worker[] getWorkersByPost(Post post);
    Worker[] getWorkersBySurname(String surname);
    Worker[] getWorkersByName(String name);
    Worker[] getWorkersByPatronymic(String patronymic);
    void hireWorker(Worker worker);
    void dismissWorker(Worker worker);
    void dismissWorker(int id);
}
