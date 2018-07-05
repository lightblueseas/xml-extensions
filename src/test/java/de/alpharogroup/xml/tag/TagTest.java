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
package de.alpharogroup.xml.tag;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.EqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link Tag}
 */
public class TagTest
{

	/**
	 * Test method for {@link Tag#equals(Object)} , {@link Tag#hashCode()} and
	 * {@link Tag#toString()}
	 *
	 * @throws NoSuchMethodException
	 *             if an accessor method for this property cannot be found
	 * @throws IllegalAccessException
	 *             if the caller does not have access to the property accessor method
	 * @throws InvocationTargetException
	 *             if the property accessor method throws an exception
	 * @throws InstantiationException
	 *             if a new instance of the bean's class cannot be instantiated
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClass() throws NoSuchMethodException,
		IllegalAccessException, InvocationTargetException, InstantiationException, IOException
	{
		boolean expected;
		boolean actual;
		actual = EqualsHashCodeAndToStringEvaluator.evaluateEqualsHashcodeAndToString(Tag.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Tag}
	 */
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
		actual = tag.toXmlString();
		/* check if equal */
		assertEquals(expected, actual);

		tag.setEndTag(false);

		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" />";
		actual = tag.toXmlString();
		/* check if equal */
		assertEquals(expected, actual);

		tag.setEndTag(true);

		final Tag child1 = new Tag();

		child1.addAttribute("wicket:id", "name");
		child1.addAttribute("class", "other");
		child1.setName("span");
		child1.setContent("Hello ");
		child1.setEndTag(true);

		tag.addChild(child1, 1);
		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" >x<span wicket:id=\"name\" class=\"other\" >Hello </span>y</div>";
		actual = tag.toXmlString();
		/* check if equal */
		assertEquals(expected, actual);

		final Tag granChild1 = new Tag();
		granChild1.setName("b");
		granChild1.setContent("world");
		granChild1.setEndTag(true);
		// Add this grand child tag at the end of the content from the child1 tag.
		child1.addChild(granChild1, child1.getContent().length());

		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" >x<span wicket:id=\"name\" class=\"other\" >Hello <b>world</b></span>y</div>";
		actual = tag.toXmlString();
		/* check if equal */
		assertEquals(expected, actual);

		child1.removeChild(granChild1);

		// Add this grand child tag at the start of the content from the child1 tag.
		child1.addChild(granChild1, 0);

		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" >x<span wicket:id=\"name\" class=\"other\" ><b>world</b>Hello </span>y</div>";
		actual = tag.toXmlString();
		/* check if equal */
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Tag}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(Tag.class);
	}

}
