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
package io.github.astrapi69.xml.crypto.file;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.codec.DecoderException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.google.common.collect.BiMap;
import com.thoughtworks.xstream.XStream;

import io.github.astrapi69.collection.map.MapFactory;
import io.github.astrapi69.collection.pair.KeyValuePair;
import io.github.astrapi69.crypt.data.obfuscation.rule.ObfuscationOperationRule;
import io.github.astrapi69.file.search.PathFinder;
import io.github.astrapi69.xstream.factory.XStreamFactory;

/**
 * The unit test class for the class {@link XmlDecryptionExtensions}
 */
public class XmlDecryptionExtensionsTest
{

	BiMap<Character, ObfuscationOperationRule<Character, Character>> actual;
	/** The aliases for the {@link XStream} object */
	Map<String, Class<?>> aliases;
	BiMap<Character, ObfuscationOperationRule<Character, Character>> expected;

	File xmlDir;
	File xmlFile;
	/** The {@link XStream} object */
	XStream xStream;
	{
		aliases = MapFactory.newLinkedHashMap();
		aliases.put("KeyValuePair", KeyValuePair.class);
		aliases.put("ObfuscationOperationRule", ObfuscationOperationRule.class);
		xStream = XStreamFactory.newXStream(XStreamFactory.newXStream(), aliases,
			"io.github.astrapi69.**", "com.google.common.collect.**");
	}

	/**
	 * Test method for {@link XmlDecryptionExtensions#readFromFileAsXmlAndHex(XStream, Map, File)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws DecoderException
	 *             is thrown if an odd number or illegal of characters is supplied
	 */
	@Test
	public void testReadFromFileAsXmlAndHex() throws IOException, DecoderException
	{
		xmlDir = new File(PathFinder.getSrcTestResourcesDir(), "xml");
		xmlFile = new File(xmlDir, "foo.sor");
		expected = ObfuscationOperationTestData.getFirstBiMapObfuscationOperationRules();
		actual = XmlDecryptionExtensions.readFromFileAsXmlAndHex(xStream, aliases, xmlFile);
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlDecryptionExtensions#readFromFileAsXmlAndHex(Map, File, String...)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws DecoderException
	 *             is thrown if an odd number or illegal of characters is supplied
	 */
	@Test
	public void testReadFromFileAsXmlAndHexAliasesAndAllowTypesByWildcard()
		throws IOException, DecoderException
	{
		xmlDir = new File(PathFinder.getSrcTestResourcesDir(), "xml");
		xmlFile = new File(xmlDir, "foo.sor");
		expected = ObfuscationOperationTestData.getFirstBiMapObfuscationOperationRules();
		actual = XmlDecryptionExtensions.readFromFileAsXmlAndHex(aliases, xmlFile,
			"io.github.astrapi69.**");
		assertEquals(actual, expected);
	}

	/**
	 * Test method for
	 * {@link XmlDecryptionExtensions#readFromFileAsXmlAndHex(XStream, Map, File, String)}
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws DecoderException
	 *             is thrown if an odd number or illegal of characters is supplied
	 */
	@Test
	public void testReadFromFileAsXmlAndHexAndCharset() throws IOException, DecoderException
	{
		xmlDir = new File(PathFinder.getSrcTestResourcesDir(), "xml");
		xmlFile = new File(xmlDir, "foo.sor");
		expected = ObfuscationOperationTestData.getFirstBiMapObfuscationOperationRules();
		actual = XmlDecryptionExtensions.readFromFileAsXmlAndHex(xStream, aliases, xmlFile,
			"UTF-8");
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XmlDecryptionExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XmlDecryptionExtensions.class);
	}

}
