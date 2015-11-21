package de.alpharogroup.xml.tag;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;


public class TagAttributeTest
{

	@Test
	public void testJoinValues()
	{
		final String actual = TagAttribute.builder().delimiter(" ").value("row").value("fluid")
			.build().joinValues();
		final String expected = "row fluid";
		AssertJUnit.assertEquals(expected, actual);
	}

}
