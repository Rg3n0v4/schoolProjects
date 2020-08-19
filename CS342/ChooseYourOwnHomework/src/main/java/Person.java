public abstract class Person
{
    public String identity; //secret identity
    public String codename; //codename they are called by in the field
    public String quirk; //their super power
    public String headquarters;

    public abstract void createPerson(); //every Person is unique
    public abstract String ultimateMove(); //what's a hero/villain without a boss move
    public abstract void sidekick();
    public abstract void headquarters();

    //every hero or villain needs an intro
    public final void intro()
    {
        try
        {
            System.out.println("INTRODUCING: Codename: " + codename + " | Real Name: " + identity + " | Quirk: " + quirk);
        }
        catch(Exception e)
        {
            System.out.println("EXPOSED: Identity: " + identity);
        }
    }

    //showcase hero or villain
    public final void showcase(boolean showOff)
    {
        createPerson();
        intro();
        if(showOff)
        {
            System.out.println(codename + " used their ultimate move!! " + ultimateMove() + "!!!!!!!!");
            sidekick();
            System.out.println("");
            headquarters();
            System.out.println("Their headquarters are: " + headquarters);
            System.out.println("");
        }
    }

}
