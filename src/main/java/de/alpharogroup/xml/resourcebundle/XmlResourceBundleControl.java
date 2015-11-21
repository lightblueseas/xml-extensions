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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The Class XmlResourceBundleControl.
 */
public class XmlResourceBundleControl extends ResourceBundle.Control
{

	/** The Constant XML. */
	private static final String XML = "xml";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getFormats(final String baseName)
	{
		return Collections.unmodifiableList(Arrays.asList(XML));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ResourceBundle newBundle(final String baseName, final Locale locale,
		final String format, final ClassLoader loader, final boolean reload)
		throws IllegalAccessException, InstantiationException, IOException
	{

		if (baseName == null || locale == null || format == null || loader == null)
		{
			throw new NullPointerException();
		}
		ResourceBundle bundle = null;
		if (!format.equals(XML))
		{
			return null;
		}

		final String bundleName = toBundleName(baseName, locale);
		final String resourceName = toResourceName(bundleName, format);
		final URL url = loader.getResource(resourceName);
		if (url == null)
		{
			return null;
		}
		final URLConnection connection = url.openConnection();
		if (connection == null)
		{
			return null;
		}
		if (reload)
		{
			connection.setUseCaches(false);
		}
		final InputStream stream = connection.getInputStream();
		if (stream == null)
		{
			return null;
		}
		final BufferedInputStream bis = new BufferedInputStream(stream);
		bundle = new XmlResourceBundle(bis);
		bis.close();

		return bundle;
	}

}