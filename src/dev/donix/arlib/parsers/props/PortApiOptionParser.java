package dev.donix.arlib.parsers.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IARPropParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import dev.donix.arlib.props.PortApiOption;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.PORT_API_OPTION)
public class PortApiOptionParser extends SimpleParser implements IARPropParser
{

    @Override
    public IARTagParser factory()
    {
        return new PortApiOptionParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.PORT_REF);
        addTagPathNeed(ARTag.ENABLE_TAKE_ADDRESS);
        addTagPathNeed(ARTag.INDIRECT_API);
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new PortApiOption(
                model,
                getCaptured(ARTag.PORT_REF),
                getCaptured(ARTag.ENABLE_TAKE_ADDRESS),
                getCaptured(ARTag.INDIRECT_API)
        );
    }


}
