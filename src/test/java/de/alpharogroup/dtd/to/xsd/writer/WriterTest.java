package de.alpharogroup.dtd.to.xsd.writer;

import static org.testng.Assert.assertNotNull;

import org.apache.xerces.xni.parser.XMLInputSource;
import org.testng.annotations.Test;

/**
 * The unit test class for the class {@link Writer}
 */
public class WriterTest
{

	/**
	 * Test method for {@link Writer} constructors
	 */
	@Test
	public final void testConstructors()
	{
		Writer model = new Writer();
		assertNotNull(model);
	}
	
	/**
	 * Test method for {@link Writer#parse(XMLInputSource)}.
	 */
	@Test
	public final void testParse() 
	{
		Writer model = new Writer();
		assertNotNull(model);
		// TODO implement scenario...
	}

}
