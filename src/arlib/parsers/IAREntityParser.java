package arlib.parsers;

import arlib.ARModel;
import arlib.AREntity;
import org.xml.sax.SAXException;

public interface IAREntityParser extends IARTagParser
{

    /**
     * Called when the element processing ended during XML parsing
     * @return the constructed element by the ElementParser
     */
    AREntity end(ARModel model) throws SAXException;

}
