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
package io.github.astrapi69.xml;

import static org.testng.AssertJUnit.assertEquals;

import java.io.File;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.jaxb.Club;
import io.github.astrapi69.test.object.Employee;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;

public class XmlFrameworkConverterTest
{

	/**
	 * Test method for {@link XmlFrameworkConverter#jacksonToJaxb(String, Class)}
	 */
	@Test
	public void testJacksonToJaxb() throws JsonProcessingException
	{
		String actual;
		String expected;
		expected = "<Club>\n" + "  <personsList>\n" + "    <personsList>\n"
			+ "      <name>Lea</name>\n" + "      <gender>woman</gender>\n"
			+ "      <married>false</married>\n" + "      <nickname>princess</nickname>\n"
			+ "    </personsList>\n" + "    <personsList>\n" + "      <name>Luke</name>\n"
			+ "      <gender>man</gender>\n" + "      <married>false</married>\n"
			+ "      <nickname>wannabejedi</nickname>\n" + "    </personsList>\n"
			+ "  </personsList>\n" + "  <name>StarPiece</name>\n"
			+ "  <location>Greece/Katerini</location>\n" + "</Club>\n";
		actual = XmlFrameworkConverter.jacksonToJaxb(expected, Club.class);

		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<ns2:club xmlns:ns2=\"io.github.astrapi69.jaxb\">\n" + "    <personList>\n"
			+ "        <person>\n" + "            <name>Lea</name>\n"
			+ "            <gender>woman</gender>\n" + "            <married>false</married>\n"
			+ "            <nickname>princess</nickname>\n" + "        </person>\n"
			+ "        <person>\n" + "            <name>Luke</name>\n"
			+ "            <gender>man</gender>\n" + "            <married>false</married>\n"
			+ "            <nickname>wannabejedi</nickname>\n" + "        </person>\n"
			+ "    </personList>\n" + "    <name>StarPiece</name>\n"
			+ "    <location>Greece/Katerini</location>\n" + "</ns2:club>\n";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlFrameworkConverter#jacksonToXstream(String, Class)}
	 */
	@Test
	public void testJacksonToXstream() throws JsonProcessingException
	{

		String actual;
		String expected;
		expected = "<Employee>\n" + "  <id>23</id>\n" + "  <person>\n" + "    <about/>\n"
			+ "    <gender>FEMALE</gender>\n" + "    <married/>\n" + "    <name>Anna</name>\n"
			+ "    <nickname/>\n" + "  </person>\n" + "  <subOrdinates/>\n" + "</Employee>\n";
		actual = XmlFrameworkConverter.jacksonToXstream(expected, Employee.class);

		expected = "<io.github.astrapi69.test.object.Employee>\n" + "  <id>23</id>\n"
			+ "  <person>\n" + "    <about></about>\n" + "    <gender>FEMALE</gender>\n"
			+ "    <name>Anna</name>\n" + "    <nickname></nickname>\n" + "  </person>\n"
			+ "  <subOrdinates/>\n" + "</io.github.astrapi69.test.object.Employee>";
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlFrameworkConverter#xstreamToJaxb(String)}
	 */
	@Test
	public void testXstreamToJaxb() throws JsonProcessingException
	{
		String actual;
		String expected;

		expected = "<io.github.astrapi69.jaxb.Club>\n" + "  <personsList>\n"
			+ "    <io.github.astrapi69.jaxb.Person>\n" + "      <name>Lea</name>\n"
			+ "      <gender>woman</gender>\n" + "      <married>false</married>\n"
			+ "      <nickname>princess</nickname>\n" + "    </io.github.astrapi69.jaxb.Person>\n"
			+ "    <io.github.astrapi69.jaxb.Person>\n" + "      <name>Luke</name>\n"
			+ "      <gender>man</gender>\n" + "      <married>false</married>\n"
			+ "      <nickname>wannabejedi</nickname>\n"
			+ "    </io.github.astrapi69.jaxb.Person>\n" + "  </personsList>\n"
			+ "  <name>StarPiece</name>\n" + "  <location>Greece/Katerini</location>\n"
			+ "</io.github.astrapi69.jaxb.Club>";
		actual = XmlFrameworkConverter.xstreamToJaxb(expected);

		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
			+ "<ns2:club xmlns:ns2=\"io.github.astrapi69.jaxb\">\n" + "    <personList>\n"
			+ "        <person>\n" + "            <name>Lea</name>\n"
			+ "            <gender>woman</gender>\n" + "            <married>false</married>\n"
			+ "            <nickname>princess</nickname>\n" + "        </person>\n"
			+ "        <person>\n" + "            <name>Luke</name>\n"
			+ "            <gender>man</gender>\n" + "            <married>false</married>\n"
			+ "            <nickname>wannabejedi</nickname>\n" + "        </person>\n"
			+ "    </personList>\n" + "    <name>StarPiece</name>\n"
			+ "    <location>Greece/Katerini</location>\n" + "</ns2:club>\n";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link XmlFrameworkConverter#xstreamToJackson(String)}
	 */
	@Test
	public void testXstreamToJackson() throws JsonProcessingException
	{
		String actual;
		String expected;
		expected = "<io.github.astrapi69.test.object.Employee>\n" + "  <id>23</id>\n"
			+ "  <person>\n" + "    <about></about>\n" + "    <gender>FEMALE</gender>\n"
			+ "    <name>Anna</name>\n" + "    <nickname></nickname>\n" + "  </person>\n"
			+ "  <subOrdinates/>\n" + "</io.github.astrapi69.test.object.Employee>";
		actual = XmlFrameworkConverter.xstreamToJackson(expected);

		expected = "<Employee>\n" + "  <id>23</id>\n" + "  <person>\n" + "    <about></about>\n"
			+ "    <gender>FEMALE</gender>\n" + "    <married/>\n" + "    <name>Anna</name>\n"
			+ "    <nickname></nickname>\n" + "  </person>\n" + "  <subOrdinates/>\n"
			+ "</Employee>\n";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link XmlFrameworkConverter#jaxbToXstream(String, Class)}
	 */
	@Test
	public void testJaxbToXstream()
	{
		String actual;
		String expected;
		File xmlFile;
		// new scenario...
		xmlFile = new File(PathFinder.getSrcTestResourcesDir(), "club-jaxb.xml");
		expected = RuntimeExceptionDecorator
			.decorate(() -> ReadFileExtensions.readFromFile(xmlFile));
		actual = XmlFrameworkConverter.jaxbToXstream(expected, Club.class);

		expected = "<io.github.astrapi69.jaxb.Club>\n" + "  <personsList>\n"
			+ "    <io.github.astrapi69.jaxb.Person>\n" + "      <name>Lea</name>\n"
			+ "      <gender>woman</gender>\n" + "      <married>false</married>\n"
			+ "      <nickname>princess</nickname>\n" + "    </io.github.astrapi69.jaxb.Person>\n"
			+ "    <io.github.astrapi69.jaxb.Person>\n" + "      <name>Luke</name>\n"
			+ "      <gender>man</gender>\n" + "      <married>false</married>\n"
			+ "      <nickname>wannabejedi</nickname>\n"
			+ "    </io.github.astrapi69.jaxb.Person>\n" + "  </personsList>\n"
			+ "  <name>StarPiece</name>\n" + "  <location>Greece/Katerini</location>\n"
			+ "</io.github.astrapi69.jaxb.Club>";
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link XmlFrameworkConverter#jaxbToJackson(String, Class)}
	 */
	@Test
	public void testJaxbToJackson() throws JsonProcessingException
	{
		String actual;
		String expected;
		File xmlFile;
		// new scenario...
		xmlFile = new File(PathFinder.getSrcTestResourcesDir(), "club-jaxb.xml");
		expected = RuntimeExceptionDecorator
			.decorate(() -> ReadFileExtensions.readFromFile(xmlFile));
		actual = XmlFrameworkConverter.jaxbToJackson(expected, Club.class);

		expected = "<Club>\n" + "  <personsList>\n" + "    <personsList>\n"
			+ "      <name>Lea</name>\n" + "      <gender>woman</gender>\n"
			+ "      <married>false</married>\n" + "      <nickname>princess</nickname>\n"
			+ "    </personsList>\n" + "    <personsList>\n" + "      <name>Luke</name>\n"
			+ "      <gender>man</gender>\n" + "      <married>false</married>\n"
			+ "      <nickname>wannabejedi</nickname>\n" + "    </personsList>\n"
			+ "  </personsList>\n" + "  <name>StarPiece</name>\n"
			+ "  <location>Greece/Katerini</location>\n" + "</Club>\n";
		assertEquals(expected, actual);
	}
}
