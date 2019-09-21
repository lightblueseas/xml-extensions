package de.alpharogroup.xml.sax.factory;

import lombok.experimental.UtilityClass;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

/**
 * A factory for creating {@link SAXParserFactory} objects
 */
@UtilityClass
public class ParserFactory
{

	/**
	 * Factory method for create a new {@link SAXParserFactory} with a flag for deactivate the
	 * parser
	 *
	 * @param withoutParser
	 *            the without parser
	 * @return the new {@link SAXParserFactory} object
	 * @throws SAXNotSupportedException
	 *             is thrown if the SAX operation not supported
	 * @throws SAXNotRecognizedException
	 *             is thrown if the SAX have unrecognized identifier
	 * @throws ParserConfigurationException
	 *             is thrown if a serious configuration error is indicated
	 */
	public static SAXParserFactory newSAXParserFactory(boolean withoutParser)
		throws SAXNotSupportedException, SAXNotRecognizedException, ParserConfigurationException
	{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		if (withoutParser)
		{
			factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
			factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
		}
		return factory;
	}
}
