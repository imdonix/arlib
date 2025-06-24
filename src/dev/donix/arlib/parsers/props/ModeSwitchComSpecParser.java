package dev.donix.arlib.parsers.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IARPropParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import dev.donix.arlib.props.ModeSwitchComSpec;
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
