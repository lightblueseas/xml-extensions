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

/**
 * The class {@link ChildTagPosition}
 */
public class ChildTagPosition implements Serializable
{

	public static class ChildTagPositionBuilder
	{
		private Tag child;
		private Integer position;

		ChildTagPositionBuilder()
		{
		}

		public ChildTagPosition build()
		{
			return new ChildTagPosition(child, position);
		}

		public ChildTagPosition.ChildTagPositionBuilder child(Tag child)
		{
			this.child = child;
			return this;
		}

		public ChildTagPosition.ChildTagPositionBuilder position(Integer position)
		{
			this.position = position;
			return this;
		}

		@Override
		public String toString()
		{
			return "ChildTagPosition.ChildTagPositionBuilder(child=" + this.child + ", position="
				+ this.position + ")";
		}
	}

	private static final long serialVersionUID = 1L;

	public static ChildTagPositionBuilder builder()
	{
		return new ChildTagPositionBuilder();
	}

	private Tag child;

	private Integer position;

	public ChildTagPosition()
	{
	}

	public ChildTagPosition(Tag child, Integer position)
	{
		this.child = child;
		this.position = position;
	}

	protected boolean canEqual(final Object other)
	{
		return other instanceof ChildTagPosition;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof ChildTagPosition))
			return false;
		final ChildTagPosition other = (ChildTagPosition)o;
		if (!other.canEqual(this))
			return false;
		final Object this$child = this.getChild();
		final Object other$child = other.getChild();
		if (this$child == null ? other$child != null : !this$child.equals(other$child))
			return false;
		final Object this$position = this.getPosition();
		final Object other$position = other.getPosition();
		if (this$position == null ? other$position != null : !this$position.equals(other$position))
			return false;
		return true;
	}

	public Tag getChild()
	{
		return this.child;
	}

	public Integer getPosition()
	{
		return this.position;
	}

	@Override
	public int hashCode()
	{
		final int PRIME = 59;
		int result = 1;
		final Object $child = this.getChild();
		result = result * PRIME + ($child == null ? 43 : $child.hashCode());
		final Object $position = this.getPosition();
		result = result * PRIME + ($position == null ? 43 : $position.hashCode());
		return result;
	}

	public ChildTagPosition setChild(Tag child)
	{
		this.child = child;
		return this;
	}

	public ChildTagPosition setPosition(Integer position)
	{
		this.position = position;
		return this;
	}

	public ChildTagPositionBuilder toBuilder()
	{
		return new ChildTagPositionBuilder().child(this.child).position(this.position);
	}

	@Override
	public String toString()
	{
		return "ChildTagPosition(child=" + this.getChild() + ", position=" + this.getPosition()
			+ ")";
	}
}
