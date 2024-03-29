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

import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

import io.github.astrapi69.xml.api.Xmlable;
import io.github.astrapi69.xstream.ObjectToXmlExtensions;

public class TestBox implements Xmlable
{

	private PrivateKey privateKey;

	public TestBox(PrivateKey privateKey)
	{
		this.privateKey = privateKey;
	}

	public PrivateKey getPrivateKey()
	{
		return privateKey;
	}

	public void setPrivateKey(PrivateKey privateKey)
	{
		this.privateKey = privateKey;
	}

	public String toXml()
	{
		final String lqSimpleName = this.getClass().getSimpleName().toLowerCase();
		final Map<String, Class<?>> aliases = new HashMap<>();
		aliases.put(lqSimpleName, this.getClass());
		return ObjectToXmlExtensions.toXml(this, aliases);
	}
}
