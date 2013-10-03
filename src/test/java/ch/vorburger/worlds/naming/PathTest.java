package ch.vorburger.worlds.naming;

import org.junit.Test;
import static org.junit.Assert.*;

public class PathTest {

	class SomeKindOfStringPath extends Path<String> {
		public SomeKindOfStringPath(String rootSegment) {
			super(rootSegment);
		}
		public SomeKindOfStringPath(Path<String> parent, String lastSegment) {
			super(parent, lastSegment);
		}
	}
	
	@Test public void testRootPath() {
		SomeKindOfStringPath p = new SomeKindOfStringPath("root");
		SomeKindOfStringPath p2 = new SomeKindOfStringPath("root");
		assertEquals("root", p.toString());
		assertEquals("root", p.getLastSegment());
		assertEquals(1, p.getSegments().size());
		assertEquals("root", p.getSegments().get(0));
		assertEquals(p.hashCode(), p2.hashCode());
		assertTrue(p.equals(p2));
	}
	
	@Test public void testNonRootPath() {
		SomeKindOfStringPath pX = new SomeKindOfStringPath(new SomeKindOfStringPath("root"), "sub");

		assertEquals("root.sub", pX.toString());
		assertEquals("sub", pX.getLastSegment());
		assertEquals(2, pX.getSegments().size());
		assertEquals("root", pX.getSegments().get(0));
		assertEquals("sub", pX.getSegments().get(1));
		
		SomeKindOfStringPath p2X = new SomeKindOfStringPath(new SomeKindOfStringPath("root"), "sub");
		assertEquals(pX.hashCode(), p2X.hashCode());
		assertTrue(pX.equals(p2X));
	}

	@Test public void testEqualsBetweenDifferentPathSubclasses() {
		SomeKindOfStringPath p = new SomeKindOfStringPath("root");
		QualifiedName p2 = QualifiedName.create("root");
		assertFalse(p.equals(p2));
		// This isn't required: assertNotEquals(p.hashCode(), p2.hashCode());
	}

}
