package Observable;

public class NewsAgency extends Observable{
    String latestNews;

    public void setLatestNews(String news)
    {
        System.out.println("Latest news is : " + news);
        latestNews = news;
        notifyObservers(news);
    }
}
