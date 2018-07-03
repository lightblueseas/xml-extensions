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
package de.alpharogroup.xml.resourcebundle;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.testng.annotations.Test;

import de.alpharogroup.collections.iterators.EnumerationIterator;
import de.alpharogroup.file.search.PathFinder;

/**
 * The unit test class for the class {@link XmlResourceBundle}
 */
public class XmlResourceBundleTest
{

	File propertiesXml = new File(PathFinder.getSrcTestResourcesDir(),
		"SigninPanel.xml");

	/**
	 * Test method for {@link XmlResourceBundle#getKeys()}.
	 *
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public final void testGetKeys() throws FileNotFoundException, IOException
	{
		Properties properties;
		properties = new Properties();
		properties.setProperty("global.email.label", "Email");
		properties.setProperty("global.enter.your.email.label", "Enter your email");
		properties.setProperty("global.password.label", "Password");
		properties.setProperty("global.enter.your.password.label", "Enter your password");
		properties.setProperty("password.forgotten.label", "Forgot your password?");
		XmlResourceBundle model = new XmlResourceBundle(new FileInputStream(propertiesXml));
		assertNotNull(model);
		Enumeration<String> keys = model.getKeys();
		EnumerationIterator<String> iterator = new EnumerationIterator<>(keys);
		while( iterator.hasNext()) {
			String key = iterator.next(); 
			assertTrue(properties.containsKey(key));
			String actual = model.getString(key);
			String expected = properties.getProperty(key);
			assertEquals(expected, actual);
		}
	}

	/**
	 * Test method for {@link XmlResourceBundle} constructors.
	 *
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public final void testConstructors() throws FileNotFoundException, IOException
	{
		String actual;
		String expected;
		XmlResourceBundle model = new XmlResourceBundle(new FileInputStream(propertiesXml));
		assertNotNull(model);
		actual = model.getString("global.enter.your.email.label");
		expected = "Enter your email";
		assertEquals(expected, actual);
	}

}
