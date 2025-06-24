package arlib.parsers;

import arlib.ARModel;
import arlib.ARProp;
import org.xml.sax.SAXException;

public interface IARPropParser extends  IARTagParser
{

    /**
     * Called when the element processing ended during XML parsing
     * @return the constructed element by the ElementParser
     */
    ARProp end(ARModel model) throws SAXException;

}
