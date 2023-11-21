package me.croshaw.pz3.models;

public class Food {
    private int kkal;// количество калорий
    private String title;// название

    public Food(int kkal, String title) {
        this.setKkal(kkal);
        this.setTitle(title);
    }

    @Override
    public String toString() {
        return String.format("%s: %s ккал", this.getTitle(), this.getKkal());
    }

    public int getKkal() {
        return kkal;
    }

    public void setKkal(int kkal) {
        this.kkal = kkal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return "";
    }

}
