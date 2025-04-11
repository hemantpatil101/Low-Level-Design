package Models;
import java.util.List;
import java.util.ArrayList;

public class Show {
    String showID;
    Movie movie;
    String startTime;
    String endTime;
    int totalSeats;
    int ticketPrice;
    List<Integer> seats = new ArrayList<>();  // 1 shows seat is available and 0 shows its occupied
    
    public Show(String showID,Movie movie,String startTime,String endTime,int totalSeats){
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalSeats = totalSeats;
        this.ticketPrice = 250;
        for(int i=0;i<=totalSeats;i++)
        {
           seats.add(1);
        }
    }

    public List<Integer> getAvailableSeats(){
        List<Integer> availableSeats = new ArrayList<>();

        for(int i=1;i<=totalSeats;i++){
            if(seats.get(i) == 1){
                availableSeats.add(i);
            }
        }

        return availableSeats;
    }

    public Boolean bookSeats(List<Integer> seatsToBook){
        for(int seat : seatsToBook){
            if(seat <= 0  || seat > totalSeats || seats.get(seat) == 0){
                return false;
            }
        }

        for(int seat : seatsToBook){
            seats.set(seat,1);
        }

        return true;
    }

    public int getPrice(){
        return this.ticketPrice;
    }

    public String getMovieName(){
        return this.movie.getMovieName();
    }

    public void cancelSeats(List<Integer> cancelSeatList){
        for(Integer seat : cancelSeatList){
            seats.set(seat,1);
        }
    }

}
