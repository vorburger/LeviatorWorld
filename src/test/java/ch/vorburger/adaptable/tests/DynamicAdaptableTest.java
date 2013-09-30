package ch.vorburger.adaptable.tests;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Test;

import ch.vorburger.adaptable.Adaptable;

public class DynamicAdaptableTest {

	@Test
	public void testDynamicAdaptable() {
		SomethingDynamicAdaptable o = new SomethingDynamicAdaptable();
		o.addAdapted(Serializable.class);
		assertTrue(o.isAdaptableTo(Serializable.class));
		assertFalse(o.isAdaptableTo(Iterable.class));
		assertTrue(o.isAdaptableTo(Adaptable.class));
	}

}
