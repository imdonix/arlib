package dev.donix.arlib.parsers.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.entities.SWPrototypeComponent;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IAREntityParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
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
