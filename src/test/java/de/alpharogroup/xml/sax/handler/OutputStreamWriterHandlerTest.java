/**
 * The MIT License
 *
 * Copyright (C) 2007 Asterios Raptis
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
package de.alpharogroup.xml.sax.handler;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;

public class OutputStreamWriterHandlerTest
{

	@BeforeMethod
	public void setUp() throws Exception
	{
	}

	@AfterMethod
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testWrite()
	{
		// The resources destination dir
		final File testResDir = PathFinder.getSrcTestResourcesDir();
		final String templateName = "LoginAsProvider";
		final File parseFile = new File(testResDir, templateName + ".xml");
		Writer out;
		// Use the default (non-validating) parser
		final SAXParserFactory factory = SAXParserFactory.newInstance();
		try
		{
			// Set up output stream
			out = new OutputStreamWriter(System.out, "UTF8");
			final WriterHandler handler = new OutputStreamWriterHandler(out);
			// Parse the input
			final SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(parseFile, handler);
		}
		catch (final Throwable t)
		{
			t.printStackTrace();
		}
	}


}
