package arlib.parsers.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IARPropParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.PortApiOption;
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
