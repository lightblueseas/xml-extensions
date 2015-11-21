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
package de.alpharogroup.xml.xpath;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * The Class XPathUtils.
 */
public class XPathUtils
{

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
	 *             the sAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static NodeList getNodeList(final File xml, final String xpathExpression)
		throws XPathExpressionException, ParserConfigurationException, SAXException, IOException
	{
		final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		final DocumentBuilder builder = domFactory.newDocumentBuilder();
		final Document doc = builder.parse(xml);
		final XPath xpath = XPathFactory.newInstance().newXPath();
		final XPathExpression expr = xpath.compile(xpathExpression);

		final Object result = expr.evaluate(doc, XPathConstants.NODESET);
		final NodeList nodes = (NodeList)result;
		return nodes;
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
	 *             the sAX exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static NodeList getNodeList(final String xml, final String xpathExpression)
		throws XPathExpressionException, ParserConfigurationException, SAXException, IOException
	{
		final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		final DocumentBuilder builder = domFactory.newDocumentBuilder();
		final Document doc = builder.parse(xml);
		final XPath xpath = XPathFactory.newInstance().newXPath();
		final XPathExpression expr = xpath.compile(xpathExpression);

		final Object result = expr.evaluate(doc, XPathConstants.NODESET);
		final NodeList nodes = (NodeList)result;
		return nodes;
	}
}
