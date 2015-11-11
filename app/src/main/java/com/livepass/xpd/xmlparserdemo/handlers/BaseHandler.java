package com.livepass.xpd.xmlparserdemo.handlers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by VuVT on 11/11/2015.
 * XML Parser base
 */
public abstract class BaseHandler extends DefaultHandler {
    protected final static String ERROR_CODE_XML = "ErrorCode";
    protected final static String ERROR_CODE_ID_XML = "errorID";
    protected final static String ERROR_CODE_MSG_XML = "errorMsg";
    protected StringBuilder builder;
    protected ErrorCode errorCode;
    protected int toTalPage = 0;

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        super.characters(ch, start, length);
        builder.append(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        super.endElement(uri, localName, qName);
        if (this.errorCode != null) {
            if (localName.equalsIgnoreCase(ERROR_CODE_ID_XML)) {
                errorCode.setErrorID(builder.toString().trim());
            } else if (localName.equalsIgnoreCase(ERROR_CODE_MSG_XML)) {
                errorCode.setErrorMsg(builder.toString().trim());
            }
        } else {
            processNotEndErrorCode(localName);
        }
        builder.setLength(0);

    }

    protected abstract void processNotEndErrorCode(String localName);

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        builder = new StringBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        if (localName.equalsIgnoreCase(ERROR_CODE_XML)) {
            this.errorCode = new ErrorCode();
        } else {
            processNotStartErrorCode(localName);
        }
    }

    public abstract void processNotStartErrorCode(String localName);

    public Object getResult() {
        if (errorCode != null) {
            return errorCode;
        } else {
            return returnNotErrorCode();
        }
    }

    public int getTotalPage() {
        return toTalPage;
    }

    protected abstract Object returnNotErrorCode();

}