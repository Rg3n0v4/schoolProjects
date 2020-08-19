import java.sql.Array;
import java.util.ArrayList;

public class Dealer
{
    Deck theDeck;
    ArrayList<Card> dealersHand;
    // Making this addition for evalPPWinnings
    private int evalPPWinnings;

    // Is this what they mean by no arg constructor? - FIX ME,  also initialize the deck means which deck?
    Dealer()
    {
        theDeck = new Deck();
        this.evalPPWinnings = -1;
        dealersHand = new ArrayList<>();
    }
    // Setter and getter for evalPPWinnings
    public int getEvalPPWinnings (){return evalPPWinnings;}
    public void setEvalPPWinnings (int winning){this.evalPPWinnings = winning;}

    //Dealer's hand
    public ArrayList<Card> dealHand()
    {

        // if cards greater than 34 cards, then just remove the cards and continue
        if (theDeck.size() <= 34)
        {
           theDeck = new Deck();
        }

        // takes cards from deck and distributes them to the dealer
        dealersHand = new ArrayList<Card> (theDeck.subList(theDeck.size()-4, theDeck.size()-1));

        //removes the dealer's cards from theDeck
        for (int i = theDeck.size()-4; i < theDeck.size()-1; i++)
        {
            theDeck.remove(i);
        }

        return dealersHand;
    }

    // Dealer draws cards and distributes to the players
    public ArrayList<Card> DrawCards(Player p1)
    {
        // Distributing to player
        p1.hand = new ArrayList<Card> (theDeck.subList(theDeck.size()-4, theDeck.size()-1));

        //removes the dealer's cards from theDeck
        for (int i = theDeck.size()-4; i < theDeck.size()-1; i++)
        {
            theDeck.remove(i);
        }
        return p1.hand;
    }

}
