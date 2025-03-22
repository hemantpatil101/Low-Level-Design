import Observable.NewsAgency;
import Observer.AdminObserver;
import Observer.UserObservser;

public class ObserverPatternDemo {
    public static void main(String[] args) throws Exception {
        NewsAgency TimesOfindia = new NewsAgency();

        UserObservser user1 = new UserObservser("User 01");
        UserObservser user2 = new UserObservser("User 02");

        TimesOfindia.addObserver(user1);
        TimesOfindia.addObserver(user2);

        TimesOfindia.setLatestNews("This is latest news 01");

        AdminObserver admin1 = new AdminObserver("Admin 01");

        TimesOfindia.addObserver(admin1);
        
        TimesOfindia.setLatestNews("This is latest news 02");
    }
}
