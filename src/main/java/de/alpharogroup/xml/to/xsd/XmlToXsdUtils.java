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
package de.alpharogroup.xml.to.xsd;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.inst2xsd.Inst2Xsd;
import org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions;
import org.apache.xmlbeans.impl.xb.xsdschema.SchemaDocument;

import de.alpharogroup.file.delete.DeleteFileUtils;
import de.alpharogroup.file.search.PathFinder;

/**
 * The Class XmlToXsdUtils generates XML schema from XML files. It makes use of XMLBeans tools.
 */
public class XmlToXsdUtils
{

	/**
	 * The main method.
	 * 
	 * @param args
	 *            the arguments
	 * @throws XmlException
	 *             the xml exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(final String[] args) throws XmlException, IOException
	{
		final File srcTestResourcesDir = PathFinder.getSrcTestResourcesDir();
		final File projectDir = PathFinder.getProjectDirectory();
		final File xmlFile = new File(projectDir, "pom.xml");

		final String xsdString = xmlToXsd(xmlFile);
		System.err.println(xsdString);

		final File[] xmlFiles = { xmlFile };
		final Inst2XsdOptions inst2XsdOptions = new Inst2XsdOptions();
		xmlToXsd(xmlFiles, inst2XsdOptions, srcTestResourcesDir, null);
		DeleteFileUtils.delete(new File(srcTestResourcesDir, "schema0.xsd"));
	}

	/**
	 * Returns a xsd String from the given XML file that can used for several purposes.
	 * 
	 * @param xmlFile
	 *            the XML file
	 * @return the resulted xsd String
	 * @throws XmlException
	 *             the xml exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String xmlToXsd(final File xmlFile) throws XmlException, IOException
	{
		return xmlToXsd(xmlFile, new Inst2XsdOptions());
	}

	/**
	 * Creates or update the given xsd output file from the given XML file.
	 * 
	 * @param xmlInputFile
	 *            the xml input file
	 * @param xsdOutFile
	 *            the xsd out file
	 * @throws XmlException
	 *             the xml exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void xmlToXsd(final File xmlInputFile, final File xsdOutFile)
		throws XmlException, IOException
	{
		xmlToXsd(xmlInputFile, xsdOutFile, new Inst2XsdOptions());
	}

	/**
	 * Creates or update the given xsd output file from the given XML file.
	 * 
	 * @param xmlInputFile
	 *            the xml input file
	 * @param xsdOutFile
	 *            the xsd out file
	 * @param inst2XsdOptions
	 *            the inst2 xsd options
	 * @throws XmlException
	 *             occurs when a give xml file is invalid.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void xmlToXsd(final File xmlInputFile, final File xsdOutFile,
		final Inst2XsdOptions inst2XsdOptions) throws XmlException, IOException
	{
		xmlToXsd(xmlInputFile, xsdOutFile, inst2XsdOptions, new XmlOptions().setSavePrettyPrint());
	}

	/**
	 * Creates or update the given xsd output file from the given XML file.
	 * 
	 * @param xmlFile
	 *            the xml file
	 * @param xsdOutFile
	 *            the xsd out file
	 * @param inst2XsdOptions
	 *            the inst2 xsd options
	 * @param xmlOptions
	 *            the xml options
	 * @throws XmlException
	 *             occurs when a give xml file is invalid.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void xmlToXsd(final File xmlFile, final File xsdOutFile,
		final Inst2XsdOptions inst2XsdOptions, final XmlOptions xmlOptions) throws XmlException,
		IOException
	{
		final XmlObject[] xmlInstances = new XmlObject[1];
		xmlInstances[0] = XmlObject.Factory.parse(xmlFile);
		final SchemaDocument[] schemaDocs = Inst2Xsd.inst2xsd(xmlInstances, inst2XsdOptions);
		final SchemaDocument schema = schemaDocs[0];
		schema.save(xsdOutFile, xmlOptions);
	}

	/**
	 * Returns a xsd String from the given XML file that can used for several purposes.
	 * 
	 * @param xmlFile
	 *            the XML file.
	 * @param inst2XsdOptions
	 *            the inst2 xsd options
	 * @return the resulted xsd String
	 * @throws XmlException
	 *             occurs when a give xml file is invalid.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String xmlToXsd(final File xmlFile, final Inst2XsdOptions inst2XsdOptions)
		throws XmlException, IOException
	{
		return xmlToXsd(xmlFile, inst2XsdOptions, new XmlOptions().setSavePrettyPrint());
	}

	/**
	 * Returns a xsd String from the given XML file that can used for several purposes.
	 * 
	 * @param xmlFile
	 *            the xml file
	 * @param inst2XsdOptions
	 *            the inst2 xsd options
	 * @param xmlOptions
	 *            the xml options
	 * @return the resulted xsd String
	 * @throws XmlException
	 *             occurs when a give xml file is invalid.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String xmlToXsd(final File xmlFile, final Inst2XsdOptions inst2XsdOptions,
		final XmlOptions xmlOptions) throws XmlException, IOException
	{
		final XmlObject[] xmlInstances = new XmlObject[1];
		xmlInstances[0] = XmlObject.Factory.parse(xmlFile);
		final SchemaDocument[] schemaDocs = Inst2Xsd.inst2xsd(xmlInstances, inst2XsdOptions);
		final SchemaDocument schema = schemaDocs[0];
		return schema.xmlText(xmlOptions);
	}

	/**
	 * Creates or update the given xsd output file from the given XML file.
	 * 
	 * @param xmlFiles
	 *            the XML files
	 * @param inst2XsdOptions
	 *            the inst2 xsd options
	 * @param outDir
	 *            The directory path for output files. Default is '.' (the current directory).
	 * @param outPrefix
	 *            The prefix for output file names. Default is "schema".
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void xmlToXsd(final File[] xmlFiles, final Inst2XsdOptions inst2XsdOptions,
		File outDir, String outPrefix) throws IOException
	{
		if (xmlFiles == null || xmlFiles.length == 0)
		{
			throw new IllegalArgumentException("XML file array can not be null and not empty.");
		}
		if (outDir == null)
		{
			outDir = new File(".");
		}
		if (outPrefix == null)
		{
			outPrefix = "schema";
		}
		final XmlObject[] xmlInstances = new XmlObject[xmlFiles.length];
		int i = 0;
		try
		{
			for (i = 0; i < xmlFiles.length; i++)
			{
				xmlInstances[i] = XmlObject.Factory.parse(xmlFiles[i]);
			}
		}
		catch (final XmlException e)
		{
			throw new IllegalArgumentException("Invalid xml file: '" + xmlFiles[i].getName()
				+ "'. \n" + e.getMessage(), e);
		}
		catch (final IOException e)
		{
			throw new IllegalArgumentException("Could not read file: '" + xmlFiles[i].getName()
				+ "'. " + e.getMessage(), e);
		}

		final SchemaDocument[] schemaDocs = Inst2Xsd.inst2xsd(xmlInstances, inst2XsdOptions);

		try
		{
			for (i = 0; i < schemaDocs.length; i++)
			{
				final SchemaDocument schema = schemaDocs[i];
				schema.save(new File(outDir, outPrefix + i + ".xsd"),
					new XmlOptions().setSavePrettyPrint());
			}
		}
		catch (final IOException e)
		{
			throw new IOException("Could not write file: '" + outDir + File.pathSeparator
				+ outPrefix + i + ".xsd" + "'. " + e.getMessage(), e);
		}
	}

	public static String xmlToXsd(final String xmlFile, final Inst2XsdOptions inst2XsdOptions,
		final XmlOptions xmlOptions) throws XmlException, IOException
	{
		final XmlObject[] xmlInstances = new XmlObject[1];
		xmlInstances[0] = XmlObject.Factory.parse(xmlFile);
		final SchemaDocument[] schemaDocs = Inst2Xsd.inst2xsd(xmlInstances, inst2XsdOptions);
		final SchemaDocument schema = schemaDocs[0];
		final StringWriter writer = new StringWriter();
		schema.save(writer, xmlOptions);
		return writer.toString();
	}
}
