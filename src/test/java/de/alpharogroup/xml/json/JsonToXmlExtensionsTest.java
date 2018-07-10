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

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;

import org.json.JSONException;
import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.alpharogroup.test.objects.Employee;

/**
 * The unit test class for the class {@link JsonToXmlExtensions}
 */
public class JsonToXmlExtensionsTest
{


	/**
	 * Test method for {@link JsonToXmlExtensions#toXml(String)}
	 * 
	 * @throws JSONException
	 */
	@Test
	public void testToXmlString() throws JSONException
	{
		String expected;
		String actual;

		expected = "<person><gender>FEMALE</gender><name>Anna</name><nickname>beast</nickname><about>Ha ha ha...</about><married>true</married></person><id>23</id>";
		final String jsonString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}";
		actual = JsonToXmlExtensions.toXml(jsonString);
		assertEquals(actual, expected);

	}

	/**
	 * Test method for {@link JsonToXmlExtensions#toXml(String, Class)}
	 *
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToXmlStringClass() throws JsonParseException, JsonMappingException, IOException
	{
		String expected;
		String actual;

		expected = "<de.alpharogroup.test.objects.Employee>\n  <person>\n    <name>Anna</name>\n    <nickname>beast</nickname>\n    <gender>FEMALE</gender>\n    <about>Ha ha ha...</about>\n    <married>true</married>\n  </person>\n  <id>23</id>\n</de.alpharogroup.test.objects.Employee>";
		final String jsonString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}";
		actual = JsonToXmlExtensions.toXml(jsonString, Employee.class);
		assertEquals(actual, expected);

	}

	/**
	 * Test method for {@link JsonToXmlExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(JsonToXmlExtensions.class);
	}

}
