package game;

public class Wallet {
    private static int balance;

    public static void addBalance(int value){
        balance += value;
    }

    public static void subtractBalance(int value){
        balance -= value;
    }

    public static int getBalance(){
        return balance;
    }

}
