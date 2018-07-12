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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;

import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.lang.ClassExtensions;
import lombok.experimental.UtilityClass;

/**
 * The class {@link XmlExtensions}.
 */
@UtilityClass
public final class XmlExtensions
{

	/**
	 * Gets the input source from the given xml string.
	 *
	 * @param xmlString
	 *            the xml as string object
	 * @return the input source
	 */
	public static InputSource getInputSource(final String xmlString)
	{
		return new InputSource(new StringReader(xmlString));
	}

	/**
	 * Load from the given file name that should represent an xml file and transform it to the
	 * generic type object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param xmlFile
	 *            the xml file
	 * @return the object from the given xml file.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T loadObject(final File xmlFile) throws IOException
	{
		final InputStream is = FileUtils.openInputStream(xmlFile);
		return loadObject(is);
	}

	/**
	 * Load from the given input stream that should represent an xml file and transform it to the
	 * generic type object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param is
	 *            the input stream
	 * @return the object from the given input stream.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static <T> T loadObject(final InputStream is) throws IOException
	{
		final String xmlString = ReadFileExtensions.inputStream2String(is);
		final T object = XmlToObjectExtensions.toObjectWithXStream(xmlString);
		return object;
	}

	/**
	 * Load from the given file name that should represent an xml file and transform it to the
	 * generic type object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param xmlFileName
	 *            the xml file name
	 * @return the object from the given xml file.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T loadObject(final String xmlFileName) throws IOException
	{
		final InputStream is = ClassExtensions.getResourceAsStream(xmlFileName);
		return loadObject(is);
	}

	/**
	 * Creates a tag from the given string values. Can be used for creating html or xml tags.
	 *
	 * @param tagname
	 *            the tag name
	 * @param value
	 *            the value from the tag
	 * @param attributes
	 *            a map with the attributes
	 * @return the string
	 */
	public static String newTag(final String tagname, final String value,
		final Map<String, String> attributes)
	{
		final StringBuilder xmlTag = new StringBuilder();
		xmlTag.append("<").append(tagname);
		if (attributes != null && !attributes.isEmpty())
		{
			xmlTag.append(" ");
			int count = 1;
			for (final Map.Entry<String, String> attributte : attributes.entrySet())
			{
				xmlTag.append(attributte.getKey());
				xmlTag.append("=");
				xmlTag.append("\"").append(attributte.getValue()).append("\"");
				if (count != attributes.size())
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
	 * @param xmlString
	 *            the xml as string object
	 * @return the json string.
	 * @deprecated use instead the same name method in class {@code XmlToJsonExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static String toJson(final String xmlString)
	{
		return XmlToJsonExtensions.toJson(xmlString);
	}

	/**
	 * Creates from the given xml string a json string.
	 *
	 * @param xmlString
	 *            the xml as string object
	 * @param aliases
	 *            the aliases
	 * @return the json string.
	 * @deprecated use instead the same name method in class {@code XmlToJsonExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static String toJson(final String xmlString, final Map<String, Class<?>> aliases)
	{
		return XmlToJsonExtensions.toJson(xmlString, aliases);
	}

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param xmlString
	 *            the xml string to transform to an java object.
	 * @return the xml string
	 * @deprecated use instead the same name method in class {@code XmlToObjectExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> T toObjectWithXMLDecoder(final String xmlString)
	{
		return XmlToObjectExtensions.toObjectWithXMLDecoder(xmlString);
	}

	/**
	 * Creates from the given xml string an java object.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param xmlString
	 *            the xml as string object
	 * @return the xml string
	 * @deprecated use instead the same name method in class {@code XmlToObjectExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> T toObjectWithXStream(final String xmlString)
	{
		return XmlToObjectExtensions.toObjectWithXStream(xmlString);
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
	 * @deprecated use instead the same name method in class {@code XmlToObjectExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> T toObjectWithXStream(final String xmlString,
		final Map<String, Class<?>> aliases)
	{
		return XmlToObjectExtensions.toObjectWithXStream(xmlString, aliases);
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
	 * @deprecated use instead the same name method in class {@code XmlToObjectExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> T toObjectWithXStream(final XStream xstream, final String xmlString)
	{
		return XmlToObjectExtensions.toObjectWithXStream(xstream, xmlString);
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
	 * @deprecated use instead the same name method in class {@code XmlToObjectExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> T toObjectWithXStream(XStream xstream, final String xmlString,
		final Map<String, Class<?>> aliases)
	{
		return XmlToObjectExtensions.toObjectWithXStream(xstream, xmlString, aliases);
	}

	/**
	 * Creates from the given Object an xml string.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param obj
	 *            the obj to transform to an xml string.
	 * @return the xml string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 * @deprecated use instead the same name method in class {@code ObjectToXmlExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> String toXmlWithXMLEncoder(final T obj) throws IOException
	{
		return ObjectToXmlExtensions.toXmlWithXMLEncoder(obj);
	}

	/**
	 * Creates from the given Object an xml string.
	 *
	 * @param <T>
	 *            the generic type of the return type
	 * @param objectToXML
	 *            the object to xml
	 * @return the xml string
	 * @deprecated use instead the same name method in class {@code ObjectToXmlExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> String toXmlWithXStream(final T objectToXML)
	{
		return ObjectToXmlExtensions.toXmlWithXStream(objectToXML);
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
	 * @deprecated use instead the same name method in class {@code ObjectToXmlExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> String toXmlWithXStream(final T objectToXML,
		final Map<String, Class<?>> aliases)
	{
		return ObjectToXmlExtensions.toXmlWithXStream(objectToXML, aliases);
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
	 * @deprecated use instead the same name method in class {@code ObjectToXmlExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> String toXmlWithXStream(final XStream xstream, final T objectToXML)
	{
		return ObjectToXmlExtensions.toXmlWithXStream(xstream, objectToXML);
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
	 * @deprecated use instead the same name method in class {@code ObjectToXmlExtensions} <br>
	 *             <br>
	 *             Note: will be removed on next minor release
	 */
	@Deprecated
	public static <T> String toXmlWithXStream(XStream xstream, final T objectToXML,
		final Map<String, Class<?>> aliases)
	{
		return ObjectToXmlExtensions.toXmlWithXStream(xstream, objectToXML, aliases);
	}

}
