package arlib.parsers.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.entities.InterfaceElement;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.VARIABLE_DATA_PROTOTYPE)
@ARTagBinding(ARTag.CLIENT_SERVER_OPERATION)
public class InterfaceElementParser extends SimpleParser implements IAREntityParser
{

    @Override
    public IARTagParser factory()
    {
        return new InterfaceElementParser();
    }

    @Override
    protected void declareNeeds() {}

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new InterfaceElement(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME)
        );
    }

    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        //TODO
    }
}
