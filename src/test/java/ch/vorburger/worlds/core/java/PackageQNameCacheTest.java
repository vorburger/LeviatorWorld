package ch.vorburger.worlds.core.java;

import static org.junit.Assert.*;

import org.junit.Test;

public class PackageQNameCacheTest {

	@Test
	public void testPackageQNameCache() {
		assertEquals(getClass().getPackage().getName(), PackageQNameCache.INSTANCE.getName(getClass().getPackage()).toString());
	}

}
