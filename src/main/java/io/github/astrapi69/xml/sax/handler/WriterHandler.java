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
import java.io.Writer;
import java.util.Objects;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The abstract class {@link WriterHandler}.
 */
public abstract class WriterHandler extends DefaultHandler
{

	/** The writer. */
	private final Writer writer;
	/** The string builder. */
	private StringBuilder stringBuilder;

	/**
	 * Instantiates a new {@link WriterHandler} object with the given {@link Writer}
	 *
	 * @param writer
	 *            the writer
	 */
	public WriterHandler(final Writer writer)
	{
		Objects.requireNonNull(writer);
		this.writer = writer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void characters(final char[] buf, final int offset, final int len) throws SAXException
	{
		final String string = new String(buf, offset, len);

		if (stringBuilder == null)
		{
			stringBuilder = new StringBuilder(string);
		}
		else
		{
			stringBuilder.append(string);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endDocument() throws SAXException
	{
		insertNewLine();
		try
		{
			insertNewLine();
			writer.flush();
		}
		catch (final IOException e)
		{
			throw new SAXException("I/O error", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void endElement(final String namespaceURI, final String simpleName,
		final String qualifiedName) throws SAXException
	{
		writeToBuffer();

		String elementName = simpleName;

		if ("".equals(elementName))
		{
			elementName = qualifiedName;
		}

		write("</" + elementName + ">");
	}

	public Writer getWriter()
	{
		return writer;
	}

	/**
	 * Insert a new line to the writer
	 *
	 * @throws SAXException
	 *             any SAX exception, possibly wrapping another exception
	 */
	private void insertNewLine() throws SAXException
	{
		try
		{
			writer.write(System.getProperty("line.separator"));
		}
		catch (final IOException e)
		{
			throw new SAXException("I/O error", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startDocument() throws SAXException
	{
		write("<?xml version='1.0' encoding='UTF-8'?>");
		insertNewLine();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void startElement(final String namespaceURI, final String simpleName,
		final String qualifiedName, final Attributes attributes) throws SAXException
	{
		writeToBuffer();

		String elementName = simpleName;

		if ("".equals(elementName))
		{
			elementName = qualifiedName;
		}

		write("<" + elementName);

		if (attributes != null)
		{
			for (int i = 0; i < attributes.getLength(); i++)
			{
				String attributeName = attributes.getLocalName(i);

				if ("".equals(attributeName))
				{
					attributeName = attributes.getQName(i);
				}

				write(" ");
				write(attributeName + "=\"" + attributes.getValue(i) + "\"");
			}
		}

		write(">");
	}

	/**
	 * Write the given {@link String} object
	 *
	 * @param string
	 *            the string
	 * @throws SAXException
	 *             any SAX exception, possibly wrapping another exception
	 */
	protected abstract void write(final String string) throws SAXException;

	/**
	 * Write to buffer.
	 *
	 * @throws SAXException
	 *             any SAX exception, possibly wrapping another exception
	 */
	private void writeToBuffer() throws SAXException
	{
		if (stringBuilder == null)
		{
			return;
		}
		final String string = stringBuilder.toString().trim();
		write(string);
		stringBuilder = null;
	}
}
