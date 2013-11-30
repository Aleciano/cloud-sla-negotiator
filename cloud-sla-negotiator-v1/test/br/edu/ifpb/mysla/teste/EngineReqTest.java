package br.edu.ifpb.mysla.teste;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifpb.mysla.entidades.AmazonContext;
import br.edu.ifpb.mysla.entidades.EngineReq;
import br.edu.ifpb.mysla.entidades.estrategias.ImmediateAndUnintAcess;
import br.edu.ifpb.mysla.entidades.estrategias.OnDemand;
import br.edu.ifpb.mysla.entidades.estrategias.PriceMomentum;
import br.edu.ifpb.mysla.entidades.protocolos.DiscountedFixedPriceProto;
import br.edu.ifpb.mysla.entidades.protocolos.FixedPriceProto;
import br.edu.ifpb.mysla.entidades.protocolos.SpotInstanceProto;
import br.edu.ifpb.mysla.interfaces.Context;
import br.edu.ifpb.mysla.interfaces.InteraProtocol;
import br.edu.ifpb.mysla.interfaces.Strategy;

import com.sun.xml.internal.ws.api.pipe.Engine;

public class EngineReqTest {
	EngineReq engine;
	InteraProtocol protocol;
	Strategy strategy;

	@Before
	public void setUp() throws Exception {
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testeEngineReqArrayOfInteraProtocolsArrayOfStrategiesLoginWkey() {
		InteraProtocol someProtocol = new SpotInstanceProto();
		Strategy someStrategy = new PriceMomentum();
		ArrayList<InteraProtocol> arrayOfProtocols = new ArrayList<InteraProtocol>();
		arrayOfProtocols.add(someProtocol);
		ArrayList<Strategy> arrayOfStrategies = new ArrayList<Strategy>();
		arrayOfStrategies.add(someStrategy);

		engine = new EngineReq(arrayOfProtocols, arrayOfStrategies, "test",
				"passtest".toCharArray());
		assertEquals(engine.getLogin(), "test");
		assertTrue(engine.getKey().length > 0);
		assertEquals(engine.getProtocols(), arrayOfProtocols);
		assertTrue(engine.getProtocols().size() > 0);
		assertEquals(engine.getStrategies(), arrayOfStrategies);
		assertTrue(engine.getStrategies().size() > 0);
	}

	@Test
	public void testeEngineReqInteraProtocolStrategyLoginWkey() {
		InteraProtocol someProtocol = new SpotInstanceProto();
		Strategy someStrategy = new PriceMomentum();

		engine = new EngineReq(someProtocol, someStrategy, "test",
				"passtest".toCharArray());
		assertEquals(engine.getLogin(), "test");
		assertTrue(engine.getKey().length > 0);
		assertTrue(engine.getProtocols().size() > 0);
		assertTrue(engine.getStrategies().size() > 0);
	}

	@Test
	public void testeEngineReqInteraProtocol() {
		InteraProtocol someProtocol = new SpotInstanceProto();

		engine = new EngineReq(someProtocol);
		assertEquals(engine.getLogin(), "guest");
		assertTrue(engine.getKey().length > 0);
		assertTrue(engine.getProtocols().size() > 0);
		assertEquals(engine.getProtocols().get(0), someProtocol);
	}

	@Test
	public void testeEngineReqStrategy() {
		Strategy someStrategy = new PriceMomentum();

		engine = new EngineReq(someStrategy);
		assertEquals(engine.getLogin(), "guest");
		assertTrue(engine.getKey().length > 0);
		assertTrue(engine.getStrategies().size() > 0);
		assertEquals(engine.getStrategies().get(0), someStrategy);
	}

	/*
	 * @Test public void testeEngineReqStrategy(){ Strategy someStrategy = new
	 * PriceMomentum();
	 * 
	 * 
	 * engine = new EngineReq(someStrategy); assertEquals(engine.getLogin(),
	 * "guest"); assertTrue(engine.getKey().length > 0);
	 * assertTrue(engine.getStrategies().size() > 0 );
	 * assertEquals(engine.getStrategies().get(0), someStrategy); }
	 */

	@Test
	public void testSetProtocolInteraProtocol() {
		SpotInstanceProto someProtocol = new SpotInstanceProto();
		engine = new EngineReq();
		engine.setProtocol(someProtocol);
		int size = engine.getProtocols().size();
		assertTrue(size > 0);
		ArrayList<InteraProtocol> arrayOfProtocols = new ArrayList<InteraProtocol>();
		arrayOfProtocols.add(someProtocol);
		FixedPriceProto anotherProtocol = new FixedPriceProto();
		arrayOfProtocols.add(anotherProtocol);
		engine.setProtocols(arrayOfProtocols);
		size = engine.getProtocols().size();
		assertTrue(size > 1 && size < 3);

	}

	@Test
	public void testSetProtocolInteraProtocolEmpty() {
		exception.expect(NullPointerException.class);
		engine = new EngineReq();
		engine.setProtocol(protocol);
		assertEquals(engine.getProtocolName(0), "");
	}

	@Test
	public void testSetStrategyStrategy() {
		OnDemand someStrategy = new OnDemand();
		engine = new EngineReq();
		engine.setStrategy(someStrategy);
		int size = engine.getStrategies().size();
		assertTrue(size > 0);
		ArrayList<Strategy> arrayOfStrategies = new ArrayList<Strategy>();
		arrayOfStrategies.add(someStrategy);
		ImmediateAndUnintAcess anotherStrategy = new ImmediateAndUnintAcess();
		arrayOfStrategies.add(anotherStrategy);
		engine.setStrategies(arrayOfStrategies);
		size = engine.getStrategies().size();
		assertTrue(size > 1 && size < 3);
	}

	@Test
	public void testSetStrategyStrategyEmpty() {
		exception.expect(IndexOutOfBoundsException.class);
		engine = new EngineReq();
		engine.setStrategy(strategy);
		assertEquals(engine.getProtocolName(0), "");

	}

	@Test
	public void testSetProtocolClassOfQextendsInteraProtocol() {
		// exception.expect(IllegalAccessException.class);
		engine = new EngineReq();

		try {
			engine.setProtocol(SpotInstanceProto.class);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSetStrategyContextClassOfQextendsStrategy() {
		engine = new EngineReq();
		Context context = new AmazonContext();
		try {
			engine.setStrategy(context, OnDemand.class);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetProtocolName() {
		engine = new EngineReq();
		InteraProtocol someProtocol = new SpotInstanceProto();
		engine.setProtocol(someProtocol);
		assertEquals(engine.getProtocolName(0), "spotinstanceprotocol");
	}

	@Test
	public void testGetProtocolNameEmpty() {
		exception.expect(IndexOutOfBoundsException.class);
		engine = new EngineReq();
		// InteraProtocol someProtocol = new SpotInstanceProto();
		// engine.setProtocol(someProtocol);
		assertEquals(engine.getProtocolName(0), "spotinstanceprotocol");
	}

	@Test
	public void testGetStrategyName() {
		engine = new EngineReq();
		Strategy someStrategy = new OnDemand();
		engine.setStrategy(someStrategy);
		assertEquals(engine.getStrategyName(0), "On Demand");
	}

	@Test
	public void testGetStrategyNameEmpty() {
		exception.expect(IndexOutOfBoundsException.class);
		engine = new EngineReq();
		// Strategy someStrategy = new OnDemand();
		// engine.setStrategy(someStrategy);
		assertEquals(engine.getStrategyName(0), "On Demand");
	}

	@Test
	public void testSetLogin() {
		engine = new EngineReq();
		assertEquals(engine.getLogin(), "guest");
		engine.setLogin("lobo");
		assertEquals(engine.getLogin(), "lobo");
		assertEquals(engine.getLogin().equals("guest"), false);
	}

	@Test
	public void testSetKey() {
		engine = new EngineReq();
		assertTrue((engine.getKey().length > 0));
		engine.setKey("test".toCharArray());

		/*
		 * for (int i = 0; i < engine.getKey().length; i++) {
		 * System.out.print(Character.toString((engine.getKey()[i])) + " " + i +
		 * " "); }
		 */

		assertTrue((engine.getKey().length > 0));
		
		assertFalse(engine.setKey("".toCharArray()));
		
		
		String someStr = "";
		for (int i = 0; i < engine.getKey().length; i++) {
			someStr = someStr + (Character.toString(engine.getKey()[i]));
		}
		assertTrue(someStr.equals("test"));
		assertTrue(engine.getKey().length <= 4);

	}

	@Test
	public void testStrategyExists() {
		OnDemand someStrategy = new OnDemand();
		engine = new EngineReq();
		engine.setStrategy(someStrategy);

		assertTrue(engine.strategyExists(OnDemand.class));
	}

	@Test
	public void testStrategyNotExists() {
		OnDemand someStrategy = new OnDemand();
		engine = new EngineReq();
		engine.setStrategy(someStrategy);
		assertFalse(engine.strategyExists(PriceMomentum.class));
	}

	@Test
	public void testStrategyExistsEmpty() {
		// OnDemand someStrategy = new OnDemand();
		engine = new EngineReq();
		// engine.setStrategy(someStrategy);
		assertFalse(engine.strategyExists(PriceMomentum.class));
	}

	@Test
	public void testStartRequestFail() throws IOException {
		engine = new EngineReq();
		assertFalse(engine.startRequest(0, 0));

	}

	@Test
	public void testStartRequest() throws IOException {
		engine = new EngineReq();

		ArrayList<String> argv = new ArrayList<String>();
		argv.add(Integer.toString(-1));
		argv.add("test");
		argv.add("passtest");
		ArrayList<Object> objects;
		objects = new ArrayList<>();
		objects.add(true);
		objects.add(true);
		objects.add(true);
		objects.add(false);
		objects.add(false);
		objects.add(1);

		objects.add("t1.small");
		objects.add(4);
		AmazonContext amazonContext = new AmazonContext();
		amazonContext.setContext(objects);
		InteraProtocol someProtocol = new SpotInstanceProto();
		Strategy someStrategy = new OnDemand(amazonContext);
		engine.setStrategy(someStrategy);
		engine.setProtocol(someProtocol);
		System.out.println(engine.getProtocols().size());
		// assertTrue(engine.getProtocols().size() > 1);
		assertTrue(engine.startRequest(0, 0));
		/*assertTrue(engine
				.writeLog(
						"/home/junior/git/cloud-sla-negotiator/cloud-sla-negotiator-v1/src/logs/",
						0, 0, argv));
*/
	}

	@Test
	public void testStartRequestIndexError() throws IOException {
		exception.expect(IndexOutOfBoundsException.class);
		engine = new EngineReq();
		InteraProtocol someProtocol = new SpotInstanceProto();
		Strategy someStrategy = new OnDemand();
		engine.setStrategy(someStrategy);
		engine.setProtocol(someProtocol);
		assertFalse(engine.startRequest(1, 1));

	}

	@Test
	public void testWriteLog() throws IOException {
		engine = new EngineReq();
		ArrayList<String> argv = new ArrayList<String>();
		
		assertFalse(engine.writeLog("??", 0, 0, argv));
		
		argv.add(Integer.toString(-2));
		argv.add("test");
		argv.add("passtest");
		ArrayList<Object> objects;
		objects = new ArrayList<>();
		objects.add(true);
		objects.add(true);
		objects.add(true);
		objects.add(false);
		objects.add(false);
		objects.add(1);

		objects.add("t1.small");
		objects.add(4);
		AmazonContext amazonContext = new AmazonContext();
		amazonContext.setContext(objects);

		engine.setStrategy(new OnDemand(amazonContext));
		engine.setProtocol(new SpotInstanceProto());
		assertTrue(engine
				.writeLog(
						"/home/junior/git/cloud-sla-negotiator/cloud-sla-negotiator-v1/src/logs/",
						0, 0, argv));
	}

	@Test
	public void testWriteLogPathError() throws IOException {
		exception.expect(IOException.class);
		engine = new EngineReq();
		ArrayList<String> argv = new ArrayList<String>();
		argv.add(Integer.toString(-3));
		argv.add("test");
		argv.add("passtest");
		/*
		 * ArrayList<Object> objects; objects = new ArrayList<>();
		 * objects.add(true); objects.add(true); objects.add(true);
		 * objects.add(false); objects.add(false); objects.add(1);
		 * 
		 * objects.add("t1.small"); objects.add(4); AmazonContext amazonContext
		 * = new AmazonContext(); amazonContext.setContext(objects);
		 * 
		 * engine.setStrategy(new OnDemand(amazonContext));
		 * engine.setProtocol(new SpotInstanceProto());
		 */

		assertFalse(engine.writeLog("http://www.globo.com", 0, 0, argv));

	}

	@Test
	public void testWriteLogNullIndex() throws IOException {
		exception.expect(IndexOutOfBoundsException.class);
		engine = new EngineReq();
		ArrayList<String> argv = new ArrayList<String>();
		argv.add(Integer.toString(-4));
		argv.add("test");
		argv.add("passtest");

		ArrayList<Object> objects;
		objects = new ArrayList<>();
		objects.add(true);
		objects.add(true);
		objects.add(true);
		objects.add(false);
		objects.add(false);
		objects.add(1);

		objects.add("t1.small");
		objects.add(4);
		AmazonContext amazonContext = new AmazonContext();
		amazonContext.setContext(objects);

		// engine.setStrategy(new OnDemand(amazonContext));
		// engine.setProtocol(new SpotInstanceProto());

		assertFalse(engine
				.writeLog(
						"/home/junior/git/cloud-sla-negotiator/cloud-sla-negotiator-v1/src/logs/",
						0, 0, argv));
	}

}
