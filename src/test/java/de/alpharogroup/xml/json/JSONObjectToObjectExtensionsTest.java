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
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.alpharogroup.collections.CollectionExtensions;
import de.alpharogroup.collections.list.ListFactory;
import io.github.astrapi69.test.objects.Employee;
import io.github.astrapi69.test.objects.Person;
import io.github.astrapi69.test.objects.enums.Gender;
import de.alpharogroup.xml.factory.ObjectMapperFactory;

/**
 * The unit test class for the class {@link JSONObjectToObjectExtensions}
 */
public class JSONObjectToObjectExtensionsTest
{

	/**
	 * Test method for
	 * {@link JSONObjectToObjectExtensions#toObject(JSONObject, Class, ObjectMapper)}
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObject() throws IOException
	{
		Employee actual;
		Employee expected;
		String jsonString;
		ObjectMapper objectMapper;
		jsonString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}";
		expected = JsonStringToObjectExtensions.toObject(jsonString, Employee.class);

		JSONObject obj = new JSONObject(jsonString);
		objectMapper = ObjectMapperFactory.newObjectMapper();
		actual = JSONObjectToObjectExtensions.toObject(obj, Employee.class, objectMapper);
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JSONObjectToObjectExtensions#toObjectList(JSONArray, Class)}
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testToObjectList() throws IOException
	{
		boolean actual;
		boolean expected;
		List<Employee> jsonList;
		List<Employee> objectList;
		Employee firstExpected;
		Employee secondExpected;
		Employee thirdExpected;
		String jsonString;

		jsonString = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},"
			+ "{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},"
			+ "{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";

		JSONArray jsonArray = new JSONArray(jsonString);

		jsonList = JSONObjectToObjectExtensions.toObjectList(jsonArray, Employee.class);
		firstExpected = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Anna").nickname("beast").married(true).about("Ha ha ha...").build()).id("23")
			.build();
		secondExpected = Employee.builder().person(Person.builder().gender(Gender.MALE)
			.name("Andreas").nickname("cute").married(false).about("fine person").build()).id("24")
			.build();
		thirdExpected = Employee.builder().person(Person.builder().gender(Gender.FEMALE)
			.name("Tatjana").nickname("beautiful").married(false).about("Im hot").build()).id("25")
			.build();
		objectList = ListFactory.newArrayList(firstExpected, secondExpected, thirdExpected);

		actual = CollectionExtensions.isEqualCollection(jsonList, objectList);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link JSONObjectToObjectExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(JSONObjectToObjectExtensions.class);
	}

}
