package com.example;
import java.util.List;
import java.util.Scanner;

public class Quotes {
    Scanner sc=new Scanner(System.in);
    public void menu() {
        while (true){
            showMenu();

            try {
                int n=Integer.parseInt(sc.nextLine());
                switch (n){
                    case 1:{
                        this.addQuote();
                        break;
                    }
                    case 2:{
                        this.showQuote();
                        break;
                    }
                    case 3:{
                        this.deleteQuote();
                        break;
                    }
                    case 4:{
                        System.out.println("Exited");
                        return;

                    }
                    default:{
                        System.out.println("Invalid option");
                      break;
                    }
                }
            }catch ( Exception E ){
                System.out.println(E.getMessage());
            }

        }
    }




    private void showMenu(){
        System.out.println("1. ADD QUOTE ");
        System.out.println("2. SHOW QUOTE");
        System.out.println("3. DELETE QUOTE");
        System.out.println("4. TO EXIT");
    }

    private void addQuote(){
        System.out.println("Adding a new QUOTE :");
        System.out.println("*****************************");
        System.out.println("Enter Quote  : ");
//        sc.nextLine();
        String q=sc.nextLine();
        System.out.println("Enter Author :");
        String a=sc.nextLine();
        QuotesConn.addQuote(new QuotesData(q,a));
    }
    private void showQuote() {
        List<QuotesData> d=QuotesConn.showQuote();
        d.stream().forEach(System.out::println);
    }
    private void deleteQuote() {
        System.out.println("Enter the id to delete");
        int i=Integer.parseInt(sc.next());
        QuotesConn.deleteQuote(i);

    }
}
