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
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * The unit test class for the class {@link ValidatorHandler}
 */
public class ValidatorHandlerTest
{

	/**
	 * Test method for {@link ValidatorHandler#error(SAXParseException)}.
	 * 
	 * @throws SAXException
	 *             Any SAX exception, possibly wrapping another exception
	 */
	@Test
	public void testErrorSAXParseException() throws SAXException
	{
		SAXParseException actual;
		SAXParseException expected;
		ValidatorHandler saxHandler;

		saxHandler = new ValidatorHandler();
		expected = new SAXParseException("foo sax", null);
		saxHandler.error(expected);
		actual = saxHandler.getSaxParseException();
		assertEquals(expected, actual);
		assertTrue(saxHandler.isValidationError());

	}

	/**
	 * Test method for {@link ValidatorHandler#fatalError(SAXParseException)}.
	 * 
	 * @throws SAXException
	 *             Any SAX exception, possibly wrapping another exception
	 */
	@Test
	public void testFatalErrorSAXParseException() throws SAXException
	{
		SAXParseException actual;
		SAXParseException expected;
		ValidatorHandler saxHandler;

		saxHandler = new ValidatorHandler();
		expected = new SAXParseException("foo sax", null);
		saxHandler.fatalError(expected);
		actual = saxHandler.getSaxParseException();
		assertEquals(expected, actual);
		assertTrue(saxHandler.isValidationError());

	}

	/**
	 * Test method for {@link ValidatorHandler#isValid()}.
	 */
	@Test
	public void testIsValid()
	{
		boolean actual;
		boolean expected;
		ValidatorHandler saxHandler;

		saxHandler = new ValidatorHandler();
		saxHandler.setValidationError(true);
		actual = saxHandler.isValid();
		expected = true;
		assertEquals(expected, actual);
		assertTrue(saxHandler.isValidationError());
	}

	/**
	 * Test method for {@link ValidatorHandler#warning(SAXParseException)}
	 * 
	 * @throws SAXException
	 *             Any SAX exception, possibly wrapping another exception
	 */
	@Test
	public void testWarningSAXParseException() throws SAXException
	{
		SAXParseException actual;
		SAXParseException expected;
		ValidatorHandler saxHandler;

		saxHandler = new ValidatorHandler();
		expected = new SAXParseException("foo sax", null);
		saxHandler.warning(expected);
		actual = saxHandler.getSaxParseException();
		assertEquals(expected, actual);
		assertFalse(saxHandler.isValidationError());
	}

}
