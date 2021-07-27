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
package io.github.astrapi69.xml.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.JacksonXmlModule;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * The factory class {@link XmlMapperFactory} for creating {@link ObjectMapper} objects for
 * serializing java beans to xml string and deserialize xml string to java beans.
 */
public final class XmlMapperFactory
{
	private XmlMapperFactory()
	{
	}

	/**
	 * Factory method for create a new {@link ObjectMapper}
	 *
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newXmlMapper()
	{
		return new XmlMapper();
	}

	/**
	 * Factory method for create a new {@link ObjectMapper}.
	 *
	 * @param module
	 *            the module
	 * @return the new {@link ObjectMapper}
	 */
	public static ObjectMapper newXmlMapper(JacksonXmlModule module)
	{
		return new XmlMapper(module);
	}

}
