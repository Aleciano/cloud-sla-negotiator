package br.edu.ifpb.mysla.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifpb.mysla.entidades.AmazonContext;
import br.edu.ifpb.mysla.entidades.estrategias.Reserved;
import br.edu.ifpb.mysla.entidades.estrategias.MinimizeInterruption;
import br.edu.ifpb.mysla.interfaces.Context;

public class MinimizeInterruptionTest {
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
	public void testMinimizeInterruptionAmazonContext() {
		MinimizeInterruption strategy = new MinimizeInterruption((AmazonContext)context);
	}

	@Test
	public void testSetContext() {
		MinimizeInterruption strategy = new MinimizeInterruption();
		strategy.setContext(context);
	}
	
	@Test
	public void testGetContext() {
		MinimizeInterruption strategy = new MinimizeInterruption();
		strategy.setContext(context);
		assertEquals(strategy.getContext(), context);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSetContextAnother() {
		exception.expect(ClassCastException.class);
		MinimizeInterruption strategy = new MinimizeInterruption();
		strategy.setContext(new SomeContext());
	}

	@Test
	public void testGetName() {
		MinimizeInterruption strategy = new MinimizeInterruption();
		assertEquals(strategy.getName(), "Minimize Interruption");
	}

	@Test
	public void testPlay() {
		MinimizeInterruption strategy = new MinimizeInterruption();
		assertEquals(strategy.play(),false);
	}

}
