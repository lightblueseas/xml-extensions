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
import java.util.Objects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The class {@link JsonFileToObjectExtensions} converts json strings to java object and java
 * collections.
 *
 * @deprecated use instead the same name class in new project json-extensions
 * <br><br>Note: will be removed in next minor release
 */
public final class JsonFileToObjectExtensions
{
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
	public static <T> T toObject(final File jsonFile, final Class<T> clazz,
		final ObjectMapper mapper) throws IOException
	{
		Objects.requireNonNull(jsonFile);
		Objects.requireNonNull(clazz);
		Objects.requireNonNull(mapper);
		return mapper.readValue(jsonFile, clazz);
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
	public static <T> T toObject(final File jsonFile, final TypeReference<T> typeReference,
		final ObjectMapper mapper) throws IOException
	{
		Objects.requireNonNull(jsonFile);
		Objects.requireNonNull(typeReference);
		Objects.requireNonNull(mapper);
		return mapper.readValue(jsonFile, typeReference);
	}

	private JsonFileToObjectExtensions()
	{
	}

}
