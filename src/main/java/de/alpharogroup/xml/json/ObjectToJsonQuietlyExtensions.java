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
package de.alpharogroup.xml.json;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * The class {@link ObjectToJsonQuietlyExtensions} converts java objects to json string objects in a
 * quietly manner as the name let presume.
 */
@UtilityClass
@Slf4j
public class ObjectToJsonQuietlyExtensions
{


	/**
	 * Creates from the given Object a json string. Note if an exception occurs null will be
	 * returned.
	 *
	 * @param <T>
	 *            the generic type of the given argument
	 * @param object
	 *            the object.
	 * @return the json string or null if an error occured by parsing.
	 */
	public static <T> String toJsonQuietly(final T object)
	{
		try
		{
			return ObjectToJsonExtensions.toJson(object);
		}
		catch (final JsonProcessingException e)
		{
			log.error("An error occured when converting object to String.\nGiven object:"
				+ object.toString() + "\n", e);
		}
		return null;
	}
}
