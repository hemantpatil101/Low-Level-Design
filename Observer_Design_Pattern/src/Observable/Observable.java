package Observable;

import java.util.ArrayList;
import java.util.List;

import Observer.Observer;

public class Observable {
    protected List<Observer> observers = new ArrayList<>();
    
    public void addObserver(Observer observer)
    {
        observers.add(observer);
    }

    public void removeObserver(Observer observer)
    {
        observers.remove(observer);
    }

    public void notifyObservers(String news)
    {
        for(Observer observer:observers)
        {
            observer.Update(news);
        }
    }
}
