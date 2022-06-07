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
package io.github.astrapi69.xsd.schema;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/**
 * The class {@link ValidatorExtensions} can validate xml files.
 */
public final class ValidatorExtensions
{
	private ValidatorExtensions()
	{
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
		final ErrorHandler errorHandler)
		throws SAXException, ParserConfigurationException, IOException
	{

		final Schema schemaXSD = SchemaInitializer.newSchema(xsd, errorHandler);

		// Create a Validator capable of validating XML files according to my custom schema.
		final Validator validator = schemaXSD.newValidator();

		// parse the XML DOM tree againts the stricter XSD schema
		validator.validate(DocumentBuilderFactoryInitializer.newDOMSource(xml, errorHandler));
	}

	/**
	 * Validate given xml schema.
	 *
	 * @param schemaUrl
	 *            the schema url
	 * @param xmlDocumentUrl
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
	public static boolean validateSchema(final String schemaUrl, final String xmlDocumentUrl)
		throws SAXException, ParserConfigurationException, IOException
	{
		final DocumentBuilder builder = DocumentBuilderFactoryInitializer
			.newDocumentBuilder(schemaUrl);
		final ValidatorHandler handler = new ValidatorHandler();
		builder.setErrorHandler(handler);
		builder.parse(xmlDocumentUrl);
		return !handler.isValid();
	}

}
