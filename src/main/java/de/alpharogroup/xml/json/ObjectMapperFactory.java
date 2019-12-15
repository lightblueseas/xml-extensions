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

import java.util.Map;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.experimental.UtilityClass;

/**
 * The factory class {@link ObjectMapperFactory} for creating {@link ObjectMapper} objects
 */
@UtilityClass
public class ObjectMapperFactory
{

	/** The constant mapper. */
	private final static ObjectMapper MAPPER = newObjectMapper(true);

	/**
	 * Gets the object mapper.
	 *
	 * @return the object mapper
	 * @deprecated use instead the method <code>newObjectMapper()</code>
	 */
	@Deprecated
	public static ObjectMapper getObjectMapper()
	{
		return newObjectMapper(false);
	}

	/**
	 * Gets the object mapper.
	 *
	 * @param newMapper
	 *            flag that indicates if a new ObjectMapper should be created. if true a new
	 *            ObjectMapper will be created otherwise the ObjectMapper from this class will be
	 *            returned.
	 * @return the object mapper
	 * @deprecated use instead the method <code>newObjectMapper(boolean)</code>
	 */
	@Deprecated
	public static ObjectMapper getObjectMapper(final boolean newMapper)
	{
		return newObjectMapper(newMapper);
	}

	/**
	 * Factory method for create a new {@link ObjectMapper}
	 *
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newObjectMapper()
	{
		return newObjectMapper(false);
	}

	/**
	 * Factory method for create a new {@link ObjectMapper}. If the given flag is true a new
	 * {@link ObjectMapper} will be created otherwise the default {@link ObjectMapper} will be
	 * taken.
	 *
	 * @param newMapper
	 *            flag that indicates if a new {@link ObjectMapper} should be created, if true a new
	 *            {@link ObjectMapper} will be created otherwise the default {@link ObjectMapper}
	 *            from this class will be returned.
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newObjectMapper(final boolean newMapper)
	{
		if (newMapper)
		{
			return new ObjectMapper();
		}
		return MAPPER;
	}

	/**
	 * Factory method for create a new {@link ObjectMapper} with the given features
	 *
	 * @param features
	 *            the features for the new {@link ObjectMapper}
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newObjectMapper(Map<JsonParser.Feature, Boolean> features)
	{
		ObjectMapper objectMapper = newObjectMapper(true);
		features.entrySet()
			.forEach(entry -> objectMapper.configure(entry.getKey(), entry.getValue()));
		return objectMapper;
	}

}
