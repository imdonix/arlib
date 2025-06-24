package dev.donix.arlib.parsers.props;

import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IARPropParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.props.InitValue;
import dev.donix.arlib.props.NonqueuedComSpec;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.NONQUEUED_RECEIVER_COM_SPEC)
@ARTagBinding(ARTag.NONQUEUED_SENDER_COM_SPEC)
public class NonqueuedComSpecParser extends SimpleParser implements IARPropParser
{

    private InitValue _foundInitValue;

    @Override
    public IARTagParser factory()
    {
        return new NonqueuedComSpecParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.DATA_ELEMENT_REF);
        addTagPathNeed(ARTag.HANDLE_OUT_OF_RANGE);
        addTagPathNeed(ARTag.HANDLE_OUT_OF_RANGE_STATUS);
        addTagPathNeed(ARTag.USES_END_TO_END_PROTECTION);
        addTagPathNeed(ARTag.ALIVE_TIMEOUT);
        addTagPathNeed(ARTag.ENABLE_UPDATE);
        addTagPathNeed(ARTag.HANDLE_NEVER_RECEIVED);
        addTagPathNeed(ARTag.HANDLE_TIMEOUT_TYPE);
    }

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _foundInitValue = null;
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new NonqueuedComSpec(
                model,
                getCaptured(ARTag.DATA_ELEMENT_REF),
                getCaptured(ARTag.HANDLE_OUT_OF_RANGE),
                getCaptured(ARTag.HANDLE_OUT_OF_RANGE_STATUS),
                getCaptured(ARTag.USES_END_TO_END_PROTECTION),
                getCaptured(ARTag.ALIVE_TIMEOUT),
                getCaptured(ARTag.ENABLE_UPDATE),
                getCaptured(ARTag.HANDLE_NEVER_RECEIVED),
                getCaptured(ARTag.HANDLE_TIMEOUT_TYPE),
                _foundInitValue
        );
    }

    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        if(prop instanceof InitValue)
        {
            if(_foundInitValue == null)
            {
                _foundInitValue = (InitValue) prop;
            }
            else
            {
                throw new SAXException("Multiple init value found for NonqueuedComSpecParser NEW={" + prop.getClass().getName() + "}, OLD={" + _foundInitValue.getClass().getName() + "}");
            }
        }
    }
}
