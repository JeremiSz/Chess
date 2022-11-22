package frontEnd;

public abstract class Menu {
    protected FrontEndClient client;
    public Menu(FrontEndClient client){
        this.client = client;
    }
    public abstract void clean();
}
