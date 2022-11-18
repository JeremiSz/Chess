import dataAccess.DataAccessFile;
import dataAccess.DataAccessService;

public class Main {
    final static int DATA_ACCESS_PORT = 665533;
    public static void main(String[] args) {
        DataAccessService dataAccessService = new DataAccessService(DATA_ACCESS_PORT,new DataAccessFile());
    }
}
