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

import static org.testng.Assert.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.collections.iterators.EnumerationIterator;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.lang.ClassExtensions;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * The unit test class for the class {@link XmlResourceBundleControl}
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class XmlResourceBundleControlTest
{

	/** The {@link XmlResourceBundleControl} instance for unit tests. */
	XmlResourceBundleControl xmlResourceBundleControl;

	/** The properties for unit tests. */
	Properties properties;
	
	/** The properties german for unit tests. */
	Properties propertiesGerman;
	
	/**
	 * Sets up method will be invoked before every unit test method
	 *
	 * @throws Exception
	 *             is thrown if an exception occurs
	 */
	@BeforeMethod
	protected void setUp() throws Exception
	{
		xmlResourceBundleControl = new XmlResourceBundleControl();	
		properties = new Properties();	
		properties.setProperty("global.email.label", "Email");
		properties.setProperty("global.enter.your.email.label", "Enter your email");
		properties.setProperty("global.password.label", "Password");
		properties.setProperty("global.enter.your.password.label", "Enter your password");
		properties.setProperty("password.forgotten.label", "Forgot your password?");
		propertiesGerman = new Properties();	
		propertiesGerman.setProperty("global.email.label", "Email");
		propertiesGerman.setProperty("global.enter.your.email.label", "Gib deine Email ein");
		propertiesGerman.setProperty("global.password.label", "Passwort");
		propertiesGerman.setProperty("global.enter.your.password.label", "Gib dein Passwort ein");
		propertiesGerman.setProperty("password.forgotten.label", "Passwort vergessen?");
	}

	/**
	 * Tear down method will be invoked after every unit test method
	 *
	 * @throws Exception
	 *             is thrown if an exception occurs
	 */
	@AfterMethod
	protected void tearDown() throws Exception
	{
		xmlResourceBundleControl = null;
		properties = null;
		propertiesGerman = null;
	}

	/**
	 * Test method for {@link XmlResourceBundleControl#getFormats(String)}
	 */
	@Test
	public void testGetFormats()
	{
		List<String> actual;
		List<String> expected;
		actual = xmlResourceBundleControl.getFormats("foo");
		expected = ListFactory.newArrayList("xml");
		assertEquals(actual, expected);
	}
	
	/**
	 * Test method for {@link XmlResourceBundleControl#getFormats(String)} that throws a NullPointerException 
	 */
	@Test(expectedExceptions = {NullPointerException.class})
	public void testGetFormatsThrowNullPointerException()
	{
		xmlResourceBundleControl.getFormats(null);
	}

	/**
	 * Test method for
	 * {@link XmlResourceBundleControl#newBundle(String, Locale, String, ClassLoader, boolean)}
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testNewBundle() throws IllegalAccessException, InstantiationException, IOException
	{
		ResourceBundle actual;
		ResourceBundle expected;
		String baseName;
		Locale locale;
		String format;
		ClassLoader loader;
		boolean reload;

		baseName = "SigninPanel";
		locale = Locale.GERMAN;
		format = "xml";
		loader = ClassExtensions.getClassLoader();
		reload = true;
		// create the bundle over the factory method...
		actual = xmlResourceBundleControl.newBundle(baseName, locale, format, loader, reload);
		assertNotNull(actual);
		// create the bundle over the static method...
		expected = ResourceBundle.getBundle(baseName, locale, loader, xmlResourceBundleControl);
		assertNotNull(expected);
		Enumeration<String> keys = actual.getKeys();
		EnumerationIterator<String> iterator = new EnumerationIterator<>(keys);
		while( iterator.hasNext()) {
			String key = iterator.next(); 
			assertTrue(properties.containsKey(key));
			String actualValue = actual.getString(key);
			String expectedValue = expected.getString(key);
			assertEquals(actualValue, expectedValue);
		}	
		
		// null case with wrong format...
		baseName = "SigninPanel";
		locale = Locale.GERMAN;
		format = "txt";
		loader = ClassExtensions.getClassLoader();
		reload = true;
		// create the bundle over the factory method...
		actual = xmlResourceBundleControl.newBundle(baseName, locale, format, loader, reload);
		assertNull(actual);	
		
		// null case with wrong baseName...
		baseName = "SigninPanelFOO";
		locale = Locale.GERMAN;
		format = "xml";
		loader = ClassExtensions.getClassLoader();
		reload = true;
		// create the bundle over the factory method...
		actual = xmlResourceBundleControl.newBundle(baseName, locale, format, loader, reload);
		assertNull(actual);
		
	}

	/**
	 * Test method for
	 * {@link XmlResourceBundleControl#newBundle(String, Locale, String, ClassLoader, boolean)} that throws a NullPointerException 
	 *
	 * @throws IllegalAccessException
	 *             the illegal access exception
	 * @throws InstantiationException
	 *             the instantiation exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = {NullPointerException.class})
	public void testNewBundleThrowNullPointerException() throws IllegalAccessException, InstantiationException, IOException 
	{
		Locale locale;
		String format;
		ClassLoader loader;
		boolean reload;

		locale = Locale.GERMAN;
		format = "xml";
		loader = ClassExtensions.getClassLoader();
		reload = true;
		// create the bundle over the factory method...
		xmlResourceBundleControl.newBundle(null, locale, format, loader, reload);			
	}

}
