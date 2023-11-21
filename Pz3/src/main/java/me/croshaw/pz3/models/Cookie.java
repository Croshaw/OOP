package me.croshaw.pz3.models;

import java.util.ArrayList;

public class Cookie extends Food // булочка
{
    public Boolean getWithSugar() {
        return withSugar;
    }

    public Boolean getWithPoppy() {
        return withPoppy;
    }

    public Boolean getWithSesame() {
        return withSesame;
    }

    private Boolean withSugar;// с сахаром ли?
    private Boolean withPoppy;// или маком?
    private Boolean withSesame;// а может с кунжутом?

    public Cookie(int kkal, String title, Boolean withSugar, Boolean withPoppy, Boolean withSesame) {
        super(kkal, title);
        this.withSugar = withSugar;
        this.withPoppy = withPoppy;
        this.withSesame = withSesame;
    }
    @Override
    public String getDescription() {
        ArrayList<String> items = new ArrayList<>();
        if (this.withSugar)
            items.add("с сахаром");
        if (this.withPoppy)
            items.add("с маком");
        if (this.withSesame)
            items.add("с кунжутом");

        return String.format("Булочка %s", String.join(", ", items));
    }

}
