package ch.vorburger.meta.methods;

import java.io.Externalizable;
import java.io.File;

import org.junit.Test;

public class MethodInvokerTest {

	MethodInvoker m;
	
	@Test
	public void testMethodInvokerOnKnownSpecificTargetObject() throws Exception {
		File o = null; // usage of File is JUST an arbitrary example - could be ANY Object
		m.invoke(o).delete();
	}

	@Test
	public void testMethodInvokerOnInterfaceThatWillBeAppliedToAllMatchingObjects() throws Exception {
		// usage of Externalizable is JUST an arbitrary example - could be ANY Class
		m.invokeOnAll(Externalizable.class).writeExternal(null);
	}
	
}
