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

import java.util.Map;
import java.util.Optional;

import lombok.experimental.UtilityClass;

/**
 * The class {@link TagExtensions}.
 */
@UtilityClass
public final class TagExtensions
{

	/**
	 * Creates an {@link Optional} of {@link String} from the given attributes map
	 *
	 * @param attributes
	 *            the attributes map
	 * @return the {@link Optional} of {@link String}
	 */
	public static Optional<String> attributesToString(Map<String, String> attributes)
	{
		StringBuilder builder = new StringBuilder();
		if (attributes != null && !attributes.isEmpty())
		{
			builder.append(" ");
			for (final Map.Entry<String, String> entry : attributes.entrySet())
			{
				builder.append(entry.getKey());
				builder.append("=");
				builder.append("\"").append(entry.getValue()).append("\"");
				builder.append(" ");
			}
		}
		else
		{
			return Optional.empty();
		}
		return Optional.of(builder.toString());
	}
}
