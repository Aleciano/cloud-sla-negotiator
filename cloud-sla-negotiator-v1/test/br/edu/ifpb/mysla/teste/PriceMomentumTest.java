package br.edu.ifpb.mysla.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifpb.mysla.entidades.AmazonContext;
import br.edu.ifpb.mysla.entidades.estrategias.OnDemand;
import br.edu.ifpb.mysla.entidades.estrategias.PriceMomentum;
import br.edu.ifpb.mysla.interfaces.Context;

public class PriceMomentumTest {
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
	public void testPriceMomentumAmazonContext() {
		PriceMomentum strategy = new PriceMomentum((AmazonContext)context);
	}

	@Test
	public void testSetContext() {
		PriceMomentum strategy = new PriceMomentum();
		strategy.setContext(context);
	}
	
	@Test
	public void testGetContext() {
		PriceMomentum strategy = new PriceMomentum();
		strategy.setContext(context);
		assertEquals(strategy.getContext(), context);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testSetContextAnother() {
		exception.expect(ClassCastException.class);
		PriceMomentum strategy = new PriceMomentum();
		strategy.setContext(new SomeContext());
	}

	@Test
	public void testGetName() {
		PriceMomentum strategy = new PriceMomentum();
		assertEquals(strategy.getName(), "Price Momentum");
	}

	@Test
	public void testPlay() {
		PriceMomentum strategy = new PriceMomentum();
		assertEquals(strategy.play(), false);
	}

}
