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
import static org.testng.AssertJUnit.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.thoughtworks.xstream.XStream;

import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.EmployeeList;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link XmlToObjectExtensions}
 */
public class XmlToObjectExtensionsTest
{

	/**
	 * Test method for {@link XmlToObjectExtensions#toObjectWithXMLDecoder(String)}
	 */
	@Test
	public void testToObjectWithXMLDecoder()
	{
		Person actual;
		Person expected;
		String xmlInputString;
		String javaVersion;

		javaVersion = System.getProperty("java.version");
		xmlInputString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<java version=\""
			+ javaVersion + "\" class=\"java.beans.XMLDecoder\">\n"
			+ " <object class=\"de.alpharogroup.test.objects.Person\">\n"
			+ "  <void property=\"gender\">\n"
			+ "   <object class=\"java.lang.Enum\" method=\"valueOf\">\n"
			+ "    <class>de.alpharogroup.test.objects.enums.Gender</class>\n"
			+ "    <string>FEMALE</string>\n" + "   </object>\n" + "  </void>\n"
			+ "  <void property=\"name\">\n" + "   <string>Anna</string>\n" + "  </void>\n"
			+ " </object>\n" + "</java>\n";
		actual = XmlToObjectExtensions.toObjectWithXMLDecoder(xmlInputString);

		expected = new Person();
		expected.setGender(Gender.FEMALE);
		expected.setName("Anna");

		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlToObjectExtensions#toObjectWithXStream(String)}
	 */
	@Test
	public void testToObjectWithXStreamString()
	{
		EmployeeList actual;
		EmployeeList expected;
		Person person;
		String xmlResult;

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		final Employee employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		final List<Employee> employees = new ArrayList<>();
		employees.add(employee);

		expected = EmployeeList.builder().employees(employees).build();
		xmlResult = ObjectToXmlExtensions.toXmlWithXStream(expected);
		actual = XmlToObjectExtensions.toObjectWithXStream(xmlResult);
		assertNotNull(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlToObjectExtensions#toObjectWithXStream(String, Map)}
	 */
	@Test
	public void testToObjectWithXStreamStringMapOfStringClass()
	{
		Employee actual;
		Employee employee;
		Employee expected;
		String xmlInputString;
		Person person;
		Map<String, Class<?>> aliases;

		aliases = new HashMap<>();
		String lqSimpleName = Employee.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Employee.class);

		xmlInputString = "<employee>\n" + "  <person>\n" + "    <name>Anna</name>\n"
			+ "    <gender>FEMALE</gender>\n" + "  </person>\n" + "  <id>23</id>\n" + "</employee>";

		actual = XmlToObjectExtensions.toObjectWithXStream(xmlInputString, aliases);


		person = Person.builder().gender(Gender.FEMALE).name("Anna").nickname(null).married(null)
			.about(null).build();

		employee = Employee.builder().id("23").person(person).build();
		expected = employee;

		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlToObjectExtensions#toObjectWithXStream(XStream, String)}
	 */
	@Test
	public void testToObjectWithXStreamXStreamString()
	{
		EmployeeList actual;
		EmployeeList expected;
		Employee employee;
		Person person;
		String xmlResult;

		person = Person.builder().gender(Gender.FEMALE).name("Anna").nickname(null).married(null)
			.about(null).build();

		employee = Employee.builder().id("23").person(person).build();
		final List<Employee> employees = new ArrayList<>();
		employees.add(employee);

		expected = EmployeeList.builder().employees(employees).build();
		xmlResult = ObjectToXmlExtensions.toXmlWithXStream(expected);
		actual = XmlToObjectExtensions.toObjectWithXStream(new XStream(), xmlResult);
		assertNotNull(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlToObjectExtensions#toObjectWithXStream(XStream, String, Map)}
	 */
	@Test
	public void testToObjectWithXStreamXStreamStringMapOfStringClass()
	{
		Employee actual;
		Employee expected;
		Employee employee;
		String xmlInputString;
		Person person;
		Map<String, Class<?>> aliases;

		aliases = new HashMap<>();
		String lqSimpleName = Employee.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Employee.class);

		xmlInputString = "<employee>\n" + "  <person>\n" + "    <name>Anna</name>\n"
			+ "    <gender>FEMALE</gender>\n" + "  </person>\n" + "  <id>23</id>\n" + "</employee>";

		actual = XmlToObjectExtensions.toObjectWithXStream(new XStream(), xmlInputString, aliases);

		person = Person.builder().gender(Gender.FEMALE).name("Anna").nickname(null).married(null)
			.about(null).build();

		employee = Employee.builder().id("23").person(person).build();
		expected = employee;

		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlToObjectExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XmlToObjectExtensions.class);
	}

}
