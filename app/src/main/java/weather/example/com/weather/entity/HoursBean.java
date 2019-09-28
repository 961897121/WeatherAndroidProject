package weather.example.com.weather.entity;

/**
 * Created by krito on 2019/9/26.
 */

public class HoursBean {
    private String dayNight;
    private String weaNight;
    private String temNight;
    private String winNight;
    private String win_speedHour;

    public HoursBean(String dayNight, String weaNight, String temNight, String winNight, String win_speedHour) {
        this.dayNight = dayNight;
        this.weaNight = weaNight;
        this.temNight = temNight;
        this.winNight = winNight;
        this.win_speedHour = win_speedHour;
    }

    public String getDayNight() {
        return dayNight;
    }

    public void setDayNight(String dayNight) {
        this.dayNight = dayNight;
    }

    public String getWeaNight() {
        return weaNight;
    }

    public void setWeaNight(String weaNight) {
        this.weaNight = weaNight;
    }

    public String getTemNight() {
        return temNight;
    }

    public void setTemNight(String temNight) {
        this.temNight = temNight;
    }

    public String getWinNight() {
        return winNight;
    }

    public void setWinNight(String winNight) {
        this.winNight = winNight;
    }

    public String getWin_speedHour() {
        return win_speedHour;
    }

    public void setWin_speedHour(String win_speedHour) {
        this.win_speedHour = win_speedHour;
    }

    @Override
    public String toString() {
        return "win_speedHour='" + win_speedHour + '\'' +
                ", winNight='" + winNight + '\'' +
                ", temNight='" + temNight + '\'' +
                ", weaNight='" + weaNight + '\'' +
                ", dayNight='" + dayNight + '\'' ;
    }
}
