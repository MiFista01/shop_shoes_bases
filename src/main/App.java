package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Classes_shop.Person;
import Classes_shop.Purchase;
import Classes_shop.Shoes;
import tools.Saver_to_base;
import tools.Saver_to_file;
import ui.Keeping;

public class App {
    private Scanner scanner =  new Scanner(System.in);
    private Keeping keeping = new Saver_to_base();

    private List<Person> persons = new ArrayList<>();
    private List<Shoes> shoeses = new ArrayList<>();

    public App() {
        persons = keeping.load_persons();
        shoeses = keeping.load_shoes();
         
    }

    private Person add_person(){
        Person person = new Person();
        System.out.print("Your name - ");
        String name = scanner.next();
        boolean run = true;
        while(run){
            int chek = 0;
            for (int i = 0; i<persons.size();i++){
                if (persons.get(i) != null){
                    if (persons.get(i).getName().equals(name)){
                        chek ++;
                        System.out.println("This name is in the system");
                        System.out.print("Your name - ");
                        name = scanner.next();
                        break;
                    }
                }
            }
            if (chek == 0){
                run = false;
            }
        }
        person.setName(name);
        System.out.print("Password - ");
        person.setPassword(scanner.next());
        System.out.print("Phone - ");
        person.setPhone(scanner.next());
        person.setPurse(0);
        return person;
    }

    private Shoes add_shoes(int check){
        Shoes shoes = new Shoes();
        shoes.setPerson(persons.get(check));
        System.out.print("Name of shoes(firm) - ");
        shoes.setName(scanner.next());
        System.out.print("Size of shoes - ");
        shoes.setSize(scanner.nextInt());
        System.out.print("Color of shoes - ");
        shoes.setColor(scanner.next());
        System.out.print("Price of shoes - ");
        shoes.setPrice(scanner.nextDouble());
        System.out.print("Piece of shoes - ");
        shoes.setPiece(scanner.nextInt());
        return shoes;
    }
    public void run(){
        boolean run = true;
        do {
            System.out.println("1: Registration");
            System.out.println("2: Enter the program");
            System.out.print("Your choice - ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    persons.add(add_person());
                    keeping.save_persons(persons);
                    break;
                case 2:
                    int check  = -1;
                    while(check == -1){
                        System.out.print("Your name - ");
                        String input = scanner.next();
                        for (int i = 0; i<persons.size();i++){
                            if (persons.get(i) != null){
                                if (persons.get(i).getName().equals(input)){
                                    check = i;
                                    break;
                                }
                            }
                        }
                    }
                    int check2 = 0;
                    while(check2 == 0){
                        System.out.print("Password - ");
                        String input = scanner.next();
                        if (persons.get(check).getPassword().equals(input)){
                            check2++;
                        }
                    }
                    boolean run2 = true;
                    do {
                        System.out.println("1: My profile");
                        System.out.println("2: My purchases");
                        System.out.println("3: Buy shoes");
                        System.out.println("4: Add money");
                        System.out.println("5: Add sales");
                        System.out.println("0: log out");
                        int choice2 = scanner.nextInt();
                        switch (choice2) {
                            case 0:
                            run2 = false;
                                break;
                            case 1:
                                System.out.println("================States================");
                                System.out.println("Name - "+persons.get(check).getName());
                                System.out.println("Phone - "+persons.get(check).getPhone());
                                System.out.println("purse - "+persons.get(check).getPurse());
                                System.out.println("income - "+persons.get(check).getIncom());
                                System.out.println("================States================");
                                break;
                            case 2:
                                for (int i = 0; i< persons.get(check).getPurchase().size();i++){
                                        System.out.println("States of shoes:");
                                        System.out.println("    Name(firm) - "+persons.get(check).getPurchase().get(i).getName());
                                        System.out.println("    Price - "+persons.get(check).getPurchase().get(i).getPrice());
                                        System.out.println("    Size - "+persons.get(check).getPurchase().get(i).getSize());
                                        System.out.println("    Color - "+persons.get(check).getPurchase().get(i).getColor());
                                        System.out.println("===========================================================");
                                }
                                break;
                            case 3:
                                System.out.print("If you want log out from shop, then press 0");
                                for (int i = 0;i< shoeses.size();i++){
                                        System.out.println("==============================================");
                                        System.out.println((i+1)+")");
                                        System.out.println("Autor of sale: ");
                                        System.out.println(shoeses.get(i).getPerson().getName()+" - "+shoeses.get(i).getPerson().getPhone());
                                        System.out.println("    Shoes:");
                                        System.out.println("    Name(firm) - "+shoeses.get(i).getName());
                                        System.out.println("    Size - "+shoeses.get(i).getSize());
                                        System.out.println("    Color - "+shoeses.get(i).getColor());
                                        System.out.println("    Piece - "+shoeses.get(i).getPiece());
                                        System.out.println("    Price - "+shoeses.get(i).getPrice());
                                        System.out.println("==============================================");
                                        System.out.println("");
                                }
                                System.out.print("Your choice - ");
                                int input = scanner.nextInt();
                                boolean run3 = true;
                                while (run3){
                                    if(input <0 || input >shoeses.size()){
                                        System.out.println("You choice not in list");
                                    }else if(input != 0 && persons.get(check).getPurse()<shoeses.get(input-1).getPrice()){
                                        System.out.println("You dont't have necessary money");
                                    }else{
                                        break;
                                    }
                                    System.out.print("Your choice - ");
                                    input = scanner.nextInt();
                                }

                                if (input != 0){
                                    persons.get(check).setPurse(persons.get(check).getPurse()-shoeses.get(input-1).getPrice()); // изменение кошелька, точнее вычет
                                    Purchase purchase = new Purchase();
                                    if (persons.get(check).getPurchase().size() != 0){
                                        for (int i = 0; i < persons.get(check).getPurchase().size();i++){
                                            if(persons.get(check).getPurchase().get(i) != null){
                                                if (persons.get(check).getPurchase().get(i).getId()!= shoeses.get(input-1).getId()){
                                                    purchase.setPerson(shoeses.get(input-1).getPerson());
                                                    purchase.setColor(shoeses.get(input-1).getColor());
                                                    purchase.setName(shoeses.get(input-1).getName());
                                                    purchase.setPiece(0);
                                                    purchase.setPrice(shoeses.get(input-1).getPrice());
                                                    purchase.setSize(shoeses.get(input-1).getSize());
                                                    List<Purchase> purchases = persons.get(check).getPurchase();
                                                    purchases.add(purchase);
                                                    persons.get(check).setPurchase(purchases);
                                                }
                                                if (persons.get(check).getPurchase().get(i).getId()== shoeses.get(input-1).getId()){
                                                persons.get(check).getPurchase().get(i).setPiece(persons.get(check).getPurchase().get(i).getPiece()+1);
                                                }
                                        }
                                    }
                                }else{
                                        purchase.setPerson(shoeses.get(input-1).getPerson());
                                        purchase.setColor(shoeses.get(input-1).getColor());
                                        purchase.setName(shoeses.get(input-1).getName());
                                        purchase.setPiece(0);
                                        purchase.setPrice(shoeses.get(input-1).getPrice());
                                        purchase.setSize(shoeses.get(input-1).getSize());
                                        List<Purchase> purchases = persons.get(check).getPurchase();
                                        purchases.add(purchase);
                                        persons.get(check).setPurchase(purchases);   
                                }
                                    
                                    shoeses.get(input-1).setPiece(shoeses.get(input-1).getPiece()-1);

                                    for(int i = 0; i<persons.size();i++){//цикл для добавления денег другому пользователю так же и доход
                                        if (persons.get(i) != null){
                                            if (shoeses.get(input-1).getPerson().getName().equals(persons.get(i).getName())){
                                                persons.get(i).setIncom(persons.get(i).getIncom()+shoeses.get(input-1).getPrice());
                                                persons.get(i).setPurse(persons.get(i).getPurse()+shoeses.get(input-1).getPrice());
                                            } 
                                        }                                       
                                    }

                                    if (shoeses.get(input-1).getPiece() == 0){//штука которая делает так что если нету больше штук обуви то ячейка становится пустой
                                        shoeses.remove(input-1);
                                    }
                                    keeping.save_shoeses(shoeses);
                                    keeping.save_persons(persons);
                                }
                                break;
                            case 4:
                                System.out.println("How much money do you want to put?");
                                double money = scanner.nextDouble();
                                money += persons.get(check).getPurse();
                                persons.get(check).setPurse(money);
                                System.out.println(persons.get(check).getPurse());
                                keeping.save_persons(persons);
                                break;
                            case 5:
                                shoeses.add(add_shoes(check));
                                keeping.save_shoeses(shoeses);
                                break;
                            default:
                                break;
                        }
                    } while (run2);
                    break;
                case 0:
                    System.out.println("Good bye");
                    run = false;
                    break;
                default:
                    break;
            }
        } while (run);
    }
}