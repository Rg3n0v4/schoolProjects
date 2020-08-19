import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyObserver {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Subject sub = new Subject();
        // Client configures the number and type of Observers
        CheapObserver cheap =  new CheapObserver();
        MiddleIncomeObserver mid = new MiddleIncomeObserver();
        WealthyObserver paid = new WealthyObserver();

        //they register themselves as the subject (their setSubject() functions)
        cheap.setSubject(sub);
        mid.setSubject(sub);
        paid.setSubject(sub);

        try( Scanner scan = new Scanner(System.in);){ //just takes input
            for (int i = 0; i < 5; i++) {
                System.out.print("\nEnter the new membership cost: ");
                sub.setState(scan.nextInt()); //setting the state in the subject
                //the observers are notified every time because in the setState() there's notifyObservers()

                if(cheap.getBool() == false) {

                    cheap.unRegister();
                }


                if(mid.getBool() == false) {

                    mid.unRegister();
                }

                if(paid.getBool() == false) {

                    paid.unRegister();
                }
            }

        }
        catch(Exception e) {
            e.printStackTrace();
        }


    }

}

interface Observer {

    public abstract void update();
}

class Subject {

    private List<Observer> observers = new ArrayList<>();//the type of the class is anything that is implementing it
    private int state; //the cost of subscribing to a certain thing

    public void register(Observer o) {
        observers.add(o);
    }

    public boolean remove(Observer o) {
        return observers.remove(o);
    }

    public int getState() {
        return state;
    }

    public void setState(int value) {
        this.state = value;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

class CheapObserver implements Observer {

    private Subject subject; //this is how they call the Subject to subscribe and unsubscribe
    private boolean subscribe;

    public CheapObserver() {

    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        this.subject.register(this);
        subscribe = true;
    }

    public void update() { //state and the subject is the cost of the subscription

        int cost = subject.getState();
        if(cost <= 500) {
            System.out.println("I'm cheap but will pay "+cost+" per year");
        }
        else {
            System.out.println("I'm way too cheap to pay "+cost+" per year");
            subscribe = false;

        }
    }

    public void unRegister() { //they remove themselves
        subject.remove(this);
    }

    public boolean getBool() {
        return this.subscribe;
    }


}

class MiddleIncomeObserver implements Observer {

    private Subject subject;
    private boolean subscribe;

    public MiddleIncomeObserver() {

    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        this.subject.register(this);
        subscribe = true;
    }

    public void update() {

        int cost = subject.getState();
        if(cost <= 1000) {
            System.out.println("I'm not rich but I can pay "+cost+" per year");
        }
        else {
            System.out.println("What do you think I am a pro athlete? I will not pay "+cost+" per year");
            subscribe = false;
        }
    }

    public void unRegister() {
        subject.remove(this);
    }

    public boolean getBool() {
        return this.subscribe;
    }
}

class WealthyObserver implements Observer {

    private Subject subject;
    private boolean subscribe;

    public WealthyObserver() {

    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        this.subject.register(this);
        subscribe = true;
    }

    public void update() {
        int cost = subject.getState();
        System.out.println("I don't care if it costs "+cost+"! My accountant handles that");
    }

    public void unRegister() {
        subject.remove(this);
    }

    public boolean getBool() {
        return this.subscribe;
    }
}

