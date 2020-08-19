
public class Villain extends Person
{
    @Override
    public void createPerson()
    {
        this.codename = "Tomura Shigaraki";
        this.identity = "Tenko Shimura";
        this.quirk = "Decay";
    }

    @Override
    public String ultimateMove()
    {
        return "Instakill";
    }

    @Override
    public void sidekick()
    {
        System.out.print("Not really a sidekick per say, but a partner in crime: Kurogiri");
    }

    @Override
    public void headquarters()
    {
        this.headquarters = "Kamino, Yokohama";
    }
}
