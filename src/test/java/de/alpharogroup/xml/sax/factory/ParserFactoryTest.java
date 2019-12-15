package de.alpharogroup.xml.sax.factory;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * The unit test class for the class {@link ParserFactory}
 */
public class ParserFactoryTest
{

	/**
	 * Test method for {@link ParserFactory#newSAXParserFactory(boolean)}
	 *
	 * @throws SAXNotSupportedException
	 *             is thrown if the SAX operation not supported
	 * @throws SAXNotRecognizedException
	 *             is thrown if the SAX have unrecognized identifier
	 * @throws ParserConfigurationException
	 *             is thrown if a serious configuration error is indicated
	 */
	@Test
	public final void testNewSAXParserFactory()
		throws SAXNotSupportedException, SAXNotRecognizedException, ParserConfigurationException
	{
		SAXParserFactory saxParserFactory;

		saxParserFactory = ParserFactory.newSAXParserFactory(true);
		assertNotNull(saxParserFactory);
		assertTrue(saxParserFactory.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING));
		assertFalse(saxParserFactory.getFeature(ParserFactory.FEATURE_EXTERNAL_GENERAL_ENTITIES));
		assertTrue(saxParserFactory.getFeature(ParserFactory.FEATURE_DISALLOW_DOCTYPE_DECL));

		saxParserFactory = ParserFactory.newSAXParserFactory(false);
		assertNotNull(saxParserFactory);
		assertFalse(saxParserFactory.getFeature(XMLConstants.FEATURE_SECURE_PROCESSING));
		assertTrue(saxParserFactory.getFeature(ParserFactory.FEATURE_EXTERNAL_GENERAL_ENTITIES));
		assertFalse(saxParserFactory.getFeature(ParserFactory.FEATURE_DISALLOW_DOCTYPE_DECL));

	}

	/**
	 * Test method for {@link SAXParserFactory}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(SAXParserFactory.class);
	}

}
