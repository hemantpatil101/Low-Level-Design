package Observer;

public class UserObservser implements Observer{
    String userName;

    public UserObservser(String username)
    {
        this.userName = username;
    }
    
    @Override
    public void Update(String news)
    {
        System.out.println("News : " + news +  ", Received by User :" + userName);
    }
    
}
