import java.io.*;
import java.util.*;


    public class People {
        private String[] lineArray = new String[50];
        public int personID;
        public String name;
        public String sex;
        public int weight;
        public int height;
        public int age;
        public int calExpectedPerDay;
        public int calEated;
        public int cayBurned;


        public void reader()throws IOException

        {


            File file = new File("people.txt");
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(file));


            int a=0;

            while ((line = reader.readLine()) != null)
            {
                System.out.println(a+"   "+line);
                lineArray[a]=(line);
                a++;

            };
        }
}