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
package de.alpharogroup.xml.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;

import de.alpharogroup.test.objects.Employee;
import de.alpharogroup.test.objects.Gender;
import de.alpharogroup.test.objects.Person;


/**
 * Test class for {@link JsonTransformer}.
 */
public class JsonTransformerTest
{

	/**
	 * Test method for {@link JsonTransformer#toJson(Object)}.
	 *
	 * @throws JsonProcessingException
	 *             the json processing exception
	 */
	@Test
	public void testToJson() throws JsonProcessingException
	{
		final Employee employee = Employee
			.builder()
			.person(
				Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
					.about("Ha ha ha...").nickname("beast").build()).id("23").build();
		final String actual = JsonTransformer.toJson(employee);
		final String expected = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}";
		AssertJUnit.assertTrue("", actual.equals(expected));

	}

	/**
	 * Test method for {@link JsonTransformer#toJson(java.util.List)}.
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToJsonList() throws IOException
	{
		final List<Employee> employees = new ArrayList<>();
		employees.add(Employee
			.builder()
			.person(
				Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
					.about("Ha ha ha...").nickname("beast").build()).id("23").build());
		employees.add(Employee
			.builder()
			.person(
				Person.builder().gender(Gender.MALE).name("Andreas").married(false)
					.about("fine person").nickname("cute").build()).id("24").build());
		employees.add(Employee
			.builder()
			.person(
				Person.builder().gender(Gender.FEMALE).name("Tatjana").married(false)
					.about("Im hot").nickname("beautiful").build()).id("25").build());
		final String actual = JsonTransformer.toJson(employees);

		final String expected = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";
		AssertJUnit.assertTrue("", actual.equals(expected));
	}

	/**
	 * Test method for {@link JsonTransformer#toObject(String, Class)}.
	 *
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToObject() throws JsonParseException, JsonMappingException, IOException
	{
		final Employee expected = Employee
			.builder()
			.person(
				Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
					.about("Ha ha ha...").nickname("beast").build()).id("23").build();
		final String jsonString = "{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"}";
		final Employee actual = JsonTransformer.toObject(jsonString, Employee.class);
		AssertJUnit.assertEquals(expected, actual);
	}


	/**
	 * Test method for {@link JsonTransformer#toObject(String, Class)}.
	 *
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToObjectList() throws JsonParseException, JsonMappingException, IOException
	{
		final String jsonString = "[{\"person\":{\"name\":\"Anna\",\"nickname\":\"beast\",\"gender\":\"FEMALE\",\"about\":\"Ha ha ha...\",\"married\":true},\"id\":\"23\"},{\"person\":{\"name\":\"Andreas\",\"nickname\":\"cute\",\"gender\":\"MALE\",\"about\":\"fine person\",\"married\":false},\"id\":\"24\"},{\"person\":{\"name\":\"Tatjana\",\"nickname\":\"beautiful\",\"gender\":\"FEMALE\",\"about\":\"Im hot\",\"married\":false},\"id\":\"25\"}]";

		final List<Employee> actual = JsonTransformer.toObjectList(jsonString, Employee.class);

		System.out.println(actual);
	}

	/**
	 * Test method for
	 * {@link JsonTransformer#toObject(String, Class, com.fasterxml.jackson.databind.Module...)}.
	 * This method shows also how to map a json string created from the org.json library. This is
	 * provided by a Module, the {@link JsonOrgModule}.
	 *
	 * @throws JsonParseException
	 *             If an error occurs when parsing the string into Object
	 * @throws JsonMappingException
	 *             the If an error occurs when mapping the string into Object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testToObjectWithModules() throws JsonParseException, JsonMappingException,
		IOException
	{
		final Employee expected = Employee
			.builder()
			.person(
				Person.builder().gender(Gender.FEMALE).name("Anna").married(true)
					.about("Ha ha ha...").nickname("beast").build()).id("23").build();
		final String jsonString = "{\"id\":\"23\",\"person\":{\"married\":true,\"nickname\":\"beast\",\"name\":\"Anna\",\"about\":\"Ha ha ha...\",\"gender\":\"FEMALE\"}}";
		final Employee actual = JsonTransformer.toObject(jsonString, Employee.class,
			new JsonOrgModule());
		AssertJUnit.assertEquals(expected, actual);
	}

}
