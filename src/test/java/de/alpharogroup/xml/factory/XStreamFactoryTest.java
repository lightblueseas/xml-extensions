package de.alpharogroup.xml.factory;

import static org.testng.Assert.assertNotNull;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * The unit test class for the class {@link XStreamFactory}
 */
public class XStreamFactoryTest
{

	/**
	 * Test method for {@link XStreamFactory#newXStream()}
	 */
	@Test
	public void testNewXStream()
	{
		XStream xStream = XStreamFactory.newXStream();
		assertNotNull(xStream);
	}

	/**
	 * Test method for {@link XStreamFactory#newXStream(com.thoughtworks.xstream.io.HierarchicalStreamDriver)}
	 */
	@Test
	public void testNewXStreamWithHierarchicalStreamDriver()
	{
		XStream xStream = XStreamFactory.newXStream(new StaxDriver());
		assertNotNull(xStream);
	}

	/**
	 * Test method for {@link XStreamFactory}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XStreamFactory.class);
	}

}
