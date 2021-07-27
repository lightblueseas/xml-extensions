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
package io.github.astrapi69.xml.sax.handler;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.xml.sax.SAXException;

/**
 * The class {@link TemplateHandler}
 */
public class TemplateHandler extends WriterHandler
{

	/** The data. */
	private final Map<String, String> data;

	/**
	 * Instantiates a new {@link TemplateHandler} object with the given data map and the given
	 * {@link Writer}
	 *
	 * @param data
	 *            the data
	 * @param writer
	 *            the writer
	 */
	public TemplateHandler(final Map<String, String> data, final StringWriter writer)
	{
		super(writer);
		this.data = data;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void write(final String s) throws SAXException
	{
		try
		{
			if (s.startsWith("$"))
			{
				final String newValue = data.get(s.substring(1, s.length()));

				getWriter().append(newValue);

			}
			else
			{
				getWriter().append(s);
			}
		}
		catch (final IOException e)
		{
			throw new SAXException("I/O error", e);
		}
	}

}
