package dev.donix.arlib.parsers.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IARPropParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import dev.donix.arlib.props.CSComSpec;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.CLIENT_COM_SPEC)
@ARTagBinding(ARTag.SERVER_COM_SPEC)
public class ComSpecParser extends SimpleParser implements IARPropParser
{

    @Override
    public IARTagParser factory()
    {
        return new ComSpecParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.OPERATION_REF);
        addTagPathNeed(ARTag.QUEUE_LENGTH);
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new CSComSpec(
            model,
            getCaptured(ARTag.OPERATION_REF),
            getCaptured(ARTag.QUEUE_LENGTH)
        );
    }
}
