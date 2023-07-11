package com.deloitte.ads.mariosy;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;

import java.util.*;

public class ConsoleApp {

    boolean running = true;
    Scanner scanner = new Scanner(System.in);

    App app;
    public ConsoleApp(App app) {
        this.app = app;
    }

    private void printMenu(){
        System.out.println("1. List Users");
        System.out.println("2. Create User");
        System.out.println("3. List Marioses");
        System.out.println("4. Create Marios");
        System.out.println("5. List Marioses created by user");
        System.out.println("6. List Marioses received by user");
        System.out.println("0. Exit");
    }

    private void listUsers(){
        List<User> users = new ArrayList<>(this.app.getUsers());
        Integer counter = 1;
        for(User u:users){
            System.out.println(counter+" "+u);
            counter+=1;
        }
    }

    private void addUser(){
       String firstName =  RandomStringUtils.randomAlphabetic(10);
       String lastName = RandomStringUtils.randomAlphabetic(10);
       app.createUser(firstName, lastName);
    }

    private void listMarioses(){
        List<Marios> marioses = new ArrayList<>(this.app.getMarioses());
        Integer counter = 1;
        for(Marios elem:marioses){
            System.out.println(counter+" "+elem);
            counter+=1;
        }
    }

    private void addMarios(){
        String comment =  RandomStringUtils.randomAlphabetic(50);
        List <User> users = new ArrayList<>(app.getUsers());

        System.out.println("Creator number: ");
        int number = scanner.nextInt();
        User creator = users.get(number);
        boolean moreReceivers = true;
        Set<User> receivers = new HashSet<User>();
        while (moreReceivers){
            System.out.println("Receiver number (0 if done): ");
            number = scanner.nextInt();
            if (number==0) {
                moreReceivers = false;
            }else {
                receivers.add(users.get(number));
            }
        }
        app.createMarios(creator,receivers,MariosType.MARIOS_T1, comment);
    }

    public void listMariosesCratedByUser(){
        List <User> users = new ArrayList<>(app.getUsers());
        System.out.println("Creator number: ");
        int number = scanner.nextInt();
        User creator = users.get(number);
        List<Marios> marioses = this.app.getSortedMariosesCreatedByUser(creator);
        Integer counter = 1;
        for(Marios elem:marioses){
            System.out.println(counter+" "+elem);
            counter+=1;
        }
    }

    public void listMariosesReceivedByUser(){
        List <User> users = new ArrayList<>(app.getUsers());
        System.out.println("Receiver number: ");
        int number = scanner.nextInt();
        User receiver = users.get(number);
        List<Marios> marioses = this.app.getSortedMariosesReceivedByUser(receiver);
        Integer counter = 1;
        for(Marios elem:marioses){
            System.out.println(counter+" "+elem);
            counter+=1;
        }
    }

    public void run(){
        while(this.running){

            printMenu();
            System.out.println("Please enter the number: ");
            int number   = scanner.nextInt();

            switch (number){
                case 1:
                    listUsers();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                    listMarioses();
                    break;
                case 4:
                    addMarios();
                    break;
                case 5:
                    listMariosesCratedByUser();
                    break;
                case 6:
                    listMariosesReceivedByUser();
                    break;
                case 0:
                    this.running = false;
                    break;
                default:
                    break;
            }
            System.out.println();
        }


    }




}
