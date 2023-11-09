package classes;

import java.util.UUID;

public abstract class Rabbit implements IBehaviour {
    private final int lifeTime;
    private final UUID id;

    public Rabbit(int lifeTime) {
        this.lifeTime = lifeTime;
        id = UUID.randomUUID();
    }
    public UUID getId() {
        return id;
    }

    @Override
    public int getLifeTime() {
        return lifeTime;
    }
}
