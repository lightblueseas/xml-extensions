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
package de.alpharogroup.xsl.transform;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.AbstractTestCase;
import de.alpharogroup.file.read.ReadFileExtensions;
import de.alpharogroup.file.search.PathFinder;
import de.alpharogroup.io.StreamExtensions;

/**
 * The unit test class for the class {@link XsltTransformerExtensions}
 */
public class XsltTransformerExtensionsTest extends AbstractTestCase<String, String>
{	

	/**
	 * Sets up method will be invoked before every unit test method
	 *
	 * @throws Exception
	 *             is thrown if an exception occurs
	 */
	@BeforeMethod
	protected void setUp() throws Exception
	{
		super.setUp();
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><birthdates>"
			+ "\r\n"
			+ "    <birthdate>\r\n" + "        <id>1</id>\r\n" + "        <date>19:07</date>\r\n"
			+ "    </birthdate>\r\n" + "    <birthdate>\r\n" + "        <id>2</id>\r\n"
			+ "        <date>13:48</date>\r\n" + "    </birthdate>\r\n" + "    <birthdate>\r\n"
			+ "        <id>3</id>\r\n" + "        <date>08:40</date>\r\n" + "    </birthdate>\r\n"
			+ "</birthdates>\r\n";
	}

	/**
	 * Tear down method will be invoked after every unit test method
	 *
	 * @throws Exception
	 *             is thrown if an exception occurs
	 */
	@AfterMethod
	protected void tearDown() throws Exception
	{
		actual = null;
		expected = null;
	}
	/**
	 * Test method for {@link XsltTransformerExtensions#transform(File, File, OutputStream)}
	 *
	 * @throws TransformerConfigurationException the transformer configuration exception
	 * @throws TransformerFactoryConfigurationError the transformer factory configuration error
	 * @throws TransformerException the transformer exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetTransformerFile() throws TransformerConfigurationException,
		TransformerFactoryConfigurationError, TransformerException, IOException
	{
		final File resDestDir = PathFinder.getSrcTestResourcesDir();
		final String[] dirsAndFilename = { "de", "alpharogroup", "xsl", "transform",
				"birthdates.xml" };
		final File xmlFile = PathFinder.getRelativePathTo(resDestDir,
			Arrays.asList(dirsAndFilename));
		final File xsltFile = PathFinder.getRelativePathTo(resDestDir, "\\.",
			"de.alpharogroup.xsl.transform", "functions.xsl");
		final File outputFile = PathFinder.getRelativePathTo(resDestDir, "\\.",
			"de.alpharogroup.xsl.transform", "output.xml");
		XsltTransformerExtensions.transform(xmlFile, xsltFile, new FileOutputStream(outputFile));
		actual = ReadFileExtensions.readFromFile(outputFile);
		actual = StringUtils.remove(actual, '\r');
		actual = StringUtils.remove(actual, '\n');
		expected = StringUtils.remove(expected, '\r');
		expected = StringUtils.remove(expected, '\n');
		AssertJUnit.assertTrue("", expected.equals(actual));
	}

	@Test(enabled = false)
	public void testGetTransformerSource()
	{
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled = false)
	public void testGetTransformerString()
	{
		throw new RuntimeException("Test not implemented");
	}

	@Test(enabled = false)
	public void testTransformFileFileOutputStream()
	{
		throw new RuntimeException("Test not implemented");
	}

	/**
	 * Test method for {@link XsltTransformerExtensions#transform(Source, Source, OutputStream)}
	 *
	 * @throws TransformerException the transformer exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Test(enabled = true)
	public void testTransformSourceSourceOutputStream() throws TransformerException, IOException
	{
		final File resDestDir = PathFinder.getSrcTestResourcesDir();
		final String[] dirsAndFilename = { "de", "alpharogroup", "xsl", "transform",
				"birthdates.xml" };
		final File xmlFile = PathFinder.getRelativePath(resDestDir, dirsAndFilename);
		final File xsltFile = PathFinder.getRelativePathTo(resDestDir, "\\.",
			"de.alpharogroup.xsl.transform", "functions.xsl");
		final InputStream is = StreamExtensions.getInputStream(xsltFile);
		final Source xsltSource = new StreamSource(is);

		final InputStream xmlIs = StreamExtensions.getInputStream(xmlFile);
		final File outputFile = PathFinder.getRelativePathTo(resDestDir, "\\.",
			"de.alpharogroup.xsl.transform", "data_02_output.xml");
		final OutputStream output = StreamExtensions.getOutputStream(outputFile, true);
		final Source xmlSource = new StreamSource(xmlIs);
		XsltTransformerExtensions.transform(xmlSource, xsltSource, output);
		actual = ReadFileExtensions.readFromFile(outputFile);
		actual = StringUtils.remove(actual, '\r');
		actual = StringUtils.remove(actual, '\n');
		expected = StringUtils.remove(expected, '\r');
		expected = StringUtils.remove(expected, '\n');
		AssertJUnit.assertTrue("", expected.equals(actual));
	}

	@Test(enabled = false)
	public void testTransformStringStringOutputStream()
	{
		throw new RuntimeException("Test not implemented");
	}

}
