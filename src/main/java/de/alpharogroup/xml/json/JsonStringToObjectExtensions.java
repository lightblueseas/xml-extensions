/**
 * The MIT License
 *
 * Copyright (C) 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.xml.json;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.alpharogroup.xml.factory.ObjectMapperFactory;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link JsonStringToObjectExtensions} converts json strings to java object and java
 * collections.
 */
@UtilityClass
public final class JsonStringToObjectExtensions
{

	/**
	 * Transforms the given json string into a java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonString
	 *            the json string
	 * @param clazz
	 *            the clazz of the generic type
	 * @return the object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T toObject(final @NonNull String jsonString, final @NonNull Class<T> clazz)
		throws IOException
	{
		return toObject(jsonString, clazz, false);
	}

	/**
	 * Transforms the given json string into a java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonString
	 *            the json string
	 * @param clazz
	 *            the clazz of the generic type
	 * @param newMapper
	 *            flag that indicates if a new ObjectMapper should be created. if true a new
	 *            ObjectMapper will be created otherwise the ObjectMapper from this class will be
	 *            returned.
	 * @return the object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T toObject(final @NonNull String jsonString, final @NonNull Class<T> clazz,
		final boolean newMapper) throws IOException
	{
		final ObjectMapper mapper = ObjectMapperFactory.newObjectMapper(newMapper);
		return toObject(jsonString, clazz, mapper);
	}

	/**
	 * Transforms the given json string into a java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonString
	 *            the json string
	 * @param clazz
	 *            the clazz of the generic type
	 * @param modules
	 *            The modules to register for the mapper
	 * @return the object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T toObject(final @NonNull String jsonString, final @NonNull Class<T> clazz,
		final Module... modules) throws IOException
	{
		ObjectMapper mapper = ObjectMapperFactory.newObjectMapper(true);
		mapper = mapper.registerModules(modules);
		return toObject(jsonString, clazz, mapper);
	}

	/**
	 * Transforms the given json string into a java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonString
	 *            the json string
	 * @param clazz
	 *            the clazz of the generic type
	 * @param mapper
	 *            the object mapper
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> T toObject(final @NonNull String jsonString, final @NonNull Class<T> clazz,
		final @NonNull ObjectMapper mapper) throws IOException
	{
		return mapper.readValue(jsonString, clazz);
	}

	/**
	 * Transforms the given json string into a java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonString
	 *            the json string
	 * @param typeReference
	 *            the type reference
	 * @param mapper
	 *            the object mapper
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> T toObject(final @NonNull String jsonString,
		final @NonNull TypeReference<T> typeReference, final @NonNull ObjectMapper mapper)
		throws IOException
	{
		return mapper.readValue(jsonString, typeReference);
	}

	/**
	 * Transforms the given json string into a java map object
	 *
	 * @param <K>
	 *            the generic type of keys
	 * @param <V>
	 *            the generic type of values
	 * @param jsonString
	 *            the json string
	 * @param typeReference
	 *            the type reference
	 * @param mapper
	 *            the object mapper
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <K, V> Map<K, V> toMapObject(final @NonNull String jsonString,
		final @NonNull TypeReference<Map<K, V>> typeReference, final @NonNull ObjectMapper mapper)
		throws IOException
	{
		return mapper.readValue(jsonString, typeReference);
	}

	/**
	 * Transforms the given json string into a java object {@link List}
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonString
	 *            the json string
	 * @param elementClass
	 *            the element class of the generic type
	 * @return the list with the java objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> List<T> toObjectList(final @NonNull String jsonString,
		final @NonNull Class<T> elementClass) throws IOException
	{
		return (List<T>)toObjectCollection(jsonString, List.class, elementClass);
	}

	/**
	 * Transforms the given json string into a java object {@link Collection}
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonString
	 *            the json string
	 * @param collectionClass
	 *            the collection class
	 * @param elementClass
	 *            the element class
	 * @return the list with the java objects.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> Collection<T> toObjectCollection(final @NonNull String jsonString,
		@SuppressWarnings("rawtypes") final @NonNull Class<? extends Collection> collectionClass,
		final @NonNull Class<T> elementClass) throws IOException
	{
		final ObjectMapper mapper = ObjectMapperFactory.newObjectMapper(true);
		return mapper.readValue(jsonString,
			mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass));
	}

}
