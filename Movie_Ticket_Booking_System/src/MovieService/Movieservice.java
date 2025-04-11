package MovieService;

import java.util.ArrayList;
import java.util.List;

import Models.Theater;

public class Movieservice {
    List<Theater> theaters;

    public Movieservice(List<Theater> theaters){
        this.theaters = theaters;
    }

    public List<Theater> getTheatersByCity(String city){
        List<Theater> theatorList = new ArrayList<>();

        for(Theater theater : theaters){
            if(theater.getCity() == city){
                theatorList.add(theater);
            }
        }

        return theatorList;
    }
}
