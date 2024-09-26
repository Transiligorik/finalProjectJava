package our.project.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum Gender {
    MALE("мужской"),
    FEMALE("женский");

    private final String value;
    private static final Map<String,Gender> GENDERS;

    Gender(String gender) {
        this.value = gender;
    }

    public String getValue() {
        return value;
    }

    static {
        Map<String,Gender> map = new ConcurrentHashMap<String, Gender>();
        for (Gender instance : Gender.values()) {
            map.put(instance.getValue().toLowerCase(),instance);
        }
        GENDERS = Collections.unmodifiableMap(map);
    }

    public static Gender get (String name) {
        return GENDERS.get(name.toLowerCase());
    }


}
