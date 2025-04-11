package Models;

public class Movie {
    String movieName;
    String duration;

    public Movie(String movieName,String duration){
        this.movieName = movieName;
        this.duration = duration;
    }

    public String getMovieName(){
        return movieName;
    }
}
