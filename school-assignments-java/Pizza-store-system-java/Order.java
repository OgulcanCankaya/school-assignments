import java.io.*;
import java.util.*;


public class Order  implements OrderDAO {
    private int orderNo;
    private int orderCustomerId;
    private int orderCost;
    private Order[] orders = new Order[50];
    private String[] lineArray =  new String[50];
    private boolean orderexist;
    private ArrayList<String> arrayy=new ArrayList<String>();
    private ArrayList<Integer> orderOrder = new ArrayList<Integer>();
    public void creator(){
        for (int i=0;i<orders.length;i++){
            orders[i]=new Order();
        }
    }



    public void reader()throws Exception

    {
        File file = new File("order.txt");
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));


        int a = 0;
        while ((line = reader.readLine()) != null) {
            lineArray[a] = (line);
            a++;

            }
            reader.close();
        for (a=0;a<lineArray.length;a++){
            if (lineArray[a]!=null)
            arrayy.add(a,lineArray[a]);
        }
        int abc=0;
        for (a=0 ; a<lineArray.length ; a++){
            if (lineArray[a]!=(null)){
            String[] parts=lineArray[a].split(" ");
            if (parts[0].equals("Order:") ){
                orderOrder.add(abc,Integer.parseInt(lineArray[a].split(" ")[1]));
                abc++;
            }
            }
            }
        }


    public void parser() throws  Exception{
        int a=0;
        int orderer=0;
        while(lineArray[a]!=null){
            String[] str = lineArray[a].split(":");
            if (str[0].equals("Order")){
                String[] parts = str[1].split(" ");
                orders[orderer].orderNo=Integer.parseInt(parts[1]);
                orders[orderer].orderCustomerId=Integer.parseInt(parts[2]);
                orders[orderer].orderexist=true;
                while (lineArray[a+1]!=null && !lineArray[a+1].split(":")[0].equals("Order")){
                    String[] parts2= lineArray[a+1].split(" ");
                    for (int bored=0;bored< parts2.length;bored++){
                        if (parts2[bored].equals("AmericanPan")){
                            orders[orderer].orderCost+=5;
                        }
                        if (parts2[bored].equals("NapolitanPan")){
                            orders[orderer].orderCost+=10;
                        }
                        if (parts2[bored].equals("salami")){
                            orders[orderer].orderCost+=3;
                        }
                        if (parts2[bored].equals("soudjouk")){
                            orders[orderer].orderCost+=3;
                        }
                        if (parts2[bored].equals("onion")){
                            orders[orderer].orderCost+=2;
                        }
                        if (parts2[bored].equals("pepper")){
                            orders[orderer].orderCost+=1;
                        }
                        if (parts2[bored].equals("softdrink")){
                            orders[orderer].orderCost+=2;
                        }
                    }
                    a++;
                }
            orderer++;
            }

            a++;
        }
    }
    public void checkIt(int orderid){
        for (int a =0 ; a<arrayy.size();a++){
            System.out.println(arrayy.get(a)+"   "+a);

            }
        }
    public void getByIdFor(int orderid,PrintWriter bw) throws Exception{

        bw.println("PayCheck for order "+orderid);
        for (int a = 0; a < arrayy.size(); a++) {
            if (arrayy.get(a).split(" ")[0].equals("Order:")) {
                int totalcost=0;

                if (arrayy.get(a).split(" ")[1].equals(Integer.toString(orderid))) {

                    for (int b = a+1; b < arrayy.size(); b++) {

                        if (!arrayy.get(b).split(" ")[0].equals("Order:")) {
                            int cost=0;

                            String[] parts2= arrayy.get(b).split(" ");

                            for (int bored=0;  bored< parts2.length;  bored++){
                                if (parts2[bored].equals("AmericanPan") || parts2[bored].equals("AmericianPan")){
                                    cost+=5;
                                }
                                if (parts2[bored].equals("Napolitan") || parts2[bored].equals("Neapolitan")){
                                    cost+=10;
                                }
                                if (parts2[bored].equals("Salami") || parts2[bored].equals("salami")){
                                    cost+=3;
                                }
                                if (parts2[bored].equals("Soudjouk") || parts2[bored].equals("soudjouk")){
                                    cost+=3;
                                }
                                if (parts2[bored].equals("Onion") || parts2[bored].equals("onion")){
                                    cost+=2;
                                }
                                if (parts2[bored].equals("HotPepper") || parts2[bored].equals("pepper")){
                                    cost+=1;                                }
                                if (parts2[bored].equals("softdrink")){
                                    cost+=2;                                }
                            }
                            bw.println("\t"+arrayy.get(b)+""+cost+"$");
                            totalcost+=cost;
                        } else {
                            break;
                        }
                    }
                    bw.println("\tTotal: "+totalcost+"$");

                }
            }
        }
    }



    public void getbyID(int orderId) throws Exception{
        int a=0;
        while(arrayy.get(a)!=null){
            String[] parts = arrayy.get(a).split(":");
            if (parts[0].equals("Order")){
                String[] parts2 = parts[1].split(" ");
                if (Integer.parseInt(parts2[1])==orderId){
                    while (arrayy.get(a+1)!=null && !arrayy.get(a+1).split(":")[0].equals("Order")){
                        System.out.println(arrayy.get(a+1));
                        a++;
                    }

                break;
                }
            }
        a++;
        }

    }

    public void addOrder(int orderno, int costumerNumber){
        arrayy.add((arrayy.size()),("Order: "+orderno+" "+costumerNumber));
        orderOrder.add(orderno);
    }
    public void addToOrder(int orderNo, String str){
        int a=0;
        while (arrayy.get(a)!=null){
            if (arrayy.get(a).split(" ")[0].equals("Order:")){
            if ((arrayy.get(a).split(" ")[1]).equals(Integer.toString(orderNo))){
                arrayy.add(a+1,str);
                break;
                }
            }
         a++;
        }
        for (a=0;a<arrayy.size();a++){
        }
    }

    public void anotherFivePoints() throws Exception{
        orderOrder.add(orderOrder.size(),-1);
        Collections.sort(orderOrder);
        File newCustomer = new File("order(After).txt");
        PrintWriter bw = new PrintWriter(newCustomer);
        for (int a=0;a<orderOrder.size();a++){
            int b=0;
            while (b<arrayy.size()){
                try{
                 if (arrayy.get(b).split(" ")[0].equals("Order:") && arrayy.get(b).split(" ")[1].equals(Integer.toString(orderOrder.get(a))))  {
                     bw.println(arrayy.get(b));
                     while (!arrayy.get(b+1).split(" ")[0].equals("Order:")) {
                         b++;
                         bw.println(arrayy.get(b));
                     }
                     b++;
                 }
                b++;
                }
                catch (IndexOutOfBoundsException index){
                    break;
                }
            }
        }
        bw.close();
    }
    public void removeOrder(int customerId){
        for (int a=0;a<arrayy.size();a++){
            String[] parts = arrayy.get(a).split(" ");
            if (parts[0].equals("Order:")){
                orderOrder.remove(Integer.parseInt(parts[1]));
            }
        }
    }

}
