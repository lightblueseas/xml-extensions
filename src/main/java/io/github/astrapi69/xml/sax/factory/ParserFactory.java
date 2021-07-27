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
package io.github.astrapi69.xml.sax.factory;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 * A factory for creating {@link SAXParserFactory} objects
 */
public final class ParserFactory
{

	/** The Constant for the key of the feature to disallow doctype declarations */
	public static final String FEATURE_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";

	/** The Constant for the key of the feature to allow external general entities */
	public static final String FEATURE_EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";

	private ParserFactory()
	{
	}

	/**
	 * Factory method for create a new {@link SAXParserFactory} with a flag for deactivate the
	 * parser for protection from xml-bomb
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
			factory.setFeature(ParserFactory.FEATURE_EXTERNAL_GENERAL_ENTITIES, false);
			factory.setFeature(ParserFactory.FEATURE_DISALLOW_DOCTYPE_DECL, true);
		}
		return factory;
	}
}
