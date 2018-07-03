/**
 * 
 */
package de.alpharogroup.xml.tag;

import static org.testng.AssertJUnit.assertNotNull;

import org.meanbean.lang.Factory;
import org.meanbean.test.BeanTester;
import org.meanbean.test.Configuration;
import org.meanbean.test.ConfigurationBuilder;
import org.testng.annotations.Test;


/**
 * The unit test class for the class {@link ChildTagPosition}
 */
public class ChildTagPositionTest
{

	/**
	 * Test method for {@link ChildTagPosition} constructors and builders
	 */
	@Test
	public final void testConstructors()
	{
		ChildTagPosition model = new ChildTagPosition();
		assertNotNull(model);
		model = new ChildTagPosition(Tag.builder().build(), 1);
		assertNotNull(model);
		model = ChildTagPosition.builder().build();
		assertNotNull(model);
	}

	/**
	 * Test method for {@link ChildTagPosition}
	 */
	@Test
	public void testWithBeanTester()
	{
		Configuration configuration = new ConfigurationBuilder()
			.overrideFactory("child", new Factory<Tag>()
			{

				@Override
				public Tag create()
				{
					return Tag.builder().build();
				}

			}).build();
		final BeanTester beanTester = new BeanTester();
		beanTester.addCustomConfiguration(ChildTagPosition.class, configuration);
		beanTester.testBean(ChildTagPosition.class);
	}

}
