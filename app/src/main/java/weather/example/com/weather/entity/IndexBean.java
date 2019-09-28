package weather.example.com.weather.entity;

/**
 * Created by krito on 2019/9/26.
 */

public class IndexBean {
    private String title;
    private String level;
    private String desc;

    public IndexBean(String title, String level, String desc) {
        this.title = title;
        this.level = level;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "title='" + title + '\'' +
                ", level='" + level + '\'' +
                ", desc='" + desc + '\'';
    }
}
