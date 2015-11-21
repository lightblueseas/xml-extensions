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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.alpharogroup.lang.ObjectExtensions;

/**
 * The Class Tag represents an tag for xml or html where you can set the position of the child tags.
 */
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
			this.attributes = new LinkedHashMap<>();
		}
		return getAttributes().put(name, value);
	}

	public boolean addChild(final Tag child, final Integer position)
	{
		if (getChildren() == null)
		{
			this.childTagPositions = new ArrayList<>();
		}
		final ChildTagPosition childTagPosition = new ChildTagPosition(child, position);

		return getChildren().add(childTagPosition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object clone()
	{
		return ObjectExtensions.clone(this);
	}

	/**
	 * Gets the attributes.
	 * 
	 * @return the attributes
	 */
	public Map<String, String> getAttributes()
	{
		return attributes;
	}

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	public List<ChildTagPosition> getChildren()
	{
		return childTagPositions;
	}

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Checks if is end tag.
	 * 
	 * @return true, if checks if is end tag
	 */
	public boolean isEndTag()
	{
		return endTag;
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
	 * Sets the content.
	 * 
	 * @param content
	 *            the content
	 */
	public void setContent(final String content)
	{
		this.content = content;
	}

	/**
	 * Sets the end tag.
	 * 
	 * @param endTag
	 *            the end tag
	 */
	public void setEndTag(final boolean endTag)
	{
		this.endTag = endTag;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString()
	{
		final StringBuilder buffer = toXmlString();
		return buffer.toString();
	}

	/**
	 * Creates from this Tag object an xml string.
	 *
	 * @return the string buffer
	 */
	public StringBuilder toXmlString()
	{
		final StringBuilder buffer = new StringBuilder();
		buffer.append("<");
		buffer.append(getName());
		if (getAttributes() != null && !getAttributes().isEmpty())
		{
			buffer.append(" ");
			for (final Map.Entry<String, String> entry : getAttributes().entrySet())
			{
				buffer.append(entry.getKey());
				buffer.append("=");
				buffer.append("\"").append(entry.getValue()).append("\"");
				buffer.append(" ");
			}
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
					buffer.append(child.getChild().toString());
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
		return buffer;
	}

}
