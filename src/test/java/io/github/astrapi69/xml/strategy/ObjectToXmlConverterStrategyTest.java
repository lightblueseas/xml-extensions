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
package io.github.astrapi69.xml.strategy;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;

import org.testng.annotations.Test;

import io.github.astrapi69.design.pattern.strategy.Strategy;
import io.github.astrapi69.file.create.FileFactory;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.test.object.Person;
import io.github.astrapi69.test.object.enumeration.Gender;
import io.github.astrapi69.throwable.RuntimeExceptionDecorator;
import io.github.astrapi69.xml.api.ObjectToXml;

public class ObjectToXmlConverterStrategyTest
{

	@Test
	public void testStrategy()
	{
		String actual;
		String expected;
		Person person;
		Strategy<String, Object> strategy;
		ObjectToXml objectToXmlConverter;
		File xmlFile;
		// new scenario with xstream ...
		person = Person.builder().gender(Gender.FEMALE).name("Anna").nickname(null).married(null)
			.about(null).build();
		objectToXmlConverter = new io.github.astrapi69.xstream.ObjectToXmlConverter();
		strategy = new ObjectToXmlConverterStrategy(objectToXmlConverter);
		actual = strategy.execute(person);
		expected = "<io.github.astrapi69.test.object.Person>\n" + "  <gender>FEMALE</gender>\n"
			+ "  <name>Anna</name>\n" + "</io.github.astrapi69.test.object.Person>";
		assertNotNull(actual);
		assertEquals(actual, expected);
		// new scenario with xml.jackson ...
		objectToXmlConverter = new io.github.astrapi69.xml.jackson.ObjectToXmlConverter();
		strategy = new ObjectToXmlConverterStrategy(objectToXmlConverter);
		actual = strategy.execute(person);
		expected = "<Person>" + System.lineSeparator() + "  <about/>" + System.lineSeparator()
			+ "  <gender>FEMALE</gender>" + System.lineSeparator() + "  <married/>"
			+ System.lineSeparator() + "  <name>Anna</name>" + System.lineSeparator()
			+ "  <nickname/>" + System.lineSeparator() + "</Person>" + System.lineSeparator();
		assertNotNull(actual);
		assertEquals(actual, expected);

		xmlFile = PathFinder.getRelativePath(PathFinder.getSrcTestResourcesDir(), "newtest.xml");
		RuntimeExceptionDecorator.decorate(() -> FileFactory.newFile(xmlFile));
	}
}
