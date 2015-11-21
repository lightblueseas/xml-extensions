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
package de.alpharogroup.xml.node;

import org.w3c.dom.Node;

/**
 * The Enum NodeType is a helper enum class for determine the type of an
 * <code>org.w3c.dom.Node</code> object.
 */
public enum NodeType
{
	/**
	 * The node is an <code>Element</code>.
	 */
	ELEMENT_NODE,
	/**
	 * The node is an <code>Attr</code>.
	 */
	ATTRIBUTE_NODE,
	/**
	 * The node is a <code>Text</code> node.
	 */
	TEXT_NODE,
	/**
	 * The node is a <code>CDATASection</code>.
	 */
	CDATA_SECTION_NODE,
	/**
	 * The node is an <code>EntityReference</code>.
	 */
	ENTITY_REFERENCE_NODE,
	/**
	 * The node is an <code>Entity</code>.
	 */
	ENTITY_NODE,
	/**
	 * The node is a <code>ProcessingInstruction</code>.
	 */
	PROCESSING_INSTRUCTION_NODE,
	/**
	 * The node is a <code>Comment</code>.
	 */
	COMMENT_NODE,
	/**
	 * The node is a <code>Document</code>.
	 */
	DOCUMENT_NODE,
	/**
	 * The node is a <code>DocumentType</code>.
	 */
	DOCUMENT_TYPE_NODE,
	/**
	 * The node is a <code>DocumentFragment</code>.
	 */
	DOCUMENT_FRAGMENT_NODE,
	/**
	 * The node is a <code>Notation</code>.
	 */
	NOTATION_NODE,

	/** The UNDEFINED is a NullObject and is returned when nothing matches to the other one. */
	UNDEFINED;

	/**
	 * Gets the node type from the given node.
	 *
	 * @param node
	 *            the node
	 * @return the node type from the given node.
	 */
	public static NodeType getNodeType(final Node node)
	{
		return getNodeType(node.getNodeType());
	}

	/**
	 * Gets the node type from the given short value.
	 *
	 * @param value
	 *            the short value.
	 * @return the node type
	 */
	public static NodeType getNodeType(final short value)
	{
		for (final NodeType current : values())
		{
			if (current.ordinal() + 1 == value)
			{
				return current;
			}
		}
		return UNDEFINED;
	}

}
