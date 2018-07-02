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
