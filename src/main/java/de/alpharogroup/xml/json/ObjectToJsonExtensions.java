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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

/**
 * The class {@link ObjectToJsonExtensions} converts java objects to json string objects.
 */
@UtilityClass
public class ObjectToJsonExtensions
{
	
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
	public static <T> String toJson(final List<T> list)
		throws JsonGenerationException, JsonMappingException, IOException
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
		final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper(newMapper);
		final String json = mapper.writeValueAsString(object);
		return json;
	}

}