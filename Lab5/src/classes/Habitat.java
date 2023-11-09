package classes;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class Habitat {
    public Vector<Rabbit> rabbits;
    public TreeSet<Rabbit> rabbitsSet;
    public HashMap<Rabbit, Integer> rabbitsMap;
    private final Random random = new Random();
    private boolean isStop;
    private long startTime;
    private double duration;
    private final int periodToBirthForDefaultRabbits;
    private final float probabilityToBirthForDefaultRabbits;
    private final int periodToBirthForAlbinoRabbits;
    private final int conditionForAlbinoRabbits;
    private final int lifeTimeForDefaultRabbits;
    private final int lifeTimeForAlbinoRabbits;
    private int countCreatedDefault;
    private int countCreatedAlbino;
    private int countDeathDefault;
    private int countDeathAlbino;
    private String report;

    public Habitat(int periodToBirthForDefaultRabbits, float probabilityToBirthForDefaultRabbits, int periodToBirthForAlbinoRabbits, int conditionForAlbinoRabbits, int lifeTimeForDefaultRabbits, int lifeTimeForAlbinoRabbits) {
        rabbits = new Vector<>();
        rabbitsSet = new TreeSet<>(Comparator.comparing(Rabbit::getId));
        rabbitsMap = new HashMap<>();
        this.conditionForAlbinoRabbits = conditionForAlbinoRabbits;
        this.periodToBirthForAlbinoRabbits = periodToBirthForAlbinoRabbits;
        this.periodToBirthForDefaultRabbits = periodToBirthForDefaultRabbits;
        this.probabilityToBirthForDefaultRabbits = probabilityToBirthForDefaultRabbits;
        this.lifeTimeForDefaultRabbits = lifeTimeForDefaultRabbits;
        this.lifeTimeForAlbinoRabbits = lifeTimeForAlbinoRabbits;
        isStop = true;
        countCreatedDefault = 0;
        countDeathDefault = 0;
        countCreatedAlbino = 0;
        countDeathAlbino = 0;
    }
    public int getDuration() {
        return (int)duration;
    }
    private Vector<DefaultRabbit> getDefaultRabbits() {
        return rabbits.stream()
                .filter(DefaultRabbit.class::isInstance)
                .map(DefaultRabbit.class::cast)
                .collect(Collectors.toCollection(Vector::new));
    }

    private Vector<AlbinoRabbit> getAlbinoRabbits() {
        return rabbits.stream()
                .filter(AlbinoRabbit.class::isInstance)
                .map(AlbinoRabbit.class::cast)
                .collect(Collectors.toCollection(Vector::new));
    }

    public void Start() {
        isStop = false;
        startTime = System.currentTimeMillis();
        rabbits.add(new DefaultRabbit(lifeTimeForDefaultRabbits));
        rabbits.add(new AlbinoRabbit(lifeTimeForAlbinoRabbits));
        rabbits.forEach(rabbit -> rabbitsMap.put(rabbit, 0));
        countCreatedDefault = 1;
        countDeathDefault = 0;
        countCreatedAlbino = 1;
        countDeathAlbino = 0;
    }

    public void Update() {
        duration = (double)(System.currentTimeMillis() - startTime) / 1000;
        if(duration % periodToBirthForDefaultRabbits == 0) {
            getDefaultRabbits().forEach(rabbit -> {
                if (random.nextFloat() <= probabilityToBirthForDefaultRabbits) {
                    Rabbit daughter = rabbit.createRabbit(lifeTimeForDefaultRabbits);
                    rabbits.add(daughter);
                    rabbitsSet.add(daughter);
                    rabbitsMap.put(daughter, (int)duration);
                    countCreatedDefault++;
                }
                if(rabbit.getLifeTime() + rabbitsMap.get(rabbit) <= duration) {
                    rabbitsMap.remove(rabbit);
                    rabbitsSet.remove(rabbit);
                    rabbits.remove(rabbit);
                    countDeathDefault++;
                }
            });
        }
        if(duration % periodToBirthForAlbinoRabbits == 0) {
            Vector<AlbinoRabbit> albinoRabbits = getAlbinoRabbits();
            albinoRabbits.forEach(rabbit -> {
                if (((float) albinoRabbits.size() / rabbits.size()) < ((float) conditionForAlbinoRabbits / 100)) {
                    Rabbit daughter = rabbit.createRabbit(lifeTimeForAlbinoRabbits);
                    rabbits.add(daughter);
                    rabbitsSet.add(daughter);
                    rabbitsMap.put(daughter, (int)duration);
                    countCreatedAlbino++;
                }
                if(rabbit.getLifeTime() + rabbitsMap.get(rabbit) <= duration) {
                    rabbitsMap.remove(rabbit);
                    rabbitsSet.remove(rabbit);
                    rabbits.remove(rabbit);
                    countDeathAlbino++;
                }
            });
        }
    }
    public void Stop() {
        isStop = true;
        long endTime = System.currentTimeMillis();
        duration = (double)(endTime - startTime)/1000;
        report = this.toString();
        rabbits = new Vector<>();
        rabbitsSet = new TreeSet<>(Comparator.comparing(Rabbit::getId));
        rabbitsMap = new HashMap<>();
    }

    @Override
    public String toString() {
        return ("Время симуляции: %s с.\nЗа всё время было создано %s кроликов\nИз них: %s обычных и %s альбиносов\n" +
                "За всё время погибло %s кроликов\nИз них: %s обычных и %s альбиносов\n" +
                "Кол-во кроликов на момент окончания симуляции: %s\nИз них: %s обычных и %s альбиносов").formatted((int)duration, countCreatedDefault+ countCreatedAlbino,
                countCreatedDefault, countCreatedAlbino, countDeathDefault+ countDeathAlbino, countDeathDefault, countDeathAlbino, rabbits.size(), getDefaultRabbits().size(),
                getAlbinoRabbits().size());
    }

    public boolean isStop() {
        return isStop;
    }

    public String getReport() {
        return report;
    }
}
