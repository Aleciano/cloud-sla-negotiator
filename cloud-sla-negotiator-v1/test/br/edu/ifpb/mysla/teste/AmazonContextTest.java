package br.edu.ifpb.mysla.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifpb.mysla.entidades.AmazonContext;

public class AmazonContextTest {

	ArrayList<Object> objects;

	@Before
	public void setUp() throws Exception {
		objects = new ArrayList<>();
		objects.add(true);
		objects.add(true);
		objects.add(true);
		objects.add(false);
		objects.add(false);
		objects.add(1);

		objects.add("t1.small");
		objects.add(4);
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testSetContext() {
		exception.expect(ClassCastException.class);
		// exception.expectMessage("error1");
		AmazonContext amazonContext = new AmazonContext();
		ArrayList<Object> objectsTest = objects;
		objectsTest.set(5, "nothing");
		amazonContext.setContext(objectsTest);

	}

	@Test
	public void testSetContextNotFail() {
		// exception.expectMessage("error1");

		AmazonContext amazonContext = new AmazonContext();
		amazonContext.setContext(objects);

	}

	@Test
	public void testAmazonContextBooleanBooleanBooleanBooleanBooleanIntStringInt() {
		AmazonContext amazonContext = new AmazonContext(
				(boolean) objects.get(0), (boolean) objects.get(1),
				(boolean) objects.get(2), (boolean) objects.get(3),
				(boolean) objects.get(4), (int) objects.get(5),
				(String) objects.get(6), (int) objects.get(7));
	}

	@Test
	public void testToString() {
		AmazonContext amazonContext = new AmazonContext();
		amazonContext.setContext(objects);
		System.out.println(amazonContext.toString());
	}

	@Test
	public void testGeoZone() {
		assertEquals(AmazonContext.checkGeoZone(5), true);
		assertEquals(AmazonContext.checkGeoZone(6), false);
		assertEquals(AmazonContext.checkGeoZone(0), false);
		assertEquals(AmazonContext.checkGeoZone(-1), false);
	}

}
