package ch.vorburger.worlds.persistence.gson;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * GSon Type Adapter for java.lang.class.
 * Needed because of DynamicMarkerInterfaceAdaptable.
 * 
 * @see http://stackoverflow.com/questions/8119138/gson-not-parsing-class-variable
 */
public class ClassTypeAdapter implements JsonSerializer<Class<?>>, JsonDeserializer<Class<?>> {

    @Override
    public JsonElement serialize(Class<?> src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.getName());
    }

    @Override
    public Class<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return Class.forName(json.getAsString());
        } catch (Throwable e) {
            throw new JsonParseException("could not create class: " + json.getAsString(), e);
        }
    }
	
	
}
