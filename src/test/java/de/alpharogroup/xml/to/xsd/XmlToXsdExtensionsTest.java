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
package de.alpharogroup.xml.to.xsd;

import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions;
import org.meanbean.factories.ObjectCreationException;
import org.meanbean.test.BeanTestException;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.array.ArrayFactory;
import de.alpharogroup.crypto.file.checksum.FileChecksumExtensions;
import de.alpharogroup.file.delete.DeleteFileExtensions;
import de.alpharogroup.file.search.PathFinder;

/**
 * The unit test class for the class {@link XmlToXsdExtensions}
 */
public class XmlToXsdExtensionsTest
{

	/**
	 * Test method for {@link XmlToXsdExtensions}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(XmlToXsdExtensions.class);
	}

	/**
	 * Test method for {@link XmlToXsdExtensions#xmlToXsd(File)}.
	 *
	 * @throws XmlException
	 *             the xml exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testXmlToXsdFile() throws XmlException, IOException
	{
		String xml;
		String xsdString;

		File srcTestResourcesDir;

		srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();

		xml = "test-pom.xml";
		final File xmlFile = new File(srcTestResourcesDir, xml);

		xsdString = XmlToXsdExtensions.xmlToXsd(xmlFile);
		assertNotNull(xsdString);
	}

	/**
	 * Test method for {@link XmlToXsdExtensions#xmlToXsd(File[], Inst2XsdOptions, File, String)}.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testXmlToXsdFileArrayInst2XsdOptionsFileString() throws IOException
	{
		File expected;
		File[] xmlFiles;
		Inst2XsdOptions inst2XsdOptions;
		String xml;
		String xsd;
		File srcTestResourcesDir;
		File xmlFile;
		// 1. scenario ...
		xsd = "schema0.xsd";
		srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
		xml = "test-pom.xml";
		xmlFile = new File(srcTestResourcesDir, xml);

		xmlFiles = ArrayFactory.newArray(xmlFile);
		inst2XsdOptions = new Inst2XsdOptions();
		XmlToXsdExtensions.xmlToXsd(xmlFiles, inst2XsdOptions, srcTestResourcesDir, null);

		expected = new File(srcTestResourcesDir, xsd);
		assertTrue(expected.exists());
		DeleteFileExtensions.delete(expected);
		// 2. scenario ...
		srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
		xml = "test-pom.xml";
		xmlFile = new File(srcTestResourcesDir, xml);

		xmlFiles = ArrayFactory.newArray(xmlFile);
		inst2XsdOptions = new Inst2XsdOptions();
		XmlToXsdExtensions.xmlToXsd(xmlFiles, inst2XsdOptions, null, null);

		expected = new File(new File("."), xsd);
		assertTrue(expected.exists());
		DeleteFileExtensions.delete(expected);
	}

	/**
	 * Test method for {@link XmlToXsdExtensions#xmlToXsd(File[], Inst2XsdOptions, File, String)}
	 * that throws an IllegalArgumentException
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = IllegalArgumentException.class)
	public void testXmlToXsdFileArrayInst2XsdOptionsFileStringIllegalArgumentException()
		throws IOException
	{
		XmlToXsdExtensions.xmlToXsd(null, new Inst2XsdOptions(),
			PathFinder.getSrcTestResourcesDir(), null);
	}

	/**
	 * Test method for {@link XmlToXsdExtensions#xmlToXsd(File[], Inst2XsdOptions, File, String)}
	 * that causes a IOException and throw an IllegalArgumentException
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(expectedExceptions = { IllegalArgumentException.class })
	public void testXmlToXsdFileArrayInst2XsdOptionsFileStringIOException() throws IOException
	{
		File projectDir;
		File[] xmlFiles;
		Inst2XsdOptions inst2XsdOptions;
		String xml;
		File xmlFile;
		// 1. scenario ...
		projectDir = PathFinder.getProjectDirectory();
		xml = "pom.xml";
		xmlFile = new File(projectDir, xml);

		xmlFiles = ArrayFactory.newArray(xmlFile, new File(projectDir, "notExists.xml"));
		inst2XsdOptions = new Inst2XsdOptions();
		XmlToXsdExtensions.xmlToXsd(xmlFiles, inst2XsdOptions, null, null);
	}

	/**
	 * Test method for {@link XmlToXsdExtensions#xmlToXsd(File, File)}.
	 *
	 * @throws XmlException
	 *             the xml exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testXmlToXsdFileFile() throws XmlException, IOException
	{
		File expected;
		File srcTestResourcesDir;
		File xmlFile;
		File xsdOutFile;
		String xsdFilename;
		String xmlFilename;
		String filename;

		srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
		filename = "test-xml";
		xsdFilename = filename + ".xsd";
		xmlFilename = filename + ".xml";
		xmlFile = new File(srcTestResourcesDir, xmlFilename);
		xsdOutFile = new File(srcTestResourcesDir, xsdFilename);
		expected = new File(srcTestResourcesDir, "test-xsd-expected.xsd");
		assertFalse(xsdOutFile.exists());
		XmlToXsdExtensions.xmlToXsd(xmlFile, xsdOutFile);
		assertTrue(xsdOutFile.exists());
		assertTrue(FileChecksumExtensions.getCheckSumAdler32(expected) == FileChecksumExtensions
			.getCheckSumAdler32(xsdOutFile));
		// clean up...
		xsdOutFile.delete();
	}

	/**
	 * Test method for {@link XmlToXsdExtensions#xmlToXsd(File, File, Inst2XsdOptions)}.
	 *
	 * @throws XmlException
	 *             the xml exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testXmlToXsdFileFileInst2XsdOptions() throws XmlException, IOException
	{
		File expected;
		File srcTestResourcesDir;
		File xmlFile;
		File xsdOutFile;
		String xsdFilename;
		String xmlFilename;
		String filename;
		Inst2XsdOptions inst2XsdOptions;

		inst2XsdOptions = new Inst2XsdOptions();

		srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
		filename = "test-xml";
		xsdFilename = filename + ".xsd";
		xmlFilename = filename + ".xml";
		xmlFile = new File(srcTestResourcesDir, xmlFilename);
		xsdOutFile = new File(srcTestResourcesDir, xsdFilename);
		expected = new File(srcTestResourcesDir, "test-xsd-expected.xsd");
		assertFalse(xsdOutFile.exists());

		XmlToXsdExtensions.xmlToXsd(xmlFile, xsdOutFile, inst2XsdOptions);
		assertTrue(xsdOutFile.exists());
		assertTrue(FileChecksumExtensions.getCheckSumAdler32(expected) == FileChecksumExtensions
			.getCheckSumAdler32(xsdOutFile));
		// clean up...
		xsdOutFile.delete();
	}

	/**
	 * Test method for {@link XmlToXsdExtensions#xmlToXsd(File, File, Inst2XsdOptions, XmlOptions)}
	 *
	 * @throws XmlException
	 *             occurs when a give xml file is invalid
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testXmlToXsdFileFileInst2XsdOptionsXmlOptions() throws XmlException, IOException
	{
		File expected;
		File srcTestResourcesDir;
		File xmlFile;
		File xsdOutFile;
		String xsdFilename;
		String xmlFilename;
		String filename;
		Inst2XsdOptions inst2XsdOptions;
		XmlOptions xmlOptions;

		xmlOptions = new XmlOptions().setSavePrettyPrint();

		inst2XsdOptions = new Inst2XsdOptions();

		srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
		filename = "test-xml";
		xsdFilename = filename + ".xsd";
		xmlFilename = filename + ".xml";
		xmlFile = new File(srcTestResourcesDir, xmlFilename);
		xsdOutFile = new File(srcTestResourcesDir, xsdFilename);
		expected = new File(srcTestResourcesDir, "test-xsd-expected.xsd");
		assertFalse(xsdOutFile.exists());

		XmlToXsdExtensions.xmlToXsd(xmlFile, xsdOutFile, inst2XsdOptions, xmlOptions);
		assertTrue(xsdOutFile.exists());
		assertTrue(FileChecksumExtensions.getCheckSumAdler32(expected) == FileChecksumExtensions
			.getCheckSumAdler32(xsdOutFile));
		// clean up...
		xsdOutFile.delete();
	}

	/**
	 * Test method for {@link XmlToXsdExtensions#xmlToXsd(File, Inst2XsdOptions)}.
	 *
	 * @throws XmlException
	 *             the xml exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test
	public void testXmlToXsdFileInst2XsdOptions() throws XmlException, IOException
	{
		String xml;
		String xsdString;
		Inst2XsdOptions inst2XsdOptions;

		File srcTestResourcesDir;

		srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
		inst2XsdOptions = new Inst2XsdOptions();
		xml = "test-pom.xml";
		final File xmlFile = new File(srcTestResourcesDir, xml);

		xsdString = XmlToXsdExtensions.xmlToXsd(xmlFile, inst2XsdOptions);
		assertNotNull(xsdString);
	}

	/**
	 * Test method for {@link XmlToXsdExtensions#xmlToXsd(String, Inst2XsdOptions, XmlOptions)}
	 *
	 * @throws XmlException
	 *             the xml exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Test(enabled = true)
	public void testXmlToXsdStringInst2XsdOptionsXmlOptions() throws XmlException, IOException
	{
		String actual;
		String expected;
		Inst2XsdOptions inst2XsdOptions;
		String xml;
		xml = "<person><name>Alfred</name></person>";
		inst2XsdOptions = new Inst2XsdOptions();
		actual = XmlToXsdExtensions.xmlToXsd(xml, inst2XsdOptions,
			new XmlOptions().setSavePrettyPrint());
		expected = "<xs:schema attributeFormDefault=\"unqualified\" elementFormDefault=\"qualified\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n"
			+ "  <xs:element name=\"person\" type=\"personType\"/>\n"
			+ "  <xs:complexType name=\"personType\">\n" + "    <xs:sequence>\n"
			+ "      <xs:element type=\"xs:string\" name=\"name\"/>\n" + "    </xs:sequence>\n"
			+ "  </xs:complexType>\n" + "</xs:schema>";
		assertTrue("Expected should be equal with the result.", expected.equals(actual));
	}

}
