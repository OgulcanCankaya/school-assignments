import java.io.*;
import java.util.ArrayList;

public class Input {


    public void letsDoIt() throws Exception{
        File output=new File("output.txt");

        PrintWriter bw=new PrintWriter(output);


        Customer    c=new Customer();
        Order   o=new Order();
        c.creator();
        c.reader();
        c.readIt();
        o.creator();
        o.reader();
        o.parser();
        /*lets read the file right*/
        File input = new File("input.txt");
        String line;
        ArrayList<String> inputArray = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(input));

        while ((line=reader.readLine())!=null){
            String[] parts = line.split(" ");
            if (parts[0].equals("AddCustomer")){
                StringBuilder newaddrress = new StringBuilder();
                for (int addr=0;addr<(parts.length)-5;addr++){
                    newaddrress.append(parts[5+addr]).append(" ");
                }
                int customerido=Integer.parseInt(parts[1]);
                c.addCustomer(Integer.parseInt(parts[1]),parts[2],parts[3],parts[4],newaddrress.toString());
                bw.println("Customer "+customerido+" "+c.getTheName(customerido)+" added");

            }
            if (parts[0].equals("RemoveCustomer")){
                int customerid=Integer.parseInt(parts[1]);
                c.removeCustomer(Integer.parseInt(parts[1]));
                bw.println("Customer "+ customerid +" "+ c.getTheName(customerid)+" removed");
            }
            if (parts[0].equals("CreateOrder")){
                o.addOrder(Integer.parseInt(parts[1]),Integer.parseInt(parts[2]));
                bw.println("Order "+Integer.parseInt(parts[1])+" created");
            }
            if (parts[0].equals("AddPizza")){
                int orderno = Integer.parseInt(parts[1]);
                StringBuilder sb = new StringBuilder();
                for (int a=2;a<parts.length;a++){
                    sb.append(parts[a]).append(" ");
                }
                o.addToOrder(orderno,sb.toString());
                bw.println(parts[2]+" pizza added to order "+parts[1]);
            }
            if (parts[0].equals("AddDrink")){
                o.addToOrder(Integer.parseInt(parts[1]),"softdrink");
                bw.println("Drink added to order "+ parts[1]);
            }
            /*--------------------------*/
            if (parts[0].equals("PayCheck")){
                o.getByIdFor(Integer.parseInt(parts[1]),bw);
            }
            if (parts[0].equals("List")){
                c.listCustomer(bw);

            }


        }

        bw.close();
        c.forExtraFive();
        o.anotherFivePoints();
    }

}
