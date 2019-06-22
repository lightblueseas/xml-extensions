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

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.alpharogroup.clone.object.CloneObjectExtensions;
import de.alpharogroup.collections.list.ListFactory;
import de.alpharogroup.collections.map.MapFactory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * The class Tag represents an tag for xml or html.
 */
@Getter
@Setter
@ToString(exclude = { "attributes", "children" })
@EqualsAndHashCode(exclude = { "attributes", "children" })
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimpleTag implements Serializable
{

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The attributes of the tag. */
	Map<String, String> attributes;

	/** The children. */
	List<SimpleTag> children;

	/** The content of the tag. */
	String content;

	/** The flag endTag signals if this tag has an ending tag. */
	boolean endTag;

	/** The name of the tag. */
	String name;

	/**
	 * Adds the attribute with the given name and value.
	 *
	 * @param name
	 *            the name
	 * @param value
	 *            the value
	 * @return the string
	 */
	public String addAttribute(final String name, final String value)
	{
		if (getAttributes() == null)
		{
			setAttributes(MapFactory.newLinkedHashMap());
		}
		return getAttributes().put(name, value);
	}

	/**
	 * Adds the given child.
	 *
	 * @param child
	 *            the child
	 * @return true, if successful
	 */
	public boolean addChild(final SimpleTag child)
	{
		if (getChildren() == null)
		{
			setChildren(ListFactory.newArrayList());
		}
		return getChildren().add(child);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone() throws CloneNotSupportedException
	{
		try
		{
			return CloneObjectExtensions.clone(this);
		}
		catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException
			| InvocationTargetException | ClassNotFoundException | InstantiationException
			| IOException e)
		{
			throw new CloneNotSupportedException();
		}
	}

	/**
	 * Removes the attribute with the given name.
	 *
	 * @param name
	 *            the name
	 * @return the string
	 */
	public String removeAttribute(final String name)
	{
		if (getAttributes() != null)
		{
			getAttributes().remove(name);
		}
		return null;
	}

	/**
	 * Removes the given child if exists.
	 *
	 * @param child
	 *            the child
	 * @return true, if successful
	 */
	public boolean removeChild(final SimpleTag child)
	{
		if (getChildren() != null)
		{
			return getChildren().remove(child);
		}
		return false;
	}

	/**
	 * Creates from this Tag object an velocity template as String object. It puts children Tag
	 * object in the template recursively.
	 *
	 * @return the string buffer
	 */
	public StringBuilder toVelocityTemplate()
	{
		final StringBuilder buffer = new StringBuilder();
		buffer.append("<");
		buffer.append("${").append(getName()).append(".name}\n");
		if (getAttributes() != null && !getAttributes().isEmpty())
		{
			buffer.append(" #foreach(" + "$attribute in $").append(getName())
				.append(".attributes.keySet()" + ")\n");
			buffer.append("$attribute=\"$").append(getName())
				.append(".getAttributes().get($attribute)\"\n");
			buffer.append(" #end\n");
		}
		buffer.append("#if(${").append(getName()).append(".endTag})>${").append(getName())
			.append(".content}\n");
		if (getChildren() != null && !getChildren().isEmpty())
		{
			buffer.append("#foreach($").append(getChildren().get(0).getName()).append(" in $")
				.append(getName()).append(".children)\n");
			for (final SimpleTag child : getChildren())
			{
				buffer.append(child.toVelocityTemplate().toString());
			}
			buffer.append("#end\n");
		}
		buffer.append("</${").append(getName()).append(".name}>\n");
		buffer.append("#else />\n" + "#end\n");
		return buffer;
	}

	/**
	 * Creates from this Tag object an xml string.
	 *
	 * @return the string
	 */
	public String toXmlString()
	{
		final StringBuilder buffer = new StringBuilder();
		buffer.append("<");
		buffer.append(getName());
		Optional<String> attr = TagExtensions.attributesToString(getAttributes());
		if (attr.isPresent())
		{
			buffer.append(attr.get());
		}
		if (isEndTag())
		{
			buffer.append(">");
			buffer.append(getContent());
			if (getChildren() != null && !getChildren().isEmpty())
			{
				for (final SimpleTag child : getChildren())
				{
					buffer.append(child.toXmlString());
				}
			}
			buffer.append("</");
			buffer.append(getName());
			buffer.append(">");
		}
		else
		{
			buffer.append("/>");
		}
		return buffer.toString();
	}

}
