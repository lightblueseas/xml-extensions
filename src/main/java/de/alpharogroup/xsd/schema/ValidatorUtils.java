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
package de.alpharogroup.xsd.schema;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/**
 * The Class ValidatorUtils can validate xml files.
 */
public class ValidatorUtils
{

	/** The Constant logger. */
	protected static final Logger logger = Logger.getLogger(ValidatorUtils.class);

	/** The Constant DOCUMENT_BUILDER_FACTORY_VALUE. */
	private static final String DOCUMENT_BUILDER_FACTORY_VALUE = "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl";

	/** The Constant DOCUMENT_BUILDER_FACTORY_KEY. */
	private static final String DOCUMENT_BUILDER_FACTORY_KEY = "javax.xml.parsers.DocumentBuilderFactory";

	/** The Constant SCHEMA_SOURCE_KEY. */
	private static final String SCHEMA_SOURCE_KEY = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	/** The Constant SCHEMA_LANGUAGE_KEY. */
	private static final String SCHEMA_LANGUAGE_KEY = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	/** The Constant HTTP_WWW_W3_ORG_2001_XML_SCHEMA. */
	private static final String HTTP_WWW_W3_ORG_2001_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	/**
	 * Gets the document builder factory.
	 *
	 * @param schema
	 *            the schema
	 * @return the document builder factory
	 */
	public static DocumentBuilderFactory getDocumentBuilderFactory(final String schema)
	{
		System.setProperty(DOCUMENT_BUILDER_FACTORY_KEY, DOCUMENT_BUILDER_FACTORY_VALUE);
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		factory.setAttribute(SCHEMA_LANGUAGE_KEY, HTTP_WWW_W3_ORG_2001_XML_SCHEMA);
		factory.setAttribute(SCHEMA_SOURCE_KEY, schema);
		return factory;
	}

	/**
	 * Gets the dOM source.
	 *
	 * @param xml
	 *            the xml
	 * @param errorHandler
	 *            the error handler
	 * @return the dOM source
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static DOMSource getDOMSource(final File xml, final ErrorHandler errorHandler)
		throws SAXException, ParserConfigurationException, IOException
	{
		return new DOMSource(parse(xml, errorHandler));
	}

	/**
	 * Gets the schema.
	 *
	 * @param xsd
	 *            the xsd
	 * @param errorHandler
	 *            the error handler
	 * @return the schema
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 */
	public static Schema getSchema(final File xsd, final ErrorHandler errorHandler)
		throws SAXException
	{
		// Create a new instance for an XSD-aware SchemaFactory
		final SchemaFactory schemaFactory = SchemaFactory
			.newInstance(HTTP_WWW_W3_ORG_2001_XML_SCHEMA);

		// Set the ErrorHandler implementation.
		schemaFactory.setErrorHandler(errorHandler);

		// get the custom xsd schema that describes
		// the required format for my XML files.
		return schemaFactory.newSchema(xsd);
	}

	/**
	 * Parses the.
	 *
	 * @param xml
	 *            the xml
	 * @param errorHandler
	 *            the error handler
	 * @return the document
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Document parse(final File xml, final ErrorHandler errorHandler)
		throws SAXException, ParserConfigurationException, IOException
	{
		final DocumentBuilderFactory factory = getDocumentBuilderFactory(xml.getName());
		final DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setErrorHandler(errorHandler);
		return builder.parse(xml);
	}

	/**
	 * Validate xml through xsd.
	 *
	 * @param xsd
	 *            the xsd
	 * @param xml
	 *            the xml
	 * @param errorHandler
	 *            the error handler
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void validateSchema(final File xsd, final File xml,
		final ErrorHandler errorHandler) throws SAXException, ParserConfigurationException,
		IOException
	{

		final Schema schemaXSD = getSchema(xsd, errorHandler);

		// Create a Validator capable of validating XML files according to my custom schema.
		final Validator validator = schemaXSD.newValidator();

		// parse the XML DOM tree againts the stricter XSD schema
		validator.validate(getDOMSource(xml, errorHandler));
	}


	/**
	 * Validate given xml schema.
	 *
	 * @param SchemaUrl
	 *            the schema url
	 * @param XmlDocumentUrl
	 *            the xml document url
	 * @return true if the given xml is valid otherwise false
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static boolean validateSchema(final String SchemaUrl, final String XmlDocumentUrl)
		throws SAXException, ParserConfigurationException, IOException
	{
		System.setProperty(DOCUMENT_BUILDER_FACTORY_KEY, DOCUMENT_BUILDER_FACTORY_VALUE);
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		factory.setValidating(true);
		factory.setAttribute(SCHEMA_LANGUAGE_KEY, HTTP_WWW_W3_ORG_2001_XML_SCHEMA);
		factory.setAttribute(SCHEMA_SOURCE_KEY, SchemaUrl);
		final DocumentBuilder builder = factory.newDocumentBuilder();
		final ValidatorHandler handler = new ValidatorHandler();
		builder.setErrorHandler(handler);
		builder.parse(XmlDocumentUrl);
		if (handler.isValid())
		{
			return false;
		}
		return true;
	}


}
