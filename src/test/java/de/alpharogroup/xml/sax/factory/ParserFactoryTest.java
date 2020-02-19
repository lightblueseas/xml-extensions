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
package de.alpharogroup.xml.sax.factory;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * The unit test class for the class {@link ParserFactory}
 */
public class ParserFactoryTest
{

	/**
	 * Test method for {@link ParserFactory#newSAXParserFactory(boolean)}
	 *
	 * @throws SAXNotSupportedException
	 *             is thrown if the SAX operation not supported
	 * @throws SAXNotRecognizedException
	 *             is thrown if the SAX have unrecognized identifier
	 * @throws ParserConfigurationException
	 *             is thrown if a serious configuration error is indicated
	 */
	@Test
	public final void testNewSAXParserFactory()
		throws SAXNotSupportedException, SAXNotRecognizedException, ParserConfigurationException
	{
		SAXParserFactory saxParserFactory;

		saxParserFactory = ParserFactory.newSAXParserFactory(true);
		assertNotNull(saxParserFactory);
		assertTrue(saxParserFactory.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING));
		assertFalse(saxParserFactory.getFeature(ParserFactory.FEATURE_EXTERNAL_GENERAL_ENTITIES));
		assertTrue(saxParserFactory.getFeature(ParserFactory.FEATURE_DISALLOW_DOCTYPE_DECL));

		saxParserFactory = ParserFactory.newSAXParserFactory(false);
		assertNotNull(saxParserFactory);
		assertFalse(saxParserFactory.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING));
		assertTrue(saxParserFactory.getFeature(ParserFactory.FEATURE_EXTERNAL_GENERAL_ENTITIES));
		assertFalse(saxParserFactory.getFeature(ParserFactory.FEATURE_DISALLOW_DOCTYPE_DECL));

	}

	/**
	 * Test method for {@link ParserFactory}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ParserFactory.class);
	}

}
