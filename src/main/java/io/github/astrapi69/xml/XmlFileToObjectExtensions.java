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
package io.github.astrapi69.xml;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

/**
 * The class {@link XmlFileToObjectExtensions} provides algorithms for transform a given xml file to a
 * java object
 */
public final class XmlFileToObjectExtensions
{

	private XmlFileToObjectExtensions()
	{
	}

	/**
	 * Creates from the given xml file a java object
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlFile
	 *            the xml file
	 * @param clazz
	 *            the class of the generic type
	 * @return the created object from the given xml file
	 * @throws JsonProcessingException
	 *             is thrown when processing json content that are not pure I/O problems
	 */
	public static <T> T toObjectWithJackson(final File xmlFile, final Class<T> clazz)
		throws JsonProcessingException
	{
		Objects.requireNonNull(xmlFile);
		Objects.requireNonNull(clazz);
		final String xmlString = RuntimeExceptionDecorator
			.decorate(() -> ReadFileExtensions.readFromFile(xmlFile));
		return XmlToObjectExtensions.toObjectWithJackson(xmlString, clazz);
	}

	/**
	 * Creates from the given xml file a java object
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlFile
	 *            the xml file
	 * @param typeReference
	 *            the type reference
	 * @return the created object from the given xml file
	 * @throws JsonProcessingException
	 *             is thrown when processing json content that are not pure I/O problems
	 */
	public static <T> T toObjectWithJackson(final File xmlFile,
		final TypeReference<T> typeReference) throws JsonProcessingException
	{
		Objects.requireNonNull(xmlFile);
		Objects.requireNonNull(typeReference);
		final String xmlString = RuntimeExceptionDecorator
			.decorate(() -> ReadFileExtensions.readFromFile(xmlFile));
		return XmlToObjectExtensions.toObjectWithJackson(xmlString, typeReference);
	}

	/**
	 * Creates from the given xml file a java object
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlFile
	 *            the xml file
	 * @param typeReference
	 *            the type reference
	 * @param xmlMapper
	 *            the xml mapper
	 * @return the created object from the given xml file
	 * @throws JsonProcessingException
	 *             is thrown when processing json content that are not pure I/O problems
	 */
	public static <T> T toObjectWithJackson(final File xmlFile,
		final TypeReference<T> typeReference, final ObjectMapper xmlMapper)
		throws JsonProcessingException
	{
		Objects.requireNonNull(xmlFile);
		Objects.requireNonNull(typeReference);
		Objects.requireNonNull(xmlMapper);
		final String xmlString = RuntimeExceptionDecorator
			.decorate(() -> ReadFileExtensions.readFromFile(xmlFile));
		return XmlToObjectExtensions.toObjectWithJackson(xmlString, typeReference, xmlMapper);
	}

	/**
	 * Creates from the given xml file a java object
	 *
	 * @param xmlFile
	 *            the xml file
	 * @param <T>
	 *            the generic type
	 * @return the created object from the given xml file
	 */
	public static <T> T toObjectWithXMLDecoder(final File xmlFile)
	{
		Objects.requireNonNull(xmlFile);
		final String xmlString = RuntimeExceptionDecorator
			.decorate(() -> ReadFileExtensions.readFromFile(xmlFile));
		return XmlToObjectExtensions.toObjectWithXMLDecoder(xmlString);
	}

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlFile
	 *            the xml file
	 * @return the created object from the given xml file
	 */
	public static <T> T toObjectWithXStream(final File xmlFile)
	{
		Objects.requireNonNull(xmlFile);
		return XmlToObjectExtensions.toObjectWithXStream(null, RuntimeExceptionDecorator
				.decorate(() -> ReadFileExtensions.readFromFile(xmlFile)));
	}

	/**
	 * Creates from the given xml file a java object. The given map hold the aliases. For more
	 * information with aliasing see documation of xstream.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlFile
	 *            the xml file
	 * @param aliases
	 *            the aliases
	 * @return the created object from the given xml file
	 */
	public static <T> T toObjectWithXStream(final File xmlFile, final Map<String, Class<?>> aliases)
	{
		Objects.requireNonNull(xmlFile);
		return XmlToObjectExtensions.toObjectWithXStream(null, RuntimeExceptionDecorator
				.decorate(() -> ReadFileExtensions.readFromFile(xmlFile)), aliases);
	}

	/**
	 * Creates from the given xml file a java object
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xstream
	 *            the xstream object.
	 * @param xmlFile
	 *            the xml file
	 * @return the created object from the given xml file
	 */
	public static <T> T toObjectWithXStream(final XStream xstream, final File xmlFile)
	{
		Objects.requireNonNull(xmlFile);
		return XmlToObjectExtensions.toObjectWithXStream(xstream, RuntimeExceptionDecorator
				.decorate(() -> ReadFileExtensions.readFromFile(xmlFile)), null);
	}

	/**
	 * Creates from the given xml file a java object. The given map hold the aliases. For more
	 * information with aliasing see documentation of xstream.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xstream
	 *            the xstream object.
	 * @param xmlFile
	 *            the xml file
	 * @param aliases
	 *            the aliases
	 * @return the created object from the given xml file
	 */
	public static <T> T toObjectWithXStream(XStream xstream, final File xmlFile,
											final Map<String, Class<?>> aliases)
	{
		Objects.requireNonNull(xmlFile);
		return XmlToObjectExtensions.toObjectWithXStream(xstream, RuntimeExceptionDecorator
				.decorate(() -> ReadFileExtensions.readFromFile(xmlFile)), aliases);
	}

	/**
	 * Creates from the given xml file a java object. The given map hold the aliases. For more
	 * information with aliasing see documentation of xstream.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xstream
	 *            the xstream object.
	 * @param xmlFile
	 *            the xml file
	 * @param charsetName
	 *            the encoding name of the charset form the given xml file
	 * @param aliases
	 *            the aliases
	 * @return the created object from the given xml file
	 */
	public static <T> T toObjectWithXStream(XStream xstream, final File xmlFile, final String charsetName,
											final Map<String, Class<?>> aliases)
	{
		Objects.requireNonNull(xmlFile);
		return XmlToObjectExtensions.toObjectWithXStream(xstream, RuntimeExceptionDecorator
				.decorate(() -> ReadFileExtensions.readFromFile(xmlFile, Charset.forName(charsetName))), aliases);
	}

}
