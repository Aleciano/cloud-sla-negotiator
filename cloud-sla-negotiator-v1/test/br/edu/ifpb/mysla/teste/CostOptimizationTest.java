package br.edu.ifpb.mysla.teste;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifpb.mysla.entidades.AmazonContext;
import br.edu.ifpb.mysla.entidades.estrategias.CostOptimization;
import br.edu.ifpb.mysla.interfaces.Context;

public class CostOptimizationTest {
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
	public void testCostOptimizationAmazonContext() {
		CostOptimization strategy = new CostOptimization((AmazonContext)context);
	}

	@Test
	public void testSetContext() {
		CostOptimization strategy = new CostOptimization();
		strategy.setContext(context);
	}
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSetContextAnother() {
		exception.expect(ClassCastException.class);
		CostOptimization strategy = new CostOptimization();
		strategy.setContext(new SomeContext());
	}

	@Test
	public void testGetName() {
		CostOptimization strategy = new CostOptimization();
		assertEquals(strategy.getName(), "Cost Optimization");
	}

	@Test
	public void testPlay() {
		CostOptimization strategy = new CostOptimization();
		assertEquals(strategy.play(), false);
	}

}

