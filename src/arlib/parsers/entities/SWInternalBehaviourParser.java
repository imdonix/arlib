package arlib.parsers.entities;

import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.AREntity;
import arlib.entities.SWInternalBehaviour;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IAREntityParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.DataTypeMappingRef;
import arlib.props.PortApiOption;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

@ARTagBinding(ARTag.SWC_INTERNAL_BEHAVIOR)
public class SWInternalBehaviourParser extends SimpleParser implements IAREntityParser
{

    private List<DataTypeMappingRef> _dataTypeMappingRefs;
    private List<String> _events;
    private List<PortApiOption> _options;
    private List<String> _runnables;
    private List<String> _variableDataList;
    private List<String> _serviceDependencyRefs;

    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.SUPPORTS_MULTIPLE_INSTANTIATION);
        addTagPathNeed(ARTag.HANDLE_TERMINATION_AND_RESTART);
    }

    @Override
    public IARTagParser factory()
    {
        return new SWInternalBehaviourParser();
    }

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _dataTypeMappingRefs = new ArrayList<>();
        _events = new ArrayList<>();
        _options = new ArrayList<>();
        _runnables = new ArrayList<>();
        _variableDataList = new ArrayList<>();
        _serviceDependencyRefs = new ArrayList<>();
    }

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWInternalBehaviour(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                getCaptured(ARTag.SUPPORTS_MULTIPLE_INSTANTIATION),
                getCaptured(ARTag.HANDLE_TERMINATION_AND_RESTART),
                _dataTypeMappingRefs,
                _events,
                _options,
                _runnables,
                _variableDataList,
                _serviceDependencyRefs
        );
    }

    @Override
    public void passEntity(AREntity entity) throws SAXException
    {
        if (parserPath().equals(ARTag.EVENTS))
        {
            _events.add(entity.getReference());
        }
        else if (parserPath().equals(ARTag.RUNNABLE_LIST))
        {
            _runnables.add(entity.getReference());
        }
        else if (parserPath().equals(ARTag.AR_TYPED_PER_INSTANCE_MEMORY_LIST))
        {
            _variableDataList.add(entity.getReference());
        }
        else if (parserPath().equals(ARTag.SERVICE_DEPENDENCY_LIST))
        {
            _serviceDependencyRefs.add(entity.getReference());
        }
        else if (parserPath().equals(ARTag.IMPLICIT_INTER_RUNNABLE_VARIABLE_LIST) || parserPath().equals(ARTag.EXPLICIT_INTER_RUNNABLE_VARIABLE_LIST))
        {
            //ignore
        }
        else if (parserPath().equals(ARTag.SHARED_PARAMETER_LIST))
        {
            //ignore
        }
        else
        {
            throw new SAXException("Illegal entity found during SWInternalBehaviour parsing -> " + entity.getClass().getName() + " PATH=(" + parserPath() + ")");
        }
    }

    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        if (parserPath().equals(ARTag.DATA_TYPE_MAPPING_REFS))
        {
            _dataTypeMappingRefs.add((DataTypeMappingRef) prop);

        }
        else if (parserPath().equals(ARTag.PORT_API_OPTION_LIST))
        {
            _options.add((PortApiOption) prop);
        }
        else
        {
            throw new SAXException("Illegal prop found during SWInternalBehaviour parsing -> " + prop.getClass().getName());
        }

    }
}
