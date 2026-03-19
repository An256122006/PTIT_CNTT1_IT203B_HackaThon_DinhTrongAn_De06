package entity;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Movie {
    private String movieId;
    private String movieName;
    private int duration;
    private int views;
    public Movie() {

    }

    public Movie(String movieId, String movieName, int duration, int views) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.duration = duration;
        this.views = views;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getViews() {
        return views;
    }


    public void setViews(int views) {
        this.views = views;
    }

    public void inputData(Scanner scanner , List<Movie> movie) {
        System.out.print("Nhap ma phim: ");

        while (true) {
            String movieIdtemp=scanner.nextLine();
            if(movieIdtemp.isEmpty()){
                System.out.println("khong dc de trong");
                System.out.println("nhap lai!!");
                continue;
            }
            boolean isExit=movie.stream().anyMatch(movie1 -> movie1.getMovieId().equalsIgnoreCase(movieIdtemp));
            if(!isExit){
                movieId=movieIdtemp;
                break;
            }
            System.out.println("Ma phim da ton tai");
            System.out.print("Nhap lai ma phim:");
        }
        System.out.print("Nhap ten phim:");
        while (true){
           String name=scanner.nextLine();
           if(name.isEmpty()){
               System.out.println("ten ko dc de trong");
               System.out.println("nhap lai");
               continue;
           }
           movieName=name;
           break;
        }
        System.out.print("Nhap do dai phim:");
        int durationtemp;
        while (true) {
            try {
                durationtemp = Integer.parseInt(scanner.nextLine());
                if(durationtemp<0 || durationtemp==0){
                    System.out.println("do dai phai lon hon 0");
                    System.out.println("nhap lai");
                    continue;
                }
                duration=durationtemp;
                break;
            } catch (NumberFormatException e) {
                System.out.println("Do dai phim phai la so");
                continue;
            }

        }
        System.out.print("nhap luot xem:");
        int viewstemp;
        while (true) {
            try {
                viewstemp=Integer.parseInt(scanner.nextLine());
                if (viewstemp < 0) {
                    System.out.println("luot xem phai lon hon hoac bang 0");
                    System.out.println("nhap lai");
                    continue;
                }
                views =viewstemp;
                break;

            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                continue;
            }
        }

    }

    public void displayAll() {
        System.out.printf("%s | %s | %d | %d\n", movieId, movieName, duration, views);
    }
}
