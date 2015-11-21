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

import java.io.IOException;

import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.inst2xsd.Inst2XsdOptions;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class XmlToXsdUtilsTest
{

	@Test(enabled = false)
	public void testXmlToXsdStringInst2XsdOptionsXmlOptions() throws XmlException, IOException
	{
		final String xml = "<person><name>Alfred</name></person>";
		final Inst2XsdOptions inst2XsdOptions = new Inst2XsdOptions();
		final String result = XmlToXsdUtils.xmlToXsd(xml, inst2XsdOptions,
			new XmlOptions().setSavePrettyPrint());
		final String expected = "<xs:schema attributeFormDefault=\"unqualified\" elementFormDefault=\"qualified\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\r\n"
			+ "  <xs:element name=\"person\" type=\"personType\"/>\r\n"
			+ "  <xs:complexType name=\"personType\">\r\n"
			+ "    <xs:sequence>\r\n"
			+ "      <xs:element type=\"xs:string\" name=\"name\"/>\r\n"
			+ "    </xs:sequence>\r\n"
			+ "  </xs:complexType>\r\n" + "</xs:schema>";
		AssertJUnit
			.assertTrue("Expected should be equal with the result.", expected.equals(result));
	}

}
