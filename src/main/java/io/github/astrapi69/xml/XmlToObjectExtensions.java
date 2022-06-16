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

import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.astrapi69.xml.factory.XmlMapperFactory;

/**
 * The class {@link XmlToObjectExtensions}.
 */
public final class XmlToObjectExtensions
{

	private XmlToObjectExtensions()
	{
	}

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlString
	 *            the xml
	 * @param clazz
	 *            the class of the generic type
	 * @return the object
	 * @throws JsonProcessingException
	 *             is thrown when processing json content that are not pure I/O problems
	 */
	public static <T> T toObjectWithJackson(final String xmlString, final Class<T> clazz)
		throws JsonProcessingException
	{
		Objects.requireNonNull(xmlString);
		Objects.requireNonNull(clazz);
		return XmlMapperFactory.newXmlMapper().readValue(xmlString, clazz);
	}

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlString
	 *            the xml
	 * @param typeReference
	 *            the type reference
	 * @return the object
	 * @throws JsonProcessingException
	 *             is thrown when processing json content that are not pure I/O problems
	 */
	public static <T> T toObjectWithJackson(final String xmlString,
		final TypeReference<T> typeReference) throws JsonProcessingException
	{
		Objects.requireNonNull(xmlString);
		Objects.requireNonNull(typeReference);
		return toObjectWithJackson(xmlString, typeReference, XmlMapperFactory.newXmlMapper());
	}

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlString
	 *            the xml
	 * @param typeReference
	 *            the type reference
	 * @param xmlMapper
	 *            the xml mapper
	 * @return the object
	 * @throws JsonProcessingException
	 *             is thrown when processing json content that are not pure I/O problems
	 */
	public static <T> T toObjectWithJackson(final String xmlString,
		final TypeReference<T> typeReference, final ObjectMapper xmlMapper)
		throws JsonProcessingException
	{
		Objects.requireNonNull(xmlString);
		Objects.requireNonNull(typeReference);
		Objects.requireNonNull(xmlMapper);
		return xmlMapper.readValue(xmlString, typeReference);
	}

}
