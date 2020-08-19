import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

class Homework4Test {
    Person person;

    public void heroInit()
    {
        person = new Hero();
    }
    public void villainInit()
    {
        person = new Villain();
    }

    @Test
    public void testHeroInit()
    {
        heroInit();
        assertEquals("Hero", person.getClass().getName(), "Not proper Hero init");
    }

    @Test
    public void testVillainInit()
    {
        villainInit();
        assertEquals("Villain", person.getClass().getName(), "Not proper Villain init");
    }

    @Test
    public void testCreateHero()
    {
        heroInit();
        person.createPerson();
        assertEquals("All Might", person.codename, "Not proper codename entry for Hero");
    }

    @Test
    public void testCreateVillain()
    {
        villainInit();
        person.createPerson();
        assertEquals("Tomura Shigaraki", person.codename, "Not proper codename entry for Villain");
    }

    @Test
    public void testUltimateHero()
    {
        heroInit();
        assertEquals("UNITED STATES OF SMASH", person.ultimateMove(), "ultimateMove() doesn't work");
    }

    @Test
    public void testUltimateVillain()
    {
        villainInit();
        assertEquals("Instakill", person.ultimateMove(), "ultimateMove() doesn't work");
    }

    @Test
    public void testHeroSidekick()
    {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        heroInit();
        person.sidekick();
        assertEquals("Not really a sidekick per say, more of a predecessor: Deku", outputContent.toString());
    }

    @Test
    public void testVillainSidekick()
    {
        ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputContent));
        villainInit();
        person.sidekick();
        assertEquals("Not really a sidekick per say, but a partner in crime: Kurogiri", outputContent.toString());
    }

    @Test
    public void testHeroHeadquarters()
    {
        heroInit();
        person.headquarters();
        assertEquals("UA High", person.headquarters, "Headquarters not established");
    }

    @Test
    public void testVillainHeadquarters()
    {
        villainInit();
        person.headquarters();
        assertEquals("Kamino, Yokohama", person.headquarters, "Headquarters not established");
    }

}
