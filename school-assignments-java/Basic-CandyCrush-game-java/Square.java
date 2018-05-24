import java.util.ArrayList;

public class Square implements Jewels{
    @Override
    public int points() {
        return 15;
    }

    @Override
    public boolean haveMatch(ArrayList<ArrayList<String>> col,int a, int b) {
        return false;
    }
}
