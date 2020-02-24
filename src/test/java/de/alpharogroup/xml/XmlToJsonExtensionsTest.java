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
package de.alpharogroup.xml;

import static org.testng.AssertJUnit.assertEquals;

import java.util.Map;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link XmlToJsonExtensions}
 */
public class XmlToJsonExtensionsTest
{

	/**
	 * Test method for {@link XmlToJsonExtensions#toJson(String)}
	 */
	@Test
	public void testToJsonString()
	{
		String actual;
		String expected;
		Person person;
		Employee employee;
		String xmlResult;

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		xmlResult = ObjectToXmlExtensions.toXmlWithXStream(employee);
		actual = XmlToJsonExtensions.toJson(xmlResult);
		expected = "{\"de.alpharogroup.test.objects.Employee\":{\"id\":23,\"person\":{\"about\":\"\",\"gender\":\"FEMALE\",\"married\":false,\"name\":\"Anna\",\"nickname\":\"\"}}}";
		assertEquals(actual, expected);

		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		xmlResult = ObjectToXmlExtensions.toXmlWithXStream(employee);
		actual = XmlToJsonExtensions.toJson(xmlResult);
		expected = "{\"de.alpharogroup.test.objects.Employee\":{\"id\":23,\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}}";

		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlToJsonExtensions#toJson(String, Map)}
	 */
	@Test
	public void testToJsonStringMapOfStringClass()
	{
		String actual;
		String expected;
		Person person;
		Employee employee;
		String xmlResult;
		Map<String, Class<?>> aliases;

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		xmlResult = ObjectToXmlExtensions.toXmlWithXStream(employee);

		aliases = MapFactory.newHashMap();
		aliases.put("employee", Employee.class);

		actual = XmlToJsonExtensions.toJson(xmlResult, aliases);
		expected = "{\"employee\":{\"id\":23,\"person\":{\"about\":\"\",\"gender\":\"FEMALE\",\"married\":false,\"name\":\"Anna\",\"nickname\":\"\"}}}";
		assertEquals(actual, expected);


		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		xmlResult = ObjectToXmlExtensions.toXmlWithXStream(employee);

		actual = XmlToJsonExtensions.toJson(xmlResult, aliases);
		expected = "{\"employee\":{\"id\":23,\"person\":{\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\",\"married\":true,\"name\":\"Anna\",\"nickname\":\"beast\"}}}";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlToJsonExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XmlToJsonExtensions.class);
	}

}
