package Observer;

public class AdminObserver implements Observer{
    String adminName;

    public AdminObserver(String adminname)
    {
        this.adminName = adminname;
    }
    
    @Override
    public void Update(String news)
    {
        System.out.println("News : " + news +  ", Received by Admin :" + adminName);
    }
}
