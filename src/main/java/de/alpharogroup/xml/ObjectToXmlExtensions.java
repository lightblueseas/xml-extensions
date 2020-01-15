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

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.xstream.XStream;

import de.alpharogroup.xml.factory.XStreamFactory;
import de.alpharogroup.xml.factory.XmlMapperFactory;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link ObjectToXmlExtensions}.
 */
@UtilityClass
public final class ObjectToXmlExtensions
{

	/**
	 * Creates from the given Object an xml string.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param objectToXML
	 *            the object to xml
	 * @return the xml string
	 */
	public static <T> String toXmlWithXStream(final T objectToXML)
	{
		return toXmlWithXStream(null, objectToXML);
	}

	/**
	 * Creates from the given Object an xml string. The given map hold the aliases. For more
	 * information with aliasing see documation of xstream.
	 *
	 * @param <T>
	 *            the generic type of the object that will be transformed to xml
	 * @param objectToXML
	 *            the object to xml
	 * @param aliases
	 *            the aliases
	 * @return the xml string
	 */
	public static <T> String toXmlWithXStream(final T objectToXML,
		final Map<String, Class<?>> aliases)
	{
		return toXmlWithXStream(null, objectToXML, aliases);
	}

	/**
	 * Creates from the given Object an xml string.
	 *
	 * @param <T>
	 *            the generic type of the object that will be transformed to xml
	 * @param xstream
	 *            the xstream object.
	 * @param objectToXML
	 *            the object to xml
	 * @return the xml string
	 */
	public static <T> String toXmlWithXStream(final XStream xstream, final T objectToXML)
	{
		return toXmlWithXStream(xstream, objectToXML, null);
	}

	/**
	 * Creates from the given Object an xml string. The given map hold the aliases. For more
	 * information with aliasing see documation of xstream.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xstream
	 *            the xstream
	 * @param objectToXML
	 *            the object to xml
	 * @param aliases
	 *            the aliases
	 * @return the xml string
	 */
	public static <T> String toXmlWithXStream(XStream xstream, final T objectToXML,
		final Map<String, Class<?>> aliases)
	{
		xstream = XStreamFactory.initializeXStream(xstream, aliases);
		return xstream.toXML(objectToXML);
	}

	/**
	 * Creates from the given Object an xml string.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param objectToXML
	 *            the object to xml
	 * @return the xml string
	 */
	public static <T> String toXmlWithJackson(final @NonNull T objectToXML)
		throws JsonProcessingException
	{
		ObjectMapper xmlMapper = XmlMapperFactory.newXmlMapper();
		return xmlMapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(objectToXML);
	}

}
