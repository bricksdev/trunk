/*
 * Copyright 2002-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.tags;

import cn.com.utils.StringUtil;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Stack;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

/**
 * Utility class for writing HTML content to a {@link Writer} instance.
 *
 * <p>Intended to support output from JSP tag libraries.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @since 2.0
 */
public class TagWriter {

    private static final String _TAG_END = ">\n";
    private static final String _TAG_START = "<";
    private static final String __BLANK = " ";
    private static final String __CLOSE_CHAR = "\" ";
    private static final String __EQUAL_CHAR = "=\"";
    private static final String __REPLACE_CHAR = "\\\"";
    private static final String __SPECIAL_CHAR_REG = "[\"]*";
    private static final String __TAG_CLOSE_END = "/>\n";
    private static final String __TAG_CLOSE_START = "</";
    private static final String __TAG_FORMAT_TAB = "\t";
    private static final String __VALUE_TARGET = "\"";
    /**
     * The {@link SafeWriter} to write to.
     */
    private final SafeWriter writer;
    /**
     * Stores {@link TagStateEntry tag state}. Stack model naturally supports tag nesting.
     */
    private final Stack tagState = new Stack();

    /**
     * Create a new instance of the {@link TagWriter} class that writes to
     * the supplied {@link Writer}.
     * @param pageContext the JSP PageContext to obtain the {@link Writer} from
     */
    public TagWriter(PageContext pageContext) {
        this.writer = new SafeWriter(pageContext);
    }

    /**
     * Create a new instance of the {@link TagWriter} class that writes to
     * the supplied {@link Writer}.
     * @param writer the {@link Writer} to write tag content to
     */
    public TagWriter(Writer writer) {
        this.writer = new SafeWriter(writer);
    }

    /**
     * Start a new tag with the supplied name. Leaves the tag open so
     * that attributes, inner text or nested tags can be written into it.
     * @see #endTag()
     */
    public void startTag(String tagName) throws JspException {
        if (inTag()) {
            closeTagAndMarkAsBlock();
        }
        push(tagName);
        this.formatElementTab();
        this.writer.append(_TAG_START).append(tagName);
    }

    private void formatElementTab() throws JspException {
        for (int i = 0; i < this.tagState.size() - 1; i++) {
            this.writer.append(__TAG_FORMAT_TAB);
        }
    }

    /**
     * Write an HTML attribute with the specified name and value.
     * <p>Be sure to write all attributes <strong>before</strong> writing
     * any inner text or nested tags.
     * @throws IllegalStateException if the opening tag is closed
     */
    public void writeAttribute(String attributeName, String attributeValue) throws JspException {
        if (currentState().isBlockTag()) {
            throw new IllegalStateException("Cannot write attributes after opening tag is closed.");
        }
        // 处理value包含特殊【”】字符
        String value = attributeValue;
        if (value.contains(__VALUE_TARGET)) {
            value = attributeValue.replaceAll(__SPECIAL_CHAR_REG, __REPLACE_CHAR);
        }

        this.writer.append(__BLANK).append(attributeName).append(__EQUAL_CHAR).append(value).append(__CLOSE_CHAR);
    }

    /**
     * Write an HTML attribute if the supplied value is not <code>null</code>
     * or zero length.
     * @see #writeAttribute(String, String)
     */
    public void writeOptionalAttributeValue(String attributeName, String attributeValue) throws JspException {
        if (!StringUtil.isEmpty(attributeValue)) {
            writeAttribute(attributeName, attributeValue);
        }
    }

    /**
     * Close the current opening tag (if necessary) and appends the
     * supplied value as inner text.
     * @throws IllegalStateException if no tag is open
     */
    public void appendValue(String value) throws JspException {
        if (!inTag()) {
            throw new IllegalStateException("Cannot write tag value. No open tag available.");
        }
        closeTagAndMarkAsBlock();
        this.writer.append(value);
    }

    /**
     * Indicate that the currently open tag should be closed and marked
     * as a block level element.
     * <p>Useful when you plan to write additional content in the body
     * outside the context of the current {@link TagWriter}.
     */
    public void forceBlock() throws JspException {
        if (currentState().isBlockTag()) {
            return; // just ignore since we are already in the block
        }
        closeTagAndMarkAsBlock();
    }

    public void close() {
        this.writer.close();
    }

    /**
     * Close the current tag.
     * <p>Correctly writes an empty tag if no inner text or nested tags
     * have been written.
     */
    public void endTag() throws JspException {
        endTag(false);
    }

    /**
     * Close the current tag, allowing to enforce a full closing tag.
     * <p>Correctly writes an empty tag if no inner text or nested tags
     * have been written.
     * @param enforceClosingTag whether a full closing tag should be
     * rendered in any case, even in case of a non-block tag
     */
    public void endTag(boolean enforceClosingTag) throws JspException {
        if (!inTag()) {
            throw new IllegalStateException("Cannot write end of tag. No open tag available.");
        }
        boolean renderClosingTag = true;
        if (!currentState().isBlockTag()) {
            // Opening tag still needs to be closed...
            if (enforceClosingTag) {
                this.writer.append(_TAG_END);
            } else {
                this.writer.append(__TAG_CLOSE_END);
                renderClosingTag = false;
            }
        }
        if (renderClosingTag) {
            formatElementClosingTab();
            this.writer.append(__TAG_CLOSE_START).append(currentState().getTagName()).append(_TAG_END);
        }
        this.tagState.pop();
    }

    private void formatElementClosingTab() throws JspException {
        for (int i = this.tagState.size() - 1; i > 0; i--) {
            this.writer.append(__TAG_FORMAT_TAB);
        }
    }

    /**
     * Close the current tag, allowing to enforce a full closing tag.
     * <p>Correctly writes an empty tag if no inner text or nested tags
     * have been written.
     * @param enforceClosingTag whether a full closing tag should be
     * rendered in any case, even in case of a non-block tag
     */
    public void endTag(boolean enforceClosingTag, String bodyContent) throws JspException {
        if (!inTag()) {
            throw new IllegalStateException("Cannot write end of tag. No open tag available.");
        }
        boolean renderClosingTag = true;
        if (!currentState().isBlockTag()) {
            // Opening tag still needs to be closed...
            if (enforceClosingTag) {
                this.writer.append(_TAG_END);
            } else {
                this.writer.append(__TAG_CLOSE_END);
                renderClosingTag = false;
            }
        }
        if (renderClosingTag) {
            formatElementClosingTab();
            if (!StringUtil.isEmpty(bodyContent)) {
                this.writer.append(bodyContent);
            }

            this.writer.append(__TAG_CLOSE_START).append(currentState().getTagName()).append(_TAG_END);
        }
        this.tagState.pop();
    }

    /**
     * 获取写入的
     * @return
     */
    public String getWritedString() {
        return this.writer.getWritedString();
    }

    /**
     * Adds the supplied tag name to the {@link #tagState tag state}.
     */
    private void push(String tagName) {
        this.tagState.push(new TagStateEntry(tagName));
    }

    /**
     * Closes the current opening tag and marks it as a block tag.
     */
    private void closeTagAndMarkAsBlock() throws JspException {
        if (!currentState().isBlockTag()) {
            currentState().markAsBlockTag();
            this.writer.append(_TAG_END);
        }
    }

    private boolean inTag() {
        return this.tagState.size() > 0;
    }

    private TagStateEntry currentState() {
        return (TagStateEntry) this.tagState.peek();
    }

    /**
     * Holds state about a tag and its rendered behaviour.
     */
    private static final class TagStateEntry {

        private final String tagName;
        private boolean blockTag;

        public TagStateEntry(String tagName) {
            this.tagName = tagName;
        }

        public String getTagName() {
            return this.tagName;
        }

        public void markAsBlockTag() {
            this.blockTag = true;
        }

        public boolean isBlockTag() {
            return this.blockTag;
        }
    }

    /**
     * Simple {@link Writer} wrapper that wraps all
     * {@link IOException IOExceptions} in {@link JspException JspExceptions}.
     */
    private static final class SafeWriter {

        private PageContext pageContext;
        private Writer writer;

        public SafeWriter(PageContext pageContext) {
            this.pageContext = pageContext;
        }

        public SafeWriter(Writer writer) {
            this.writer = writer;
        }

        public SafeWriter append(String value) throws JspException {
            try {
                getWriterToUse().write(value);
                return this;
            } catch (IOException ex) {
                throw new JspException("Unable to write to JspWriter", ex);
            }
        }

        public void close() {
            try {
                this.writer.close();
            } catch (IOException ex) {
                Logger.getLogger(TagWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        /**
         * 获取已加入的字符
         * @return
         */
        public String getWritedString() {
            if (writer instanceof CharArrayWriter) {
                return new String(((CharArrayWriter) writer).toCharArray());
            } else if (writer instanceof StringWriter) {
                return ((StringWriter) writer).toString();
            }
            return writer.toString();
        }

        private Writer getWriterToUse() {
            return (this.pageContext != null ? this.pageContext.getOut() : this.writer);
        }
    }
}
