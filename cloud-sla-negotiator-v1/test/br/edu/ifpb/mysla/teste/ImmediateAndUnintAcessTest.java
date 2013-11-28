package br.edu.ifpb.mysla.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifpb.mysla.entidades.AmazonContext;
import br.edu.ifpb.mysla.entidades.estrategias.CostOptimization;
import br.edu.ifpb.mysla.entidades.estrategias.ImmediateAndUnintAcess;
import br.edu.ifpb.mysla.interfaces.Context;

public class ImmediateAndUnintAcessTest {
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
	public void testImmediateAndUnintAcessAmazonContext() {
		ImmediateAndUnintAcess strategy = new ImmediateAndUnintAcess((AmazonContext)context);
	}

	@Test
	public void testSetContext() {
		ImmediateAndUnintAcess strategy = strategy = new ImmediateAndUnintAcess();
		strategy.setContext(context);
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSetContextAnother() {
		exception.expect(ClassCastException.class);
		ImmediateAndUnintAcess strategy = new ImmediateAndUnintAcess();
		strategy.setContext(new SomeContext());
	}

	@Test
	public void testGetName() {
		ImmediateAndUnintAcess strategy = new ImmediateAndUnintAcess();
		assertEquals(strategy.getName(), "Immediate and Uninterrupted Acess");
	}

	@Test
	public void testPlay() {
		ImmediateAndUnintAcess strategy = new ImmediateAndUnintAcess();
		assertEquals(strategy.play(), false);
	}

}
