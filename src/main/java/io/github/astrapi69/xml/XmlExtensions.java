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
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.xml.sax.InputSource;

import io.github.astrapi69.lang.ClassExtensions;
import io.github.astrapi69.read.ReadFileExtensions;

/**
 * The class {@link XmlExtensions}.
 */
public final class XmlExtensions
{

	private XmlExtensions()
	{
	}

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
	 * Load from the given file name that should represent an xml file and transform it to the
	 * generic type object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param xmlFile
	 *            the xml file
	 * @param clazz
	 *            the class of the generic type
	 * @return the object from the given xml file.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T loadObject(final File xmlFile, final Class<T> clazz) throws IOException
	{
		Objects.requireNonNull(clazz);
		final InputStream is = FileUtils.openInputStream(xmlFile);
		return loadObject(is, clazz);
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
	 * Load from the given input stream that should represent an xml file and transform it to the
	 * generic type object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param is
	 *            the input stream
	 * @param clazz
	 *            the clazz of the generic type
	 * @return the object from the given input stream.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private static <T> T loadObject(final InputStream is, final Class<T> clazz) throws IOException
	{
		Objects.requireNonNull(is);
		Objects.requireNonNull(clazz);
		final String xmlString = ReadFileExtensions.inputStream2String(is);
		final T object = XmlToObjectExtensions.toObjectWithJackson(xmlString, clazz);
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
	 * Load from the given file name that should represent an xml file and transform it to the
	 * generic type object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param xmlFileName
	 *            the xml file name
	 * @param clazz
	 *            the class of the generic type
	 * @return the object from the given xml file.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> T loadObject(final String xmlFileName, final Class<T> clazz)
		throws IOException
	{
		Objects.requireNonNull(clazz);
		final InputStream is = ClassExtensions.getResourceAsStream(xmlFileName);
		return loadObject(is, clazz);
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

}
