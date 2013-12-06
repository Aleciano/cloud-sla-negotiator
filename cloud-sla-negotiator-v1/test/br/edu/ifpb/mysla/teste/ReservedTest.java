package br.edu.ifpb.mysla.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifpb.mysla.entidades.AmazonContext;
import br.edu.ifpb.mysla.entidades.estrategias.CostOptimization;
import br.edu.ifpb.mysla.entidades.estrategias.Reserved;
import br.edu.ifpb.mysla.interfaces.Context;

public class ReservedTest {
	ArrayList<Object> objects;
	Context context;
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
		context = new AmazonContext();
		context.setContext(objects);
	}

	@Test
	public void testReservedAmazonContext() {
		Reserved strategy = new Reserved((AmazonContext)context);
	}

	@Test
	public void testSetContext() {
		Reserved strategy = strategy = new Reserved();
		strategy.setContext(context);
	}
	
	@Test
	public void testGetContext() {
		Reserved strategy = new Reserved();
		strategy.setContext(context);
		assertEquals(strategy.getContext(), context);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSetContextAnother() {
		exception.expect(ClassCastException.class);
		Reserved strategy = new Reserved();
		strategy.setContext(new SomeContext());
	}

	@Test
	public void testGetName() {
		Reserved strategy = new Reserved();
		assertEquals(strategy.getName(), "Reserved");
	}

	@Test
	public void testPlay() {
		Reserved strategy = new Reserved();
		assertEquals(strategy.play(), false);
	}

}
