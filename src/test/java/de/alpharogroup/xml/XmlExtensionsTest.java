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

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;
import org.xml.sax.InputSource;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Person;
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

		person = Person.builder().gender(Gender.FEMALE).name("Anna").nickname(null).married(null)
			.about(null).build();

		employee = Employee.builder().id("23").person(person).build();

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

		person = Person.builder().gender(Gender.FEMALE).name("Anna").nickname(null).married(null)
			.about(null).build();

		employee = Employee.builder().id("23").person(person).build();

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
	 * Test method for {@link XmlExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XmlExtensions.class);
	}

}
