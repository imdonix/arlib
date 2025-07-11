package dev.donix.arlib.parsers.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.entities.Interface;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IAREntityParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

@ARTagBinding(ARTag.SENDER_RECEIVER_INTERFACE)
@ARTagBinding(ARTag.CLIENT_SERVER_INTERFACE)
public class InterfaceParser extends SimpleParser implements IAREntityParser
{

    private List<String> _elementRefs;

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _elementRefs = new ArrayList<>();
    }

    @Override
    public IARTagParser factory()
    {
        return new InterfaceParser();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.IS_SERVICE);
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new Interface(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                getCaptured(ARTag.IS_SERVICE),
                _elementRefs
        );
    }

    @Override
    public void passEntity(AREntity entity) throws SAXException
    {
        if(parserPath().equals(ARTag.DATA_ELEMENT_LIST) || parserPath().equals(ARTag.OPERATION_LIST))
        {
            _elementRefs.add(entity.getReference());
        }
    }
}
