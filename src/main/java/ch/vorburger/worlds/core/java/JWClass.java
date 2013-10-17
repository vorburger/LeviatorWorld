package ch.vorburger.worlds.core.java;

import ch.vorburger.worlds.core.WClass;
import ch.vorburger.worlds.naming.QualifiedName;

public class JWClass<T> implements WClass {

	protected final Class<T> jClass;
	private static final WClass theJWClassWClass = new JWClassWClass();
	
	public static <T> JWClass<T> fromJavaClass(Class<T> jClass) {
		// TODO probably use a cache, like in PackageQNameCache, LATER
		return new JWClass<T>(jClass);
	}
	
	protected JWClass(Class<T> jClass) {
		this.jClass = jClass;
	}
	
	@Override
	public QualifiedName name() {
		return qualifiedNameForClass(jClass);
	}

	private static QualifiedName qualifiedNameForClass(Class<?> clazz) {
		QualifiedName jPackageName = PackageQNameCache.INSTANCE.getName(clazz.getPackage());
		return QualifiedName.create(jPackageName, clazz.getSimpleName());
	}

	@Override
	public WClass getWClass() {
		return theJWClassWClass;
	}

	@Override
	public String toString() {
		return "JWClass [" + jClass + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jClass == null) ? 0 : jClass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		JWClass<T> other = (JWClass<T>) obj;
		if (jClass == null) {
			if (other.jClass != null)
				return false;
		} else if (!jClass.equals(other.jClass))
			return false;
		return true;
	}

	private static class JWClassWClass implements WClass {

		@Override
		public WClass getWClass() {
			// just like Class.class.getClass() == class java.lang.Class
			return this;
		}

		@Override
		public QualifiedName name() {
			return qualifiedNameForClass(this.getClass());
		}

		// no hashCode() & equals() needed here, because there is only ever one instance of this
	}
}
