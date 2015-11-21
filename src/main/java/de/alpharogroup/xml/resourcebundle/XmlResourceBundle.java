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
package de.alpharogroup.xml.resourcebundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * The Class XmlResourceBundle offer an alternative to the {@link java.util.ResourceBundle} classes
 * since they do not support non-ANSI character sets. The intended use for this code is to enable
 * multi-language support.
 * 
 * @see java.util.ResourceBundle
 */
class XmlResourceBundle extends ResourceBundle
{

	/** The properties. */
	private final Properties properties;

	/**
	 * Instantiates a new XmlResourceBundle.
	 * 
	 * @param inputStream
	 *            the stream
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	XmlResourceBundle(final InputStream inputStream) throws IOException
	{
		properties = new Properties();
		properties.loadFromXML(inputStream);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Enumeration<String> getKeys()
	{
		return Collections.enumeration(properties.stringPropertyNames());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object handleGetObject(final String key)
	{
		return properties.getProperty(key);
	}
}