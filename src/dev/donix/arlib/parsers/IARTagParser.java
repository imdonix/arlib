package dev.donix.arlib.parsers;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARProp;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public interface IARTagParser
{
    /**
     * Called when the element is being processed during XML parsing
     * @param path
     */
    void start(String path, String tag);

    /**
     * Called when a start tag found during XML parsing
     * @param tag Tag such as 'PROVIDED-COM-SPECS'
     */
    void startElement(String tag, Attributes attributes) throws SAXException;

    /**
     * Called when an end tag found during XML parsing
     * @param tag Tag without / such as 'PROVIDED-COM-SPECS'
     * @param text Text between the start and end tag
     */
    void endElement(String tag, String text) throws SAXException;

    /**
     * Pass extra information from main parser to the sub parsers.
     * @param info key
     * @param value value
     * @return if the shared value is new then return True
     */
    boolean shareInfo(String info, String value);

    /**
     * Pass the parsed child entity for the parent to collect
     */
    void passEntity(AREntity entity) throws SAXException;

    /**
     * Pass the parsed child prop for the parent to collect
     */
    void passProp(ARProp prop) throws SAXException;


    /**
     * Clone the parser
     */
    IARTagParser factory();
}
