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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.meanbean.test.BeanTester;
import org.testng.annotations.Test;

import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.map.MapFactory;
import de.alpharogroup.evaluate.object.evaluators.EqualsHashCodeAndToStringEvaluator;
import de.alpharogroup.velocity.VelocityExtensions;

/**
 * The unit test class for the class {@link SimpleTag}
 */
public class SimpleTagTest
{

	/**
	 * Test method for {@link SimpleTag}
	 */
	@Test
	public void test() throws IOException
	{
		String expected;
		String actual;
		final SimpleTag tag = new SimpleTag();
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

		final SimpleTag cloned = (SimpleTag)tag.clone();

		assertEquals(tag, cloned);

		assertEquals(tag.hashCode(), cloned.hashCode());

		tag.setEndTag(true);

		final SimpleTag child1 = new SimpleTag();

		child1.addAttribute("wicket:id", "name");
		child1.addAttribute("class", "other");
		child1.setName("span");
		child1.setContent("Hello ");
		child1.setEndTag(true);

		tag.addChild(child1);
		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" >xy<span wicket:id=\"name\" class=\"other\" >Hello </span></div>";
		actual = tag.toXmlString();
		/* check if equal */
		assertEquals(expected, actual);

		final SimpleTag granChild1 = new SimpleTag();
		granChild1.setName("b");
		granChild1.setContent("world");
		granChild1.setEndTag(true);

		child1.addChild(granChild1);

		expected = "<div wicket:id=\"contentLabel\" class=\"myClass\" >xy<span wicket:id=\"name\" class=\"other\" >Hello <b>world</b></span></div>";
		actual = tag.toXmlString();
		/* check if equal */
		assertEquals(expected, actual);


		/* create a velocity template as String object from the tag */
		final StringBuilder velocityTemplateBuilder = tag.toVelocityTemplate();
		actual = velocityTemplateBuilder.toString();
		expected = "<${div.name}\n " + "#foreach($attribute in $div.attributes.keySet())\n"
			+ "$attribute=\"$div.getAttributes().get($attribute)\"\n" + " #end\n"
			+ "#if(${div.endTag})>${div.content}\n" + "#foreach($span in $div.children)\n"
			+ "<${span.name}\n" + " #foreach($attribute in $span.attributes.keySet())\n"
			+ "$attribute=\"$span.getAttributes().get($attribute)\"\n" + " #end\n"
			+ "#if(${span.endTag})>${span.content}\n" + "#foreach($b in $span.children)\n"
			+ "<${b.name}\n" + "#if(${b.endTag})>${b.content}\n" + "</${b.name}>\n" + "#else />\n"
			+ "#end\n" + "#end\n" + "</${span.name}>\n" + "#else />\n" + "#end\n" + "#end\n"
			+ "</${div.name}>\n" + "#else />\n" + "#end\n";
		/* check if equal */
		assertEquals(expected, actual);
		/* Merge the created velocity template from the tag and merge it with the context. */
		final String velocityTemplate = actual;
		/* first, we init the runtime engine. Defaults are fine. */
		Velocity.init();

		/* lets make a Context and put data into it */
		final VelocityContext context = new VelocityContext();
		/* put the tag into the context */
		context.put(tag.getName(), tag);
		actual = VelocityExtensions.merge(context, velocityTemplate);
		expected = "<div\n wicket:id=\"contentLabel\"\n class=\"myClass\"\n >xy\n<span\n wicket:id=\"name\"\n class=\"other\"\n >Hello \n<b\n>world\n</b>\n</span>\n</div>\n";
		/* check if equal */
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link SimpleTag} constructors and builders
	 */
	@Test
	public final void testConstructors()
	{
		SimpleTag model = new SimpleTag();
		assertNotNull(model);
		model = new SimpleTag(MapFactory.newLinkedHashMap(), ListFactory.newArrayList(), "bar",
			false, "foo");
		assertNotNull(model);
		model = SimpleTag.builder().build();
		assertNotNull(model);
	}

	/**
	 * Test method for {@link SimpleTag#equals(Object)} , {@link SimpleTag#hashCode()} and
	 * {@link SimpleTag#toString()}
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
		actual = EqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToString(SimpleTag.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link SimpleTag#removeAttribute(String)}
	 */
	@Test
	public void testRemoveAttribute()
	{
		SimpleTag child1 = SimpleTag.builder().name("img").build();
		SimpleTag child2 = SimpleTag.builder().name("b").build();
		SimpleTag simpleTag = new SimpleTag(MapFactory.newLinkedHashMap(),
			ListFactory.newArrayList(child1, child2), "bar", false, "foo");

		simpleTag.addAttribute("class", "fluid box");

		assertTrue(simpleTag.getAttributes().containsKey("class"));

		simpleTag.removeAttribute("class");
		assertFalse(simpleTag.getAttributes().containsKey("class"));
	}

	/**
	 * Test method for {@link SimpleTag#removeChild(SimpleTag)}
	 */
	@Test
	public void testRemoveChild()
	{
		SimpleTag child1 = SimpleTag.builder().name("img").build();
		SimpleTag child2 = SimpleTag.builder().name("b").build();
		SimpleTag simpleTag = new SimpleTag(MapFactory.newLinkedHashMap(),
			ListFactory.newArrayList(child1, child2), "bar", false, "foo");

		assertTrue(simpleTag.getChildren().contains(child1));

		simpleTag.removeChild(child1);

		assertFalse(simpleTag.getChildren().contains(child1));

		assertFalse(child1.removeChild(child2));
	}

	/**
	 * Test method for {@link SimpleTag}
	 */
	@Test
	public void testWithBeanTester()
	{
		final BeanTester beanTester = new BeanTester();
		beanTester.testBean(SimpleTag.class);
	}

}
