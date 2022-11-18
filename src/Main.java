import backEnd.BackendService;
import dataAccess.DataAccessService;

public class Main {
    final static int DATA_ACCESS_PORT = 665533;
    final static int BACK_END_PORT = 65534;
    final static int FRONT_END_PORT = 65535;
    public static void main(String[] args) {
        DataAccessService dataAccessService = new DataAccessService(DATA_ACCESS_PORT);
        BackendService backendService = new BackendService(BACK_END_PORT,DATA_ACCESS_PORT,FRONT_END_PORT);
    }
}
