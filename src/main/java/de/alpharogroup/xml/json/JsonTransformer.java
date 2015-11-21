/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class JsonTransformer helps to transform json string to objects and back.
 */
public final class JsonTransformer
{
	/** The Constant logger. */
	static final Logger LOG = Logger.getLogger(JsonTransformer.class.getName());
	/** The Constant mapper. */
	private final static ObjectMapper MAPPER = getObjectMapper(true);

	/**
	 * Gets the object mapper.
	 *
	 * @return the object mapper
	 */
	public static ObjectMapper getObjectMapper()
	{
		return getObjectMapper(false);
	}

	/**
	 * Gets the object mapper.
	 *
	 * @param newMapper
	 *            flag that indicates if a new ObjectMapper should be created. if true a new
	 *            ObjectMapper will be created otherwise the ObjectMapper from this class will be
	 *            returned.
	 * @return the object mapper
	 */
	public static ObjectMapper getObjectMapper(final boolean newMapper)
	{
		if (newMapper)
		{
			return new ObjectMapper();
		}
		return MAPPER;
	}

	/**
	 * Creates from the given {@link List} to a json string.
	 *
	 * @param <T>
	 *            the generic type
	 * @param list
	 *            the list
	 * @return the json string.
	 * @throws JsonGenerationException
	 *             If an error occurs by writing json string
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> String toJson(final List<T> list) throws JsonGenerationException,
		JsonMappingException, IOException
	{
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		final ObjectMapper mapper = new ObjectMapper();

		mapper.writeValue(out, list);

		final byte[] bytes = out.toByteArray();
		out.close();
		return new String(bytes);
	}

	/**
	 * Creates from the given Object a json string.
	 * 
	 * @param <T>
	 *            the generic type of the given argument
	 * @param object
	 *            the object.
	 * @return the json string.
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static <T> String toJson(final T object) throws JsonProcessingException
	{
		return toJson(object, false);
	}

	/**
	 * To json.
	 *
	 * @param <T>
	 *            the generic type
	 * @param object
	 *            the object
	 * @param newMapper
	 *            flag that indicates if a new ObjectMapper should be created. if true a new
	 *            ObjectMapper will be created otherwise the ObjectMapper from this class will be
	 *            returned.
	 * @return the string
	 * @throws JsonProcessingException
	 *             If an error occurs when converting object to String
	 */
	public static <T> String toJson(final T object, final boolean newMapper)
		throws JsonProcessingException
	{
		final ObjectMapper mapper = getObjectMapper(newMapper);
		final String json = mapper.writeValueAsString(object);
		return json;
	}

	/**
	 * Creates from the given Object a json string. Note if an exception occurs null will be
	 * returned.
	 * 
	 * @param <T>
	 *            the generic type of the given argument
	 * @param object
	 *            the object.
	 * @return the json string or null if an error occured by parsing.
	 */
	public static <T> String toJsonQuietly(final T object)
	{
		try
		{
			return toJson(object);
		}
		catch (final JsonProcessingException e)
		{
			LOG.error(
				"An error occured when converting object to String.\nGiven object:"
					+ object.toString() + "\n", e);
		}
		return null;
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
	 * @return the object
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T toObject(final String jsonString, final Class<T> clazz)
		throws JsonParseException, JsonMappingException, IOException
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
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T toObject(final String jsonString, final Class<T> clazz,
		final boolean newMapper) throws JsonParseException, JsonMappingException, IOException
	{
		final ObjectMapper mapper = getObjectMapper(newMapper);
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
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T toObject(final String jsonString, final Class<T> clazz,
		final Module... modules) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = getObjectMapper(true);
		mapper = mapper.registerModules(modules);
		final T object = mapper.readValue(jsonString, clazz);
		return object;
	}

	/**
	 * Transforms the given json string into a java object list.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param jsonString
	 *            the json string
	 * @param clazz
	 *            the clazz of the generic type
	 * @return the list with the java objects.
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> List<T> toObjectList(final String jsonString, final Class<T> clazz)
		throws JsonParseException, JsonMappingException, IOException
	{
		final ObjectMapper mapper = getObjectMapper(true);
		final List<T> objectList = mapper.readValue(jsonString, mapper.getTypeFactory()
			.constructCollectionType(List.class, clazz));
		return objectList;
	}

	/**
	 * private constructor.
	 */
	private JsonTransformer()
	{
	}

}
