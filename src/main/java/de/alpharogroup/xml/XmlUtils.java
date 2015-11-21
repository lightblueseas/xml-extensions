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
package de.alpharogroup.xml;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Map;

import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

/**
 * The Class XmlUtils.
 */
public final class XmlUtils
{

	/**
	 * Gets the input source from the given xml string.
	 * 
	 * @param xmlString
	 *            the xml string
	 * @return the input source
	 */
	public static InputSource getInputSource(final String xmlString)
	{
		return new InputSource(new StringReader(xmlString));
	}

	/**
	 * Creates a tag from the given string values. Can be used for creating html or xml tags.
	 * 
	 * @param tagname
	 *            the tagname
	 * @param value
	 *            the value from the tag.
	 * @param attributtes
	 *            a map with the attributtes
	 * @return the string
	 */
	public static String newTag(final String tagname, final String value,
		final Map<String, String> attributtes)
	{
		final StringBuilder xmlTag = new StringBuilder();
		xmlTag.append("<").append(tagname);
		if (attributtes != null && !attributtes.isEmpty())
		{
			xmlTag.append(" ");
			int count = 1;
			for (final Map.Entry<String, String> attributte : attributtes.entrySet())
			{
				xmlTag.append(attributte.getKey());
				xmlTag.append("=");
				xmlTag.append("\"").append(attributte.getValue()).append("\"");
				if (count != attributtes.size())
				{
					xmlTag.append(" ");
				}
				count++;
			}
		}
		xmlTag.append(">");
		xmlTag.append(value);
		xmlTag.append("</").append(tagname).append(">");
		return xmlTag.toString();
	}

	/**
	 * Creates from the given xml string a json string.
	 * 
	 * @param xml
	 *            the xml string.
	 * @return the json string.
	 */
	public static String toJson(final String xml)
	{
		return toJson(xml, null);
	}

	/**
	 * Creates from the given xml string a json string.
	 * 
	 * @param xml
	 *            the xml string.
	 * @param aliases
	 *            the aliases
	 * @return the json string.
	 */
	public static String toJson(final String xml, final Map<String, Class<?>> aliases)
	{
		final Object object = toObjectWithXStream(xml);
		final XStream xstream = new XStream(new JettisonMappedXmlDriver());
		if (aliases != null)
		{
			for (final Map.Entry<String, Class<?>> alias : aliases.entrySet())
			{
				xstream.alias(alias.getKey(), alias.getValue());
			}
		}
		final String json = xstream.toXML(object);
		return json;
	}

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
	 * @param xml
	 *            the xml
	 * @return the xml string
	 */
	public static <T> T toObjectWithXStream(final String xml)
	{
		return toObjectWithXStream(null, xml);
	}

	/**
	 * Creates from the given xml string an Object. The given map hold the aliases. For more
	 * information with aliasing see documation of xstream.
	 * 
	 * @param <T>
	 *            the generic type of the return type
	 * @param xml
	 *            the xml string
	 * @param aliases
	 *            the aliases
	 * @return the created object from the given xml string.
	 */
	public static <T> T toObjectWithXStream(final String xml, final Map<String, Class<?>> aliases)
	{
		return toObjectWithXStream(null, xml, aliases);
	}

	/**
	 * Creates from the given xml string an java object.
	 * 
	 * @param <T>
	 *            the generic type of the return type
	 * @param xstream
	 *            the xstream object.
	 * @param xml
	 *            the xml
	 * @return the object
	 */
	public static <T> T toObjectWithXStream(final XStream xstream, final String xml)
	{
		return toObjectWithXStream(xstream, xml, null);
	}

	/**
	 * Creates from the given xml string an java object. The given map hold the aliases. For more
	 * information with aliasing see documation of xstream.
	 * 
	 * @param <T>
	 *            the generic type of the return type
	 * @param xstream
	 *            the xstream object.
	 * @param xml
	 *            the xml
	 * @param aliases
	 *            the aliases
	 * @return the object
	 */
	@SuppressWarnings("unchecked")
	public static <T> T toObjectWithXStream(XStream xstream, final String xml,
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
		return (T)xstream.fromXML(xml);
	}

	/**
	 * Creates from the given Object an xml string.
	 * 
	 * @param <T>
	 *            the generic type of the return type
	 * @param obj
	 *            the obj to transform to an xml string.
	 * @return the xml string
	 */
	public static <T> String toXmlWithXMLEncoder(final T obj)
	{
		XMLEncoder enc = null;
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try
		{
			enc = new XMLEncoder(baos);
			enc.writeObject(obj);
			enc.close();
			enc = null;
		}
		finally
		{
			if (enc != null)
			{
				enc.close();
			}
		}
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

	/**
	 * Private constructor.
	 */
	private XmlUtils()
	{
	}
}
