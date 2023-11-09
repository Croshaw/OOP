package classes;

public class AlbinoRabbit extends Rabbit {
    public AlbinoRabbit(int lifeTime) {
        super(lifeTime);
    }

    @Override
    public Rabbit createRabbit(int lifeTime) {
        return new AlbinoRabbit(lifeTime);
    }
}
