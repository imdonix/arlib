package dev.donix.arlib.parsers.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.entities.InterfaceElement;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IAREntityParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
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
