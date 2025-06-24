package arlib.parsers.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARTag;
import arlib.entities.SWNVBlockNeeds;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.NV_BLOCK_NEEDS)
public class SWNVBlockNeedsParser extends SimpleParser implements IAREntityParser
{

    @Override
    public IARTagParser factory()
    {
        return new SWNVBlockNeedsParser();
    }

    @Override
    protected void declareNeeds()
    {
        //TODO
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWNVBlockNeeds(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME)
        );
    }

}
