import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "Game")
@SessionScoped
public class Bean {
    private int min;
    private int max;
    private int number;

    public void init() {
        number = min + (max - min) / 2;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNumber() {
        return number;
    }

    public String lesser() {
        if(max-min <= 1){
            return "cheat";
        }
        max = number;
        number = min + (max - min) / 2;
        return "";
    }

    public String greater() {
        if(max-min <= 1){
            return "cheat";
        }
        min = number;
        number = min + (max - min) / 2;
        return "";
    }

    public String play() {
        number = min + (max - min) / 2;
        return "play";
    }

    public String again(){
        min = 0;
        max = 0;
        return "again";
    }
}