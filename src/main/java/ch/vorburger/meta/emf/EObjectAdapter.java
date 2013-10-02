package ch.vorburger.meta.emf;

import org.eclipse.emf.ecore.EObject;

public class EObjectAdapter {

	protected final EObject eObject;
	
	public EObjectAdapter(EObject eObject) {
		this.eObject = eObject;
	}

	public EObjectAdapter() {
		// org.eclipse.emf.ecore.impl.MinimalEObjectImpl ?
		// org.eclipse.emf.ecore.impl.DynamicEObjectImpl ?
		// org.eclipse.emf.ecore.impl.FlatEObjectImpl ?
		// org.eclipse.emf.ecore.impl.EObjectImpl ?
		// org.eclipse.emf.ecore.impl.BasicEObjectImpl ?
		this.eObject = null;
	}

}
