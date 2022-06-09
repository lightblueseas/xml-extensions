package io.github.astrapi69.xsd.schema;

import java.io.File;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

/**
 * The class {@link SchemaInitializer} provides method for initialize {@link Schema} objects
 * 
 * @deprecated use instead the same named class in the module xml-base
 */
public class SchemaInitializer
{

	/** The Constant HTTP_WWW_W3_ORG_2001_XML_SCHEMA. */
	private static final String HTTP_WWW_W3_ORG_2001_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	/**
	 * Gets the schema.
	 *
	 * @param xsd
	 *            the xsd
	 * @param errorHandler
	 *            the error handler
	 * @return the schema
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 */
	public static Schema newSchema(final File xsd, final ErrorHandler errorHandler)
		throws SAXException
	{
		// Create a new instance for an XSD-aware SchemaFactory
		final SchemaFactory schemaFactory = SchemaFactory
			.newInstance(HTTP_WWW_W3_ORG_2001_XML_SCHEMA);

		// Set the ErrorHandler implementation.
		schemaFactory.setErrorHandler(errorHandler);

		// get the custom xsd schema that describes
		// the required format for my XML files.
		return schemaFactory.newSchema(xsd);
	}

	/**
	 * Gets the schema.
	 *
	 * @param xsd
	 *            the xsd
	 * @return the schema
	 * @throws SAXException
	 *             If a SAX error occurs during parsing.
	 */
	public static Schema newSchema(final File xsd) throws SAXException
	{
		// Create a new instance for an XSD-aware SchemaFactory
		final SchemaFactory schemaFactory = SchemaFactory
			.newInstance(HTTP_WWW_W3_ORG_2001_XML_SCHEMA);
		// get the custom xsd schema that describes
		// the required format for my XML files.
		return schemaFactory.newSchema(xsd);
	}
}
