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
package io.github.astrapi69.xml.xpath;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import io.github.astrapi69.xsd.schema.DocumentBuilderFactoryInitializer;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import io.github.astrapi69.xsd.schema.ValidatorExtensions;

/**
 * The class {@link XPathExtensions}.
 */
public final class XPathExtensions
{

	private XPathExtensions()
	{
	}

	/**
	 * Gets the node list from the given xml file and the given xpath expression.
	 *
	 * @param xml
	 *            the xml
	 * @param xpathExpression
	 *            the xpath expression
	 * @return the node list
	 * @throws XPathExpressionException
	 *             the x path expression exception
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             is thrown if a sax parse error occurs
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static NodeList getNodeList(final File xml, final String xpathExpression)
		throws XPathExpressionException, ParserConfigurationException, SAXException, IOException
	{
		final Document document = DocumentBuilderFactoryInitializer.newDocument(xml);
		final XPath xpath = XPathFactory.newInstance().newXPath();
		final XPathExpression xPathExpression = xpath.compile(xpathExpression);

		final Object result = xPathExpression.evaluate(document, XPathConstants.NODESET);
		return (NodeList)result;
	}

	/**
	 * Gets the node list from the given xml file and the given xpath expression.
	 *
	 * @param xml
	 *            the xml file as string.
	 * @param xpathExpression
	 *            the xpath expression as string.
	 * @return the node list
	 * @throws XPathExpressionException
	 *             the x path expression exception
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             is thrown if a sax parse error occurs
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static NodeList getNodeList(final String xml, final String xpathExpression)
		throws XPathExpressionException, ParserConfigurationException, SAXException, IOException
	{
		final Document document = DocumentBuilderFactoryInitializer.newDocument(xml);
		final XPath xpath = XPathFactory.newInstance().newXPath();
		final XPathExpression xPathExpression = xpath.compile(xpathExpression);

		final Object result = xPathExpression.evaluate(document, XPathConstants.NODESET);
		return (NodeList)result;
	}
}
