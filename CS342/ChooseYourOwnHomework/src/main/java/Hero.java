
public class Hero extends Person
{
    @Override
    public void createPerson()
    {
        this.identity = "Toshinori";
        this.codename = "All Might";
        this.quirk = "One for All";
    }

    @Override
    public String ultimateMove()
    {
        return "UNITED STATES OF SMASH";
    }

    @Override
    public void sidekick()
    {
        System.out.print("Not really a sidekick per say, more of a predecessor: Deku");
    }

    @Override
    public void headquarters()
    {
        this.headquarters = "UA High";
    }

}
