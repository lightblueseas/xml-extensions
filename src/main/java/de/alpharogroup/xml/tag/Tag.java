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

/**
 * The class {@link Tag} represents an tag for xml or html where you can set the position of the
 * child tags
 */
public class Tag implements Serializable
{

	public static class TagBuilder
	{
		private Map<String, String> attributes;
		private List<ChildTagPosition> childTagPositions;
		private String content;
		private boolean endTag;
		private String name;

		TagBuilder()
		{
		}

		public Tag.TagBuilder attributes(Map<String, String> attributes)
		{
			this.attributes = attributes;
			return this;
		}

		public Tag build()
		{
			return new Tag(attributes, childTagPositions, content, endTag, name);
		}

		public Tag.TagBuilder childTagPositions(List<ChildTagPosition> childTagPositions)
		{
			this.childTagPositions = childTagPositions;
			return this;
		}

		public Tag.TagBuilder content(String content)
		{
			this.content = content;
			return this;
		}

		public Tag.TagBuilder endTag(boolean endTag)
		{
			this.endTag = endTag;
			return this;
		}

		public Tag.TagBuilder name(String name)
		{
			this.name = name;
			return this;
		}

		@Override
		public String toString()
		{
			return "Tag.TagBuilder(attributes=" + this.attributes + ", childTagPositions="
				+ this.childTagPositions + ", content=" + this.content + ", endTag=" + this.endTag
				+ ", name=" + this.name + ")";
		}
	}

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	public static TagBuilder builder()
	{
		return new TagBuilder();
	}

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

	public Tag()
	{
	}

	public Tag(Map<String, String> attributes, List<ChildTagPosition> childTagPositions,
		String content, boolean endTag, String name)
	{
		this.attributes = attributes;
		this.childTagPositions = childTagPositions;
		this.content = content;
		this.endTag = endTag;
		this.name = name;
	}

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

	protected boolean canEqual(final Object other)
	{
		return other instanceof Tag;
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

	@Override
	public boolean equals(final Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof Tag))
			return false;
		final Tag other = (Tag)o;
		if (!other.canEqual(this))
			return false;
		final Object this$content = this.getContent();
		final Object other$content = other.getContent();
		if (this$content == null ? other$content != null : !this$content.equals(other$content))
			return false;
		if (this.isEndTag() != other.isEndTag())
			return false;
		final Object this$name = this.getName();
		final Object other$name = other.getName();
		if (this$name == null ? other$name != null : !this$name.equals(other$name))
			return false;
		return true;
	}

	public Map<String, String> getAttributes()
	{
		return this.attributes;
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

	public List<ChildTagPosition> getChildTagPositions()
	{
		return this.childTagPositions;
	}

	public String getContent()
	{
		return this.content;
	}

	public String getName()
	{
		return this.name;
	}

	@Override
	public int hashCode()
	{
		final int PRIME = 59;
		int result = 1;
		final Object $content = this.getContent();
		result = result * PRIME + ($content == null ? 43 : $content.hashCode());
		result = result * PRIME + (this.isEndTag() ? 79 : 97);
		final Object $name = this.getName();
		result = result * PRIME + ($name == null ? 43 : $name.hashCode());
		return result;
	}

	public boolean isEndTag()
	{
		return this.endTag;
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
					break;
				}
			}
			if (found != null)
			{
				return getChildren().remove(found);
			}
		}
		return false;
	}

	public Tag setAttributes(Map<String, String> attributes)
	{
		this.attributes = attributes;
		return this;
	}

	public Tag setChildTagPositions(List<ChildTagPosition> childTagPositions)
	{
		this.childTagPositions = childTagPositions;
		return this;
	}

	public Tag setContent(String content)
	{
		this.content = content;
		return this;
	}

	public Tag setEndTag(boolean endTag)
	{
		this.endTag = endTag;
		return this;
	}

	public Tag setName(String name)
	{
		this.name = name;
		return this;
	}

	public TagBuilder toBuilder()
	{
		return new TagBuilder().attributes(this.attributes)
			.childTagPositions(this.childTagPositions).content(this.content).endTag(this.endTag)
			.name(this.name);
	}

	@Override
	public String toString()
	{
		return "Tag(content=" + this.getContent() + ", endTag=" + this.isEndTag() + ", name="
			+ this.getName() + ")";
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
				int lastPosition = 0;
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
