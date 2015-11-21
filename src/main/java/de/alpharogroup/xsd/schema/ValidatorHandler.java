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
package de.alpharogroup.xsd.schema;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The Class ValidatorHandler.
 */
public class ValidatorHandler extends DefaultHandler
{

	/** The sax parse exception. */
	private SAXParseException saxParseException = null;

	/** The validation error. */
	private boolean validationError = false;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void error(final SAXParseException exception) throws SAXException
	{
		validationError = true;
		saxParseException = exception;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fatalError(final SAXParseException exception) throws SAXException
	{
		validationError = true;
		saxParseException = exception;
	}

	/**
	 * Gets the sax parse exception.
	 *
	 * @return the sax parse exception
	 */
	public SAXParseException getSaxParseException()
	{
		return saxParseException;
	}

	/**
	 * Checks if is validation error.
	 *
	 * @return true, if is validation error
	 */
	public boolean isValid()
	{
		return validationError;
	}

	public boolean isValidationError()
	{
		return validationError;
	}

	/**
	 * Sets the sax parse exception.
	 *
	 * @param saxParseException
	 *            the new sax parse exception
	 */
	public void setSaxParseException(final SAXParseException saxParseException)
	{
		this.saxParseException = saxParseException;
	}

	/**
	 * Sets the validation error.
	 *
	 * @param validationError
	 *            the new validation error
	 */
	public void setValidationError(final boolean validationError)
	{
		this.validationError = validationError;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void warning(final SAXParseException exception) throws SAXException
	{
	}
}