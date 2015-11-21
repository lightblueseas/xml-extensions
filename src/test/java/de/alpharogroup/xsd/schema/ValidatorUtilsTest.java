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

import javax.xml.parsers.ParserConfigurationException;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import de.alpharogroup.file.search.PathFinder;

/**
 * The Class ValidatorUtilsTest.
 */
public class ValidatorUtilsTest
{

	/**
	 * Test validate schema file file error handler.
	 * 
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testValidateSchemaFileFileErrorHandler() throws SAXException,
		ParserConfigurationException, IOException
	{
		final ValidatorHandler errorHandler = new ValidatorHandler();
		final File xsd = new File(PathFinder.getSrcTestResourcesDir(), "dataset.xsd");
		final File xml = new File(PathFinder.getSrcTestResourcesDir(), "dataset.xml");
		ValidatorUtils.validateSchema(xsd, xml, errorHandler);
		AssertJUnit.assertTrue("Validation failed.", errorHandler.isValid());
	}

	/**
	 * Test validate schema string string.
	 * 
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testValidateSchemaStringString() throws SAXException, ParserConfigurationException,
		IOException
	{
		final File xsd = new File(PathFinder.getSrcTestResourcesDir(), "dataset.xsd");
		final File xml = new File(PathFinder.getSrcTestResourcesDir(), "dataset.xml");
		final String schemaUrl = xsd.getAbsolutePath();
		final String xmlDocumentUrl = xml.getAbsolutePath();
		final boolean result = ValidatorUtils.validateSchema(schemaUrl, xmlDocumentUrl);
		AssertJUnit.assertTrue("Validation failed.", result);
	}

}
