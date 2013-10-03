package ch.vorburger.base;

import org.eclipse.jdt.annotation.NonNull;

@SuppressWarnings("null")
public class Preconditions {
	public static @NonNull <T> T checkNotNull(T reference) {
		return com.google.common.base.Preconditions.checkNotNull(reference);
	}
}

