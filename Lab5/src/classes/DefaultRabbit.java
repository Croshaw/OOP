package classes;

public class DefaultRabbit extends Rabbit {
    public DefaultRabbit(int lifeTime) {
        super(lifeTime);
    }

    @Override
    public Rabbit createRabbit(int lifeTime) {
        return new DefaultRabbit(lifeTime);
    }
}
