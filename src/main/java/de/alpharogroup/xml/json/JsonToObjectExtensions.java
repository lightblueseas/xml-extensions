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

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link JsonToObjectExtensions} converts json strings to java object and java
 * collections.
 */
@UtilityClass
public final class JsonToObjectExtensions
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
		final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper(newMapper);
		final T object = mapper.readValue(jsonString, clazz);
		return object;
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
		ObjectMapper mapper = ObjectMapperFactory.getObjectMapper(true);
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
		final T object = mapper.readValue(jsonString, clazz);
		return object;
	}

	/**
	 * Transforms the given json file into a java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonFile
	 *            the json file
	 * @param clazz
	 *            the clazz
	 * @param mapper
	 *            the mapper
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> T toObject(final @NonNull File jsonFile, final @NonNull Class<T> clazz,
		final @NonNull ObjectMapper mapper) throws IOException
	{
		final T object = mapper.readValue(jsonFile, clazz);
		return object;
	}

	/**
	 * Transforms the given json file into a java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonFile
	 *            the json file
	 * @param typeReference
	 *            the type reference
	 * @param mapper
	 *            the mapper
	 * @return the t
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> T toObject(final @NonNull File jsonFile,
		final @NonNull TypeReference<T> typeReference, final @NonNull ObjectMapper mapper)
		throws IOException
	{
		final T object = mapper.readValue(jsonFile, typeReference);
		return object;
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
		final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper(true);
		return mapper.readValue(jsonString,
			mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass));
	}

}
