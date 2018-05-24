import java.util.ArrayList;

public class Wild implements Jewels{
    @Override
    public int points() {
        return 10;
    }

    @Override
    public boolean haveMatch(ArrayList<ArrayList<String>> col,int a, int b) {
        return false;
    }
}
