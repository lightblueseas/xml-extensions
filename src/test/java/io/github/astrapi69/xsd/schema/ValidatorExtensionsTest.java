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

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import io.github.astrapi69.file.search.PathFinder;

/**
 * The unit test class for the class {@link ValidatorExtensions}
 */
public class ValidatorExtensionsTest
{

	/**
	 * Test method for
	 * {@link ValidatorExtensions#validateSchema(File, File, org.xml.sax.ErrorHandler)}
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
	public void testValidateSchemaFileFileErrorHandler()
		throws SAXException, ParserConfigurationException, IOException
	{
		boolean actual;
		boolean expected;
		ValidatorHandler errorHandler;
		File xsd;
		File xml;

		errorHandler = new ValidatorHandler();
		xsd = new File(PathFinder.getSrcTestResourcesDir(), "dataset.xsd");
		xml = new File(PathFinder.getSrcTestResourcesDir(), "dataset.xml");
		ValidatorExtensions.validateSchema(xsd, xml, errorHandler);
		actual = errorHandler.isValid();
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ValidatorExtensions#validateSchema(String, String)}
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
	public void testValidateSchemaStringString()
		throws SAXException, ParserConfigurationException, IOException
	{
		boolean actual;
		boolean expected;
		File xsd;
		File xml;
		String schemaUrl;
		String xmlDocumentUrl;

		xsd = new File(PathFinder.getSrcTestResourcesDir(), "dataset.xsd");
		xml = new File(PathFinder.getSrcTestResourcesDir(), "dataset.xml");
		schemaUrl = xsd.getAbsolutePath();
		xmlDocumentUrl = xml.getAbsolutePath();
		actual = ValidatorExtensions.validateSchema(schemaUrl, xmlDocumentUrl);
		expected = true;
		assertEquals(expected, actual);

		xsd = new File(PathFinder.getSrcTestResourcesDir(), "dataset.xsd");
		xml = new File(PathFinder.getSrcTestResourcesDir(), "xml2xsdTest.xml");
		schemaUrl = xsd.getAbsolutePath();
		xmlDocumentUrl = xml.getAbsolutePath();
		actual = ValidatorExtensions.validateSchema(schemaUrl, xmlDocumentUrl);
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ValidatorExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ValidatorExtensions.class);
	}

}
