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
package io.github.astrapi69.xml.tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TagAttribute
{

	/** The delimiter of the values from the attribute. */
	private String delimiter;
	/** The name of the attribute. */
	private String name;
	/** The values of the attribute. */
	private List<String> values;

	{
		onInitializerBlock();
	}

	public TagAttribute()
	{
	}

	public TagAttribute(String delimiter, String name, List<String> values)
	{
		this.delimiter = delimiter;
		this.name = name;
		this.values = values;
	}

	public static TagAttributeBuilder builder()
	{
		return new TagAttributeBuilder();
	}

	protected boolean canEqual(final Object other)
	{
		return other instanceof TagAttribute;
	}

	@Override
	public boolean equals(final Object o)
	{
		if (o == this)
			return true;
		if (!(o instanceof TagAttribute))
			return false;
		final TagAttribute other = (TagAttribute)o;
		if (!other.canEqual(this))
			return false;
		final Object this$delimiter = this.getDelimiter();
		final Object other$delimiter = other.getDelimiter();
		if (this$delimiter == null
			? other$delimiter != null
			: !this$delimiter.equals(other$delimiter))
			return false;
		final Object this$name = this.getName();
		final Object other$name = other.getName();
		if (this$name == null ? other$name != null : !this$name.equals(other$name))
			return false;
		final Object this$values = this.getValues();
		final Object other$values = other.getValues();
		if (this$values == null ? other$values != null : !this$values.equals(other$values))
			return false;
		return true;
	}

	public String getDelimiter()
	{
		return this.delimiter;
	}

	public TagAttribute setDelimiter(String delimiter)
	{
		this.delimiter = delimiter;
		return this;
	}

	public String getName()
	{
		return this.name;
	}

	public TagAttribute setName(String name)
	{
		this.name = name;
		return this;
	}

	public List<String> getValues()
	{
		return this.values;
	}

	public TagAttribute setValues(List<String> values)
	{
		this.values = values;
		return this;
	}

	@Override
	public int hashCode()
	{
		final int PRIME = 59;
		int result = 1;
		final Object $delimiter = this.getDelimiter();
		result = result * PRIME + ($delimiter == null ? 43 : $delimiter.hashCode());
		final Object $name = this.getName();
		result = result * PRIME + ($name == null ? 43 : $name.hashCode());
		final Object $values = this.getValues();
		result = result * PRIME + ($values == null ? 43 : $values.hashCode());
		return result;
	}

	public String joinValues()
	{
		return String.join(delimiter, values);
	}

	protected void onInitializerBlock(Object... objects)
	{
	}

	public TagAttributeBuilder toBuilder()
	{
		return new TagAttributeBuilder().delimiter(this.delimiter).name(this.name)
			.values(this.values == null ? java.util.Collections.emptyList() : this.values);
	}

	@Override
	public String toString()
	{
		return "TagAttribute(delimiter=" + this.getDelimiter() + ", name=" + this.getName()
			+ ", values=" + this.getValues() + ")";
	}

	public static class TagAttributeBuilder
	{
		private String delimiter;
		private String name;
		private ArrayList<String> values;

		TagAttributeBuilder()
		{
		}

		public TagAttribute build()
		{
			List<String> values;
			switch (this.values == null ? 0 : this.values.size())
			{
				case 0 :
					values = java.util.Collections.emptyList();
					break;
				case 1 :
					values = java.util.Collections.singletonList(this.values.get(0));
					break;
				default :
					values = java.util.Collections
						.unmodifiableList(new ArrayList<String>(this.values));
			}

			return new TagAttribute(delimiter, name, values);
		}

		public TagAttribute.TagAttributeBuilder clearValues()
		{
			if (this.values != null)
				this.values.clear();
			return this;
		}

		public TagAttribute.TagAttributeBuilder delimiter(String delimiter)
		{
			this.delimiter = delimiter;
			return this;
		}

		public TagAttribute.TagAttributeBuilder name(String name)
		{
			this.name = name;
			return this;
		}

		@Override
		public String toString()
		{
			return "TagAttribute.TagAttributeBuilder(delimiter=" + this.delimiter + ", name="
				+ this.name + ", values=" + this.values + ")";
		}

		public TagAttribute.TagAttributeBuilder value(String value)
		{
			if (this.values == null)
				this.values = new ArrayList<String>();
			this.values.add(value);
			return this;
		}

		public TagAttribute.TagAttributeBuilder values(Collection<? extends String> values)
		{
			if (this.values == null)
				this.values = new ArrayList<String>();
			this.values.addAll(values);
			return this;
		}
	}
}
