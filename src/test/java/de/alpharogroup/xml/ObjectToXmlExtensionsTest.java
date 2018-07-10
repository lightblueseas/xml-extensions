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

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.thoughtworks.xstream.XStream;

import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.auth.AccessRight;
import de.alpharogroup.test.objects.auth.Role;
import de.alpharogroup.test.objects.auth.Roles;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link ObjectToXmlExtensions}
 */
public class ObjectToXmlExtensionsTest
{

	/**
	 * Test method for {@link ObjectToXmlExtensions#toXmlWithXMLEncoder(Object)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToXmlWithXMLEncoder() throws IOException
	{
		String actual;
		String expected;
		Person person;
		String javaVersion;

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		actual = ObjectToXmlExtensions.toXmlWithXMLEncoder(person);
		assertNotNull(actual);
		javaVersion = System.getProperty("java.version");
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<java version=\"" + javaVersion
			+ "\" class=\"java.beans.XMLDecoder\">\n"
			+ " <object class=\"de.alpharogroup.test.objects.Person\">\n"
			+ "  <void property=\"gender\">\n"
			+ "   <object class=\"java.lang.Enum\" method=\"valueOf\">\n"
			+ "    <class>de.alpharogroup.test.objects.enums.Gender</class>\n"
			+ "    <string>FEMALE</string>\n" + "   </object>\n" + "  </void>\n"
			+ "  <void property=\"name\">\n" + "   <string>Anna</string>\n" + "  </void>\n"
			+ " </object>\n" + "</java>\n";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link ObjectToXmlExtensions#toXmlWithXStream(Object)}
	 */
	@Test(enabled = true)
	public void testToXmlWithXStreamObject()
	{
		String actual;
		String expected;
		Person person;
		Employee employee;

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		actual = ObjectToXmlExtensions.toXmlWithXStream(employee);
		expected = "<de.alpharogroup.test.objects.Employee>\n" + "  <person>\n"
			+ "    <name>Anna</name>\n" + "    <gender>FEMALE</gender>\n" + "  </person>\n"
			+ "  <id>23</id>\n" + "</de.alpharogroup.test.objects.Employee>";
		assertNotNull(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link ObjectToXmlExtensions#toXmlWithXStream(Object, Map)}
	 */
	@Test(enabled = true)
	public void testToXmlWithXStreamObjectMapOfStringClass()
	{
		String actual;
		String expected;
		Person person;
		Employee employee;
		Map<String, Class<?>> aliases;
		Set<Role> rs;
		Roles roles;
		Role role;
		Set<AccessRight> rights;
		AccessRight right;

		// new scenario ...
		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		aliases = new HashMap<>();
		String lqSimpleName = Employee.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Employee.class);

		actual = ObjectToXmlExtensions.toXmlWithXStream(employee, aliases);
		expected = "<employee>\n" + "  <person>\n" + "    <name>Anna</name>\n"
			+ "    <gender>FEMALE</gender>\n" + "  </person>\n" + "  <id>23</id>\n" + "</employee>";
		assertEquals(expected, actual);

		// new scenario ...
		rs = new HashSet<>();
		roles = Roles.builder().roles(rs).build();

		role = Role.builder().build();
		rs.add(role);
		rights = new HashSet<>();
		role.setRights(rights);
		right = AccessRight.builder().build();
		right.setDescription("bla");
		rights.add(right);
		aliases = new HashMap<>();
		lqSimpleName = Roles.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Roles.class);
		lqSimpleName = Role.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Role.class);
		lqSimpleName = AccessRight.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, AccessRight.class);

		actual = ObjectToXmlExtensions.toXmlWithXStream(roles, aliases);
		expected = "<roles>\n" + "  <roles class=\"empty-set\"/>\n" + "</roles>";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ObjectToXmlExtensions#toXmlWithXStream(XStream, Object)}.
	 */
	@Test
	public void testToXmlWithXStreamXStreamObject()
	{
		String actual;
		String expected;
		Person person;
		Employee employee;

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		actual = ObjectToXmlExtensions.toXmlWithXStream(new XStream(), employee);
		expected = "<de.alpharogroup.test.objects.Employee>\n" + "  <person>\n"
			+ "    <name>Anna</name>\n" + "    <gender>FEMALE</gender>\n" + "  </person>\n"
			+ "  <id>23</id>\n" + "</de.alpharogroup.test.objects.Employee>";
		assertNotNull(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link ObjectToXmlExtensions#toXmlWithXStream(XStream, Object, Map)}.
	 */
	@Test
	public void testToXmlWithXStreamXStreamTMapOfStringClassOfQ()
	{
		String actual;
		String expected;
		Person person;
		Employee employee;
		Map<String, Class<?>> aliases;
		Set<Role> rs;
		Roles roles;
		Role role;
		Set<AccessRight> rights;
		AccessRight right;

		// new scenario ...
		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		aliases = new HashMap<>();
		String lqSimpleName = Employee.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Employee.class);

		actual = ObjectToXmlExtensions.toXmlWithXStream(new XStream(), employee, aliases);
		expected = "<employee>\n" + "  <person>\n" + "    <name>Anna</name>\n"
			+ "    <gender>FEMALE</gender>\n" + "  </person>\n" + "  <id>23</id>\n" + "</employee>";
		assertEquals(expected, actual);

		// new scenario ...
		rs = new HashSet<>();
		roles = Roles.builder().roles(rs).build();

		role = Role.builder().build();
		rs.add(role);
		rights = new HashSet<>();
		role.setRights(rights);
		right = AccessRight.builder().build();
		right.setDescription("bla");
		rights.add(right);
		aliases = new HashMap<>();
		lqSimpleName = Roles.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Roles.class);
		lqSimpleName = Role.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Role.class);
		lqSimpleName = AccessRight.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, AccessRight.class);

		actual = ObjectToXmlExtensions.toXmlWithXStream(new XStream(), roles, aliases);
		expected = "<roles>\n" + "  <roles class=\"empty-set\"/>\n" + "</roles>";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link ObjectToXmlExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(ObjectToXmlExtensions.class);
	}

}
