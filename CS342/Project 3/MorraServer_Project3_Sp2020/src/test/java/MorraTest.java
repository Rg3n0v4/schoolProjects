import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MorraTest {
	MorraInfo morraInfo;

	@BeforeEach
	void init()
	{
		morraInfo = new MorraInfo();
	}

	@Test
	void testInit()
	{
		assertEquals("MorraInfo", morraInfo.getClass().getName(), "initialization of MorraInfo is wrong");
	}

	@Test
	void testScoreString()
	{
		morraInfo.setPlayer1Score(0);
		morraInfo.setPlayer2Score(0);
		assertEquals("Client#0: 0 points, Client#1: 0 points", morraInfo.scoreString(), "scoreString() return is wrong");
	}

	@Test
	void testEvaluatePlayersBoth()
	{
		morraInfo.setPlayer1ActualGuess(0);
		morraInfo.setPlayer2ActualGuess(0);
		morraInfo.setPlayer1ActualTotal(0);
		morraInfo.setPlayer2ActualTotal(0);
		assertEquals(3, morraInfo.evaluatePlayers(), "morraInfo evaluate players is wrong");
	}

	@Test
	void testEvaluatePlayersPlayer1Win()
	{
		morraInfo.setPlayer1ActualGuess(1);
		morraInfo.setPlayer2ActualGuess(0);
		morraInfo.setPlayer1ActualTotal(1);
		morraInfo.setPlayer2ActualTotal(0);
		assertEquals(1, morraInfo.evaluatePlayers(), "morraInfo evaluate players is wrong");
	}

	@Test
	void testEvaluatePlayersPlayer2Win()
	{
		morraInfo.setPlayer1ActualGuess(0);
		morraInfo.setPlayer2ActualGuess(1);
		morraInfo.setPlayer1ActualTotal(0);
		morraInfo.setPlayer2ActualTotal(1);
		assertEquals(2, morraInfo.evaluatePlayers(), "morraInfo evaluate players is wrong");
	}

	@Test
	void testEvaluatePlayersNoOneWins()
	{
		morraInfo.setPlayer1ActualGuess(5);
		morraInfo.setPlayer2ActualGuess(0);
		morraInfo.setPlayer1ActualTotal(1);
		morraInfo.setPlayer2ActualTotal(3);
		assertEquals(0, morraInfo.evaluatePlayers(), "morraInfo evaluate players is wrong");
	}

	@Test
	void testPrintInfo()
	{
		assertEquals("Server: No one won\n", morraInfo.printInfo(), "morraInfo printInfo function is wrong");
	}
}
