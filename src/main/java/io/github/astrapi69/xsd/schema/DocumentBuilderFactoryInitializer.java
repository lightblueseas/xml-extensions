package io.github.astrapi69.xsd.schema;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;

import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/**
 * The class {@link DocumentBuilderFactoryInitializer} provides method for initialize
 * {@link DocumentBuilderFactory}, {@link DocumentBuilder}, {@link Document} and {@link DOMSource}
 * objects
 * 
 * @deprecated use instead the same named class in the module xml-base
 */
public class DocumentBuilderFactoryInitializer
{

	/** The Constant DOCUMENT_BUILDER_FACTORY_KEY. */
	private static final String DOCUMENT_BUILDER_FACTORY_KEY = "javax.xml.parsers.DocumentBuilderFactory";

	/** The Constant DOCUMENT_BUILDER_FACTORY_VALUE. */
	private static final String DOCUMENT_BUILDER_FACTORY_VALUE = "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl";

	/** The Constant HTTP_WWW_W3_ORG_2001_XML_SCHEMA. */
	private static final String HTTP_WWW_W3_ORG_2001_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	/** The Constant SCHEMA_LANGUAGE_KEY. */
	private static final String SCHEMA_LANGUAGE_KEY = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	/** The Constant SCHEMA_SOURCE_KEY. */
	private static final String SCHEMA_SOURCE_KEY = "http://java.sun.com/xml/jaxp/properties/schemaSource";

	/**
	 * Gets the document builder factory.
	 *
	 * @param schema
	 *            the schema
	 * @param schemaLanguage
	 *            the schema language
	 * @param documentBuilderFactoryName
	 *            the name of the document builder factory
	 * @param namespaceAwareness
	 *            the flag if the namespace should be aware
	 * @param factoryValidating
	 *            the flag if the factory should validate
	 * @return the document builder factory
	 */
	public static DocumentBuilderFactory newDocumentBuilderFactory(final String schema,
		final String schemaLanguage, final String documentBuilderFactoryName,
		boolean namespaceAwareness, boolean factoryValidating)
	{
		if (documentBuilderFactoryName != null)
		{
			System.setProperty(DOCUMENT_BUILDER_FACTORY_KEY, documentBuilderFactoryName);
		}
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(namespaceAwareness);
		factory.setValidating(factoryValidating);

		if (schemaLanguage != null)
		{
			factory.setAttribute(SCHEMA_LANGUAGE_KEY, schemaLanguage);
		}
		if (schema != null)
		{
			factory.setAttribute(SCHEMA_SOURCE_KEY, schema);
		}
		return factory;
	}

	/**
	 * Factory method for create a new {@link DocumentBuilderFactory} object
	 *
	 * @param schema
	 *            the schema
	 * @return the document builder factory
	 */
	public static DocumentBuilderFactory newDocumentBuilderFactory(final String schema)
	{
		return newDocumentBuilderFactory(schema, HTTP_WWW_W3_ORG_2001_XML_SCHEMA,
			DOCUMENT_BUILDER_FACTORY_VALUE, true, true);
	}

	/**
	 * Factory method for create a new {@link DocumentBuilder} object
	 *
	 * @param schema
	 *            the schema
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 */
	public static DocumentBuilder newDocumentBuilder(final String schema)
		throws ParserConfigurationException
	{
		final DocumentBuilderFactory factory = DocumentBuilderFactoryInitializer
			.newDocumentBuilderFactory(schema);
		return factory.newDocumentBuilder();
	}

	/**
	 * Factory method for create a new {@link DocumentBuilder} object
	 *
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 */
	public static DocumentBuilder newDocumentBuilder() throws ParserConfigurationException
	{
		final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setNamespaceAware(true);
		return documentBuilderFactory.newDocumentBuilder();
	}

	/**
	 * Gets the {@link Document} from the given xml file
	 *
	 * @param xml
	 *            the xml file as string
	 * @return the node list
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             is thrown if a sax parse error occurs
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Document newDocument(File xml)
		throws ParserConfigurationException, SAXException, IOException
	{
		return DocumentBuilderFactoryInitializer.newDocumentBuilder().parse(xml);
	}

	/**
	 * Gets the {@link Document} from the given xml string
	 *
	 * @param xml
	 *            the xml file as string
	 * @return the node list
	 * @throws ParserConfigurationException
	 *             the parser configuration exception
	 * @throws SAXException
	 *             is thrown if a sax parse error occurs
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Document newDocument(String xml)
		throws ParserConfigurationException, SAXException, IOException
	{
		return DocumentBuilderFactoryInitializer.newDocumentBuilder().parse(xml);
	}

	/**
	 * Parses the given xml file and the given error handler
	 *
	 * @param xml
	 *            the xml
	 * @param errorHandler
	 *            the error handler
	 * @return the document
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static Document parse(final File xml, final ErrorHandler errorHandler)
		throws SAXException, ParserConfigurationException, IOException
	{
		final DocumentBuilder builder = DocumentBuilderFactoryInitializer
			.newDocumentBuilder(xml.getName());
		builder.setErrorHandler(errorHandler);
		return builder.parse(xml);
	}

	/**
	 * Factory method for create a new {@link DOMSource} object with the given error handler
	 *
	 * @param xml
	 *            the xml
	 * @param errorHandler
	 *            the error handler
	 * @return the dOM source
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 * @throws ParserConfigurationException
	 *             if a DocumentBuilder cannot be created which satisfies the configuration
	 *             requested.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static DOMSource newDOMSource(final File xml, final ErrorHandler errorHandler)
		throws SAXException, ParserConfigurationException, IOException
	{
		return new DOMSource(DocumentBuilderFactoryInitializer.parse(xml, errorHandler));
	}
}
