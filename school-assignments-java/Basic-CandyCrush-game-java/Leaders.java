import org.omg.PortableInterceptor.INACTIVE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;


public class Leaders implements Comparable<Integer>  {

    @Override
    public int compareTo(Integer o) {
        return 0;
    }

    static void bubbleSort(ArrayList<Integer> arr) {
        int n = arr.size();
        int temp = 0;
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                if(arr.get(j-1) > arr.get(j)){
                    temp = arr.get(j-1);
                    arr.set(j-1,arr.get(j));
                    arr.set(j,temp);
                }
            }
        }
    }
    public void takeLeaders(String playerName, int newPoint) throws Exception {

        ArrayList<String> leadersArray = new ArrayList<>();
        ArrayList<Integer> leadPoints = new ArrayList<>();
        HashMap<String , Integer>  hmap = new HashMap<String, Integer>();
        HashMap<Integer , String>  hmap2 = new HashMap<Integer, String>();
        File leaderBoard = new File("leaderboard.txt");
        if (!leaderBoard.exists()){
            System.out.println("There was no leaderboard.txt so i created one. ");
        }
        BufferedReader bf = new BufferedReader(new FileReader (leaderBoard));
        String reader =new String();
        while ((reader= bf.readLine()) != null)
        {
            String[] parts= reader.split(" ");
            String name = parts[0];
            int topPoint = Integer.parseInt(parts[1]);
            hmap.put(name, topPoint);
            hmap2.put(topPoint,name);
        }
        if (hmap.containsKey(playerName)) {
            if (newPoint > hmap.get(playerName)) {
                hmap.put(playerName, newPoint);
                hmap2.put(newPoint, playerName);
            }
        }else {
            hmap.put(playerName, newPoint);
            hmap2.put(newPoint, playerName);
        }
        for (String key : hmap.keySet()) {
            leadPoints.add((hmap.get(key)));
        }

        bubbleSort(leadPoints);
        Collections.reverse(leadPoints);

        for (int a =0 ;a<leadPoints.size();a++){
            if (leadPoints.get(0)==newPoint){
                int higherPoint;
                higherPoint=leadPoints.get(a + 1);
                System.out.println("Your rank is: "+ (a+1) + "/" + leadPoints.size() +", "+"your score is "+ (((leadPoints.get(a))-(higherPoint)))+" higher than "+hmap2.get(higherPoint).toUpperCase());
                break;
            }
            else {
                if (newPoint==leadPoints.get(leadPoints.size()-1)) {
                    int lowerPoints;
                    lowerPoints = leadPoints.get(a - 1);
                    System.out.println("Your rank is: " + (a + 1) + "/" + leadPoints.size() + ", " + "your score is " + (((leadPoints.get(a)) - (lowerPoints)) * (-1)) + " lower than " + hmap2.get(lowerPoints).toUpperCase());
                    break;
                }
                else {
                    if (a>0) {
                        int higherPoint;
                        int lowerPoints;
                        lowerPoints = leadPoints.get(a - 1);
                        higherPoint = leadPoints.get(a + 1);
                        System.out.println("Your rank is: " + (a + 1) + "/" + leadPoints.size() + ", " + "your score is " + (((leadPoints.get(a)) - (lowerPoints)) * (-1))
                                + " lower than " + hmap2.get(lowerPoints).toUpperCase() + " and " + ((-1)*(higherPoint - leadPoints.get(a))) + " higher than " + hmap2.get(higherPoint).toUpperCase());
                        break;
                    }
                }
            }
        }
    }
}