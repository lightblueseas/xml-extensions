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
import static org.testng.AssertJUnit.assertEquals;

import java.util.Map;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.alpharogroup.collections.map.MapFactory;

/**
 * The unit test class for the class {@link ObjectMapperFactory}
 */
public class ObjectMapperFactoryTest
{

	/**
	 * Test method for {@link ObjectMapperFactory#newObjectMapper()}.
	 */
	@Test
	public void testNewObjectMapper()
	{
		ObjectMapper actual;
		ObjectMapper expected;

		actual = ObjectMapperFactory.newObjectMapper();
		expected = ObjectMapperFactory.newObjectMapper(false);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link ObjectMapperFactory#newObjectMapper(boolean)}.
	 */
	@Test
	public void testNewObjectMapperBoolean()
	{
		ObjectMapper actual;
		ObjectMapper expected;

		actual = ObjectMapperFactory.newObjectMapper(false);
		expected = ObjectMapperFactory.newObjectMapper(false);
		assertEquals(actual, expected);

		actual = ObjectMapperFactory.newObjectMapper(false);
		expected = ObjectMapperFactory.newObjectMapper(true);
		assertThat(actual, not(expected));

		actual = ObjectMapperFactory.newObjectMapper(true);
		expected = ObjectMapperFactory.newObjectMapper(true);
		assertThat(actual, not(expected));
	}


	/**
	 * Test method for {@link ObjectMapperFactory#newObjectMapper(java.util.Map)}
	 */
	@Test
	public void testNewObjectMapperMapOfFeatureBoolean()
	{
		ObjectMapper actual;
		ObjectMapper expected;
		Map<JsonParser.Feature, Boolean> features;

		features = MapFactory.newHashMap();
		features.put(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		actual = ObjectMapperFactory.newObjectMapper(features);
		expected = ObjectMapperFactory.newObjectMapper(true);
		assertThat(actual, not(expected));
	}

	/**
	 * Test method for {@link ObjectMapperFactory}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ObjectMapperFactory.class);
	}

}
