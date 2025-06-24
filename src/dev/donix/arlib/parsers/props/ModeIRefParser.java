package dev.donix.arlib.parsers.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IARPropParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import dev.donix.arlib.props.ModeIRef;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.MODE_IREF)
@ARTagBinding(ARTag.DISABLED_MODE_IREF)
public class ModeIRefParser extends SimpleParser implements IARPropParser
{

    @Override
    public IARTagParser factory()
    {
        return new ModeIRefParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.CONTEXT_PORT_REF);
        addTagPathNeed(ARTag.CONTEXT_MODE_DECLARATION_GROUP_PROTOTYPE_REF);
        addTagPathNeed(ARTag.TARGET_MODE_DECLARATION_REF);
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new ModeIRef(
                model,
                getCaptured(ARTag.CONTEXT_PORT_REF),
                getCaptured(ARTag.CONTEXT_MODE_DECLARATION_GROUP_PROTOTYPE_REF),
                getCaptured(ARTag.TARGET_MODE_DECLARATION_REF)
        );
    }
}
