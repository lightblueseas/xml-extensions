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
package io.github.astrapi69.xsl.transform;

import java.io.File;
import java.io.OutputStream;
import java.util.Objects;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import io.github.astrapi69.xml.transform.TransformerFactoryInitializer;

/**
 * The class {@link XsltTransformerExtensions}
 */
public final class XsltTransformerExtensions
{

	/** The Constant TRANSFORMER_FACTORY. */
	private static final TransformerFactory TRANSFORMER_FACTORY = TransformerFactory.newInstance();

	private XsltTransformerExtensions()
	{
	}

	/**
	 * Transform.
	 *
	 * @param xmlFile
	 *            the xml file
	 * @param xsltFile
	 *            the xslt file
	 * @param outputStream
	 *            the output stream
	 * @throws TransformerConfigurationException
	 *             is thrown if there are errors when parsing the <code>Source</code> or it is not
	 *             possible to create a <code>Transformer</code> instance.
	 * @throws TransformerException
	 *             is thrown if an unrecoverable error occurs during the course of the
	 *             transformation.
	 */
	public static void transform(final File xmlFile, final File xsltFile,
		final OutputStream outputStream)
		throws TransformerConfigurationException, TransformerException
	{
		Objects.requireNonNull(xmlFile);
		Objects.requireNonNull(xsltFile);
		final Source xmlSource = new StreamSource(xmlFile);
		final Source xsltSource = new StreamSource(xsltFile);
		transform(xmlSource, xsltSource, outputStream);
	}

	/**
	 * Transform.
	 *
	 * @param xmlSource
	 *            the xml source
	 * @param xsltSource
	 *            the xslt source
	 * @param outputStream
	 *            the output stream
	 * @throws TransformerConfigurationException
	 *             is thrown if there are errors when parsing the <code>Source</code> or it is not
	 *             possible to create a <code>Transformer</code> instance.
	 * @throws TransformerException
	 *             is thrown if an unrecoverable error occurs during the course of the
	 *             transformation.
	 */
	public static void transform(final Source xmlSource, final Source xsltSource,
		final OutputStream outputStream)
		throws TransformerConfigurationException, TransformerException
	{
		final Transformer transformer = TransformerFactoryInitializer.newTransformer(xsltSource);
		transformer.transform(xmlSource, new StreamResult(outputStream));
	}

	/**
	 * Transform.
	 *
	 * @param xmlInputFile
	 *            the xml input file
	 * @param xsltInputFile
	 *            the xslt input file
	 * @param outputStream
	 *            the output stream
	 * @throws TransformerConfigurationException
	 *             is thrown if there are errors when parsing the <code>Source</code> or it is not
	 *             possible to create a <code>Transformer</code> instance.
	 * @throws TransformerException
	 *             is thrown if an unrecoverable error occurs during the course of the
	 *             transformation.
	 */
	public static void transform(final String xmlInputFile, final String xsltInputFile,
		final OutputStream outputStream)
		throws TransformerConfigurationException, TransformerException
	{
		final File xmlFile = new File(xmlInputFile);
		final File xsltFile = new File(xsltInputFile);
		transform(xmlFile, xsltFile, outputStream);
	}

}
