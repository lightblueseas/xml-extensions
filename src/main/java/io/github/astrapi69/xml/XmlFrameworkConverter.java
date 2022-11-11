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
package io.github.astrapi69.xml;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * The class {@link XmlFrameworkConverter} provides algorithms that can convert xml strings from
 * frameworks like jackson, xstream and jaxb to another framework xml string
 */
public class XmlFrameworkConverter
{

	/**
	 * Converts the given jackson style xml string(that was previously converted with jackson) to a
	 * xStream style xml string
	 *
	 * @param <T>
	 *            the generic type of the object
	 * @param jacksonXml
	 *            the jackson style xml string(that was previously converted with jackson)
	 * @param clazz
	 *            the class
	 * @return the xStream style xml string
	 * @throws JsonProcessingException
	 *             is thrown when processing xml content that are not pure I/O problems
	 */
	public static <T> String jacksonToXstream(String jacksonXml, final Class<T> clazz)
		throws JsonProcessingException
	{
		T object = io.github.astrapi69.xml.jackson.XmlToObjectExtensions.toObject(jacksonXml,
			clazz);
		return io.github.astrapi69.xstream.ObjectToXmlExtensions.toXml(object);
	}

	/**
	 * Converts the given jackson style xml string(that was previously converted with jackson) to a
	 * jaxb style xml string
	 *
	 * @param <T>
	 *            the generic type of the object
	 * @param jacksonXml
	 *            the jackson style xml string(that was previously converted with jackson)
	 * @param clazz
	 *            the class
	 * @return the jaxb style xml string
	 * @throws JsonProcessingException
	 *             is thrown when processing xml content that are not pure I/O problems
	 */
	public static <T> String jacksonToJaxb(String jacksonXml, final Class<T> clazz)
		throws JsonProcessingException
	{
		T object = io.github.astrapi69.xml.jackson.XmlToObjectExtensions.toObject(jacksonXml,
			clazz);
		return io.github.astrapi69.jaxb.ObjectToXmlExtensions.toXml(object);
	}

	/**
	 * Converts the given xStream style xml string(that was previously converted with xStream) to a
	 * jackson style xml string
	 *
	 * @param <T>
	 *            the generic type of the object
	 * @param xStreamXml
	 *            the xStream style xml string(that was previously converted with xStream)
	 * @return the jackson style xml string
	 * @throws JsonProcessingException
	 *             is thrown when processing xml content that are not pure I/O problems
	 */
	public static <T> String xstreamToJackson(String xStreamXml) throws JsonProcessingException
	{
		T object = io.github.astrapi69.xstream.XmlToObjectExtensions.toObject(xStreamXml);
		return io.github.astrapi69.xml.jackson.ObjectToXmlExtensions.toXml(object);
	}

	/**
	 * Converts the given xStream style xml string(that was previously converted with xStream) to a
	 * jaxb style xml string
	 *
	 * @param <T>
	 *            the generic type of the object
	 * @param xStreamXml
	 *            the xStream style xml string(that was previously converted with xStream)
	 * @return the jaxb style xml string
	 */
	public static <T> String xstreamToJaxb(String xStreamXml)
	{
		T object = io.github.astrapi69.xstream.XmlToObjectExtensions.toObject(xStreamXml);
		return io.github.astrapi69.jaxb.ObjectToXmlExtensions.toXml(object);
	}

	/**
	 * Converts the given jaxb style xml string(that was previously converted with jaxb) to a
	 * xStream style xml string
	 *
	 * @param <T>
	 *            the generic type of the object
	 * @param jaxbXml
	 *            the jaxb style xml string(that was previously converted with jaxb)
	 * @param clazz
	 *            the class
	 * @return the xStream style xml string
	 */
	public static <T> String jaxbToXstream(String jaxbXml, final Class<T> clazz)
	{
		T object = io.github.astrapi69.jaxb.XmlToObjectExtensions.toObject(jaxbXml, clazz);
		return io.github.astrapi69.xstream.ObjectToXmlExtensions.toXml(object);
	}

	/**
	 * Converts the given jaxb style xml string(that was previously converted with jaxb) to a
	 * jackson style xml string
	 *
	 * @param jaxbXml
	 *            the jaxb style xml string(that was previously converted with jaxb)
	 * @param clazz
	 *            the class
	 * @return the jackson style xml string
	 * @param <T>
	 *            the generic type of the object
	 * @throws JsonProcessingException
	 *             is thrown when processing xml content that are not pure I/O problems
	 */
	public static <T> String jaxbToJackson(String jaxbXml, final Class<T> clazz)
		throws JsonProcessingException
	{
		T object = io.github.astrapi69.jaxb.XmlToObjectExtensions.toObject(jaxbXml, clazz);
		return io.github.astrapi69.xml.jackson.ObjectToXmlExtensions.toXml(object);
	}
}
