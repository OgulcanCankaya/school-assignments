import java.io.*;
import java.util.*;
    public class Foods{
        private String[] lineArray = new String[100];


        public void reader()throws IOException

        {
            File file = new File("food.txt");
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(file));


            int a=0;

            while ((line = reader.readLine()) != null) {
                System.out.println(a+"   "+line);
                lineArray[a]=(line);
                a++;

            };
        }
    }