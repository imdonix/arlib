package dev.donix.arlib.parsers.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.entities.SWImplementation;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IAREntityParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.SWC_IMPLEMENTATION)
public class SWImplementationParser extends SimpleParser implements IAREntityParser
{

    @Override
    public IARTagParser factory()
    {
        return new SWImplementationParser();
    }

    @Override
    protected void declareNeeds()
    {
        //TODO
    }


    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWImplementation(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME)
        );
    }
}
