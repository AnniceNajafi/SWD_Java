package RequestInheritance;

public class Payment {
    String sender;
    String receiver;
    int amount;
    Payment(String sender, int amount, String receiver){
        this.sender=sender;
        this.amount=amount;
        this.receiver=receiver;
    }
}
