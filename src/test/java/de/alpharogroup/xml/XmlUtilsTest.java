/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.EmployeeList;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;
import de.alpharogroup.test.objects.auth.AccessRight;
import de.alpharogroup.test.objects.auth.Role;
import de.alpharogroup.test.objects.auth.Roles;

public class XmlUtilsTest
{

	@Test
	public void testCreateTag()
	{
		String result = XmlUtils.newTag("land", "france", null);
		String expected = "<land>france</land>";
		AssertJUnit.assertTrue("", result.equals(expected));
		final Map<String, String> attributes = new LinkedHashMap<>();
		attributes.put("population", "65350000");
		attributes.put("capital", "paris");
		result = XmlUtils.newTag("land", "france", attributes);
		expected = "<land population=\"65350000\" capital=\"paris\">france</land>";
		AssertJUnit.assertTrue("", result.equals(expected));
	}

	@Test(enabled = true)
	public void testToJson()
	{
		final Person person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		Employee employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		String xmlResult = XmlUtils.toXmlWithXStream(employee);
		String actual = XmlUtils.toJson(xmlResult);
		String expected = "{\"de.alpharogroup.test.objects.Employee\":{\"person\":{\"name\":\"Anna\",\"nickname\":\"\",\"gender\":\"FEMALE\",\"about\":\"\",\"married\":false},\"id\":23}}";
		AssertJUnit.assertTrue("", actual.equals(expected));
		final Map<String, Class<?>> aliases = new HashMap<>();
		aliases.put("employee", Employee.class);

		actual = XmlUtils.toJson(xmlResult, aliases);
		expected = "{\"employee\":{\"person\":{\"name\":\"Anna\",\"nickname\":\"\",\"gender\":\"FEMALE\",\"about\":\"\",\"married\":false},\"id\":23}}";

		AssertJUnit.assertTrue("", actual.equals(expected));


		employee = Employee
			.builder()
			.person(
				Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
					.about("Ha ha ha...").nickname("beast").build()).id("23").build();
		xmlResult = XmlUtils.toXmlWithXStream(employee);
		actual = XmlUtils.toJson(xmlResult);
		expected = "{\"de.alpharogroup.test.objects.Employee\":{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":23}}";
		System.out.println(expected);
	}

	@Test
	public void testToObjectWithXStreamString()
	{
		final Person person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		final Employee employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		final List<Employee> employees = new ArrayList<>();
		employees.add(employee);
		final EmployeeList employeeList = new EmployeeList();
		employeeList.setList(employees);
		final String xmlResult = XmlUtils.toXmlWithXStream(employeeList);

		final EmployeeList actual = XmlUtils.toObjectWithXStream(xmlResult);
		AssertJUnit.assertNotNull(actual);

	}

	// @Test
	public void testToObjectWithXStreamXStreamString()
	{
		// fail("Not yet implemented");
	}

	// @Test
	public void testToObjectWithXStreamXStreamStringMapOfStringClassOfQ()
	{
		// fail("Not yet implemented");
	}

	// @Test
	public void testToXmlWithXMLEncoder()
	{
		// fail("Not yet implemented");
	}

	@Test(enabled = false)
	public void testToXmlWithXStreamObject()
	{
		final Person person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		final Employee employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		final String xmlResult = XmlUtils.toXmlWithXStream(employee);
		final String expected = "<de.alpharogroup.test.objects.Employee>\n" + "  <person>\n"
			+ "    <name>Anna</name>\n" + "    <gender>FEMALE</gender>\n" + "    <about></about>\n"
			+ "    <married>false</married>\n" + "  </person>\n" + "  <id>23</id>\n"
			+ "</de.alpharogroup.test.objects.Employee>";
		AssertJUnit.assertTrue("", xmlResult.equals(expected));
	}

	@Test(enabled = false)
	public void testToXmlWithXStreamXStreamObject()
	{
		// fail("Not yet implemented");

	}

	@Test(enabled = false)
	public void testToXmlWithXStreamXStreamObjectMapOfStringClassOfQ()
	{
		final Person person = new Person();
		person.setGender(Gender.FEMALE);
		person.setName("Anna");
		final Employee employee = new Employee();
		employee.setPerson(person);
		employee.setId("23");
		Map<String, Class<?>> aliases = new HashMap<>();
		String lqSimpleName = Employee.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Employee.class);
		String xmlResult = XmlUtils.toXmlWithXStream(employee, aliases);
		final String expected = "<employee>\n" + "  <person>\n" + "    <name>Anna</name>\n"
			+ "    <gender>FEMALE</gender>\n" + "    <about></about>\n"
			+ "    <married>false</married>\n" + "  </person>\n" + "  <id>23</id>\n"
			+ "</employee>";
		AssertJUnit.assertTrue("", xmlResult.equals(expected));

		final Roles roles = new Roles();
		final Set<Role> rs = new HashSet<>();
		roles.setRoles(rs);
		final Role role = new Role();
		rs.add(role);
		final Set<AccessRight> rights = new HashSet<>();
		role.setRights(rights);
		final AccessRight right = new AccessRight();
		right.setDescription("bla");
		rights.add(right);
		aliases = new HashMap<>();
		lqSimpleName = Roles.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Roles.class);
		lqSimpleName = Role.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, Role.class);
		lqSimpleName = AccessRight.class.getSimpleName().toLowerCase();
		aliases.put(lqSimpleName, AccessRight.class);

		xmlResult = XmlUtils.toXmlWithXStream(roles, aliases);
		System.out.println(xmlResult);

	}

}
