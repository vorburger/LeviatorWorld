package ch.vorburger.worlds.core.java;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import ch.vorburger.worlds.naming.QualifiedName;

// intentionally package-local, not public
class PackageQNameCache {
	
	 protected LoadingCache<Package, QualifiedName> cache = CacheBuilder.newBuilder()
		       .maximumSize(10000)
		       //.expireAfterWrite(10, TimeUnit.MINUTES)
		       //.removalListener(MY_LISTENER)
		       .build(
		           new CacheLoader<Package, QualifiedName>() {
		             public QualifiedName load(Package key) {
		               return getUncachedName(key);
		             }
		           });

	public static final PackageQNameCache INSTANCE = new PackageQNameCache();

	private PackageQNameCache() { }

	protected QualifiedName getUncachedName(Package aPackage) {
		String[] packageNames = aPackage.getName().split("\\.");
		QualifiedName current = QualifiedName.create(packageNames[0]);
		for (int i = 1; i < packageNames.length; i++) {
			String subPackageName = packageNames[i];
			current = QualifiedName.create(current, subPackageName);
		}
		return current;
	}

	public QualifiedName getName(Package aPackage) {
		return cache.getUnchecked(aPackage);
	}

}
