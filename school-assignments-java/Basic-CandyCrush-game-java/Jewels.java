import java.util.ArrayList;

public interface Jewels extends Cards {


    @Override
    boolean haveMatch(ArrayList<ArrayList<String>> col,int a, int b);


    int  points();
}
