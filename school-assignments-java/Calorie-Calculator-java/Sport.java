import java.io.*;
import java.util.*;

public class Sport
    {
        public int retu;
        public String[] lineArray = new String[100];
        public String[][] parsedArray = new String[100][3];

        public int calPerHour;
        public int sportCode;
        public String sportName;


        Sport[] sporter = new Sport[100];


        public void creator() {
            for (int i = 0; i < 100; i++) {
                sporter[i] = new Sport();

            }
        }







                                        /*We can try to see our lines and check if they are correct*/
        public void reader() throws IOException
        {
            int a=0;
            File sportFile = new File("sports.txt");
            String line;

            BufferedReader reader = new BufferedReader(new FileReader(sportFile));
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split("\t");
                sporter[a].sportCode=Integer.parseInt(parts[0]);
                sporter[a].sportName=parts[1];
                sporter[a].calPerHour=Integer.parseInt(parts[1]);
                a++;
            };
        }




                                        /*lets see if we can parse them and print them seperately*/
        public void parsedArrayReader()throws IOException
        {
            File sportFile = new File("sports.txt");
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(sportFile));

            int a=0;

            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split("\t");
                System.out.println(parts[0]);
                System.out.println(parts[1]);
                System.out.println(parts[2]);
                System.out.println("---------");

                System.out.println(parts[0]+"  ---+---  "+parts[1]);
                //

                a++;
            };



        }

        /*lets have a parsed array to read and etc. */
        public void parseAndToArray() throws IOException
        {
            File sportFile = new File("sports.txt");
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(sportFile));

            int a=0;

            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split("\t");

                parsedArray[a][0]=parts[0];
                parsedArray[a][1]=parts[1];
                parsedArray[a][2]=parts[2];
                //System.out.println(parsedArray[a][1]+"    "+a);


                a++;
            };



        };
        /*lets get the hourly calorie*/
        public int getCalPerHour(int Code){
            for (int i =0; i <parsedArray.length;i++)
            {
                System.out.println("----  "+i);
                if (Code==Integer.parseInt(parsedArray[i][0])){
                    retu=Integer.parseInt(parsedArray[i][2]);
                    break;

                }
            }
        return retu;
        }


    }
