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
package io.github.astrapi69.xml.crypto.file;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.codec.DecoderException;

import com.thoughtworks.xstream.XStream;

import io.github.astrapi69.crypto.hex.HexExtensions;
import io.github.astrapi69.file.read.ReadFileExtensions;
import io.github.astrapi69.xml.XmlToObjectExtensions;
import io.github.astrapi69.xml.factory.XStreamFactory;

/**
 * The class {@link XmlDecryptionExtensions} provides methods for read an encrypted file and decrypt
 * xml data.
 */
public final class XmlDecryptionExtensions
{
	private XmlDecryptionExtensions()
	{
	}

	/**
	 * Read from file the data object that was before saved as xml and encoded into a hexadecimal
	 * {@link String} object.
	 *
	 * @param <T>
	 *            the generic type of the data object
	 * @param aliases
	 *            the aliases for the {@link XStream} object
	 * @param selectedFile
	 *            the selected file to read
	 * @param allowTypesByWildcard
	 *            the allow types by wildcard
	 * @return the generic data object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws DecoderException
	 *             is thrown if an odd number or illegal of characters is supplied
	 */
	public static <T> T readFromFileAsXmlAndHex(final Map<String, Class<?>> aliases,
		final File selectedFile, String... allowTypesByWildcard)
		throws IOException, DecoderException
	{
		Objects.requireNonNull(aliases);
		Objects.requireNonNull(selectedFile);
		XStream xStream = XStreamFactory.newXStream();
		XStreamFactory.newXStream(xStream, aliases, allowTypesByWildcard);
		return readFromFileAsXmlAndHex(xStream, aliases, selectedFile);
	}

	/**
	 * Read from file the data object that was before saved as xml and encoded into a hexadecimal
	 * {@link String} object.
	 *
	 * @param <T>
	 *            the generic type of the data object
	 * @param xstream
	 *            the {@link XStream} object
	 * @param aliases
	 *            the aliases for the {@link XStream} object
	 * @param selectedFile
	 *            the selected file to read
	 * @return the generic data object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws DecoderException
	 *             is thrown if an odd number or illegal of characters is supplied
	 */
	public static <T> T readFromFileAsXmlAndHex(final XStream xstream,
		final Map<String, Class<?>> aliases, final File selectedFile)
		throws IOException, DecoderException
	{
		Objects.requireNonNull(xstream);
		Objects.requireNonNull(aliases);
		Objects.requireNonNull(selectedFile);
		return readFromFileAsXmlAndHex(xstream, aliases, selectedFile, "UTF-8");
	}

	/**
	 * Read from file the data object that was before saved as xml and encoded into a hexadecimal
	 * {@link String} object.
	 *
	 * @param <T>
	 *            the generic type of the data object
	 * @param xstream
	 *            the {@link XStream} object
	 * @param aliases
	 *            the aliases for the {@link XStream} object
	 * @param selectedFile
	 *            the selected file to read
	 * @param charset
	 *            the charset
	 * @return the generic data object
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws DecoderException
	 *             is thrown if an odd number or illegal of characters is supplied
	 */
	public static <T> T readFromFileAsXmlAndHex(final XStream xstream,
		final Map<String, Class<?>> aliases, final File selectedFile, String charset)
		throws IOException, DecoderException
	{
		Objects.requireNonNull(xstream);
		Objects.requireNonNull(aliases);
		Objects.requireNonNull(selectedFile);
		final String hexXmlString = ReadFileExtensions.readFromFile(selectedFile,
			Charset.forName(charset));
		String xmlString = HexExtensions.decodeHex(hexXmlString);
		return XmlToObjectExtensions.toObjectWithXStream(xstream, xmlString, aliases);
	}

}
