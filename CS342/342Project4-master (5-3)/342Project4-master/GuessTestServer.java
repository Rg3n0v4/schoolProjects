import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class GuessTest {
	GameTracker server;

	@BeforeEach
	void init()
	{
		server = new GameTracker();
	}

	@Test
	void testInit()
	{
		assertEquals("GameTracker", server.getClass().getName(), "Init failed");
	}



}
