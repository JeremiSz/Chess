import backEnd.BackendService;
import dataAccess.DataAccessService;
import frontEnd.FrontEndService;

public class Main {
    final static int DATA_ACCESS_PORT = 65533;
    final static int BACK_END_PORT = 65534;
    final static int FRONT_END_PORT = 65535;
    public static void main(String[] args) {
        new DataAccessService(DATA_ACCESS_PORT);
        new BackendService(BACK_END_PORT,DATA_ACCESS_PORT,FRONT_END_PORT);
        new FrontEndService(FRONT_END_PORT, BACK_END_PORT);
    }
}
