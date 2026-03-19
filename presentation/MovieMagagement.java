package presentation;

import business.MovieBusiness;
import entity.Movie;

import java.util.List;
import java.util.Scanner;

public class MovieMagagement {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        MovieBusiness movieBusiness = MovieBusiness.getInstance();
        int choice;
        do{
            System.out.println("******************* Quan ly danh Muc Phim******************");
            System.out.println("1.hien thi danh sach toan bo phim ");
            System.out.println("2.them phim moi");
            System.out.println("3.Cap nhat thong tin phim theo ma phim");
            System.out.println("4.xoa phim theo ma phim");
            System.out.println("5.Tim kiem phim theo ten");
            System.out.println("6.Loc danh sach phim thinh hanh (Luot xem > 10000)");
            System.out.println("7.Sap xep danh sach phim giam dan theo luot xem");
            System.out.println("8.thoat");
            System.out.print("moi ban nhap lua chon:");
            try {
                choice=Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                choice=-1;
            }
            switch (choice){
                case 1:
                    movieBusiness.displayAll();
                    break;
                case 2:
                    while (true){
                        Movie movie=new Movie();
                        movie.inputData(scanner,MovieBusiness.getInstance().movie);
                        movieBusiness.addMovie(movie);
                        System.out.println("Nhap thanh cong");
                        System.out.print("ban co muon tiep tuc them ko (y/n):");
                        try {
                            String choice2=scanner.nextLine();
                            if (choice2.equalsIgnoreCase("y")) {
                                continue;
                            }else if (choice2.equalsIgnoreCase("n")) {
                                break;
                            }else{
                                System.out.println("Lua chon ko hop le");
                                break;
                            }
                        }catch (Exception e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }
                    break;
                case 3:
                    System.out.print("moi ban nhap ma phim:");
                    String id=scanner.nextLine();
                    movieBusiness.updateMovie(id);
                    break;
                case 4:
                    System.out.print("moi ban nhap ma phim:");
                    movieBusiness.DeleteMovie(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("moi ban nhap ten phim:");
                   List<Movie>movieList= movieBusiness.findByName(scanner.nextLine());
                   if(movieList.isEmpty()){
                    System.out.println("ko co phim nay");
                   }else{
                    for (Movie movie : movieList) {
                        movie.displayAll();
                    }
                   }
                    break;
                case 6:
                    List<Movie> trendingMovies = movieBusiness.filterTrendingMovie();
                    if(trendingMovies.isEmpty()){
                        System.out.println("ko co phim thinh hanh");
                        break;
                    }
                    for (Movie movie : trendingMovies) {
                        movie.displayAll();
                    }
                    break;
                case 7:
                    movieBusiness.sortByViews();
                    break;
                case 8:
                    break;
                default:
                    System.out.println("Vui long nhap lai");
                    break;
            }
        }while (choice!=8);
    }
}
