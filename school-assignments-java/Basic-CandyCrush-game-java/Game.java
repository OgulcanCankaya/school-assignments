import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void popperThree(ArrayList<ArrayList<String>> colu, int x, int y){
        colu.get(x).set(y," ");
        colu.get(x-1).set(y," ");
        colu.get(x-2).set(y," ");
        for (int a = x ;  a>2 ; a--){
            colu.get(a).set(y,colu.get(a-3).get(y));
            colu.get(a-3).set(y," ");
        }
    }
    public static void popper(ArrayList<ArrayList<String>> colu, int x, int y){
        colu.get(x).set(y," ");
        for (int a = x ;  a>0 ; a--){
            colu.get(a).set(y,colu.get(a-1).get(y));
            colu.get(a-1).set(y," ");
        }
    }
    public static void mapControl(ArrayList<ArrayList<String>> column){
        System.out.println("Game Grid: ");
        System.out.println();
        for (int b =0; b<column.size() ;b++)
        {
            for (int c=0 ; c<column.get(b).size() ; c++)
            {
                System.out.print(column.get(b).get(c)+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void gameWoArgv(File grid) throws Exception{

        BufferedReader bf = new BufferedReader(new FileReader(grid));
        ArrayList<ArrayList<String>> column = new ArrayList<>();
        String reader;
        int columnCount=0;
        /*reading the game grid*/
        while ((reader= bf.readLine()) != null)
        {
            ArrayList<String> row = new ArrayList<>();
            String[] parts = reader.split(" ");
            for (int a = 0 ;  a<parts.length ;a++)
            {
                row.add(a,parts[a]);
            }
            column.add(columnCount,row);
            columnCount++;
        }
        /*control mechanism*/
        //mapControl(column);
        /*game time i suppose lets start with orders*/
        int gameCount=0;
        mapControl(column);
        int points=0;
        int oneMove=0;
        Scanner sc = new Scanner(System.in);
        int i=0;
        while (i==0) {
            try {
                System.out.print("Select coordinate or enter E to end the game: ");
                String reader2 = sc.nextLine();
                System.out.println();
                //System.out.println(orders.get(gameCount));
                int pop1x,pop1y,pop2x,pop2y,pop3x,pop3y;
                /*check if it is one lenght which is E*/
                if (reader2.split(" ").length > 1) {
                    int x = Integer.parseInt(reader2.split(" ")[0]);
                    int y = Integer.parseInt(reader2.split(" ")[1]);

                    if (x>=column.size() || y>=column.get(0).size()){
                        System.out.println("Sorry but i need proper indexes like "+(column.size()-1) +" "+(column.get(0).size()-1));
                    }
                    else {

                    if (column.get(x).get(y).equals("D"))
                    {
                        /*left up*/
                        if (y>=2 && x>=2 &&column.get(x).get(y).equals(column.get(x - 1).get(y - 1)) && column.get(x).get(y).equals(column.get(x - 2).get(y - 2)) )
                        {
                            pop1x=x;
                            pop1y=y;
                            pop2x=x-1;
                            pop2y=y-1;
                            pop3x=x-2;
                            pop3y=y-2;
                            popper(column,pop1x,pop1y);
                            popper(column,pop2x,pop2y);
                            popper(column,pop3x,pop3y);
                            mapControl(column);
                            points+=90;
                            oneMove+=90;
                            System.out.println("Score: "+ oneMove);
                        }
                        else
                        {
                            /*right down*/
                            if (y<column.get(x).size()-2 &&  x<column.size()-2
                                    &&(column.get(x).get(y).equals(column.get(x + 1).get(y + 1)) && column.get(x).get(y).equals(column.get(x + 2).get(y + 2))))
                            {
                                pop1x=x;
                                pop1y=y;
                                pop2x=x+1;
                                pop2y=y+1;
                                pop3x=x+2;
                                pop3y=y+2;
                                popper(column,pop1x,pop1y);
                                popper(column,pop2x,pop2y);
                                popper(column,pop3x,pop3y);
                                mapControl(column);
                                points+=90;
                                oneMove+=90;
                                System.out.println("Score: "+ oneMove);
                            }
                            else
                            {
                                /*right up*/
                                if (y<column.get(x).size()-2  && x>2
                                        && (column.get(x).get(y).equals(column.get(x - 1).get(y + 1)) && column.get(x).get(y).equals(column.get(x - 2).get(y + 2))))
                                {
                                    pop1x=x;
                                    pop1y=y;
                                    pop2x=x-1;
                                    pop2y=y+1;
                                    pop3x=x-2;
                                    pop3y=y+2;
                                    popper(column,pop1x,pop1y);
                                    popper(column,pop2x,pop2y);
                                    popper(column,pop3x,pop3y);
                                    mapControl(column);
                                    points+=90;
                                    oneMove+=90;
                                    System.out.println("Score: "+ oneMove);
                                }
                                else
                                {
                                    /*left down*/
                                    if ((column.get(x).get(y).equals(column.get(x + 1).get(y - 1)) && column.get(x).get(y).equals(column.get(x + 2).get(y - 2)))
                                            && x<column.get(x).size()-2 && y>2 )
                                    {
                                        pop1x=x;
                                        pop1y=y;
                                        pop2x=x+1;
                                        pop2y=y-1;
                                        pop3x=x+2;
                                        pop3y=y-2;
                                        popper(column,pop1x,pop1y);
                                        popper(column,pop2x,pop2y);
                                        popper(column,pop3x,pop3y);
                                        mapControl(column);
                                        points+=90;
                                        oneMove+=90;
                                        System.out.println("Score: "+ oneMove);
                                    }
                                }
                            }
                        }
                    }else{
                        /*---------SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS--------*/
                        if (column.get(x).get(y).equals("S"))
                        {
                            if ((column.get(x).get(y).equals(column.get(x).get(y - 1)) && column.get(x).get(y).equals(column.get(x).get(y - 2))))
                            {
                                pop1x=x;
                                pop1y=y;
                                pop2x=x;
                                pop2y=y-1;
                                pop3x=x;
                                pop3y=y-2;
                                popper(column,pop1x,pop1y);
                                popper(column,pop2x,pop2y);
                                popper(column,pop3x,pop3y);
                                points+=45;
                                oneMove+=45;
                                System.out.println("Score: "+ oneMove);
                                mapControl(column);
                            }
                            else
                            {
                                if ((column.get(x).get(y).equals(column.get(x).get(y + 1)) && column.get(x).get(y).equals(column.get(x).get(y + 2))))
                                {
                                    pop1x=x;
                                    pop1y=y;
                                    pop2x=x;
                                    pop2y=y+1;
                                    pop3x=x;
                                    pop3y=y+2;
                                    points+=45;
                                    popper(column,pop1x,pop1y);
                                    popper(column,pop2x,pop2y);
                                    popper(column,pop3x,pop3y);
                                    mapControl(column);
                                    oneMove+=45;
                                    System.out.println("Score: "+ oneMove);
                                }
                            }
                        }
                        else{
                            /*------------TTTTTTTTTTTTTTTTTTTTTTTT----------*/
                            if (column.get(x).get(y).equals("T"))
                            {
                                if ((column.get(x).get(y).equals(column.get(x - 1).get(y)) && column.get(x).get(y).equals(column.get(x - 2).get(y))))
                                {
                                    pop1x=x;
                                    pop1y=y;
                                    points+=45;
                                    popperThree(column,pop1x,pop1y);
                                    mapControl(column);
                                    oneMove+=45;
                                    System.out.println("Score: "+ oneMove);
                                }
                                else
                                {
                                    if ((column.get(x).get(y).equals(column.get(x + 1).get(y)) && column.get(x).get(y).equals(column.get(x + 2).get(y))))
                                    {
                                        pop1x=x+2;
                                        pop1y=y;
                                        points+=45;
                                        popperThree(column,pop1x,pop1y);
                                        mapControl(column);
                                        oneMove+=45;
                                        System.out.println("Score: "+ oneMove);
                                    }
                                }
                            }
                            else {
                                /*---------WWW  W    W WW W W  W W W WW W W W W W W W WWW WW W W W W WW W W WW W W W------------*/
                                if (column.get(x).get(y).equals("W")) {
                                    /*I know that wild card can be a little tricky*/
                                    /*do we have only one different card  like WWT WTW WDW WWD etc. */
                                    /*-----------------------wild to up--------------------*/
                                    /*WTT*/
                                    if (x >= 2 && column.get(x - 1).get(y).equals("T") && column.get(x - 2).get(y).equals("T")) {
                                        pop1x = x;
                                        pop1y = y;
                                        points += 40;
                                /*pop them
                                2t 1w
                                 * middle one can be T + or |
                                 * so be careful to calculate*/
                                        popperThree(column, pop1x, pop1y);
                                        mapControl(column);
                                        oneMove+=40;
                                        System.out.println("Score: "+ oneMove);
                                    } else {
                                        /*WWx*/
                                        if (x >= 2 && column.get(x).get(y).equals(column.get(x - 1).get(y))) {
                                            /* two W's  the last jewel doesn't matter */
                                            if (column.get(x - 2).get(y).equals("D")){
                                                points += 30;
                                                oneMove+=30;
                                            }
                                            if (column.get(x - 2).get(y).equals("S")){
                                                points += 15;
                                                oneMove+=15;
                                            }
                                            if (column.get(x - 2).get(y).equals("T")){
                                                points += 15;
                                                oneMove+=15;
                                            }
                                            if (column.get(x - 2).get(y).equals("W")){
                                                points += 10;
                                                oneMove+=10;
                                            }
                                            if (column.get(x - 2).get(y).equals("+")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            if (column.get(x - 2).get(y).equals("-")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            if (column.get(x - 2).get(y).equals("|")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            if (column.get(x - 2).get(y).equals("\\")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            if (column.get(x - 2).get(y).equals("/")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            points+=20;
                                            oneMove+=20;
                                            pop1x = x;
                                            pop1y = y;
                                            popperThree(column, pop1x, pop1y);
                                            mapControl(column);
                                            System.out.println("Score: "+ oneMove);
                                        /*pop them*/
                                        } else {
                                            if (x >= 2 && (column.get(x - 1).get(y).equals("+")) && ((column.get(x - 2).get(y).equals("+")) || (column.get(x - 2).get(y).equals("-"))
                                                    || (column.get(x - 2).get(y).equals("|")) || (column.get(x - 2).get(y).equals("/")) || (column.get(x - 2).get(y).equals("\\")))) {   /*w with + sign*/
                                                pop1x = x;
                                                pop1y = y;
                                                points+=50;
                                                oneMove+=50;
                                                popperThree(column, pop1x, pop1y);
                                                mapControl(column);
                                                System.out.println("Score: "+ oneMove);
                                                /*pop them*
                                                last one could be anything/
                                                dont forget
                                                 */
                                            } else {
                                                if (x >= 2 && (column.get(x - 1).get(y).equals("|")) && ((column.get(x - 2).get(y).equals("+")) || (column.get(x - 2).get(y).equals("-"))
                                                        || (column.get(x - 2).get(y).equals("|")) || (column.get(x - 2).get(y).equals("/")) || (column.get(x - 2).get(y).equals("\\")))) {   /*W with math symbol | */
                                                    pop1x = x;
                                                    pop1y = y;
                                                    points+=50;
                                                    popperThree(column, pop1x, pop1y);
                                                    mapControl(column);
                                                    oneMove+=50;
                                                    System.out.println("Score: "+ oneMove);
                                                        /*pop them/
                                                } else {
                                                    /*---**********-----wild to down-----**********-----*/
                                                    /*wwx*/
                                                    if (x < column.size() - 2 && (column.get(x).get(y).equals(column.get(x + 1).get(y)))) {
                                                        pop1x = x + 2;
                                                        pop1y = y;
                                                        points+=20;
                                                        if (column.get(x - 2).get(y).equals("D")){
                                                            points += 30;
                                                            oneMove+=30;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("S")){
                                                            points += 15;
                                                            oneMove+=15;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("T")){
                                                            points += 15;
                                                            oneMove+=15;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("W")){
                                                            points += 10;
                                                            oneMove+=10;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("+")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("-")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("|")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("\\")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("/")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        /* last one could be anything */
                                                        popperThree(column, pop1x, pop1y);
                                                        mapControl(column);
                                                        oneMove+=20;
                                                        System.out.println("Score: "+ oneMove);
                                                    } else {
                                                        /*wtt*/
                                                        if (x < column.size() - 2 && column.get(x + 1).get(y).equals("T") && column.get(x + 2).get(y).equals("T")) {
                                                            pop1x = x + 2;
                                                            pop1y = y;
                                                            points+=40;
                                                            oneMove+=40;
                                                            popperThree(column, pop1x, pop1y);
                                                            mapControl(column);
                                                            System.out.println("Score: "+ oneMove);
                                                        } else {
                                                            /*w  |  math*/
                                                            if (x < column.size() - 2 && (column.get(x + 1).get(y).equals("|")) && ((column.get(x + 2).get(y).equals("+")) || (column.get(x + 2).get(y).equals("-"))
                                                                    || (column.get(x + 2).get(y).equals("|")) || (column.get(x + 2).get(y).equals("/")) || (column.get(x + 2).get(y).equals("\\")))) {   /*W with math symbol | */
                                                                pop1x = x + 2;
                                                                pop1y = y;
                                                                points+=50;
                                                                popperThree(column, pop1x, pop1y);
                                                                mapControl(column);
                                                                oneMove+=50;
                                                                System.out.println("Score: "+ oneMove);
                                                            } else {
                                                                if (x < column.size() - 2 && (column.get(x + 1).get(y).equals("+")) && ((column.get(x + 2).get(y).equals("+")) || (column.get(x + 2).get(y).equals("-"))
                                                                        || (column.get(x + 2).get(y).equals("|")) || (column.get(x + 2).get(y).equals("/")) || (column.get(x + 2).get(y).equals("\\")))) {   /*w with + sign*/
                                                                    pop1x = x + 2;
                                                                    pop1y = y;
                                                                    points+=50;
                                                                    oneMove+=50;
                                                                    popperThree(column, pop1x, pop1y);
                                                                    mapControl(column);
                                                                    System.out.println("Score: "+ oneMove);
                                                                } else {
                                                                    if (x < column.size() - 2 && (column.get(x + 1).get(y).equals("+")) && ((column.get(x + 2).get(y).equals("+")) || (column.get(x + 2).get(y).equals("-"))
                                                                            || (column.get(x + 2).get(y).equals("|")) || (column.get(x + 2).get(y).equals("/")) || (column.get(x + 2).get(y).equals("\\")))) {   /*w with + sign*/
                                                                        pop1x = x + 2;
                                                                        pop1y = y;
                                                                        points+=50;
                                                                        /*pop them*/
                                                                        popperThree(column, pop1x, pop1y);
                                                                        oneMove+=50;
                                                                        mapControl(column);
                                                                        System.out.println("Score: "+ oneMove);
                                                                    } else {
                                                                        /*----------------------------------------wild to left----------------------------------------*/
                                                                        if (y >= 2 && column.get(x).get(y - 2).equals("S")) {
                                                                            if (column.get(x).get(y - 1).equals("S")) {
                                                                                System.out.print("");
                                                                                pop1x = x;
                                                                                pop1y = y;
                                                                                pop2x = x;
                                                                                pop2y = y - 1;
                                                                                pop3x = x;
                                                                                pop3y = y - 2;
                                                                                points += 40;
                                                                                popper(column, pop1x, pop1y);
                                                                                popper(column, pop2x, pop2y);
                                                                                popper(column, pop3x, pop3y);
                                                                                mapControl(column);
                                                                                oneMove+=40;
                                                                                System.out.println("Score: "+ oneMove);
                                                                            }
                                                                        } else {
                                                                            if (y >= 2 && column.get(x).get(y - 1).equals("+") || column.get(x).get(y - 1).equals("-")) {
                                                                                if (column.get(x).get(y - 2).equals("+") || column.get(x).get(y - 2).equals("-") || column.get(x).get(y - 2).equals("/")
                                                                                        || column.get(x).get(y - 2).equals("\\") || column.get(x).get(y - 2).equals("|")) {
                                                                                    pop1x = x;
                                                                                    pop1y = y;
                                                                                    pop2x = x;
                                                                                    pop2y = y - 1;
                                                                                    pop3x = x;
                                                                                    pop3y = y - 2;
                                                                                    points += 50;
                                                                                    popper(column, pop1x, pop1y);
                                                                                    popper(column, pop2x, pop2y);
                                                                                    popper(column, pop3x, pop3y);
                                                                                    mapControl(column);
                                                                                    oneMove+=50;
                                                                                    System.out.println("Score: "+ oneMove);
                                                                                }
                                                                            } else {
                                                                                if (y >= 2 && column.get(x).get(y - 1).equals("W")) {
                                                                                    pop1x = x;
                                                                                    pop1y = y;
                                                                                    pop2x = x;
                                                                                    pop2y = y - 1;
                                                                                    pop3x = x;
                                                                                    pop3y = y - 2;
                                                                                    points+=20;
                                                                                    if (column.get(x - 2).get(y).equals("D")){
                                                                                        points += 30;
                                                                                        oneMove+=30;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("S")){
                                                                                        points += 15;
                                                                                        oneMove+=15;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("T")){
                                                                                        points += 15;
                                                                                        oneMove+=15;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("W")){
                                                                                        points += 10;
                                                                                        oneMove+=10;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("+")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("-")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("|")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("\\")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("/")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    oneMove+=50;
                                                                                    popper(column, pop1x, pop1y);
                                                                                    popper(column, pop2x, pop2y);
                                                                                    popper(column, pop3x, pop3y);
                                                                                    mapControl(column);
                                                                                    System.out.println("Score: "+ oneMove);
                                                                                } else {
                                                                                    /*----------------------------------------wild to right----------------------------------------*/
                                                                                    if (y < column.get(x).size() - 2 && column.get(x).get(y + 2).equals("S")) {
                                                                                        if (column.get(x).get(y + 1).equals("S")) {
                                                                                            pop1x = x;
                                                                                            pop1y = y;
                                                                                            pop2x = x;
                                                                                            pop2y = y + 1;
                                                                                            pop3x = x;
                                                                                            pop3y = y + 2;
                                                                                            points+=40;
                                                                                            popper(column, pop1x, pop1y);
                                                                                            popper(column, pop2x, pop2y);
                                                                                            popper(column, pop3x, pop3y);
                                                                                            mapControl(column);
                                                                                            oneMove+=40;
                                                                                            System.out.println("Score: "+ oneMove);
                                                                                        }
                                                                                    } else {
                                                                                        if (y < column.get(x).size() - 2 && column.get(x).get(y + 1).equals("+") || column.get(x).get(y + 1).equals("-")) {
                                                                                            if (column.get(x).get(y + 1).equals("+") || column.get(x).get(y + 1).equals("-") || column.get(x).get(y + 1).equals("/")
                                                                                                    || column.get(x).get(y + 1).equals("\\") || column.get(x).get(y + 1).equals("|")) {
                                                                                                pop1x = x;
                                                                                                pop1y = y;
                                                                                                pop2x = x;
                                                                                                pop2y = y + 1;
                                                                                                pop3x = x;
                                                                                                pop3y = y + 2;
                                                                                                points+=50;
                                                                                                popper(column, pop1x, pop1y);
                                                                                                popper(column, pop2x, pop2y);
                                                                                                popper(column, pop3x, pop3y);
                                                                                                mapControl(column);
                                                                                                oneMove+=50;
                                                                                                System.out.println("Score: "+ oneMove);
                                                                                            }
                                                                                        } else {
                                                                                            if (y < column.get(x).size() - 2 && column.get(x).get(y + 1).equals("W")) {
                                                                                                pop1x = x;
                                                                                                pop1y = y;
                                                                                                pop2x = x;
                                                                                                pop2y = y + 1;
                                                                                                pop3x = x;
                                                                                                pop3y = y + 2;
                                                                                                points+=20;
                                                                                                if (column.get(x - 2).get(y).equals("D")){
                                                                                                    points += 30;
                                                                                                    oneMove+=30;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("S")){
                                                                                                    points += 15;
                                                                                                    oneMove+=15;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("T")){
                                                                                                    points += 15;
                                                                                                    oneMove+=15;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("W")){
                                                                                                    points += 10;
                                                                                                    oneMove+=10;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("+")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("-")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("|")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("\\")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("/")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                popper(column, pop1x, pop1y);
                                                                                                popper(column, pop2x, pop2y);
                                                                                                popper(column, pop3x, pop3y);
                                                                                                mapControl(column);
                                                                                                oneMove+=20;
                                                                                                System.out.println("Score: "+ oneMove);
                                                                                            } else {
                                                                                                /*--------------------wild to left up--------------------*/
                                                                                                if (y > 1 && x > 1 && (column.get(x).get(y).equals(column.get(x - 1).get(y - 1)))) {
                                                                                                    pop1x = x;
                                                                                                    pop1y = y;
                                                                                                    pop2x = x - 1;
                                                                                                    pop2y = y - 1;
                                                                                                    pop3x = x - 2;
                                                                                                    pop3y = y - 2;
                                                                                                    points+=20;
                                                                                                    if (column.get(x - 2).get(y).equals("D")){
                                                                                                        points += 30;
                                                                                                        oneMove+=30;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("S")){
                                                                                                        points += 15;
                                                                                                        oneMove+=15;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("T")){
                                                                                                        points += 15;
                                                                                                        oneMove+=15;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("W")){
                                                                                                        points += 10;
                                                                                                        oneMove+=10;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("+")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("-")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("|")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("\\")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("/")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    popper(column, pop1x, pop1y);
                                                                                                    popper(column, pop2x, pop2y);
                                                                                                    popper(column, pop3x, pop3y);
                                                                                                    mapControl(column);
                                                                                                    oneMove+=20;
                                                                                                    System.out.println("Score: "+ oneMove);
                                                                                                } else {
                                                                                                    if (y > 1 && x > 1 && (column.get(x - 1).get(y - 1).equals("D") && column.get(x - 2).get(y - 2).equals("D"))) {
                                                                                                        pop1x = x;
                                                                                                        pop1y = y;
                                                                                                        pop2x = x - 1;
                                                                                                        pop2y = y - 1;
                                                                                                        pop3x = x - 2;
                                                                                                        pop3y = y - 2;
                                                                                                        points+=70;
                                                                                                        popper(column, pop1x, pop1y);
                                                                                                        popper(column, pop2x, pop2y);
                                                                                                        popper(column, pop3x, pop3y);
                                                                                                        mapControl(column);
                                                                                                        oneMove+=70;
                                                                                                        System.out.println("Score: "+ oneMove);
                                                                                                    } else {
                                                                                                        if (y > 1 && x > 1 && column.get(x - 1).get(y - 1).equals("\\")) {
                                                                                                            if (column.get(x - 2).get(y - 2).equals("+") || column.get(x - 2).get(y - 2).equals("-") || column.get(x - 2).get(y - 2).equals("/")
                                                                                                                    || column.get(x - 2).get(y - 2).equals("\\") || column.get(x - 2).get(y - 2).equals("|")) {
                                                                                                                pop1x = x;
                                                                                                                pop1y = y;
                                                                                                                pop2x = x - 1;
                                                                                                                pop2y = y - 1;
                                                                                                                pop3x = x - 2;
                                                                                                                pop3y = y - 2;
                                                                                                                points+=50;
                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                mapControl(column);
                                                                                                                oneMove+=50;
                                                                                                                System.out.println("Score: "+ oneMove);
                                                                                                            }
                                                                                                        } else {
                                                                                                            /*--------------------wild to right down--------------------*/
                                                                                                            if (x < column.size() - 2 && y < column.get(x).size() && column.get(x + 1).get(y + 1).equals("\\")) {
                                                                                                                if (column.get(x + 2).get(y + 2).equals("+") || column.get(x + 2).get(y + 2).equals("-") || column.get(x + 2).get(y + 2).equals("/")
                                                                                                                        || column.get(x + 2).get(y + 2).equals("\\") || column.get(x + 2).get(y + 2).equals("|")) {
                                                                                                                    pop1x = x;
                                                                                                                    pop1y = y;
                                                                                                                    pop2x = x + 1;
                                                                                                                    pop2y = y + 1;
                                                                                                                    pop3x = x + 2;
                                                                                                                    pop3y = y + 2;
                                                                                                                    points+=50;
                                                                                                                    popper(column, pop1x, pop1y);
                                                                                                                    popper(column, pop2x, pop2y);
                                                                                                                    popper(column, pop3x, pop3y);
                                                                                                                    mapControl(column);
                                                                                                                    oneMove+=50;
                                                                                                                    System.out.println("Score: "+ oneMove);
                                                                                                                }
                                                                                                            } else {
                                                                                                                if (x < column.size() - 2 && y < column.get(x).size() && column.get(x + 1).get(y + 1).equals("D")) {
                                                                                                                    if (column.get(x + 2).get(y + 2).equals("D")/**/) {
                                                                                                                        pop1x = x;
                                                                                                                        pop1y = y;
                                                                                                                        pop2x = x + 1;
                                                                                                                        pop2y = y + 1;
                                                                                                                        pop3x = x + 2;
                                                                                                                        pop3y = y + 2;
                                                                                                                        points+=70;
                                                                                                                        popper(column, pop1x, pop1y);
                                                                                                                        popper(column, pop2x, pop2y);
                                                                                                                        popper(column, pop3x, pop3y);
                                                                                                                        mapControl(column);
                                                                                                                        oneMove+=70;
                                                                                                                        System.out.println("Score: "+ oneMove);
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    if (x < column.size() - 2 && y < column.get(x).size() && column.get(x + 1).get(y + 1).equals("W")) {
                                                                                                                        pop1x = x;
                                                                                                                        pop1y = y;
                                                                                                                        pop2x = x + 1;
                                                                                                                        pop2y = y + 1;
                                                                                                                        pop3x = x + 2;
                                                                                                                        pop3y = y + 2;
                                                                                                                        points+=20;
                                                                                                                        if (column.get(x - 2).get(y).equals("D")){
                                                                                                                            points += 30;
                                                                                                                            oneMove+=30;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("S")){
                                                                                                                            points += 15;
                                                                                                                            oneMove+=15;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("T")){
                                                                                                                            points += 15;
                                                                                                                            oneMove+=15;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("W")){
                                                                                                                            points += 10;
                                                                                                                            oneMove+=10;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("+")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("-")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("|")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("\\")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("/")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        popper(column, pop1x, pop1y);
                                                                                                                        popper(column, pop2x, pop2y);
                                                                                                                        popper(column, pop3x, pop3y);
                                                                                                                        mapControl(column);
                                                                                                                        oneMove+=20;
                                                                                                                        System.out.println("Score: "+ oneMove);
                                                                                                                    } else {
                                                                                                                        /*--------------------wild to right up--------------------*/
                                                                                                                        if (x >= 2 && y < column.get(x).size() - 2 && column.get(x - 1).get(y + 1).equals("/")) {
                                                                                                                            if (column.get(x - 2).get(y + 2).equals("+") || column.get(x - 2).get(y + 2).equals("-") || column.get(x - 2).get(y + 2).equals("/")
                                                                                                                                    || column.get(x - 2).get(y + 2).equals("\\") || column.get(x - 2).get(y + 2).equals("|")) {
                                                                                                                                pop1x = x;
                                                                                                                                pop1y = y;
                                                                                                                                pop2x = x - 1;
                                                                                                                                pop2y = y + 1;
                                                                                                                                pop3x = x - 2;
                                                                                                                                pop3y = y + 2;
                                                                                                                                points+=50;
                                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                                mapControl(column);
                                                                                                                                oneMove+=50;
                                                                                                                                System.out.println("Score: "+ oneMove);
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            if (x >= 2 && y < column.get(x).size() - 2 && column.get(x - 1).get(y + 1).equals("D") && column.get(x - 2).get(y + 2).equals("D")) {
                                                                                                                                pop1x = x;
                                                                                                                                pop1y = y;
                                                                                                                                pop2x = x - 1;
                                                                                                                                pop2y = y + 1;
                                                                                                                                pop3x = x - 2;
                                                                                                                                pop3y = y + 2;
                                                                                                                                points+=70;
                                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                                mapControl(column);
                                                                                                                                oneMove+=70;
                                                                                                                                System.out.println("Score: "+ oneMove);
                                                                                                                            } else {
                                                                                                                                if (x >= 2 && y < column.get(x).size() - 2 && column.get(x - 1).get(y + 1).equals("w")) {
                                                                                                                                    pop1x = x;
                                                                                                                                    pop1y = y;
                                                                                                                                    pop2x = x - 1;
                                                                                                                                    pop2y = y + 1;
                                                                                                                                    pop3x = x - 2;
                                                                                                                                    pop3y = y + 2;
                                                                                                                                    points+=20;
                                                                                                                                    if (column.get(x - 2).get(y).equals("D")){
                                                                                                                                        points += 30;
                                                                                                                                        oneMove+=30;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("S")){
                                                                                                                                        points += 15;
                                                                                                                                        oneMove+=15;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("T")){
                                                                                                                                        points += 15;
                                                                                                                                        oneMove+=15;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("W")){
                                                                                                                                        points += 10;
                                                                                                                                        oneMove+=10;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("+")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("-")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("|")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("\\")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("/")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    popper(column, pop1x, pop1y);
                                                                                                                                    popper(column, pop2x, pop2y);
                                                                                                                                    popper(column, pop3x, pop3y);
                                                                                                                                    mapControl(column);
                                                                                                                                    oneMove+=20;
                                                                                                                                    System.out.println("Score: "+ oneMove);

                                                                                                                                } else {
                                                                                                                                    /*--------------------wild to left down--------------------*/
                                                                                                                                    if (x >= 2 && y < column.get(x).size() - 2 && column.get(x + 1).get(y - 1).equals("/")) {
                                                                                                                                        if (column.get(x + 2).get(y - 2).equals("+") || column.get(x + 2).get(y - 2).equals("-") || column.get(x + 2).get(y - 2).equals("/")
                                                                                                                                                || column.get(x + 2).get(y - 2).equals("\\") || column.get(x + 2).get(y - 2).equals("|")) {
                                                                                                                                            pop1x = x;
                                                                                                                                            pop1y = y;
                                                                                                                                            pop2x = x + 1;
                                                                                                                                            pop2y = y - 1;
                                                                                                                                            pop3x = x + 2;
                                                                                                                                            pop3y = y - 2;
                                                                                                                                            points+=50;
                                                                                                                                            popper(column, pop1x, pop1y);
                                                                                                                                            popper(column, pop2x, pop2y);
                                                                                                                                            popper(column, pop3x, pop3y);
                                                                                                                                            mapControl(column);

                                                                                                                                            oneMove+=50;
                                                                                                                                            System.out.println("Score: "+ oneMove);
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        if (x >= 2 && y < column.get(x).size() - 2 && column.get(x + 1).get(y - 1).equals("D")) {
                                                                                                                                            if (column.get(x + 2).get(y - 2).equals("D")) {
                                                                                                                                                pop1x = x;
                                                                                                                                                pop1y = y;
                                                                                                                                                pop2x = x + 1;
                                                                                                                                                pop2y = y - 1;
                                                                                                                                                pop3x = x + 2;
                                                                                                                                                pop3y = y - 2;
                                                                                                                                                points += 70;
                                                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                                                mapControl(column);
                                                                                                                                                oneMove += 70;
                                                                                                                                                System.out.println("Score: " + oneMove);
                                                                                                                                            }

                                                                                                                                        } else {
                                                                                                                                            if (x >= 2 && y < column.get(x).size() - 2 && column.get(x + 1).get(y - 1).equals("W")) {
                                                                                                                                                pop1x = x;
                                                                                                                                                pop1y = y;
                                                                                                                                                pop2x = x + 1;
                                                                                                                                                pop2y = y - 1;
                                                                                                                                                pop3x = x + 2;
                                                                                                                                                pop3y = y - 2;
                                                                                                                                                points+=20;
                                                                                                                                                if (column.get(x - 2).get(y).equals("D")){
                                                                                                                                                    points += 30;
                                                                                                                                                    oneMove+=30;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("S")){
                                                                                                                                                    points += 15;
                                                                                                                                                    oneMove+=15;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("T")){
                                                                                                                                                    points += 15;
                                                                                                                                                    oneMove+=15;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("W")){
                                                                                                                                                    points += 10;
                                                                                                                                                    oneMove+=10;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("+")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("-")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("|")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("\\")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("/")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                                                mapControl(column);
                                                                                                                                                oneMove+=20;
                                                                                                                                                System.out.println("Score: "+ oneMove);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }else{
                                    /*+++++++ ++ + + + + + + ++ +++++++++*/
                                    if (column.get(x).get(y).equals("+")){
                                        /* +  looking left*/
                                        if ( y>1
                                                && (column.get(x).get(y-1).equals("+") || column.get(x).get(y-1).equals("-"))  && (column.get(x).get(y-2).equals("+")
                                                || column.get(x).get(y-2).equals("-") || column.get(x).get(y-2).equals("/") ||column.get(x).get(y-2).equals("|")
                                                ||column.get(x).get(y-2).equals("\\"))){
                                            points+=60;
                                            pop1x = x;
                                            pop1y = y;
                                            pop2x = x;
                                            pop2y = y - 1;
                                            pop3x = x;
                                            pop3y = y - 2;
                                            popper(column, pop1x, pop1y);
                                            popper(column, pop2x, pop2y);
                                            popper(column, pop3x, pop3y);
                                            mapControl(column);
                                            oneMove+=60;
                                            System.out.println("Score: "+ oneMove);
                                        }else {
                                            /*  + looking right  */
                                            if ( y<column.get(x).size()-2
                                                    && (column.get(x).get(y+1).equals("+") || column.get(x).get(y+1).equals("-"))  && (column.get(x).get(y+2).equals("+")
                                                    || column.get(x).get(y+2).equals("-") || column.get(x).get(y+2).equals("/") ||column.get(x).get(y+2).equals("|")
                                                    ||column.get(x).get(y+2).equals("\\"))){
                                                points+=60;
                                                pop1x = x;
                                                pop1y = y;
                                                pop2x = x;
                                                pop2y = y + 1;
                                                pop3x = x;
                                                pop3y = y + 2;
                                                popper(column, pop1x, pop1y);
                                                popper(column, pop2x, pop2y);
                                                popper(column, pop3x, pop3y);
                                                mapControl(column);
                                                oneMove+=60;
                                                System.out.println("Score: "+ oneMove);
                                            }else {
                                                /* +  looking up*/
                                                if ( x<column.size()-2
                                                        && (column.get(x-1).get(y).equals("+") || column.get(x-1).get(y).equals("|"))  && column.get(x-2).get(y).equals("+")
                                                        || column.get(x-2).get(y).equals("-") || column.get(x-2).get(y).equals("/") ||column.get(x-2).get(y).equals("|")
                                                        ||column.get(x-2).get(y).equals("\\")){
                                                    points+=60;
                                                    popperThree(column,x,y);
                                                    mapControl(column);
                                                    oneMove+=60;
                                                    System.out.println("Score: "+ oneMove);
                                                }else {
                                                    /* +  looking down*/
                                                    if ( x<column.size()-2
                                                            && (column.get(x+1).get(y).equals("+") || column.get(x+1).get(y).equals("|"))  && column.get(x+2).get(y).equals("+")
                                                            || column.get(x+2).get(y).equals("-") || column.get(x+2).get(y).equals("/") ||column.get(x+2).get(y).equals("|")
                                                            ||column.get(x+2).get(y).equals("\\")){
                                                        points+=60;
                                                        popperThree(column,x+2,y);
                                                        mapControl(column);
                                                        oneMove+=60;
                                                        System.out.println("Score: "+ oneMove);
                                                    }
                                                }
                                            }
                                        }
                                    }else {
                                        /*  -   - - - - - - - - */
                                        if (column.get(x).get(y).equals("-")){
                                            /* -  looking left*/
                                            if (y>1
                                                    && (column.get(x).get(y-1).equals("+") || column.get(x).get(y-1).equals("-"))  && column.get(x).get(y-2).equals("+")
                                                    || column.get(x).get(y-2).equals("-") || column.get(x).get(y-2).equals("/") ||column.get(x).get(y-2).equals("|")
                                                    ||column.get(x).get(y-2).equals("\\"))
                                            {
                                                points+=60;
                                                pop1x = x;
                                                pop1y = y;
                                                pop2x = x;
                                                oneMove+=60;
                                                pop2y = y - 1;
                                                pop3x = x;
                                                pop3y = y - 2;
                                                popper(column, pop1x, pop1y);
                                                popper(column, pop2x, pop2y);
                                                popper(column, pop3x, pop3y);
                                                mapControl(column);
                                                System.out.println("Score: "+ oneMove);
                                            }else {
                                                /*  - looking right  */
                                                if ( y<column.get(x).size()-2
                                                        && (column.get(x).get(y+1).equals("+") || column.get(x).get(y+1).equals("-"))  && column.get(x).get(y+2).equals("+")
                                                        || column.get(x).get(y+2).equals("-") || column.get(x).get(y+2).equals("/") ||column.get(x).get(y+2).equals("|")
                                                        ||column.get(x).get(y+2).equals("\\")){
                                                    pop3x = x;
                                                    pop3y = y + 2;
                                                    points+=60;
                                                    pop1x = x;
                                                    pop1y = y;
                                                    pop2x = x;
                                                    pop2y = y + 1;
                                                    popper(column, pop1x, pop1y);
                                                    popper(column, pop2x, pop2y);
                                                    popper(column, pop3x, pop3y);
                                                    mapControl(column);
                                                    oneMove+=60;
                                                    System.out.println("Score: "+ oneMove);
                                                }
                                            }
                                        }else {
                                            if (column.get(x).get(y).equals("|")){
                                                /* | looking up */
                                                if ( x>=2
                                                        && (column.get(x-1).get(y).equals("+") || column.get(x-1).get(y).equals("|"))  && (column.get(x-2).get(y).equals("+")
                                                        || column.get(x-2).get(y).equals("-") || column.get(x-2).get(y).equals("/") ||column.get(x-2).get(y).equals("|")
                                                        ||column.get(x-2).get(y).equals("\\"))){
                                                    popperThree(column,x,y);
                                                    points+=60;
                                                    mapControl(column);
                                                    oneMove+=60;
                                                    System.out.println("Score: "+ oneMove);
                                                }else {
                                                    /* |  looking down*/
                                                    if ( x<column.size()-2
                                                            && (column.get(x+1).get(y).equals("+") || column.get(x+1).get(y).equals("|"))  && (column.get(x+2).get(y).equals("+")
                                                            || column.get(x+2).get(y).equals("-") || column.get(x+2).get(y).equals("/") ||column.get(x+2).get(y).equals("|")
                                                            ||column.get(x+2).get(y).equals("\\"))){
                                                        points+=60;
                                                        popperThree(column,x+2,y);
                                                        oneMove+=60;
                                                        mapControl(column);
                                                        System.out.println("Score: "+ oneMove);
                                                    }
                                                }
                                            }
                                            else {
                                                /*////// / / / / / / */
                                                if (column.get(x).get(y).equals("/")){
                                                    /*right up*/
                                                    if (y<column.get(x).size()-2  && x>=2
                                                            && ((column.get(x).get(y).equals(column.get(x - 1).get(y + 1))) && (column.get(x-2).get(y+2).equals("+")
                                                            || column.get(x-2).get(y+2).equals("-") || column.get(x-2).get(y+2).equals("/") ||column.get(x-2).get(y+2).equals("|")
                                                            ||column.get(x-2).get(y+2).equals("\\"))))
                                                    {
                                                        pop1x=x;
                                                        pop1y=y;
                                                        pop3y=y+2;
                                                        pop2x=x-1;
                                                        pop2y=y+1;
                                                        pop3x=x-2;
                                                        popper(column,pop1x,pop1y);
                                                        popper(column,pop2x,pop2y);
                                                        popper(column,pop3x,pop3y);
                                                        mapControl(column);
                                                        oneMove+=90;
                                                        points+=90;
                                                        System.out.println("Score: "+ oneMove);
                                                    }
                                                    else
                                                    {
                                                        /*left down*/
                                                        if (x<column.get(x).size()-2 && y>=2 &&(column.get(x).get(y).equals(column.get(x + 1).get(y - 1)) && ((column.get(x+2).get(y-2).equals("+")
                                                                || column.get(x+2).get(y-2).equals("-") || column.get(x+2).get(y-2).equals("/") ||column.get(x+2).get(y-2).equals("|")
                                                                ||column.get(x+2).get(y-2).equals("\\")))))
                                                        {
                                                            pop2x=x+1;
                                                            pop2y=y-1;
                                                            pop3x=x+2;
                                                            pop3y=y-2;
                                                            pop1x=x;
                                                            pop1y=y;
                                                            popper(column,pop1x,pop1y);
                                                            popper(column,pop2x,pop2y);
                                                            popper(column,pop3x,pop3y);
                                                            mapControl(column);
                                                            points+=90;
                                                            oneMove+=90;
                                                            System.out.println("Score: "+ oneMove);
                                                        }
                                                    }
                                                }
                                                else {
                                                    if (column.get(x).get(y).equals("\\")){
                                                        /*left up*/
                                                        if (y>=2 && x>=2 &&column.get(x).get(y).equals(column.get(x - 1).get(y - 1)) && ((column.get(x+2).get(y-2).equals("+")
                                                                || column.get(x+2).get(y-2).equals("-") || column.get(x+2).get(y-2).equals("/") ||column.get(x+2).get(y-2).equals("|")
                                                                ||column.get(x+2).get(y-2).equals("\\"))) )
                                                        {
                                                            pop3x=x-2;
                                                            pop3y=y-2;
                                                            pop1x=x;
                                                            pop1y=y;
                                                            pop2x=x-1;
                                                            pop2y=y-1;
                                                            popper(column,pop2x,pop2y);
                                                            popper(column,pop1x,pop1y);
                                                            popper(column,pop3x,pop3y);
                                                            mapControl(column);
                                                            points+=90;
                                                            oneMove+=90;
                                                            System.out.println("Score: "+ oneMove);
                                                        }
                                                        else
                                                        {
                                                            /*right down*/
                                                            if (y<column.get(x).size()-2 &&  x<column.size()-2
                                                                    &&(column.get(x).get(y).equals(column.get(x + 1).get(y + 1)) && (column.get(x+2).get(y+2).equals("+")
                                                                    || column.get(x+2).get(y+2).equals("-") || column.get(x+2).get(y+2).equals("/") ||column.get(x+2).get(y+2).equals("|")
                                                                    ||column.get(x+2).get(y+2).equals("\\"))))
                                                            {
                                                                pop1x=x;
                                                                pop1y=y;
                                                                pop3y=y+2;
                                                                pop3x=x+2;
                                                                pop2x=x+1;
                                                                pop2y=y+1;
                                                                popper(column,pop2x,pop2y);
                                                                popper(column,pop3x,pop3y);
                                                                popper(column,pop1x,pop1y);
                                                                mapControl(column);
                                                                points+=90;
                                                                oneMove+=90;
                                                                System.out.println("Score: "+ oneMove);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                }
                            }
                        }
                    }
                } else{
                    if (reader2.equals("E")) {
                        System.out.println();
                        System.out.println("Your total points: " + points);
                        System.out.println();
                        System.out.print("Enter Name: ");
                        sc.nextLine();
                        System.out.println();
                        Leaders lea = new Leaders();
                        lea.takeLeaders(reader2,points);
                        System.out.println("Good bye!");
                        i++;
                        break;
                    }
                }
            }
            catch (RuntimeException aiob){
                System.out.println("Run time exception occured");
                throw  (aiob);
            }
            oneMove=0;
        }
    }
    public void gameWargv(File grid, File inputFile) throws Exception{
        BufferedReader bf = new BufferedReader(new FileReader(grid));
        BufferedReader bf2 = new BufferedReader(new FileReader(inputFile));
        ArrayList<ArrayList<String>> column = new ArrayList<>();
        ArrayList<String > orders = new ArrayList<>();
        String reader;
        int columnCount=0;
        /*reading the game grid*/
        while ((reader= bf.readLine()) != null)
        {
            ArrayList<String> row = new ArrayList<>();
            String[] parts = reader.split(" ");
            for (int a = 0 ;  a<parts.length ;a++)
            {
                //System.out.print(parts[a]);
                row.add(a,parts[a]);
            }
            column.add(columnCount,row);
            columnCount++;
        }
        /*control mechanism*/
        //mapControl(column);
        /*taking orders*/
        while((reader=bf2.readLine())!=null){
            orders.add(reader);
        }
        /*game time i suppose lets start with orders*/
        int gameCount=0;
        mapControl(column);
        int points=0;
        int oneMove=0;
        while (gameCount<orders.size()) {
            try {
                System.out.print("Select coordinate or enter E to end the game: ");
                System.out.println(orders.get(gameCount));
                int pop1x,pop1y,pop2x,pop2y,pop3x,pop3y;
                /*check if it is one lenght which is E*/
                if (orders.get(gameCount).split(" ").length > 1) {
                    int x = Integer.parseInt(orders.get(gameCount).split(" ")[0]);
                    int y = Integer.parseInt(orders.get(gameCount).split(" ")[1]);
                    if (x>=column.size() || y>=column.get(0).size()){
                        System.out.println("Sorry but i need proper indexes like "+(column.size()-1) +" "+(column.get(0).size()-1));
                    }
                    else {
                    if (column.get(x).get(y).equals("D"))
                    {
                        /*left up*/
                        if (y>=2 && x>=2 &&column.get(x).get(y).equals(column.get(x - 1).get(y - 1)) && column.get(x).get(y).equals(column.get(x - 2).get(y - 2)) )
                        {
                            pop1x=x;
                            pop1y=y;
                            pop2x=x-1;
                            pop2y=y-1;
                            pop3x=x-2;
                            pop3y=y-2;
                            popper(column,pop1x,pop1y);
                            popper(column,pop2x,pop2y);
                            popper(column,pop3x,pop3y);
                            mapControl(column);
                            points+=90;
                            oneMove+=90;
                            System.out.println("Score: "+ oneMove);
                        }
                        else
                        {
                            /*right down*/
                            if (y<column.get(x).size()-2 &&  x<column.size()-2
                                    &&(column.get(x).get(y).equals(column.get(x + 1).get(y + 1)) && column.get(x).get(y).equals(column.get(x + 2).get(y + 2))))
                            {
                                pop1x=x;
                                pop1y=y;
                                pop2x=x+1;
                                pop2y=y+1;
                                pop3x=x+2;
                                pop3y=y+2;
                                popper(column,pop1x,pop1y);
                                popper(column,pop2x,pop2y);
                                popper(column,pop3x,pop3y);
                                mapControl(column);
                                points+=90;
                                oneMove+=90;
                                System.out.println("Score: "+ oneMove);
                            }
                            else
                            {
                                /*right up*/
                                if (y<column.get(x).size()-2  && x>2
                                        && (column.get(x).get(y).equals(column.get(x - 1).get(y + 1)) && column.get(x).get(y).equals(column.get(x - 2).get(y + 2))))
                                {
                                    pop1x=x;
                                    pop1y=y;
                                    pop2x=x-1;
                                    pop2y=y+1;
                                    pop3x=x-2;
                                    pop3y=y+2;
                                    popper(column,pop1x,pop1y);
                                    popper(column,pop2x,pop2y);
                                    popper(column,pop3x,pop3y);
                                    mapControl(column);
                                    points+=90;
                                    oneMove+=90;
                                    System.out.println("Score: "+ oneMove);
                                }
                                else
                                {
                                    /*left down*/
                                    if ((column.get(x).get(y).equals(column.get(x + 1).get(y - 1)) && column.get(x).get(y).equals(column.get(x + 2).get(y - 2)))
                                            && x<column.get(x).size()-2 && y>2 )
                                    {
                                        pop1x=x;
                                        pop1y=y;
                                        pop2x=x+1;
                                        pop2y=y-1;
                                        pop3x=x+2;
                                        pop3y=y-2;
                                        popper(column,pop1x,pop1y);
                                        popper(column,pop2x,pop2y);
                                        popper(column,pop3x,pop3y);
                                        mapControl(column);
                                        points+=90;
                                        oneMove+=90;
                                        System.out.println("Score: "+ oneMove);
                                    }
                                }
                            }
                        }
                    }else{
                        /*---------SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS--------*/
                        if (column.get(x).get(y).equals("S"))
                        {
                            if ((column.get(x).get(y).equals(column.get(x).get(y - 1)) && column.get(x).get(y).equals(column.get(x).get(y - 2))))
                            {
                                pop1x=x;
                                pop1y=y;
                                pop2x=x;
                                pop2y=y-1;
                                pop3x=x;
                                pop3y=y-2;
                                popper(column,pop1x,pop1y);
                                popper(column,pop2x,pop2y);
                                popper(column,pop3x,pop3y);
                                points+=45;
                                oneMove+=45;
                                System.out.println("Score: "+ oneMove);
                                mapControl(column);
                            }
                            else
                            {
                                if ((column.get(x).get(y).equals(column.get(x).get(y + 1)) && column.get(x).get(y).equals(column.get(x).get(y + 2))))
                                {
                                    pop1x=x;
                                    pop1y=y;
                                    pop2x=x;
                                    pop2y=y+1;
                                    pop3x=x;
                                    pop3y=y+2;
                                    points+=45;
                                    popper(column,pop1x,pop1y);
                                    popper(column,pop2x,pop2y);
                                    popper(column,pop3x,pop3y);
                                    mapControl(column);
                                    oneMove+=45;
                                    System.out.println("Score: "+ oneMove);
                                }
                            }
                        }
                        else{
                            /*------------TTTTTTTTTTTTTTTTTTTTTTTT----------*/
                            if (column.get(x).get(y).equals("T"))
                            {
                                if ((column.get(x).get(y).equals(column.get(x - 1).get(y)) && column.get(x).get(y).equals(column.get(x - 2).get(y))))
                                {
                                    pop1x=x;
                                    pop1y=y;
                                    points+=45;
                                    popperThree(column,pop1x,pop1y);
                                    mapControl(column);
                                    oneMove+=45;
                                    System.out.println("Score: "+ oneMove);
                                }
                                else
                                {
                                    if ((column.get(x).get(y).equals(column.get(x + 1).get(y)) && column.get(x).get(y).equals(column.get(x + 2).get(y))))
                                    {
                                        pop1x=x+2;
                                        pop1y=y;
                                        points+=45;
                                        popperThree(column,pop1x,pop1y);
                                        mapControl(column);
                                        oneMove+=45;
                                        System.out.println("Score: "+ oneMove);
                                    }
                                }
                            }
                            else {
                                /*---------WWW  W    W WW W W  W W W WW W W W W W W W WWW WW W W W W WW W W WW W W W------------*/
                                if (column.get(x).get(y).equals("W")) {
                                    /*I know that wild card can be a little tricky*/
                                    /*do we have only one different card  like WWT WTW WDW WWD etc. */
                                    /*-----------------------wild to up--------------------*/
                                    /*WTT*/
                                    if (x >= 2 && column.get(x - 1).get(y).equals("T") && column.get(x - 2).get(y).equals("T")) {
                                        pop1x = x;
                                        pop1y = y;

                                        points += 40;
                                /*pop them
                                2t 1w
                                 * middle one can be T + or |
                                 * so be careful to calculate*/
                                        popperThree(column, pop1x, pop1y);
                                        mapControl(column);
                                        oneMove+=40;
                                        System.out.println("Score: "+ oneMove);
                                    } else {
                                        /*WWx*/
                                        if (x >= 2 && column.get(x).get(y).equals(column.get(x - 1).get(y))) {
                                            /* two W's  the last jewel doesn't matter */
                                            if (column.get(x - 2).get(y).equals("D")){
                                                points += 30;
                                                oneMove+=30;
                                            }
                                            if (column.get(x - 2).get(y).equals("S")){
                                                points += 15;
                                                oneMove+=15;
                                            }
                                            if (column.get(x - 2).get(y).equals("T")){
                                                points += 15;
                                                oneMove+=15;
                                            }
                                            if (column.get(x - 2).get(y).equals("W")){
                                                points += 10;
                                                oneMove+=10;
                                            }
                                            if (column.get(x - 2).get(y).equals("+")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            if (column.get(x - 2).get(y).equals("-")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            if (column.get(x - 2).get(y).equals("|")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            if (column.get(x - 2).get(y).equals("\\")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            if (column.get(x - 2).get(y).equals("/")){
                                                points += 20;
                                                oneMove+=20;
                                            }
                                            points+=20;
                                            oneMove+=20;
                                            pop1x = x;
                                            pop1y = y;
                                            popperThree(column, pop1x, pop1y);
                                            mapControl(column);
                                            System.out.println("Score: "+ oneMove);
                                        /*pop them*
                                        last one could be anything/
                                        dont forget
                                         */
                                        } else {
                                            if (x >= 2 && (column.get(x - 1).get(y).equals("+")) && ((column.get(x - 2).get(y).equals("+")) || (column.get(x - 2).get(y).equals("-"))
                                                    || (column.get(x - 2).get(y).equals("|")) || (column.get(x - 2).get(y).equals("/")) || (column.get(x - 2).get(y).equals("\\")))) {   /*w with + sign*/
                                                pop1x = x;
                                                pop1y = y;
                                                points+=50;
                                                oneMove+=50;
                                                popperThree(column, pop1x, pop1y);
                                                mapControl(column);
                                                System.out.println("Score: "+ oneMove);
                                                /*pop them*
                                                last one could be anything/
                                                dont forget
                                                 */
                                            } else {
                                                if (x >= 2 && (column.get(x - 1).get(y).equals("|")) && ((column.get(x - 2).get(y).equals("+")) || (column.get(x - 2).get(y).equals("-"))
                                                        || (column.get(x - 2).get(y).equals("|")) || (column.get(x - 2).get(y).equals("/")) || (column.get(x - 2).get(y).equals("\\")))) {   /*W with math symbol | */
                                                    pop1x = x;
                                                    pop1y = y;
                                                    points+=50;
                                                    popperThree(column, pop1x, pop1y);
                                                    mapControl(column);
                                                    oneMove+=50;
                                                    System.out.println("Score: "+ oneMove);
                                                        /*pop them*
                                                        last one could be anything/
                                                        dont forget
                                                         */
                                                } else {
                                                    /*---**********-----wild to down-----**********-----*/
                                                    /*wwx*/
                                                    if (x < column.size() - 2 && (column.get(x).get(y).equals(column.get(x + 1).get(y)))) {
                                                        pop1x = x + 2;
                                                        pop1y = y;
                                                        points+=20;
                                                        if (column.get(x - 2).get(y).equals("D")){
                                                            points += 30;
                                                            oneMove+=30;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("S")){
                                                            points += 15;
                                                            oneMove+=15;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("T")){
                                                            points += 15;
                                                            oneMove+=15;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("W")){
                                                            points += 10;
                                                            oneMove+=10;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("+")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("-")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("|")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("\\")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        if (column.get(x - 2).get(y).equals("/")){
                                                            points += 20;
                                                            oneMove+=20;
                                                        }
                                                        /* last one could be anything */
                                                        popperThree(column, pop1x, pop1y);
                                                        mapControl(column);
                                                        oneMove+=20;
                                                        System.out.println("Score: "+ oneMove);
                                                    } else {
                                                        /*wtt*/
                                                        if (x < column.size() - 2 && column.get(x + 1).get(y).equals("T") && column.get(x + 2).get(y).equals("T")) {
                                                            pop1x = x + 2;
                                                            pop1y = y;
                                                            points+=40;
                                                            oneMove+=40;
                                                            popperThree(column, pop1x, pop1y);
                                                            mapControl(column);
                                                            System.out.println("Score: "+ oneMove);
                                                        } else {
                                                            /*w  |  math*/
                                                            if (x < column.size() - 2 && (column.get(x + 1).get(y).equals("|")) && ((column.get(x + 2).get(y).equals("+")) || (column.get(x + 2).get(y).equals("-"))
                                                                    || (column.get(x + 2).get(y).equals("|")) || (column.get(x + 2).get(y).equals("/")) || (column.get(x + 2).get(y).equals("\\")))) {   /*W with math symbol | */
                                                                pop1x = x + 2;
                                                                pop1y = y;
                                                                points+=50;
                                                                popperThree(column, pop1x, pop1y);
                                                                mapControl(column);
                                                                oneMove+=50;
                                                                System.out.println("Score: "+ oneMove);
                                                            } else {
                                                                if (x < column.size() - 2 && (column.get(x + 1).get(y).equals("+")) && ((column.get(x + 2).get(y).equals("+")) || (column.get(x + 2).get(y).equals("-"))
                                                                        || (column.get(x + 2).get(y).equals("|")) || (column.get(x + 2).get(y).equals("/")) || (column.get(x + 2).get(y).equals("\\")))) {   /*w with + sign*/
                                                                    pop1x = x + 2;
                                                                    pop1y = y;
                                                                    points+=50;
                                                                    oneMove+=50;
                                                                    popperThree(column, pop1x, pop1y);
                                                                    mapControl(column);
                                                                    System.out.println("Score: "+ oneMove);
                                                                } else {
                                                                    if (x < column.size() - 2 && (column.get(x + 1).get(y).equals("+")) && ((column.get(x + 2).get(y).equals("+")) || (column.get(x + 2).get(y).equals("-"))
                                                                            || (column.get(x + 2).get(y).equals("|")) || (column.get(x + 2).get(y).equals("/")) || (column.get(x + 2).get(y).equals("\\")))) {   /*w with + sign*/
                                                                        pop1x = x + 2;
                                                                        pop1y = y;
                                                                        points+=50;

                            /*pop them*
                            last one could be anything/
                            dont forget
                             */
                                                                        popperThree(column, pop1x, pop1y);
                                                                        oneMove+=50;
                                                                        mapControl(column);
                                                                        System.out.println("Score: "+ oneMove);
                                                                    } else {
                                                                        /*----------------------------------------wild to left----------------------------------------*/
                                                                        if (y >= 2 && column.get(x).get(y - 2).equals("S")) {
                                                                            if (column.get(x).get(y - 1).equals("S")) {
                                                                                System.out.print("");
                                                                                pop1x = x;
                                                                                pop1y = y;
                                                                                pop2x = x;
                                                                                pop2y = y - 1;
                                                                                pop3x = x;
                                                                                pop3y = y - 2;
                                                                                points += 40;
                                                                                popper(column, pop1x, pop1y);
                                                                                popper(column, pop2x, pop2y);
                                                                                popper(column, pop3x, pop3y);
                                                                                mapControl(column);
                                                                                oneMove+=40;
                                                                                System.out.println("Score: "+ oneMove);
                                                                            }
                                                                        } else {
                                                                            if (y >= 2 && column.get(x).get(y - 1).equals("+") || column.get(x).get(y - 1).equals("-")) {
                                                                                if (column.get(x).get(y - 2).equals("+") || column.get(x).get(y - 2).equals("-") || column.get(x).get(y - 2).equals("/")
                                                                                        || column.get(x).get(y - 2).equals("\\") || column.get(x).get(y - 2).equals("|")) {
                                                                                    pop1x = x;
                                                                                    pop1y = y;
                                                                                    pop2x = x;
                                                                                    pop2y = y - 1;
                                                                                    pop3x = x;
                                                                                    pop3y = y - 2;
                                                                                    points += 50;
                                                                                    popper(column, pop1x, pop1y);
                                                                                    popper(column, pop2x, pop2y);
                                                                                    popper(column, pop3x, pop3y);
                                                                                    mapControl(column);
                                                                                    oneMove+=50;
                                                                                    System.out.println("Score: "+ oneMove);
                                                                                }
                                                                            } else {
                                                                                if (y >= 2 && column.get(x).get(y - 1).equals("W")) {
                                                                                    pop1x = x;
                                                                                    pop1y = y;
                                                                                    pop2x = x;
                                                                                    pop2y = y - 1;
                                                                                    pop3x = x;
                                                                                    pop3y = y - 2;
                                                                                    points+=20;
                                                                                    if (column.get(x - 2).get(y).equals("D")){
                                                                                        points += 30;
                                                                                        oneMove+=30;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("S")){
                                                                                        points += 15;
                                                                                        oneMove+=15;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("T")){
                                                                                        points += 15;
                                                                                        oneMove+=15;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("W")){
                                                                                        points += 10;
                                                                                        oneMove+=10;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("+")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("-")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("|")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("\\")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    if (column.get(x - 2).get(y).equals("/")){
                                                                                        points += 20;
                                                                                        oneMove+=20;
                                                                                    }
                                                                                    oneMove+=50;
                                                                                    popper(column, pop1x, pop1y);
                                                                                    popper(column, pop2x, pop2y);
                                                                                    popper(column, pop3x, pop3y);
                                                                                    mapControl(column);
                                                                                    System.out.println("Score: "+ oneMove);
                                                                                } else {
                                                                                    /*----------------------------------------wild to right----------------------------------------*/
                                                                                    if (y < column.get(x).size() - 2 && column.get(x).get(y + 2).equals("S")) {
                                                                                        if (column.get(x).get(y + 1).equals("S")) {
                                                                                            pop1x = x;
                                                                                            pop1y = y;
                                                                                            pop2x = x;
                                                                                            pop2y = y + 1;
                                                                                            pop3x = x;
                                                                                            pop3y = y + 2;
                                                                                            points+=40;
                                                                                            popper(column, pop1x, pop1y);
                                                                                            popper(column, pop2x, pop2y);
                                                                                            popper(column, pop3x, pop3y);
                                                                                            mapControl(column);
                                                                                            oneMove+=40;
                                                                                            System.out.println("Score: "+ oneMove);
                                                                                        }
                                                                                    } else {
                                                                                        if (y < column.get(x).size() - 2 && column.get(x).get(y + 1).equals("+") || column.get(x).get(y + 1).equals("-")) {
                                                                                            if (column.get(x).get(y + 1).equals("+") || column.get(x).get(y + 1).equals("-") || column.get(x).get(y + 1).equals("/")
                                                                                                    || column.get(x).get(y + 1).equals("\\") || column.get(x).get(y + 1).equals("|")) {
                                                                                                pop1x = x;
                                                                                                pop1y = y;
                                                                                                pop2x = x;
                                                                                                pop2y = y + 1;
                                                                                                pop3x = x;
                                                                                                pop3y = y + 2;
                                                                                                points+=50;
                                                                                                popper(column, pop1x, pop1y);
                                                                                                popper(column, pop2x, pop2y);
                                                                                                popper(column, pop3x, pop3y);
                                                                                                mapControl(column);
                                                                                                oneMove+=50;
                                                                                                System.out.println("Score: "+ oneMove);
                                                                                            }
                                                                                        } else {
                                                                                            if (y < column.get(x).size() - 2 && column.get(x).get(y + 1).equals("W")) {
                                                                                                pop1x = x;
                                                                                                pop1y = y;
                                                                                                pop2x = x;
                                                                                                pop2y = y + 1;
                                                                                                pop3x = x;
                                                                                                pop3y = y + 2;
                                                                                                points+=20;
                                                                                                if (column.get(x - 2).get(y).equals("D")){
                                                                                                    points += 30;
                                                                                                    oneMove+=30;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("S")){
                                                                                                    points += 15;
                                                                                                    oneMove+=15;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("T")){
                                                                                                    points += 15;
                                                                                                    oneMove+=15;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("W")){
                                                                                                    points += 10;
                                                                                                    oneMove+=10;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("+")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("-")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("|")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("\\")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                if (column.get(x - 2).get(y).equals("/")){
                                                                                                    points += 20;
                                                                                                    oneMove+=20;
                                                                                                }
                                                                                                popper(column, pop1x, pop1y);
                                                                                                popper(column, pop2x, pop2y);
                                                                                                popper(column, pop3x, pop3y);
                                                                                                mapControl(column);
                                                                                                oneMove+=20;
                                                                                                System.out.println("Score: "+ oneMove);
                                                                                            } else {
                                                                                                /*--------------------wild to left up--------------------*/
                                                                                                if (y > 1 && x > 1 && (column.get(x).get(y).equals(column.get(x - 1).get(y - 1)))) {
                                                                                                    pop1x = x;
                                                                                                    pop1y = y;
                                                                                                    pop2x = x - 1;
                                                                                                    pop2y = y - 1;
                                                                                                    pop3x = x - 2;
                                                                                                    pop3y = y - 2;
                                                                                                    points+=20;
                                                                                                    if (column.get(x - 2).get(y).equals("D")){
                                                                                                        points += 30;
                                                                                                        oneMove+=30;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("S")){
                                                                                                        points += 15;
                                                                                                        oneMove+=15;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("T")){
                                                                                                        points += 15;
                                                                                                        oneMove+=15;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("W")){
                                                                                                        points += 10;
                                                                                                        oneMove+=10;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("+")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("-")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("|")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("\\")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    if (column.get(x - 2).get(y).equals("/")){
                                                                                                        points += 20;
                                                                                                        oneMove+=20;
                                                                                                    }
                                                                                                    popper(column, pop1x, pop1y);
                                                                                                    popper(column, pop2x, pop2y);
                                                                                                    popper(column, pop3x, pop3y);
                                                                                                    mapControl(column);
                                                                                                    oneMove+=20;
                                                                                                    System.out.println("Score: "+ oneMove);
                                                                                                } else {
                                                                                                    if (y > 1 && x > 1 && (column.get(x - 1).get(y - 1).equals("D") && column.get(x - 2).get(y - 2).equals("D"))) {
                                                                                                        pop1x = x;
                                                                                                        pop1y = y;
                                                                                                        pop2x = x - 1;
                                                                                                        pop2y = y - 1;
                                                                                                        pop3x = x - 2;
                                                                                                        pop3y = y - 2;
                                                                                                        points+=70;
                                                                                                        popper(column, pop1x, pop1y);
                                                                                                        popper(column, pop2x, pop2y);
                                                                                                        popper(column, pop3x, pop3y);
                                                                                                        mapControl(column);
                                                                                                        oneMove+=70;
                                                                                                        System.out.println("Score: "+ oneMove);
                                                                                                    } else {
                                                                                                        if (y > 1 && x > 1 && column.get(x - 1).get(y - 1).equals("\\")) {
                                                                                                            if (column.get(x - 2).get(y - 2).equals("+") || column.get(x - 2).get(y - 2).equals("-") || column.get(x - 2).get(y - 2).equals("/")
                                                                                                                    || column.get(x - 2).get(y - 2).equals("\\") || column.get(x - 2).get(y - 2).equals("|")) {
                                                                                                                pop1x = x;
                                                                                                                pop1y = y;
                                                                                                                pop2x = x - 1;
                                                                                                                pop2y = y - 1;
                                                                                                                pop3x = x - 2;
                                                                                                                pop3y = y - 2;
                                                                                                                points+=50;
                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                mapControl(column);
                                                                                                                oneMove+=50;
                                                                                                                System.out.println("Score: "+ oneMove);
                                                                                                            }
                                                                                                        } else {
                                                                                                            /*--------------------wild to right down--------------------*/
                                                                                                            if (x < column.size() - 2 && y < column.get(x).size() && column.get(x + 1).get(y + 1).equals("\\")) {
                                                                                                                if (column.get(x + 2).get(y + 2).equals("+") || column.get(x + 2).get(y + 2).equals("-") || column.get(x + 2).get(y + 2).equals("/")
                                                                                                                        || column.get(x + 2).get(y + 2).equals("\\") || column.get(x + 2).get(y + 2).equals("|")) {
                                                                                                                    pop1x = x;
                                                                                                                    pop1y = y;
                                                                                                                    pop2x = x + 1;
                                                                                                                    pop2y = y + 1;
                                                                                                                    pop3x = x + 2;
                                                                                                                    pop3y = y + 2;
                                                                                                                    points+=50;
                                                                                                                    popper(column, pop1x, pop1y);
                                                                                                                    popper(column, pop2x, pop2y);
                                                                                                                    popper(column, pop3x, pop3y);
                                                                                                                    mapControl(column);
                                                                                                                    oneMove+=50;
                                                                                                                    System.out.println("Score: "+ oneMove);
                                                                                                                }
                                                                                                            } else {
                                                                                                                if (x < column.size() - 2 && y < column.get(x).size() && column.get(x + 1).get(y + 1).equals("D")) {
                                                                                                                    if (column.get(x + 2).get(y + 2).equals("D")/**/) {
                                                                                                                        pop1x = x;
                                                                                                                        pop1y = y;
                                                                                                                        pop2x = x + 1;
                                                                                                                        pop2y = y + 1;
                                                                                                                        pop3x = x + 2;
                                                                                                                        pop3y = y + 2;
                                                                                                                        points += 70;
                                                                                                                        popper(column, pop1x, pop1y);
                                                                                                                        popper(column, pop2x, pop2y);
                                                                                                                        popper(column, pop3x, pop3y);
                                                                                                                        mapControl(column);
                                                                                                                        oneMove += 70;
                                                                                                                        System.out.println("Score: " + oneMove);
                                                                                                                    }
                                                                                                                } else {
                                                                                                                    if (x < column.size() - 2 && y < column.get(x).size() && column.get(x + 1).get(y + 1).equals("W")) {
                                                                                                                        pop1x = x;
                                                                                                                        pop1y = y;
                                                                                                                        pop2x = x + 1;
                                                                                                                        pop2y = y + 1;
                                                                                                                        pop3x = x + 2;
                                                                                                                        pop3y = y + 2;
                                                                                                                        points+=20;
                                                                                                                        if (column.get(x - 2).get(y).equals("D")){
                                                                                                                            points += 30;
                                                                                                                            oneMove+=30;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("S")){
                                                                                                                            points += 15;
                                                                                                                            oneMove+=15;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("T")){
                                                                                                                            points += 15;
                                                                                                                            oneMove+=15;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("W")){
                                                                                                                            points += 10;
                                                                                                                            oneMove+=10;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("+")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("-")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("|")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("\\")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        if (column.get(x - 2).get(y).equals("/")){
                                                                                                                            points += 20;
                                                                                                                            oneMove+=20;
                                                                                                                        }
                                                                                                                        popper(column, pop1x, pop1y);
                                                                                                                        popper(column, pop2x, pop2y);
                                                                                                                        popper(column, pop3x, pop3y);
                                                                                                                        mapControl(column);
                                                                                                                        oneMove+=20;
                                                                                                                        System.out.println("Score: "+ oneMove);
                                                                                                                    } else {
                                                                                                                        /*--------------------wild to right up--------------------*/
                                                                                                                        if (x >= 2 && y < column.get(x).size() - 2 && column.get(x - 1).get(y + 1).equals("/")) {
                                                                                                                            if (column.get(x - 2).get(y + 2).equals("+") || column.get(x - 2).get(y + 2).equals("-") || column.get(x - 2).get(y + 2).equals("/")
                                                                                                                                    || column.get(x - 2).get(y + 2).equals("\\") || column.get(x - 2).get(y + 2).equals("|")) {
                                                                                                                                pop1x = x;
                                                                                                                                pop1y = y;
                                                                                                                                pop2x = x - 1;
                                                                                                                                pop2y = y + 1;
                                                                                                                                pop3x = x - 2;
                                                                                                                                pop3y = y + 2;
                                                                                                                                points+=50;
                                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                                mapControl(column);
                                                                                                                                oneMove+=50;
                                                                                                                                System.out.println("Score: "+ oneMove);
                                                                                                                            }
                                                                                                                        } else {
                                                                                                                            if (x >= 2 && y < column.get(x).size() - 2 && column.get(x - 1).get(y + 1).equals("D") && column.get(x - 2).get(y + 2).equals("D")) {
                                                                                                                                pop1x = x;
                                                                                                                                pop1y = y;
                                                                                                                                pop2x = x - 1;
                                                                                                                                pop2y = y + 1;
                                                                                                                                pop3x = x - 2;
                                                                                                                                pop3y = y + 2;
                                                                                                                                points+=70;
                                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                                mapControl(column);
                                                                                                                                oneMove+=70;
                                                                                                                                System.out.println("Score: "+ oneMove);
                                                                                                                            } else {
                                                                                                                                if (x >= 2 && y < column.get(x).size() - 2 && column.get(x - 1).get(y + 1).equals("w")) {
                                                                                                                                    pop1x = x;
                                                                                                                                    pop1y = y;
                                                                                                                                    pop2x = x - 1;
                                                                                                                                    pop2y = y + 1;
                                                                                                                                    pop3x = x - 2;
                                                                                                                                    pop3y = y + 2;
                                                                                                                                    points+=20;
                                                                                                                                    if (column.get(x - 2).get(y).equals("D")){
                                                                                                                                        points += 30;
                                                                                                                                        oneMove+=30;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("S")){
                                                                                                                                        points += 15;
                                                                                                                                        oneMove+=15;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("T")){
                                                                                                                                        points += 15;
                                                                                                                                        oneMove+=15;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("W")){
                                                                                                                                        points += 10;
                                                                                                                                        oneMove+=10;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("+")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("-")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("|")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("\\")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    if (column.get(x - 2).get(y).equals("/")){
                                                                                                                                        points += 20;
                                                                                                                                        oneMove+=20;
                                                                                                                                    }
                                                                                                                                    popper(column, pop1x, pop1y);
                                                                                                                                    popper(column, pop2x, pop2y);
                                                                                                                                    popper(column, pop3x, pop3y);
                                                                                                                                    mapControl(column);
                                                                                                                                    oneMove+=20;
                                                                                                                                    System.out.println("Score: "+ oneMove);
                                                                                                                                } else {
                                                                                                                                    /*--------------------wild to left down--------------------*/
                                                                                                                                    if (x >= 2 && y < column.get(x).size() - 2 && column.get(x + 1).get(y - 1).equals("/")) {
                                                                                                                                        if (column.get(x + 2).get(y - 2).equals("+") || column.get(x + 2).get(y - 2).equals("-") || column.get(x + 2).get(y - 2).equals("/")
                                                                                                                                                || column.get(x + 2).get(y - 2).equals("\\") || column.get(x + 2).get(y - 2).equals("|")) {
                                                                                                                                            pop1x = x;
                                                                                                                                            pop1y = y;
                                                                                                                                            pop2x = x + 1;
                                                                                                                                            pop2y = y - 1;
                                                                                                                                            pop3x = x + 2;
                                                                                                                                            pop3y = y - 2;
                                                                                                                                            points+=50;
                                                                                                                                            popper(column, pop1x, pop1y);
                                                                                                                                            popper(column, pop2x, pop2y);
                                                                                                                                            popper(column, pop3x, pop3y);
                                                                                                                                            mapControl(column);
                                                                                                                                            oneMove+=50;
                                                                                                                                            System.out.println("Score: "+ oneMove);
                                                                                                                                        }
                                                                                                                                    } else {
                                                                                                                                        if (x >= 2 && y < column.get(x).size() - 2 && column.get(x + 1).get(y - 1).equals("D")) {
                                                                                                                                            if (column.get(x + 2).get(y - 2).equals("D")) {
                                                                                                                                                pop1x = x;
                                                                                                                                                pop1y = y;
                                                                                                                                                pop2x = x + 1;
                                                                                                                                                pop2y = y - 1;
                                                                                                                                                pop3x = x + 2;
                                                                                                                                                pop3y = y - 2;
                                                                                                                                                points+=70;
                                                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                                                mapControl(column);
                                                                                                                                                oneMove+=70;
                                                                                                                                                System.out.println("Score: "+ oneMove);

                                                                                                                                            }

                                                                                                                                        } else {
                                                                                                                                            if (x >= 2 && y < column.get(x).size() - 2 && column.get(x + 1).get(y - 1).equals("W")) {
                                                                                                                                                pop1x = x;
                                                                                                                                                pop1y = y;
                                                                                                                                                pop2x = x + 1;
                                                                                                                                                pop2y = y - 1;
                                                                                                                                                pop3x = x + 2;
                                                                                                                                                pop3y = y - 2;
                                                                                                                                                points+=20;
                                                                                                                                                if (column.get(x - 2).get(y).equals("D")){
                                                                                                                                                    points += 30;
                                                                                                                                                    oneMove+=30;
                                                                                                                                                }

                                                                                                                                                if (column.get(x - 2).get(y).equals("W")){
                                                                                                                                                    points += 10;
                                                                                                                                                    oneMove+=10;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("S")){
                                                                                                                                                    points += 15;
                                                                                                                                                    oneMove+=15;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("T")){
                                                                                                                                                    points += 15;
                                                                                                                                                    oneMove+=15;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("+")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("-")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("|")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("\\")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                if (column.get(x - 2).get(y).equals("/")){
                                                                                                                                                    points += 20;
                                                                                                                                                    oneMove+=20;
                                                                                                                                                }
                                                                                                                                                popper(column, pop1x, pop1y);
                                                                                                                                                popper(column, pop2x, pop2y);
                                                                                                                                                popper(column, pop3x, pop3y);
                                                                                                                                                mapControl(column);
                                                                                                                                                oneMove+=20;
                                                                                                                                                System.out.println("Score: "+ oneMove);
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }else{
                                    /*+++++++ ++ + + + + + + ++ +++++++++*/
                                    if (column.get(x).get(y).equals("+")){
                                        /* +  looking left*/
                                        if ( y>1
                                                && (column.get(x).get(y-1).equals("+") || column.get(x).get(y-1).equals("-"))  && (column.get(x).get(y-2).equals("+")
                                                || column.get(x).get(y-2).equals("-") || column.get(x).get(y-2).equals("/") ||column.get(x).get(y-2).equals("|")
                                                ||column.get(x).get(y-2).equals("\\"))){
                                            points+=60;
                                            pop1x = x;
                                            pop1y = y;
                                            pop2x = x;
                                            pop2y = y - 1;
                                            pop3x = x;
                                            pop3y = y - 2;
                                            popper(column, pop1x, pop1y);
                                            popper(column, pop2x, pop2y);
                                            popper(column, pop3x, pop3y);
                                            mapControl(column);
                                            oneMove+=60;
                                            System.out.println("Score: "+ oneMove);
                                        }else {
                                            /*  + looking right  */
                                            if ( y<column.get(x).size()-2
                                                    && (column.get(x).get(y+1).equals("+") || column.get(x).get(y+1).equals("-"))  && (column.get(x).get(y+2).equals("+")
                                                    || column.get(x).get(y+2).equals("-") || column.get(x).get(y+2).equals("/") ||column.get(x).get(y+2).equals("|")
                                                    ||column.get(x).get(y+2).equals("\\"))){
                                                points+=60;
                                                pop1x = x;
                                                pop1y = y;
                                                pop2x = x;
                                                pop2y = y + 1;
                                                pop3x = x;
                                                pop3y = y + 2;
                                                popper(column, pop1x, pop1y);
                                                popper(column, pop2x, pop2y);
                                                popper(column, pop3x, pop3y);
                                                mapControl(column);
                                                oneMove+=60;
                                                System.out.println("Score: "+ oneMove);
                                            }else {
                                                /* +  looking up*/
                                                if ( x<column.size()-2
                                                        && (column.get(x-1).get(y).equals("+") || column.get(x-1).get(y).equals("|"))  && column.get(x-2).get(y).equals("+")
                                                        || column.get(x-2).get(y).equals("-") || column.get(x-2).get(y).equals("/") ||column.get(x-2).get(y).equals("|")
                                                        ||column.get(x-2).get(y).equals("\\")){

                                                    points+=60;
                                                    popperThree(column,x,y);
                                                    mapControl(column);
                                                    oneMove+=60;
                                                    System.out.println("Score: "+ oneMove);
                                                }else {
                                                    /* +  looking down*/
                                                    if ( x<column.size()-2
                                                            && (column.get(x+1).get(y).equals("+") || column.get(x+1).get(y).equals("|"))  && column.get(x+2).get(y).equals("+")
                                                            || column.get(x+2).get(y).equals("-") || column.get(x+2).get(y).equals("/") ||column.get(x+2).get(y).equals("|")
                                                            ||column.get(x+2).get(y).equals("\\")){
                                                        points+=60;
                                                        popperThree(column,x+2,y);
                                                        mapControl(column);
                                                        oneMove+=60;
                                                        System.out.println("Score: "+ oneMove);
                                                    }
                                                }
                                            }
                                        }
                                    }else {
                                        /*  -   - - - - - - - - */
                                        if (column.get(x).get(y).equals("-")){
                                            /* -  looking left*/
                                            if (y>1
                                                    && (column.get(x).get(y-1).equals("+") || column.get(x).get(y-1).equals("-"))  && column.get(x).get(y-2).equals("+")
                                                    || column.get(x).get(y-2).equals("-") || column.get(x).get(y-2).equals("/") ||column.get(x).get(y-2).equals("|")
                                                    ||column.get(x).get(y-2).equals("\\"))
                                            {
                                                points+=60;
                                                pop1x = x;
                                                pop1y = y;
                                                pop2x = x;
                                                oneMove+=60;
                                                pop2y = y - 1;
                                                pop3x = x;
                                                pop3y = y - 2;
                                                popper(column, pop1x, pop1y);
                                                popper(column, pop2x, pop2y);
                                                popper(column, pop3x, pop3y);
                                                mapControl(column);
                                                System.out.println("Score: "+ oneMove);
                                            }else {
                                                /*  - looking right  */
                                                if ( y<column.get(x).size()-2
                                                        && (column.get(x).get(y+1).equals("+") || column.get(x).get(y+1).equals("-"))  && column.get(x).get(y+2).equals("+")
                                                        || column.get(x).get(y+2).equals("-") || column.get(x).get(y+2).equals("/") ||column.get(x).get(y+2).equals("|")
                                                        ||column.get(x).get(y+2).equals("\\")){
                                                    pop3x = x;
                                                    pop3y = y + 2;
                                                    points+=60;
                                                    pop1x = x;
                                                    pop1y = y;
                                                    pop2x = x;
                                                    pop2y = y + 1;
                                                    popper(column, pop1x, pop1y);
                                                    popper(column, pop2x, pop2y);
                                                    popper(column, pop3x, pop3y);
                                                    mapControl(column);
                                                    oneMove+=60;
                                                    System.out.println("Score: "+ oneMove);
                                                }
                                                }
                                            }else {
                                            if (column.get(x).get(y).equals("|")){
                                                /* | looking up */
                                                if ( x>=2
                                                        && (column.get(x-1).get(y).equals("+") || column.get(x-1).get(y).equals("|"))  && (column.get(x-2).get(y).equals("+")
                                                        || column.get(x-2).get(y).equals("-") || column.get(x-2).get(y).equals("/") ||column.get(x-2).get(y).equals("|")
                                                        ||column.get(x-2).get(y).equals("\\"))){
                                                    popperThree(column,x,y);
                                                    points+=60;
                                                    mapControl(column);
                                                    oneMove+=60;
                                                    System.out.println("Score: "+ oneMove);
                                                }else {
                                                    /* |  looking down*/
                                                    if ( x<column.size()-2
                                                            && (column.get(x+1).get(y).equals("+") || column.get(x+1).get(y).equals("|"))  && (column.get(x+2).get(y).equals("+")
                                                            || column.get(x+2).get(y).equals("-") || column.get(x+2).get(y).equals("/") ||column.get(x+2).get(y).equals("|")
                                                            ||column.get(x+2).get(y).equals("\\"))){
                                                        points+=60;
                                                        popperThree(column,x+2,y);
                                                        oneMove+=60;
                                                        mapControl(column);
                                                        System.out.println("Score: "+ oneMove);
                                                    }
                                                }
                                            }
                                            else {
                                                /*////// / / / / / / */
                                                if (column.get(x).get(y).equals("/")){
                                                    /*right up*/
                                                    if (y<column.get(x).size()-2  && x>=2
                                                            && ((column.get(x).get(y).equals(column.get(x - 1).get(y + 1))) && (column.get(x-2).get(y+2).equals("+")
                                                            || column.get(x-2).get(y+2).equals("-") || column.get(x-2).get(y+2).equals("/") ||column.get(x-2).get(y+2).equals("|")
                                                            ||column.get(x-2).get(y+2).equals("\\"))))
                                                    {
                                                        pop1x=x;
                                                        pop1y=y;
                                                        pop3y=y+2;
                                                        pop2x=x-1;
                                                        pop2y=y+1;
                                                        pop3x=x-2;
                                                        popper(column,pop1x,pop1y);
                                                        popper(column,pop2x,pop2y);
                                                        popper(column,pop3x,pop3y);
                                                        mapControl(column);
                                                        oneMove+=90;
                                                        points+=90;
                                                        System.out.println("Score: "+ oneMove);
                                                    }
                                                    else
                                                    {
                                                        /*left down*/
                                                        if (x<column.get(x).size()-2 && y>=2 &&(column.get(x).get(y).equals(column.get(x + 1).get(y - 1)) && ((column.get(x+2).get(y-2).equals("+")
                                                                || column.get(x+2).get(y-2).equals("-") || column.get(x+2).get(y-2).equals("/") ||column.get(x+2).get(y-2).equals("|")
                                                                ||column.get(x+2).get(y-2).equals("\\")))))
                                                        {
                                                            pop2x=x+1;
                                                            pop2y=y-1;
                                                            pop3x=x+2;
                                                            pop3y=y-2;
                                                            pop1x=x;
                                                            pop1y=y;
                                                            popper(column,pop1x,pop1y);
                                                            popper(column,pop2x,pop2y);
                                                            popper(column,pop3x,pop3y);
                                                            mapControl(column);
                                                            points+=90;
                                                            oneMove+=90;
                                                            System.out.println("Score: "+ oneMove);
                                                        }
                                                    }
                                                }
                                                else {
                                                    if (column.get(x).get(y).equals("\\")){
                                                        /*left up*/
                                                        if (y>=2 && x>=2 &&column.get(x).get(y).equals(column.get(x - 1).get(y - 1)) && ((column.get(x+2).get(y-2).equals("+")
                                                                || column.get(x+2).get(y-2).equals("-") || column.get(x+2).get(y-2).equals("/") ||column.get(x+2).get(y-2).equals("|")
                                                                ||column.get(x+2).get(y-2).equals("\\"))) )
                                                        {
                                                            pop3x=x-2;
                                                            pop3y=y-2;
                                                            pop1x=x;
                                                            pop1y=y;
                                                            pop2x=x-1;
                                                            pop2y=y-1;
                                                            popper(column,pop2x,pop2y);
                                                            popper(column,pop1x,pop1y);
                                                            popper(column,pop3x,pop3y);
                                                            mapControl(column);
                                                            points+=90;
                                                            oneMove+=90;
                                                            System.out.println("Score: "+ oneMove);
                                                        }
                                                        else
                                                        {
                                                            /*right down*/
                                                            if (y<column.get(x).size()-2 &&  x<column.size()-2
                                                                    &&(column.get(x).get(y).equals(column.get(x + 1).get(y + 1)) && (column.get(x+2).get(y+2).equals("+")
                                                                    || column.get(x+2).get(y+2).equals("-") || column.get(x+2).get(y+2).equals("/") ||column.get(x+2).get(y+2).equals("|")
                                                                    ||column.get(x+2).get(y+2).equals("\\"))))
                                                            {
                                                                pop1x=x;
                                                                pop1y=y;
                                                                pop3y=y+2;
                                                                pop3x=x+2;
                                                                pop2x=x+1;
                                                                pop2y=y+1;
                                                                popper(column,pop3x,pop3y);
                                                                popper(column,pop1x,pop1y);
                                                                popper(column,pop2x,pop2y);
                                                                mapControl(column);
                                                                points+=90;
                                                                oneMove+=90;
                                                                System.out.println("Score: "+ oneMove);
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    }
                } else{
                    if (orders.get(gameCount).equals("E")) {
                        System.out.println();
                        System.out.println("Your total points: " + points);
                        System.out.println();
                        System.out.print("Enter Name: ");
                        System.out.println(orders.get(gameCount+1));
                        Leaders lea = new Leaders();
                        lea.takeLeaders(orders.get(gameCount+1),points);
                        System.out.println("Good bye!");

                        break;
                    }
                }
            }
            catch (RuntimeException aiob){
                System.out.println("Run time exception occured");
                throw  (aiob);
            }
            gameCount++;
            oneMove=0;
        }
    }
}
