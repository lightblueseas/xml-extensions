package de.alpharogroup.dtd.to.xsd.type;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.assertEquals;

import java.util.regex.Pattern;

import org.meanbean.lang.Factory;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;

import de.alpharogroup.evaluate.object.SilentEqualsHashCodeAndToStringEvaluator;

/**
 * The unit test class for the class {@link TypePattern}
 */
public class TypePatternTest
{

	/**
	 * Test method for {@link TypePattern} constructors and builders
	 */
	@Test
	public final void testConstructors()
	{
		TypePattern model = new TypePattern();
		assertNotNull(model);
		model = new TypePattern(Pattern.compile("(xsd|xml)"), "value");
		assertNotNull(model);
		model = TypePattern.builder().build();
		assertNotNull(model);
	}

	/**
	 * Test method for {@link TypePattern#equals(Object)} , {@link TypePattern#hashCode()} and
	 * {@link TypePattern#toString()}
	 */
	@Test
	public void testEqualsHashcodeAndToStringWithClassSilently()
	{
		boolean actual;
		boolean expected;
		actual = SilentEqualsHashCodeAndToStringEvaluator
			.evaluateEqualsHashcodeAndToStringQuietly(TypePattern.class);
		expected = true;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link TypePattern#match(String)}.
	 */
	@Test
	public final void testMatch() throws Exception
	{
		boolean actual;
		boolean expected;
		TypePattern typePattern;

		typePattern = new TypePattern(Pattern.compile("'.*'"), "");
		actual = typePattern.match("'xsd'");
		expected = true;
		assertEquals(expected, actual);

		typePattern = new TypePattern(null, "");
		actual = typePattern.match("'xsd'");
		expected = false;
		assertEquals(expected, actual);
	}

	/**
	 * Test method for {@link TypePattern}
	 */
	@Test
	public void testWithBeanTester()
	{
		Configuration configuration = new ConfigurationBuilder()
			.overrideFactory("pattern", new Factory<Pattern>()
			{

				@Override
				public Pattern create()
				{
					return Pattern.compile("(xsd|xml)");
				}

			}).build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(TypePattern.class, configuration);
		beanTester.testBean(TypePattern.class);
	}
}
