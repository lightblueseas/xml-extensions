package io.github.astrapi69.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

public class XmlFrameworkConverter
{

	public static <T> String jacksonToXstream(String jacksonXml, final Class<T> clazz)
		throws JsonProcessingException
	{
		T object = io.github.astrapi69.xml.jackson.XmlToObjectExtensions.toObject(jacksonXml,
			clazz);
		return io.github.astrapi69.xstream.ObjectToXmlExtensions.toXml(object);
	}

	public static <T> String xstreamToJackson(String xstreamXml, final Class<T> clazz)
			throws JsonProcessingException
	{
		T object = io.github.astrapi69.xstream.XmlToObjectExtensions.toObject(xstreamXml);
		return io.github.astrapi69.xml.jackson.ObjectToXmlExtensions.toXml(object);
	}

}
