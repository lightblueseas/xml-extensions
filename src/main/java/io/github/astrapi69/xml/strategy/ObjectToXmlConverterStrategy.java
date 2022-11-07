package io.github.astrapi69.xml.strategy;

import io.github.astrapi69.design.pattern.strategy.Strategy;
import io.github.astrapi69.xml.api.ObjectToXml;

/**
 * The class {@link ObjectToXmlConverterStrategy} provides methods for read an encrypted file and
 * decrypt xml data.
 */
public class ObjectToXmlConverterStrategy implements Strategy<String, Object>
{

	/**
	 * The object that converts the objects to xml {@link String} objects
	 */
	ObjectToXml objectToXml;

	/**
	 * Creates a new instance of {@link ObjectToXmlConverterStrategy} object
	 * 
	 * @param objectToXml
	 *            The object that converts the objects to xml {@link String} objects
	 */
	public ObjectToXmlConverterStrategy(ObjectToXml objectToXml)
	{
		this.objectToXml = objectToXml;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String execute(Object model)
	{
		return objectToXml.toXml(model);
	}
}
