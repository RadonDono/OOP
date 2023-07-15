package enums;

public enum Message {
    INVALID_CHOICE("Invalid command!"),
    MISMATCH_PASSWORD("wrong password!"), SHORT_PASSWORD("password is too short!"), LONG_PASSWORD("password is too long"), NON_ALPHA_NUMERIC_PASSWORD("password should hava a character"), SUCCESS("correct"), WRONG_CREDENTIALS("password or username is incorrect"), USER_EXIST("this username has already taken"), wrongpass("password is not correct"), NoRestaurant("there is no restaurant with thos name or id"), Nofood("there is no food with this name"), commentadded("comment added succesfully"), incorrectid("incorrectid"), progressfood("this food is in progress"), discounteror("percent is higher than 50 or there is discount before");
    private String message;

    Message(String message) {
        this.message = message;
    }

    

    @Override
    public String toString() {
        return this.message;
    }
}
