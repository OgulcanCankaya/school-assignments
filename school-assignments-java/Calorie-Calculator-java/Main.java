import java.io.*;

public class Main {


    public static void main (String[] argv) throws IOException {
        Sport[] sporte = new Sport[100];
        Foods[] fooder = new Foods[100];
        Command commandRead = new Command();
        People[] person = new People[50];

        sporte.creator();
        commandRead.reader();

        System.out.println("-------------------");

        //sporter.parseAndToArray();
        //calOf = sporter.getCalPerHour(2038);

        //System.out.println(calOf);

        //fooder.reader();

        System.out.println("--------------------------");

        //person.reader();
    };


}