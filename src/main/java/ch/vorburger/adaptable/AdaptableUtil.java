package ch.vorburger.adaptable;


public class AdaptableUtil {
	private AdaptableUtil() { }

	@SuppressWarnings("unchecked")
	// package-local - normal clients must use public method below, but StaticAdaptable needs this (and cannot use public, else infi. loop) 
	static <T> T getAdapterIfIsInstance(Object sourceObject, Class<T> adapterType) {
        if (sourceObject == null) {
            return null;
        }
        if (adapterType.isInstance(sourceObject)) {
            return (T) sourceObject;
        }
        return null;
	}
	
	public static <T> T getAdapter(Object sourceObject, Class<T> adapterType) {
		T result = getAdapterIfIsInstance(sourceObject, adapterType);
		if (result != null)
			return result;
		
        if (sourceObject instanceof Adaptable) {
            Adaptable adaptable = (Adaptable) sourceObject;

            result = adaptable.adaptTo(adapterType);
            return result;
        } 
        
//        if (!(sourceObject instanceof PlatformObject)) {
//            Object result = Platform.getAdapterManager().getAdapter(sourceObject, adapterType);
//            if (result != null) {
//                return result;
//            }
//        }

        return null;
    }
	
}
