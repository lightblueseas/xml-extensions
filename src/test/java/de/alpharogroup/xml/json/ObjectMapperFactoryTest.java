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
package de.alpharogroup.xml.json;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertEquals;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The unit test class for the class {@link ObjectMapperFactory}
 */
public class ObjectMapperFactoryTest
{

	/**
	 * Test method for {@link ObjectMapperFactory#getObjectMapper()}.
	 */
	@Test
	public void testGetObjectMapper()
	{
		ObjectMapper actual;
		ObjectMapper expected;

		actual = ObjectMapperFactory.getObjectMapper();
		expected = ObjectMapperFactory.getObjectMapper(false);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link ObjectMapperFactory#getObjectMapper(boolean)}.
	 */
	@Test
	public void testGetObjectMapperBoolean()
	{
		ObjectMapper actual;
		ObjectMapper expected;

		actual = ObjectMapperFactory.getObjectMapper(false);
		expected = ObjectMapperFactory.getObjectMapper(false);
		assertEquals(actual, expected);

		actual = ObjectMapperFactory.getObjectMapper(false);
		expected = ObjectMapperFactory.getObjectMapper(true);
		assertThat(actual, not(expected));

		actual = ObjectMapperFactory.getObjectMapper(true);
		expected = ObjectMapperFactory.getObjectMapper(true);
		assertThat(actual, not(expected));
	}

	/**
	 * Test method for {@link ObjectMapperFactory}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ObjectMapperFactory.class);
	}

}
