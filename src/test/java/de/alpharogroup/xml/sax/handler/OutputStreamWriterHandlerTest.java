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
package de.alpharogroup.xml.sax.handler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.testng.annotations.Test;

import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.io.StreamExtensions;

/**
 * The unit test class for the class {@link OutputStreamWriterHandler}
 */
public class OutputStreamWriterHandlerTest
{

	/**
	 * Test method for {@link OutputStreamWriterHandler#write(String)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testWrite() throws IOException
	{
		File testResDir;
		String templateName;
		File outputFile;
		File parseFile;
		Writer out;
		OutputStream outputStream;
		SAXParserFactory factory;

		// The resources destination dir
		testResDir = PathFinder.getSrcTestResourcesDir();
		outputFile = new File(testResDir, "foo.out");
		outputStream = StreamExtensions.getOutputStream(outputFile, true);
		templateName = "LoginAsProvider";
		parseFile = new File(testResDir, templateName + ".xml");

		// Use the default (non-validating) parser
		factory = SAXParserFactory.newInstance();
		try
		{
			// Set up output stream
			out = new OutputStreamWriter(outputStream, "UTF8");
			final WriterHandler handler = new OutputStreamWriterHandler(out);
			// Parse the input
			final SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(parseFile, handler);
		}
		catch (final Throwable t)
		{
			t.printStackTrace();
		}
		outputFile.deleteOnExit();
	}

}
