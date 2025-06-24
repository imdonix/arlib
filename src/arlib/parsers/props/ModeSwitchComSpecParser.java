package arlib.parsers.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IARPropParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.ModeSwitchComSpec;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.MODE_SWITCH_RECEIVER_COM_SPEC)
@ARTagBinding(ARTag.MODE_SWITCH_SENDER_COM_SPEC)
public class ModeSwitchComSpecParser extends SimpleParser implements IARPropParser
{

    @Override
    public IARTagParser factory()
    {
        return new ModeSwitchComSpecParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.MODE_GROUP_REF);
        addTagPathNeed(ARTag.ENHANCED_MODE_API);
        addTagPathNeed(ARTag.SUPPORTS_ASYNCHRONOUS_MODE_SWITCH);
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new ModeSwitchComSpec(
                model,
                getCaptured(ARTag.MODE_GROUP_REF),
                getCaptured(ARTag.ENHANCED_MODE_API),
                getCaptured(ARTag.SUPPORTS_ASYNCHRONOUS_MODE_SWITCH)
        );
    }

}
