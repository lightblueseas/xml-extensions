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

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.alpharogroup.collections.list.ListFactory;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * The class {@link JSONObjectToObjectExtensions} converts json strings to java object and java
 * collections.
 */
@UtilityClass
public final class JSONObjectToObjectExtensions
{

	/**
	 * Transforms the given json object into a java object
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonObject
	 *            the json object
	 * @param clazz
	 *            the clazz of the generic type
	 * @param mapper
	 *            the object mapper
	 * @return the java object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred
	 */
	public static <T> T toObject(final @NonNull JSONObject jsonObject,
		final @NonNull Class<T> clazz, final @NonNull ObjectMapper mapper) throws IOException
	{
		return JsonStringToObjectExtensions.toObject(jsonObject.toString(), clazz, mapper);
	}

	/**
	 * Transforms the given {@link JSONArray} into a java object {@link List}.
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonArray
	 *            the json array the element class of the generic type
	 * @param elementClass
	 *            the element class
	 * @return the list with the java objects
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static <T> List<T> toObjectList(final @NonNull JSONArray jsonArray,
		final @NonNull Class<T> elementClass) throws IOException
	{
		List<String> list = ListFactory.newArrayList();
		for (int i = 0; i < jsonArray.length(); i++)
		{
			Object object = jsonArray.get(i);
			list.add(object.toString());
		}
		List<T> result = ListFactory.newArrayList();
		for (int i = 0; i < list.size(); i++)
		{
			result.add(JsonStringToObjectExtensions.toObject(list.get(i), elementClass));
		}
		return result;
	}

}
