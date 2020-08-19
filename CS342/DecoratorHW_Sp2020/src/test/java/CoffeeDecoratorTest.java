import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.DecimalFormat;

class CoffeeDecoratorTest {
	Coffee order;
	DecimalFormat df = new DecimalFormat("0.00");
	@BeforeEach
	void init()
	{
		order = new BasicCoffee();
	}

	@Test
	void testBasicCoffeeInit()
	{
		assertEquals("BasicCoffee", order.getClass().getName(), "Initialization of BasicCoffee is wrong");
	}
	@Test
	void testBasicCoffee()
	{
		assertEquals(3.99, order.makeCoffee(), "BasicCoffee makeCoffee doesn't work");
	}
	@Test
	void testShot() {
		order = new ExtraShot(order);
		assertEquals("5.19", df.format(order.makeCoffee()), "Adding in ExtraShot doesn't work");
	}

	@Test
	void testCream() {
		order = new Cream(order);
		assertEquals(4.49, order.makeCoffee(), "Adding in Cream doesn't work");
	}
	@Test
	void testSugar() {
		order = new Sugar(order);
		assertEquals(4.49, order.makeCoffee(), "Adding in Sugar doesn't work");
	}

	@Test
	void testCaramel() {
		order = new Caramel(order);
		assertEquals(4.74, order.makeCoffee(), "Adding in Caramel doesn't work");
	}

	@Test
	void testSoymilk() {
		order = new Soymilk(order);
		assertEquals(4.84, order.makeCoffee(), "Adding in Soymilk doesn't work");
	}

	@Test
	void testCalculation() {
		order = new Soymilk( new Caramel( new Sugar( new Cream( new ExtraShot (order)))));
		assertEquals("7.79", df.format(order.makeCoffee()), "Calculating one of every add-on is wrong");
	}

	@Test
	void testCalculation2() {
		order = new Soymilk ( new Soymilk ( new Caramel ( new Caramel ( new Sugar ( new Sugar ( new Cream ( new Cream ( new ExtraShot ( new ExtraShot (order))))))))));
		assertEquals("11.59", df.format(order.makeCoffee()), "Calculating two of every add-on is wrong");
	}

	@Test
	void testWrapperMultiple() {
		order = new Soymilk ( new Soymilk ( new Caramel ( new Caramel ( new Sugar ( new Sugar ( new Cream ( new Cream ( new ExtraShot ( new ExtraShot (order))))))))));
		assertEquals("Soymilk", order.getClass().getName(), "Not the correct wrapping");
	}

}
