package training.bean;

public class Setting implements Persistable {
    private String name;
    private String key;
    private String value;

    public Setting() {
    }

    public Setting(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Setting)) {
            return false;
        }
        Setting that = (Setting) o;
        return this.key.equals(that.key);
    }

    @Override
    public String toString() {
        return key + "=" + value;
    }
}