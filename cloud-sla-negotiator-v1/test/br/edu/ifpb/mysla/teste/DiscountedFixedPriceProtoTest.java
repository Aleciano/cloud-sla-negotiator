package br.edu.ifpb.mysla.teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.edu.ifpb.mysla.entidades.protocolos.DiscountedFixedPriceProto;
import br.edu.ifpb.mysla.interfaces.InteraProtocol;

public class DiscountedFixedPriceProtoTest {
	InteraProtocol proto;
	Object data;
	Object emptyData;

	@Before
	public void setUp() throws Exception {
		proto = new DiscountedFixedPriceProto();
		
	}

	@Test
	public void testGetName() {
		assertEquals(proto.getName(), "discountedfixedpriceprotocol");
		assertFalse((proto.getName().equals("")));
		assertFalse((proto.getName().equals(null)));
	}

	@Test
	public void testGetVersion() {
		assertEquals(proto.getVersion(), "0.1");
		assertFalse((proto.getVersion().equals("0")));
		assertFalse((proto.getVersion().equals(null)));
	}
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetData() {
		assertEquals(proto.getData(), null);
	}
	
	@Test
	public void testSetData() {
		exception.expect(NullPointerException.class);
		proto.setData(emptyData);
	}

	@Test
	public void testGetDataTrue() {
		data = new Object();
		DiscountedFixedPriceProto proto2 = (DiscountedFixedPriceProto) proto;
		proto2.setData(data);
		assertEquals(proto2.getData(), data);
	}
	@Test
	public void testMakeProposal() {
		data = new Object();
		DiscountedFixedPriceProto proto2 = (DiscountedFixedPriceProto) proto;
		proto2.setData(data);
		assertEquals(proto2.makeProposal(), false);
	}

	@Test
	public void testMakeProposalEmpty() {
		exception.expect(NullPointerException.class);
		assertEquals(proto.makeProposal(), false);
	}

	@Test
	public void testAcceptProposal() {
		assertEquals(proto.acceptProposal(), false);
	}

};
