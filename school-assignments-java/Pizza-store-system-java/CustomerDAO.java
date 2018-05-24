import java.io.*;
import java.util.*;


public interface CustomerDAO {
     void creator();
     void reader()throws IOException;
     void removeCustomer (int ıd);
     void objectCheck(int id);
     void setPayTime(int ıd, int  cost);

}
