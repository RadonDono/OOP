package view;

import LocationAndMap.ras;
import Order.Comment;
import Order.Food;
import controller.UserController;
import Persons.User;
import enums.Message;
import Order.Restaurant;
import LocationAndMap.map;

import java.util.ArrayList;

public class UserMenu extends Menu{
    static User user=null;

    UserController controller;

    // Singleton Pattern
    public UserMenu(User us) {
        user = us;
        this.controller = new UserController(us);
    }

    @Override
    public void run() {
        showOptions();
        String choice = this.getChoice();

        switch (choice) {
        case "1":
        case "restaurants":
        this.showRestaurant();
        break;

        case "2":
        case "orders":
        this.Showorders();
        break;

        case "3":
        case "charge account":
        this.Charge_Account();
        break;
        case "4":
        case "Status":
                this.showstatus();
                break;
        case "5":
        case "exit":
            this.exit();
            break;
        default:
        System.out.println(Message.INVALID_CHOICE);
        this.run();
        }
    }
    void show_array(ArrayList<String> s){
        for (String x:s) {
            System.out.println(x);
        }
    }
    private void showstatus() {
        show_array(this.controller.showcart());
        System.out.println("what do you want to do?");
        System.out.println("1. confirm");
        System.out.println("2. exit");
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "Confirm":
                this.controller.confirmorder();
                this.time();
                break;
            case "2":
            case "exit":
                this.run();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
        this.showstatus();
    }

    private void time() {
        System.out.println("what do you want to do?");
        System.out.println("1. time");
        System.out.println("2. exit");
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "time":
                System.out.println();this.controller.showtime();
                break;
            case "2":
            case "exit":
                this.run();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
        this.time();
    }

    private void showRestaurant() {
        map m=new map();
        Restaurant restaurant=new Restaurant("ajdar",99,"hotdog",m.get_location(7));
        Restaurant restauran=new Restaurant("ajdar",99,"hotdog",m.get_location(7));
        Restaurant restaura=new Restaurant("ajdar",99,"hotdog",m.get_location(7));
        for (int i = 0; i < Restaurant.restaurants.size(); i++) {
            System.out.println(Restaurant.restaurants.get(i));
        }
        showRestaurantoption();
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "select":
                this.showSelectRestaurant();
                break;

            case "2":
            case "search":
                this.searchrestaurant();
                break;
            case "3":
            case "exit":
                this.user.cleanCart();
                this.run();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
        this.searchrestaurant();
    }

    private void showSelectRestaurant() {
        showselectOptions();
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "select":
                String username = this.getInput("enter ID");
                user.newOrder(Integer.parseInt(username));
                controller.orderidres=Integer.parseInt(username);
                Message message = this.controller.selectrestaurant(username);
                System.out.println(message == Message.SUCCESS ? "login in restaurant successfully" : message);
                if (message == Message.SUCCESS){
                    showMenu();
                }
                break;
            case "2":
            case "exit":
                this.exit();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
    }

    private void showMenu() {
        ArrayList<String> menu=controller.restaurant_now.getFood();
        for (String ar:menu) {
            System.out.println(ar);
        }
        showMenuOptions();
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "select":
                this.showSelectfood();
                break;

            case "2":
            case "search":
                this.searchmenu();
                break;
            case "3":
            case "comment":
                this.showRestaurantcomment();
                break;
            case "4":
            case "exit":
                this.showRestaurant();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
        this.showMenu();

    }

    private void showRestaurantcomment(){
        for (Comment com:this.controller.restaurant_now.receivedComments) {
            System.out.println(com);
        }
        System.out.println("what do you want to do?");
        System.out.println("1. addcomment");
        System.out.println("2. editcoment");
        System.out.println("3. rate");
        System.out.println("4. exit");
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "addcomment":
                this.addrestaurantcomment();
                break;

            case "2":
            case "editcomment":
                this.editrestaurantcomment();
                break;
            case "3":
            case "rate":
                this.raterest();
            case "4":
            case "exit":
                this.showMenu();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
        this.showRestaurantcomment();


    }

    private void raterest() {
        System.out.println("rate: "+this.controller.getrate());
        System.out.println("what do you want to do?");
        System.out.println("1. addrate");
        System.out.println("2. edit");
        System.out.println("3. exit");
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "addrate":
                String username = this.getInput("enter rate:");
                Message message = this.controller.addrate(Integer.parseInt(username));
                System.out.println(message == Message.SUCCESS ? "rated successfully" : message);
                this.addrestaurantcomment();
                break;
            case "2":
            case "editrate":
                String usernam = this.getInput("enter rate:");
                Message messag = this.controller.addrate(Integer.parseInt(usernam));
                System.out.println(messag == Message.SUCCESS ? "rated successfully" : messag);
                this.addrestaurantcomment();
                break;
            case "3":
            case "exit":
                this.showRestaurantcomment();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
    }

    private void addrestaurantcomment() {
        String username = this.getInput("enter Comment:");
        System.out.println(this.controller.addrestaurantcomment(username));
        showRestaurantcomment();
    }

    private void editrestaurantcomment() {
        String Id = this.getInput("enter CommentId:");
        String username = this.getInput("enter Comment:");
        System.out.println(this.controller.editrestaurantcomment(Integer.parseInt(Id),username));
        showRestaurantcomment();

    }

    private void searchmenu() {
        String username = this.getInput("enter Food name:");
        ArrayList<String> message = this.controller.searchfood(username);
        if (message.size()==0){
            System.out.println(Message.Nofood);
        }
        else {
            for (int i = 0; i < message.size(); i++) {
                System.out.println(message.get(i));
            }
        }
        showSelectfood();
    }

    private void showSelectfood() {
        System.out.println("what do you want to do?");
        System.out.println("1. select");
        System.out.println("2. comment");
        System.out.println("3. exit");
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "select":
                String username = this.getInput("enter ID");

                Message message = this.controller.selectfood(username);
                System.out.println(message == Message.SUCCESS ? "food selected successfully" : message);
                if (message == Message.SUCCESS){
                    this.run();
                }
                else{
                    this.showSelectfood();
                }
                break;
            case "2":
            case "comment":
                String usernam = this.getInput("enter ID");
                this.foodcomment(usernam);
                break;
            case "3":
            case "exit":
                this.showMenu();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
    }

    private void foodcomment(String x) {
        for (Comment com : this.controller.getfoodcomment(Integer.parseInt(x))) {
            System.out.println(com);
        }
        this.showSelectfood();
    }
    void ordercomment(){
        System.out.println("what do you want to do?");
        System.out.println("1. addcomment");
        System.out.println("2. editcoment");
        System.out.println("3. rate");
        System.out.println("4. exit");
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "addcomment":
                this.addordercomment();
                break;

            case "2":
            case "editcomment":
                this.editrordercomment();
                break;
            case "3":
            case "rate":
                this.rateorder();
            case "4":
            case "exit":
                this.run();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
        this.ordercomment();
    }

    private void rateorder() {
        String commentId = this.getInput("enter comment Id:");
        String username = this.getInput("enter rate:");
        Message message = this.controller.addrateorder(Integer.parseInt(username),commentId);
        System.out.println(message == Message.SUCCESS ? "rated successfully" : message);
        this.ordercomment();
    }

    private void editrordercomment() {
        String OrderId = this.getInput("enter OrderId:");
        String comment = this.getInput("enter comment:");
        System.out.println(this.controller.editoredercomment(OrderId,comment));
        ordercomment();
    }

    private void addordercomment() {
        String OrderId = this.getInput("enter OrderId:");
        String comment = this.getInput("enter comment:");
        System.out.println(this.controller.addoredercomment(OrderId,comment));
        showRestaurantcomment();
    }

    private void showMenuOptions() {
        System.out.println("what do you want to do?");
        System.out.println("1. select");
        System.out.println("1. search");
        System.out.println("3. exit");
    }

    private void showselectOptions() {
        System.out.println("what do you want to do?");
        System.out.println("1. select");
        System.out.println("2. exit");
    }

    private void showRestaurantoption() {
        System.out.println("what do you want to do?");
        System.out.println("1. select");
        System.out.println("2. search");
        System.out.println("2. comment");
        System.out.println("3. exit");
    }

    private void searchrestaurant() {
        String username = this.getInput("enter Restaurant name:");
        ArrayList<String> message = this.controller.searchrestaurant(username);
        if (message.size()==0){
            System.out.println(Message.NoRestaurant);
        }
        else {
            for (int i = 0; i < message.size(); i++) {
                System.out.println(message.get(i));
                
            }
        }
        showSelectRestaurant();
    }

    private void Showorders() {
        for (int orderid:user.finishedOrdersID) {
            System.out.println("Order :"+orderid);

        }
        System.out.println("what do you want to do?");
        System.out.println("1. information");
        System.out.println("2. comment");
        System.out.println("3. exit");
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "information":
                String Id = this.getInput("enter ID:");
                String message = this.controller.showorder(Integer.parseInt(Id));
                System.out.println(message);
                Showorders();
                break;
            case "2":
            case "comment":
                this.ordercomment();
            case "3":
            case "exit":
                this.run();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
        this.Showorders();
    }

    private void Charge_Account() {
        System.out.println(this.controller.usercon.getCash());
        System.out.println("what do you want to do?");
        System.out.println("1. charge");
        System.out.println("2. exit");
        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "charge":
                this.charge();
                this.Charge_Account();
                break;
            case "2":
            case "exit":
                this.run();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);
        }
        this.Charge_Account();
    }

    private void charge() {
        String money = this.getInput("enter money:");

        Message message = this.controller.charge(Integer.parseInt(money));
        System.out.println(message == Message.SUCCESS ? "account charged succesfully" : message);
    }

    private void exit() {
    }

    @Override
    protected void showOptions() {
            System.out.println("what do you want to do?");
            System.out.println("1. restaurants");
            System.out.println("2. orders");
            System.out.println("3. charge account");
        System.out.println("4. status");
        System.out.println("5. exit");
    }

}
