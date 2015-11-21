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
package de.alpharogroup.xml.tag;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class TagTest
{

	@Test
	public void testToXmlString()
	{
		String expected;
		String actual;
		final Tag tag = new Tag();
		tag.setName("div");
		tag.addAttribute("wicket:id", "contentLabel");
		tag.addAttribute("class", "myClass");
		tag.setContent("xy");
		tag.setEndTag(true);

		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" >xy</div>";
		actual = tag.toString();
		/* check if equal */
		AssertJUnit.assertEquals(expected, actual);

		tag.setEndTag(false);

		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" />";
		actual = tag.toString();
		/* check if equal */
		AssertJUnit.assertEquals(expected, actual);

		tag.setEndTag(true);

		final Tag child1 = new Tag();

		child1.addAttribute("wicket:id", "name");
		child1.addAttribute("class", "other");
		child1.setName("span");
		child1.setContent("Hello ");
		child1.setEndTag(true);

		tag.addChild(child1, 1);
		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" >x<span wicket:id=\"name\" class=\"other\" >Hello </span>y</div>";
		actual = tag.toString();
		/* check if equal */
		AssertJUnit.assertEquals(expected, actual);

		final Tag granChild1 = new Tag();
		granChild1.setName("b");
		granChild1.setContent("world");
		granChild1.setEndTag(true);
		// Add this grand child tag at the end of the content from the child1 tag.
		child1.addChild(granChild1, child1.getContent().length());

		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" >x<span wicket:id=\"name\" class=\"other\" >Hello <b>world</b></span>y</div>";
		actual = tag.toString();
		/* check if equal */
		AssertJUnit.assertEquals(expected, actual);

		child1.removeChild(granChild1);

		// Add this grand child tag at the start of the content from the child1 tag.
		child1.addChild(granChild1, 0);

		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" >x<span wicket:id=\"name\" class=\"other\" ><b>world</b>Hello </span>y</div>";
		actual = tag.toString();
		/* check if equal */
		AssertJUnit.assertEquals(expected, actual);
	}

}
