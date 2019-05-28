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

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang.StringUtils;
import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import de.alpharogroup.AbstractTestCase;
import de.alpharogroup.collections.array.ArrayFactory;
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
	@Override
	@BeforeMethod
	protected void setUp() throws Exception
	{
		super.setUp();
		expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><birthdates>" + "\r\n"
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
	@Override
	@AfterMethod
	protected void tearDown() throws Exception
	{
		super.tearDown();
		actual = null;
		expected = null;
	}

	/**
	 * Test method for {@link XsltTransformerExtensions#transform(File, File, OutputStream)}
	 *
	 * @throws TransformerConfigurationException
	 *             is thrown if there are errors when parsing the <code>Source</code> or it is not
	 *             possible to create a <code>Transformer</code> instance.
	 * @throws TransformerException
	 *             is thrown if an unrecoverable error occurs during the course of the
	 *             transformation.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testGetTransformerFile()
		throws TransformerConfigurationException, TransformerException, IOException
	{
		File resDestDir;
		String[] dirsAndFilename;
		File xmlFile;
		File xsltFile;
		File outputFile;

		resDestDir = PathFinder.getSrcTestResourcesDir();
		dirsAndFilename = ArrayFactory.newArray("de", "alpharogroup", "xsl", "transform",
			"birthdates.xml");

		xmlFile = PathFinder.getRelativePathTo(resDestDir, Arrays.asList(dirsAndFilename));
		xsltFile = PathFinder.getRelativePathTo(resDestDir, "\\.", "de.alpharogroup.xsl.transform",
			"functions.xsl");
		outputFile = PathFinder.getRelativePathTo(resDestDir, "\\.",
			"de.alpharogroup.xsl.transform", "output.xml");
		XsltTransformerExtensions.transform(xmlFile, xsltFile, new FileOutputStream(outputFile));
		actual = ReadFileExtensions.readFromFile(outputFile);
		actual = StringUtils.remove(actual, '\r');
		actual = StringUtils.remove(actual, '\n');
		expected = StringUtils.remove(expected, '\r');
		expected = StringUtils.remove(expected, '\n');
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XsltTransformerExtensions#getTransformer(Source)}
	 *
	 * @throws TransformerConfigurationException
	 *             is thrown if there are errors when parsing the <code>Source</code> or it is not
	 *             possible to create a <code>Transformer</code> instance.
	 */
	@Test(enabled = true)
	public void testGetTransformerSource() throws TransformerConfigurationException
	{
		Transformer actual;
		File resDestDir;
		File xsltFile;
		StreamSource xsltSource;

		resDestDir = PathFinder.getSrcTestResourcesDir();
		xsltFile = PathFinder.getRelativePathTo(resDestDir, "\\.", "de.alpharogroup.xsl.transform",
			"functions.xsl");
		xsltSource = new StreamSource(xsltFile);

		actual = XsltTransformerExtensions.getTransformer(xsltSource);
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link XsltTransformerExtensions#getTransformer(String)}
	 *
	 * @throws TransformerConfigurationException
	 *             is thrown if there are errors when parsing the <code>Source</code> or it is not
	 *             possible to create a <code>Transformer</code> instance.
	 */
	@Test(enabled = true)
	public void testGetTransformerString() throws TransformerConfigurationException
	{
		Transformer actual;
		File resDestDir;
		File xsltFile;

		resDestDir = PathFinder.getSrcTestResourcesDir();
		xsltFile = PathFinder.getRelativePathTo(resDestDir, "\\.", "de.alpharogroup.xsl.transform",
			"functions.xsl");

		actual = XsltTransformerExtensions.getTransformer(xsltFile.getAbsolutePath());
		assertNotNull(actual);
	}

	/**
	 * Test method for {@link XsltTransformerExtensions#transform(File, File, OutputStream)}
	 * 
	 * @throws TransformerConfigurationException
	 *             is thrown if there are errors when parsing the <code>Source</code> or it is not
	 *             possible to create a <code>Transformer</code> instance.
	 * @throws TransformerException
	 *             is thrown if an unrecoverable error occurs during the course of the
	 *             transformation.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = true)
	public void testTransformFileFileOutputStream()
		throws TransformerConfigurationException, TransformerException, IOException
	{
		File resDestDir;
		String[] dirsAndFilename;
		File xmlFile;
		File xsltFile;
		File outputFile;
		OutputStream output;

		resDestDir = PathFinder.getSrcTestResourcesDir();
		dirsAndFilename = ArrayFactory.newArray("de", "alpharogroup", "xsl", "transform",
			"birthdates.xml");
		xmlFile = PathFinder.getRelativePath(resDestDir, dirsAndFilename);
		xsltFile = PathFinder.getRelativePathTo(resDestDir, "\\.", "de.alpharogroup.xsl.transform",
			"functions.xsl");

		outputFile = PathFinder.getRelativePathTo(resDestDir, "\\.",
			"de.alpharogroup.xsl.transform", "data_02_output.xml");
		output = StreamExtensions.getOutputStream(outputFile, true);

		XsltTransformerExtensions.transform(xmlFile, xsltFile, output);
		actual = ReadFileExtensions.readFromFile(outputFile);
		actual = StringUtils.remove(actual, '\r');
		actual = StringUtils.remove(actual, '\n');
		expected = StringUtils.remove(expected, '\r');
		expected = StringUtils.remove(expected, '\n');
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XsltTransformerExtensions#transform(Source, Source, OutputStream)}.
	 *
	 * @throws TransformerException
	 *             is thrown if an unrecoverable error occurs during the course of the
	 *             transformation.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = true)
	public void testTransformSourceSourceOutputStream() throws TransformerException, IOException
	{
		File resDestDir;
		String[] dirsAndFilename;
		File xmlFile;
		File xsltFile;
		File outputFile;
		InputStream is;
		Source xsltSource;
		InputStream xmlIs;
		OutputStream output;
		Source xmlSource;

		resDestDir = PathFinder.getSrcTestResourcesDir();
		dirsAndFilename = ArrayFactory.newArray("de", "alpharogroup", "xsl", "transform",
			"birthdates.xml");
		xmlFile = PathFinder.getRelativePath(resDestDir, dirsAndFilename);
		xsltFile = PathFinder.getRelativePathTo(resDestDir, "\\.", "de.alpharogroup.xsl.transform",
			"functions.xsl");
		is = StreamExtensions.getInputStream(xsltFile);
		xsltSource = new StreamSource(is);

		xmlIs = StreamExtensions.getInputStream(xmlFile);
		outputFile = PathFinder.getRelativePathTo(resDestDir, "\\.",
			"de.alpharogroup.xsl.transform", "data_02_output.xml");
		output = StreamExtensions.getOutputStream(outputFile, true);
		xmlSource = new StreamSource(xmlIs);

		XsltTransformerExtensions.transform(xmlSource, xsltSource, output);
		actual = ReadFileExtensions.readFromFile(outputFile);
		actual = StringUtils.remove(actual, '\r');
		actual = StringUtils.remove(actual, '\n');
		expected = StringUtils.remove(expected, '\r');
		expected = StringUtils.remove(expected, '\n');
		assertEquals(actual, expected);
	}

	@Test(enabled = true)
	public void testTransformStringStringOutputStream()
		throws IOException, TransformerConfigurationException, TransformerException
	{
		File resDestDir;
		String[] dirsAndFilename;
		File xmlFile;
		File xsltFile;
		File outputFile;
		OutputStream output;

		resDestDir = PathFinder.getSrcTestResourcesDir();
		dirsAndFilename = ArrayFactory.newArray("de", "alpharogroup", "xsl", "transform",
			"birthdates.xml");
		xmlFile = PathFinder.getRelativePath(resDestDir, dirsAndFilename);
		xsltFile = PathFinder.getRelativePathTo(resDestDir, "\\.", "de.alpharogroup.xsl.transform",
			"functions.xsl");

		outputFile = PathFinder.getRelativePathTo(resDestDir, "\\.",
			"de.alpharogroup.xsl.transform", "data_02_output.xml");
		output = StreamExtensions.getOutputStream(outputFile, true);

		XsltTransformerExtensions.transform(xmlFile.getAbsolutePath(), xsltFile.getAbsolutePath(),
			output);
		actual = ReadFileExtensions.readFromFile(outputFile);
		actual = StringUtils.remove(actual, '\r');
		actual = StringUtils.remove(actual, '\n');
		expected = StringUtils.remove(expected, '\r');
		expected = StringUtils.remove(expected, '\n');
		assertEquals(actual, expected);
	}

	/**
	 * Test method for {@link XsltTransformerExtensions}
	 */
	@Test(expectedExceptions = { BeanTestException.class, ObjectCreationException.class })
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XsltTransformerExtensions.class);
	}


}
