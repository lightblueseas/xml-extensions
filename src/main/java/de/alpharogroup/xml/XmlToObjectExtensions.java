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
package de.alpharogroup.xml;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;

import de.alpharogroup.xml.factory.XmlMapperFactory;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link XmlToObjectExtensions}.
 */
@UtilityClass
public final class XmlToObjectExtensions
{

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param xmlString
	 *            the xml string to transform to an java object.
	 * @return the xml string
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObjectWithXMLDecoder(final String xmlString)
	{

		XMLDecoder dec = null;
		T obj = null;
		try
		{
			final InputStream is = new ByteArrayInputStream(xmlString.getBytes());
			dec = new XMLDecoder(is);

			obj = (T)dec.readObject();

		}
		finally
		{
			if (dec != null)
			{
				dec.close();
			}
		}
		return obj;
	}

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlString
	 *            the xml as string object
	 * @return the xml string
	 */
	public static <T> T toObjectWithXStream(final String xmlString)
	{
		return toObjectWithXStream(null, xmlString);
	}

	/**
	 * Creates from the given xml string an Object. The given map hold the aliases. For more
	 * information with aliasing see documation of xstream.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlString
	 *            the xml as string object
	 * @param aliases
	 *            the aliases
	 * @return the created object from the given xml string.
	 */
	public static <T> T toObjectWithXStream(final String xmlString,
		final Map<String, Class<?>> aliases)
	{
		return toObjectWithXStream(null, xmlString, aliases);
	}

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xstream
	 *            the xstream object.
	 * @param xmlString
	 *            the xml as string object
	 * @return the object
	 */
	public static <T> T toObjectWithXStream(final XStream xstream, final String xmlString)
	{
		return toObjectWithXStream(xstream, xmlString, null);
	}

	/**
	 * Creates from the given xml string an java object. The given map hold the aliases. For more
	 * information with aliasing see documation of xstream.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xstream
	 *            the xstream object.
	 * @param xmlString
	 *            the xml
	 * @param aliases
	 *            the aliases
	 * @return the object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObjectWithXStream(XStream xstream, final String xmlString,
		final Map<String, Class<?>> aliases)
	{
		xstream = XmlToObjectExtensions.initializeXStream(xstream, aliases);
		return (T)xstream.fromXML(xmlString);
	}

	/**
	 * Initialize the given {@link XStream} object with the given aliases
	 *
	 * @param xstream
	 *            the {@link XStream} object
	 * @param aliases
	 *            the aliases
	 * @return the initialized {@link XStream} object
	 */
	public static XStream initializeXStream(XStream xstream, Map<String, Class<?>> aliases)
	{
		if (xstream == null)
		{
			xstream = new XStream();
		}
		if (aliases != null)
		{
			for (final Map.Entry<String, Class<?>> alias : aliases.entrySet())
			{
				xstream.alias(alias.getKey(), alias.getValue());
			}
		}
		return xstream;
	}

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlString
	 *            the xml
	 * @param clazz
	 *            the clazz of the generic type
	 * @return the object
	 */
	public static <T> T toObjectWithJackson(final @NonNull String xmlString,
		final @NonNull Class<T> clazz) throws JsonProcessingException
	{
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
	 */
	public static <T> T toObjectWithJackson(final @NonNull String xmlString,
		final @NonNull TypeReference<T> typeReference) throws JsonProcessingException
	{
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
	 * @return the object
	 */
	public static <T> T toObjectWithJackson(final @NonNull String xmlString,
		final @NonNull TypeReference<T> typeReference, final @NonNull ObjectMapper xmlMapper)
		throws JsonProcessingException
	{
		return xmlMapper.readValue(xmlString, typeReference);
	}

}
