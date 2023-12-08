package model;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Model {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(this);
                sb.append(value).append(" ");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return sb.toString().trim();
    }
}
