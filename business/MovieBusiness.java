package business;

import entity.Movie;

import java.util.*;
import java.util.stream.Collectors;

public class MovieBusiness {
    private static Scanner scanner=new Scanner(System.in);
    public List<Movie> movie=new ArrayList<>();
    private static MovieBusiness instance;
   private MovieBusiness() {}
    public static MovieBusiness getInstance(){
       if (instance == null) {
           instance = new MovieBusiness();
       }
       return instance;
    }
    public void displayAll() {
       if(movie.isEmpty()){
           System.out.println("Khoong co phim nao");
           return;
       }
        System.out.printf("%s | %s | %s| %s\n", "movieId", "movieName", "duration", "views");
        for (Movie movie1 : movie) {
            movie1.displayAll();
        }
    }
    public void addMovie(Movie movieadd) {
        movie.add(movieadd);
    }
    public Optional<Movie> findById(String movieId){
        return movie.stream()
                .filter(movie -> movie.getMovieId().equalsIgnoreCase(movieId))
                .findFirst();

    }
    public void updateMovie(String movieId) {
           Optional<Movie> movieOptional = findById(movieId);
           if(movieOptional.isEmpty()){
               System.out.println("Khong tim thay phim nay");
               return;
           }
           int choice;

        while (true){
               System.out.println("moi ban nhap lua chon muon sua:");
               System.out.println("1.Name");
               System.out.println("2.Duration");
               System.out.println("3.Views");
               System.out.println("4.Back");
            System.out.print("lua chon cua ban:");
            try {
                   choice = Integer.parseInt(scanner.nextLine());
               } catch (NumberFormatException e) {
                   System.out.println(e.getMessage());
                   choice=-1;
               }
               switch (choice){

                   case 1:
                       System.out.print("Nhap ten phim: ");
                       while (true){
                           String name=scanner.nextLine();
                           if(name.isEmpty()){
                               System.out.println("ten ko dc de trong");
                               System.out.println("nhap lai");
                               continue;
                           }
                           movieOptional.get().setMovieName(name);
                           break;
                       }
                       break;
                   case 2:
                       System.out.print("nhap do dai phim:");
                       int durationtemp;
                       while (true){
                           try{
                               durationtemp=Integer.parseInt(scanner.nextLine());
                               if (durationtemp<0 || durationtemp==0){
                                   System.out.println("Do dai phim phai lon hon 0");
                                   System.out.print("nhap lai");
                                   continue;
                               }
                               movieOptional.get().setDuration(durationtemp);
                               break;
                           } catch (NumberFormatException e) {
                               System.out.println(e.getMessage());
                               continue;
                           }
                       }
                       break;
                   case 3:
                       System.out.print("nhap so luot xem:");
                       int views;
                       while (true){
                           try{
                               views=Integer.parseInt(scanner.nextLine());
                               if (views<0){
                                   System.out.println("luot xem phai lon hon hoac = 0");
                                   System.out.print("nhap lai");
                                   continue;
                               }
                               movieOptional.get().setViews(views);
                               break;
                           } catch (NumberFormatException e) {
                               System.out.println(e.getMessage());
                               continue;
                           }
                       }
                       break;
                   case 4:
                       return;
                   default:
                       System.out.println("Vui long nhap lai");
                       break;
               }
           }
    }
    public List<Movie> findByName(String name){
        return movie.stream()
                .filter(movie -> movie.getMovieName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
    public void DeleteMovie(String id){
       Optional<Movie>Movie=findById(id);
       if(Movie.isEmpty()){
           System.out.println("Khong tim thay phim nay");
           return;
       }
       movie.remove(Movie.get());
       System.out.println("Xoa thanh cong");
    }
    public void sortByViews(){
       if(movie.isEmpty()){
           System.out.println("Khong co phim nao");
           return;
       }
        movie.sort(Comparator.comparingInt(Movie::getViews).reversed());
        displayAll();
    }
    public List<Movie> filterTrendingMovie(){
        if(movie.isEmpty()){
            System.out.println("Khong co phim nao");
            return null;
        }
        return movie.stream()
                .filter(movie -> movie.getViews() > 10000)
                .collect(Collectors.toList());
    }
}
