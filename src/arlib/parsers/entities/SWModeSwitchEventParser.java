package arlib.parsers.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.entities.SWModeSwitchEvent;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.ModeIRef;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

@ARTagBinding(ARTag.SWC_MODE_SWITCH_EVENT)
public class SWModeSwitchEventParser extends SimpleParser implements IAREntityParser
{

    private List<ModeIRef> _modeIRef;
    private List<ModeIRef> _disabledModeIRef;

    @Override
    public IARTagParser factory()
    {
        return new SWModeSwitchEventParser();
    }

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _modeIRef = new ArrayList<>();
        _disabledModeIRef = new ArrayList<>();
    }

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.START_ON_EVENT_REF);
        addTagPathNeed(ARTag.ACTIVATION);
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWModeSwitchEvent(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                getCaptured(ARTag.START_ON_EVENT_REF),
                getCaptured(ARTag.ACTIVATION),
                _modeIRef,
                _disabledModeIRef
        );
    }


    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        if(prop instanceof ModeIRef)
        {
            if (parserPath().equals(ARTag.MODE_IREF_LIST))
            {
                _modeIRef.add((ModeIRef) prop);
            }
            else if (parserPath().equals(ARTag.DISABLED_MODE_IREF_LIST))
            {
                _disabledModeIRef.add((ModeIRef) prop);
            }
            else
            {
                throw new SAXException("ModeIRef was found during SWModeSwitchEvent parsing but in a wrong container");
            }
        }
    }
}
