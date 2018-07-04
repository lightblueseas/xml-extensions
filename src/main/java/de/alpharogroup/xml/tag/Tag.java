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

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import de.alpharogroup.clone.object.CloneObjectQuietlyExtensions;
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
 * The class {@link Tag} represents an tag for xml or html where you can set the position of the
 * child tags
 */
@Getter
@Setter
@ToString(exclude={"attributes", "childTagPositions"})
@EqualsAndHashCode(exclude={"attributes", "childTagPositions"})
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tag implements Serializable
{

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/** The attributes of the tag. */
	private Map<String, String> attributes;

	/** The children. */
	private List<ChildTagPosition> childTagPositions;

	/** The content of the tag. */
	private String content;

	/** The flag endTag signals if this tag has an ending tag. */
	private boolean endTag;

	/** The name of the tag. */
	private String name;

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
			this.attributes = MapFactory.newLinkedHashMap();
		}
		return getAttributes().put(name, value);
	}

	/**
	 * Adds the given {@link Tag} child to the given position.
	 *
	 * @param child
	 *            the {@link Tag} child
	 * @param position
	 *            the position to add
	 * @return true, if the given {@link Tag} child was added successfully to the given position
	 *         otherwise false
	 */
	public boolean addChild(final Tag child, final Integer position)
	{
		if (getChildren() == null)
		{
			this.childTagPositions = ListFactory.newArrayList();
		}
		final ChildTagPosition childTagPosition = ChildTagPosition.builder().child(child)
			.position(position).build();
		return getChildren().add(childTagPosition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone()
	{
		return CloneObjectQuietlyExtensions.cloneQuietly(this);
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<ChildTagPosition> getChildren()
	{
		return this.childTagPositions;
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
	 * Removes the given {@link Tag} child
	 *
	 * @param child
	 *            the {@link Tag} child
	 * @return true, if the given {@link Tag} child was removed successfully otherwise false
	 */
	public boolean removeChild(final Tag child)
	{
		if (getChildren() != null)
		{
			ChildTagPosition found = null;
			for (final ChildTagPosition childTagPosition : getChildren())
			{
				if (childTagPosition.getChild().equals(child))
				{
					found = childTagPosition;
				}
			}
			if (found != null)
			{
				return getChildren().remove(found);
			}
		}
		return false;
	}

	/**
	 * Creates from this {@link Tag} object an xml string.
	 *
	 * @return the string buffer
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
			if (getChildren() != null && !getChildren().isEmpty())
			{
				String processingContent = getContent();
				Integer lastPosition = 0;
				for (final ChildTagPosition child : getChildren())
				{
					final String subContent = getContent().substring(lastPosition,
						child.getPosition());
					lastPosition = child.getPosition();
					processingContent = processingContent.substring(lastPosition,
						processingContent.length());
					buffer.append(subContent);
					buffer.append(child.getChild().toXmlString());
				}
				buffer.append(processingContent);
			}
			else
			{
				buffer.append(getContent());
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
