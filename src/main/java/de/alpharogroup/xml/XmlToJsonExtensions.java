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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;

import lombok.experimental.UtilityClass;

/**
 * The class {@link XmlToJsonExtensions}.
 */
@UtilityClass
public final class XmlToJsonExtensions
{

	/**
	 * Creates from the given xml string a json string.
	 *
	 * @param xmlString
	 *            the xml as string object
	 * @return the json string.
	 */
	public static String toJson(final String xmlString)
	{
		return toJson(xmlString, null);
	}

	/**
	 * Creates from the given xml string a json string.
	 *
	 * @param xmlString
	 *            the xml as string object
	 * @param aliases
	 *            the aliases
	 * @return the json string.
	 */
	public static String toJson(final String xmlString, final Map<String, Class<?>> aliases)
	{
		final Object object = XmlToObjectExtensions.toObjectWithXStream(xmlString);
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

}
