import java.io.IOException;

public interface OrderDAO {
    void creator();
    void reader()throws Exception;
    void parser() throws Exception;
    void checkIt(int orderid);
    void getbyID(int customerId) throws Exception;


}
