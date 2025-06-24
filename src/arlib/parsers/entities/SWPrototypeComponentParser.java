package arlib.parsers.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARTag;
import arlib.entities.SWPrototypeComponent;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.SW_COMPONENT_PROTOTYPE)
public class SWPrototypeComponentParser extends SimpleParser implements IAREntityParser
{

    @Override
    public IARTagParser factory()
    {
        return new SWPrototypeComponentParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.TYPE_TREF);
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWPrototypeComponent(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                getCaptured(ARTag.TYPE_TREF)
        );
    }

}
