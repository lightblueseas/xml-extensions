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
