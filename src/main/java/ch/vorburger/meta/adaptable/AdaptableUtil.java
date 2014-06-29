package ch.vorburger.meta.adaptable;

import java.util.Optional;



public class AdaptableUtil {
	private AdaptableUtil() { }
	
	@SuppressWarnings("unchecked")
	// package-local - normal clients must use public method below, but StaticAdaptable needs this (and cannot use public, else infi. loop) 
	static <T> Optional<T> getAdapterIfIsInstance(Object sourceObject, Class<T> adapterType) {
        if (adapterType.isInstance(sourceObject)) {
            return Optional.of((T) sourceObject);
        }
        return Optional.empty();
	}

	public static <T> Optional<T> getAdapter(Object sourceObject, Class<T> adapterType) {
		Optional<T> result = getAdapterIfIsInstance(sourceObject, adapterType);
		if (result.isPresent())
			return result;
		
        if (sourceObject instanceof Adaptable) {
            Adaptable adaptable = (Adaptable) sourceObject;
            if (adaptable.isAdaptableTo(adapterType)) {
            	return Optional.of(adaptable.adaptTo(adapterType));
            } else {
            	return Optional.empty();
            }
        } 
        
//        if (!(sourceObject instanceof PlatformObject)) {
//            Object result = Platform.getAdapterManager().getAdapter(sourceObject, adapterType);
//            if (result != null) {
//                return result;
//            }
//        }

    	return Optional.empty();
    }
	
}
