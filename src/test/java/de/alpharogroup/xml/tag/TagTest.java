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
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.evaluate.object.verifier.ContractVerifier;

/**
 * The unit test class for the class {@link Tag}
 */
public class TagTest
{

	/**
	 * Test method for {@link Tag#clone()}
	 *
	 * @throws CloneNotSupportedException
	 *             is thrown if the object's class does not support the {@code Cloneable} interface.
	 *             Subclasses that override the {@code clone} method can also throw this exception
	 *             to indicate that an instance cannot be cloned.
	 */
	@Test(enabled = true)
	public void testClone() throws CloneNotSupportedException
	{
		Tag actual;
		Tag expected;
		actual = new Tag(MapFactory.newLinkedHashMap(), ListFactory.newArrayList(), "bar", false,
			"foo");
		expected = (Tag)actual.clone();
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link Tag} constructors and builders
	 */
	@Test
	public final void testConstructors()
	{
		Tag model = new Tag();
		assertNotNull(model);
		model = new Tag(MapFactory.newLinkedHashMap(), ListFactory.newArrayList(), "bar", false,
			"foo");
		assertNotNull(model);
		model = Tag.builder().build();
		assertNotNull(model);
	}

	/**
	 * Test method for {@link Tag#removeAttribute(String)}
	 */
	@Test
	public void testRemoveAttribute()
	{

		Tag child1 = Tag.builder().build();
		Tag child2 = Tag.builder().build();
		ChildTagPosition child1Position = ChildTagPosition.builder().child(child1).position(1)
			.build();
		ChildTagPosition child2Position = ChildTagPosition.builder().child(child2).position(2)
			.build();
		Tag tag = new Tag(MapFactory.newLinkedHashMap(),
			ListFactory.newArrayList(child1Position, child2Position), "bar", false, "foo");

		tag.addAttribute("class", "fluid box");

		assertTrue(tag.getAttributes().containsKey("class"));

		tag.removeAttribute("class");
		assertFalse(tag.getAttributes().containsKey("class"));
	}

	/**
	 * Test method for {@link Tag#removeChild(Tag)}
	 */
	@Test
	public void testRemoveChild()
	{
		Tag child1 = Tag.builder().build();
		Tag child2 = Tag.builder().build();
		ChildTagPosition child1Position = ChildTagPosition.builder().child(child1).position(1)
			.build();
		ChildTagPosition child2Position = ChildTagPosition.builder().child(child2).position(2)
			.build();
		Tag tag = new Tag(MapFactory.newLinkedHashMap(),
			ListFactory.newArrayList(child1Position, child2Position), "bar", false, "foo");

		assertTrue(tag.getChildren().contains(child1Position));

		tag.removeChild(child1);

		assertFalse(tag.getChildren().contains(child1Position));

		assertFalse(child1.removeChild(child2));
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

	/**
	 * Test method for {@link Tag#equals(Object)} , {@link Tag#hashCode()} and
	 * {@link Tag#toString()}
	 */
	@Test
	public void verifyEqualsHashcodeAndToStringContracts()
	{
		ContractVerifier.of(Tag.class).verify();
	}

}
