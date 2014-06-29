package ch.vorburger.meta.methods.tests;

import java.io.Externalizable;
import java.io.File;

import org.junit.Test;

import ch.vorburger.meta.methods.DirectMethodInvoker;
import ch.vorburger.meta.methods.MethodInvoker;

public class MethodInvokerTest {

	MethodInvoker m = new DirectMethodInvoker();
	
	@Test
	public void testMethodInvokerOnKnownSpecificTargetObject() throws Exception {
		File o = new File("xoxo"); // usage of File is JUST an arbitrary example - could be ANY Object
		m.invoke(o).delete();
	}

	@Test
	public void testMethodInvokerOnInterfaceThatWillBeAppliedToAllMatchingObjects() throws Exception {
		// usage of Externalizable is JUST an arbitrary example - could be ANY Class
		m.invokeOnAll(Externalizable.class).writeExternal(null);
	}
	
}
