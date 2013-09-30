package ch.vorburger.worlds.persistence.gson;

import java.lang.reflect.Type;
import java.util.Collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * GSon Type Adapter to skip empty Collections to save space.
 * 
 * @see http://stackoverflow.com/questions/11942118/how-do-you-get-gson-to-omit-null-or-empty-objects-and-empty-arrays-and-lists
 */
public class CollectionTypeAdapter implements JsonSerializer<Collection<?>> {
	@Override
	public JsonElement serialize(Collection<?> src, Type typeOfSrc, JsonSerializationContext context) {
		if (src == null || src.isEmpty()) // exclusion is made here
			return null;

		JsonArray array = new JsonArray();

		for (Object child : src) {
			JsonElement element = context.serialize(child);
			array.add(element);
		}

		return array;
	}
}