package view;
import Persons.RestaurantOwner;
import Persons.User;
import controller.WelcomeController;
import enums.Message;

public class Welcome extends Menu {
    // Singleton Pattern
    private static Welcome instance = null;

    private WelcomeController controller;

    // Singleton Pattern
    private Welcome() {
        this.controller = WelcomeController.getInstance();
    }

    // Singleton Pattern
    private static void setInstance(Welcome instance) {
        Welcome.instance = instance;
    }

    // Singleton Pattern
    public static Welcome getInstance() {
        if (Welcome.instance == null) {
            Welcome.setInstance(new Welcome());
        }
        return Welcome.instance;
    }

    @Override
    public void run() {
        this.showOptions();

        String choice = this.getChoice();

        switch (choice) {
            case "1":
            case "register":
                this.register();
                break;

            case "2":
            case "login":
                this.login();
                break;

            case "3":
            case "exit":
                this.exit();
                break;
            default:
                System.out.println(Message.INVALID_CHOICE);

        }

    }

    private void exit() {
        Menu.getScanner().close();
    }

    private void login() {
        String username = this.getInput("enter username");
        String password = this.getInput("enter password");

        String message = this.controller.handleLogin(username, password);
        if (message.equals("RestaurantOwner")) {
            RestaurantOwnerMenu restaurantOwnerMenu=new RestaurantOwnerMenu(RestaurantOwner.getRestaurantOwner(username));
            restaurantOwnerMenu.run();

        } else if (message.equals("User")) {
            UserMenu userMenu=new UserMenu(User.getUser(username));
            userMenu.run();
        }else if (message.equals("Deliver")){

        }else if (message.equals("wp")) {
            System.out.println("your password was not correct!\n");
            String ID = this.getInput("enter your ID");
            Message messag = this.controller.handlerestore(username,ID);
        } else {
            System.out.println(message);
            this.login();
        }
        this.run();
    }

    private void register() {
        System.out.println("enter register as restaurantowner or customer or deliver");
        String choice = this.getChoice();


        if (choice.equals("restaurantowner")) {
            this.registerAdmin();
        } else if (choice.equals("customer")) {
            this.registerCustomer();
        }else if (choice.equals("deliver")){
            this.registerDelivery();
        }
        else {
            System.out.println(Message.INVALID_CHOICE);
            this.register();
        }
        this.run();
    }

    private void registerCustomer() {
        String username = this.getInput("enter username");
        String password = this.getInput("enter password");
        String loc = this.getInput("enter location");

        Message message = this.controller.handleCreateUser(username, password,loc );
        System.out.println(message == Message.SUCCESS ? "customer registered successfully" : message);
    }

    private void registerAdmin() {
        String username = this.getInput("enter username");
        String password = this.getInput("enter password");
        String number=this.getInput("how much restaurant do you have?");
        Message message = this.controller.handleCreateAdmin(username, password);
        for (int i = 0; i <Integer.parseInt(number) ; i++) {
            String name = this.getInput("enter name");
            String type = this.getInput("enter type");
            String loc = this.getInput("enter loc");
            Message messag = this.controller.handleCreateRestaurant(username, this.controller.restaurantOwner.getID(),type,loc);
        }
        System.out.println(message == Message.SUCCESS ? "admin registered successfully" : message);
    }
    private void registerDelivery() {
        String username = this.getInput("enter username");
        String password = this.getInput("enter password");

        Message message = this.controller.handleCreateAdmin(username, password);
        System.out.println(message == Message.SUCCESS ? "admin registered successfully" : message);
    }

    @Override
    protected void showOptions() {
        System.out.println("enter one of the options");
        System.out.println("1. register");
        System.out.println("2. login");
        System.out.println("3. exit");

    }

}
