package io.github.astrapi69.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.astrapi69.test.object.Employee;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class XmlFrameworkConverterTest {

    /**
     * Test method for {@link XmlFrameworkConverter#jacksonToXstream(String, Class)}
     */
    @Test
    public void testJacksonToXstream() throws JsonProcessingException {

        String actual;
        String expected;
        Employee employee;
        expected = "<Employee>\n" + "  <id>23</id>\n" + "  <person>\n" + "    <about/>\n"
                + "    <gender>FEMALE</gender>\n" + "    <married/>\n" + "    <name>Anna</name>\n"
                + "    <nickname/>\n" + "  </person>\n" + "  <subOrdinates/>\n" + "</Employee>\n";
        actual = XmlFrameworkConverter.jacksonToXstream(expected, Employee.class);

        expected = "<io.github.astrapi69.test.object.Employee>\n" +
                "  <id>23</id>\n" +
                "  <person>\n" +
                "    <about></about>\n" +
                "    <gender>FEMALE</gender>\n" +
                "    <name>Anna</name>\n" +
                "    <nickname></nickname>\n" +
                "  </person>\n" +
                "  <subOrdinates/>\n" +
                "</io.github.astrapi69.test.object.Employee>";
        assertEquals(actual, expected);

    }
}
