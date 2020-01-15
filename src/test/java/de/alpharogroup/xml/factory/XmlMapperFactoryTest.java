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
package de.alpharogroup.xml.factory;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;

/**
 * The unit test class for the class {@link XmlMapperFactory}
 */
public class XmlMapperFactoryTest
{

	/**
	 * Test method for {@link XmlMapperFactory#newXmlMapper()}
	 */
	@Test
	public void testNewXmlMapper()
	{
		ObjectMapper actual;
		ObjectMapper expected;

		actual = XmlMapperFactory.newXmlMapper();
		expected = XmlMapperFactory.newXmlMapper();
		assertNotNull(actual);
		assertNotNull(expected);
		assertThat(actual, not(expected));
	}
	/**
	 * Test method for {@link XmlMapperFactory#newXmlMapper(JacksonXmlModule)}
	 */
	@Test
	public void testNewXmlMapperWithJacksonXmlModule()
	{
		ObjectMapper actual;
		ObjectMapper expected;
		JacksonXmlModule xmlModule = new JacksonXmlModule();
		xmlModule.setDefaultUseWrapper(false);

		actual = XmlMapperFactory.newXmlMapper(xmlModule);
		expected = XmlMapperFactory.newXmlMapper(xmlModule);
		assertNotNull(actual);
		assertNotNull(expected);
		assertThat(actual, not(expected));
	}

	/**
	 * Test method for {@link XmlMapperFactory}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XmlMapperFactory.class);
	}

}
