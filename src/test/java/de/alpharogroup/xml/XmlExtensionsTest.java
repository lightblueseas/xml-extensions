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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;
import org.xml.sax.InputSource;

import com.thoughtworks.xstream.XStream;

import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.EmployeeList;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.auth.AccessRight;
import de.alpharogroup.test.objects.auth.Role;
import de.alpharogroup.test.objects.auth.Roles;
import de.alpharogroup.test.objects.enums.Gender;

/**
 * The unit test class for the class {@link XmlExtensions}
 */
public class XmlExtensionsTest
{

	/**
	 * Test method for {@link XmlExtensions#getInputSource(String)}.
	 */
	@Test
	public void testGetInputSource()
	{
		InputSource actual;
		String xmlInputString;
		xmlInputString = "<employee>\n" + "  <person>\n" + "    <name>Anna</name>\n"
			+ "    <gender>FEMALE</gender>\n" + "  </person>\n" + "  <id>23</id>\n" + "</employee>";
		actual = XmlExtensions.getInputSource(xmlInputString);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link XmlExtensions#loadObject(File)}.
	 * 
	 * @throws IOException
	 */
	@Test
	public void testLoadObjectFile() throws IOException
	{
		Employee actual;
		Employee expected;
		File xmlFile;
		Person person;
		Employee employee;

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");

		xmlFile = PathFinder.getRelativePath(PathFinder.getSrcTestResourcesDir(), "newtest.xml");
		actual = XmlExtensions.loadObject(xmlFile);
		assertNotNull(actual);
		expected = employee;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#loadObject(String)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testLoadObjectString() throws IOException
	{
		Employee actual;
		Employee expected;
		Person person;
		Employee employee;

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");

		actual = XmlExtensions.loadObject("newtest.xml");
		assertNotNull(actual);
		expected = employee;
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#newTag(String, String, Map)}
	 */
	@Test
	public void testNewTag()
	{
		String actual;
		String expected;
		Map<String, String> attributes;
		actual = XmlExtensions.newTag("land", "france", null);
		expected = "<land>france</land>";
		assertEquals(actual, expected);

		attributes = new LinkedHashMap<>();
		attributes.put("population", "65350000");
		attributes.put("capital", "paris");
		actual = XmlExtensions.newTag("land", "france", attributes);
		expected = "<land population=\"65350000\" capital=\"paris\">france</land>";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlToJsonExtensions#toJson(String)}
	 */
	@SuppressWarnings("deprecation")
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
		actual = XmlExtensions.toJson(xmlResult);
		expected = "{\"de.alpharogroup.test.objects.Employee\":{\"person\":{\"name\":\"Anna\",\"gender\":\"FEMALE\"},\"id\":23}}";
		assertEquals(actual, expected);

		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		xmlResult = ObjectToXmlExtensions.toXmlWithXStream(employee);
		actual = XmlExtensions.toJson(xmlResult);
		expected = "{\"de.alpharogroup.test.objects.Employee\":{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":23}}";

		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlToJsonExtensions#toJson(String, Map)}
	 */
	@SuppressWarnings("deprecation")
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

		actual = XmlExtensions.toJson(xmlResult, aliases);
		expected = "{\"employee\":{\"person\":{\"name\":\"Anna\",\"gender\":\"FEMALE\"},\"id\":23}}";
		assertEquals(actual, expected);


		employee = Employee.builder().person(Person.builder().gender(Gender.FEMALE).name("Anna")
			.married(true).about("Ha ha ha...").nickname("beast").build()).id("23").build();
		xmlResult = ObjectToXmlExtensions.toXmlWithXStream(employee);
		actual = XmlExtensions.toJson(xmlResult, aliases);
		expected = "{\"employee\":{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":23}}";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#toObjectWithXMLDecoder(String)}.
	 */
	@SuppressWarnings("deprecation")
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
		actual = XmlExtensions.toObjectWithXMLDecoder(xmlInputString);

		expected = new Person();
		expected.setGender(Gender.FEMALE);
		expected.setName("Anna");

		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#toObjectWithXStream(String)}
	 */
	@SuppressWarnings("deprecation")
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
		xmlResult = XmlExtensions.toXmlWithXStream(expected);

		actual = XmlExtensions.toObjectWithXStream(xmlResult);
		assertNotNull(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#toObjectWithXStream(String, Map)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testToObjectWithXStreamStringMapOfStringClassOfQ()
	{
		Employee actual;
		Employee expected;
		String xmlInputString;
		Person person;
		Map<String, Class<?>> aliases;

		aliases = new HashMap<>();
		String lqSimpleName = Employee.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Employee.class);

		xmlInputString = "<employee>\n" + "  <person>\n" + "    <name>Anna</name>\n"
			+ "    <gender>FEMALE</gender>\n" + "  </person>\n" + "  <id>23</id>\n" + "</employee>";

		actual = XmlExtensions.toObjectWithXStream(xmlInputString, aliases);

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		expected = new Employee();
		expected.setPerson(person);
		expected.setId("23");

		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#toObjectWithXStream(XStream, String)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testToObjectWithXStreamXStreamString()
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
		actual = XmlExtensions.toObjectWithXStream(new XStream(), xmlResult);
		assertNotNull(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#toObjectWithXStream(XStream, String, Map)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testToObjectWithXStreamXStreamStringMapOfStringClassOfQ()
	{
		Employee actual;
		Employee expected;
		String xmlInputString;
		Person person;
		Map<String, Class<?>> aliases;

		aliases = new HashMap<>();
		String lqSimpleName = Employee.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Employee.class);

		xmlInputString = "<employee>\n" + "  <person>\n" + "    <name>Anna</name>\n"
			+ "    <gender>FEMALE</gender>\n" + "  </person>\n" + "  <id>23</id>\n" + "</employee>";

		actual = XmlExtensions.toObjectWithXStream(new XStream(), xmlInputString, aliases);

		person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		expected = new Employee();
		expected.setPerson(person);
		expected.setId("23");

		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#toXmlWithXMLEncoder(Object)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@SuppressWarnings("deprecation")
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
		actual = XmlExtensions.toXmlWithXMLEncoder(person);
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
	 * Test method for {@link XmlExtensions#toXmlWithXStream(Object)}
	 */
	@SuppressWarnings("deprecation")
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
		actual = XmlExtensions.toXmlWithXStream(employee);
		expected = "<de.alpharogroup.test.objects.Employee>\n" + "  <person>\n"
			+ "    <name>Anna</name>\n" + "    <gender>FEMALE</gender>\n" + "  </person>\n"
			+ "  <id>23</id>\n" + "</de.alpharogroup.test.objects.Employee>";
		assertNotNull(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#toXmlWithXStream(Object, Map)}
	 */
	@SuppressWarnings("deprecation")
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

		actual = XmlExtensions.toXmlWithXStream(employee, aliases);
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

		actual = XmlExtensions.toXmlWithXStream(roles, aliases);
		expected = "<roles>\n" + "  <roles class=\"empty-set\"/>\n" + "</roles>";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link XmlExtensions#toXmlWithXStream(XStream, Object)}.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void testToXmlWithXStreamXStreamT()
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
		actual = XmlExtensions.toXmlWithXStream(new XStream(), employee);
		expected = "<de.alpharogroup.test.objects.Employee>\n" + "  <person>\n"
			+ "    <name>Anna</name>\n" + "    <gender>FEMALE</gender>\n" + "  </person>\n"
			+ "  <id>23</id>\n" + "</de.alpharogroup.test.objects.Employee>";
		assertNotNull(actual);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlExtensions#toXmlWithXStream(XStream, Object, Map)}.
	 */
	@SuppressWarnings("deprecation")
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

		actual = XmlExtensions.toXmlWithXStream(new XStream(), employee, aliases);
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

		actual = XmlExtensions.toXmlWithXStream(new XStream(), roles, aliases);
		expected = "<roles>\n" + "  <roles class=\"empty-set\"/>\n" + "</roles>";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link XmlExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XmlExtensions.class);
	}


}
