import org.omg.CORBA.INTERNAL;

import java.io.*;
import java.util.*;

public class Customer implements CustomerDAO{
    Order o = new Order();
    private int customerId;
    private String customerName;
    private String customerSurname;
    private String customerAddress;
    private String customerTelNo;
    private int payTime;
    private int removed;
    private String[] lineArray = new String[50];
    private Customer[] arrayOfMine = new Customer[50];
    private String ret;
    private ArrayList<String> nameArray = new ArrayList<String>();
    private ArrayList<Integer> inputIdOrder = new ArrayList<Integer>();

    public void creator(){
        for (int i =0 ; i<50 ; i++)
        {
            arrayOfMine[i]=new Customer();
        }
    }

    public void reader() throws IOException

    {
        File file = new File("customer.txt");
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));


        int a = 0;

        while ((line = reader.readLine()) != null) {
            lineArray[a] = (line);
            String[] parts= line.split(" ");
            nameArray.add(a,parts[1]);
            inputIdOrder.add(a,Integer.parseInt(parts[0]));


            a++;

        }
    }

    public void readIt(){
        int a=0;

        while (lineArray[a]!=null)
        {
            String[] parts = lineArray[a].split(": ");
            arrayOfMine[a].customerAddress=parts[1];
            String[] parts2 = parts[0].split(" ");
            arrayOfMine[a].customerId=Integer.parseInt(parts2[0]);
            arrayOfMine[a].customerName=(parts2[1]);
            arrayOfMine[a].customerSurname=(parts2[2]);
            arrayOfMine[a].customerTelNo=(parts2[3]);
            arrayOfMine[a].removed=0;
            a++;
        }
    }

    public void addCustomer(int newid, String newName, String newSurname, String newtelNo, String newaddrr){
        for (int a=0;a<arrayOfMine.length;a++){
            if (arrayOfMine[a].customerName==null){
                arrayOfMine[a].customerId=newid;
                arrayOfMine[a].customerSurname=newSurname;
                arrayOfMine[a].customerTelNo=newtelNo;
                arrayOfMine[a].customerAddress=newaddrr;
                arrayOfMine[a].customerName=newName;
                nameArray.add(newName);
                break;

            }
        }
    }

    public void removeCustomer (int customerId) {
        for (int a =0; a<arrayOfMine.length;a++){
            if (arrayOfMine[a].customerId==customerId){
                arrayOfMine[a].removed=1;
                o.removeOrder(customerId);
            }
        }
    }

    public void objectCheck(int id){

        for (int bb8=0;bb8<arrayOfMine.length;bb8++)
        if (arrayOfMine[bb8].customerId==id){
            System.out.println(arrayOfMine[bb8].customerId+"  ----> "+arrayOfMine[bb8].customerName+"   "+arrayOfMine[bb8].customerSurname);

        }
    }

    public void setPayTime(int ID , int pay) {
        for (int a=0; a<arrayOfMine.length;a++){
            if (arrayOfMine[a].customerId==ID)
                arrayOfMine[a].payTime += pay;
        }
    }
    public String getTheName(int custoId){
        for (int a = 0 ; a<arrayOfMine.length;a++)
        {
            if (arrayOfMine[a].customerId==custoId){
                ret=arrayOfMine[a].customerName;
            }
        }
        return ret;
    }

    public void listCustomer(PrintWriter bw){
        Collections.sort(nameArray);
        bw.println("Customer List:");
        for (int a=0;a<nameArray.size();a++){
            for (int b=0;b<arrayOfMine.length;b++){
                if (nameArray.get(a).equals(arrayOfMine[b].customerName) && arrayOfMine[b].removed!=1){
                    bw.println(arrayOfMine[b].customerId+" "+arrayOfMine[b].customerName+" "+arrayOfMine[b].customerSurname+" "+arrayOfMine[b].customerTelNo+" Address: "+arrayOfMine[b].customerAddress);
                }
            }
        }
    }
    public void forExtraFive() throws  Exception{
        Collections.sort(inputIdOrder);
        File newCustomer = new File("customer(After).txt");
        PrintWriter bw = new PrintWriter(newCustomer);
        for (int a=0; a<inputIdOrder.size();a++){
            for (int b =0; b<arrayOfMine.length;b++) {
                if (inputIdOrder.get(a)==((arrayOfMine[b].customerId)))
                bw.println(arrayOfMine[b].customerId + " " + arrayOfMine[b].customerName + " " + arrayOfMine[b].customerSurname + " " + arrayOfMine[b].customerTelNo + " Address: " + arrayOfMine[b].customerAddress);
            }
        }
        bw.close();
    }
}
