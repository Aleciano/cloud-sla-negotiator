package br.edu.ifpb.mysla.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifpb.mysla.entidades.AmazonContext;
import br.edu.ifpb.mysla.entidades.estrategias.MinimizeInterruption;
import br.edu.ifpb.mysla.entidades.estrategias.OnDemand;
import br.edu.ifpb.mysla.interfaces.Context;

public class OnDemandTest {
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
	public void testOnDemandAmazonContext() {
		OnDemand strategy = new OnDemand((AmazonContext) context);
	}

	@Test
	public void testSetContext() {
		OnDemand strategy = new OnDemand();
		strategy.setContext(context);
	}

	@Test
	public void testGetContext() {
		OnDemand strategy = new OnDemand();
		strategy.setContext(context);
		assertEquals(strategy.getContext(), context);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testSetContextAnother() {
		exception.expect(ClassCastException.class);
		OnDemand strategy = new OnDemand();
		strategy.setContext(new SomeContext());
	}

	@Test
	public void testGetName() {
		OnDemand strategy = new OnDemand();
		assertEquals(strategy.getName(), "On Demand");
	}

	@Test
	public void testPlay() {
		OnDemand strategy = new OnDemand();
		assertEquals(strategy.play(), false);
	}

}
