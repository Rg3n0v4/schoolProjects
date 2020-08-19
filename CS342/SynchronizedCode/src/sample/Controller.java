package sample;


public class ThreadSync {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        SimpleCounter s = new SimpleCounter();

        for(int i = 0; i<100; i++) {
            CountThread t = new CountThread(s,"t"+i);
            t.start();
        }
/*
		Data d = new Data();

        //each thread has access to the same object
		for(int i = 0; i<100; i++) {
			MyThread t = new MyThread(d,i,"t"+i);
			t.start();
		}

 */

    }

}

class Data{

    public void printData(int val, String name) { //synchronized keyword puts lock

        //synchronized (this) {
            for (int i = 1; i < 100; i++) {
                System.out.println("From thread: " + name + " " + val * i);

                try {
                    Thread.sleep(400);
                } catch (Exception e) {

                }
            }
        //}//sync

    }
}

class MyThread extends Thread{

    Data d;
    int val;
    String name;

    MyThread(Data data, int x, String n){
        this.d = data;
        this.val = x;
        this.name = n;
    }

    public void run() {


        d.printData(val, name);

    }
}

class CountThread extends Thread{

    SimpleCounter counter;
    String name;

    CountThread(SimpleCounter c, String myName)
    {
        this.counter = c;
        this.name = myName;
    }
/*
    //adding in synchronized (does allow it to finish, but it doesn't
    // protect the data member theCount)
    //uses one instance of CountThread
	public void run()
    {
        //adding in a synchronized(this) for both for loops
        //still doesn't work because it's not using the lock inside simple counter
        //only uses lock for count thread

        //changing synchronized(counter) doesn't work either

        //putting both for loops into synchronized(counter)
        //both for loops will use counter and after the second for loop
        //it finishes each lock allowing each thread to start and finish
		for(int i = 0; i<20; i++)
		{
			counter.add();
            System.out.println("Add From " + name + " " +counter.getVal());
		}

		for(int i = 0; i<20; i++)
		{
            counter.subtract();
            System.out.println("Subtract From " + name + " " + counter.getVal());
        }
    }
    */


    public void run() {

        //synchronized (counter) { one way to fix it
            counter.combo(name);
            //combo() does the same thing as above (the two for loops)
            //makes it as 1 method call (which is in SimpleCounter)
        //}

    }
}

class SimpleCounter{

    int theCount;
    Object lock = new Object();


    SimpleCounter(){
        theCount = 0;
    }

    public void add() {
        theCount++;
    }
    //adding in synchronized (does allow it to finish, but it doesn't
    // protect the data member theCount)

    public void subtract() {
        theCount--;
    }
    //adding in synchronized (does allow it to finish, but it doesn't
    // protect the data member theCount)

    public int getVal() {
        return theCount;
    }

    public void combo(String name) {
        //sync combo method works as well

        for(int i = 0; i<1000; i++) {
            this.add();
        }
        System.out.println(name + " Adding inside method: "+ this.theCount);

        for(int i = 0; i<1000; i++) {
            this.subtract();
        }
        System.out.println(name + " subtracting inside method: "+ this.theCount);

    }

}
