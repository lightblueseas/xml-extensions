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

import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import com.thoughtworks.xstream.XStream;

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
	 * @param obj
	 *            the obj to transform to an xml string.
	 * @return the xml string
	 * @deprecated use instead the method with the xstream<br>
	 * Note: will be removed in next minor release
	 */
	@Deprecated
	public static <T> String toXmlWithXMLEncoder(final T obj)
	{
		XMLEncoder enc = null;
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		enc = new XMLEncoder(baos);
		enc.writeObject(obj);
		enc.close();
		return baos.toString();
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
		final String xml = xstream.toXML(objectToXML);
		return xml;
	}

}
