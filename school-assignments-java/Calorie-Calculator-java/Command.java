import java.io.*;
import java.util.*;

public class Command {

    public void reader() throws IOException

    {
        File file = new File("commands.txt");
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));


        while ((line = reader.readLine()) != null)

        {
            System.out.println(line);

        }
    }






}