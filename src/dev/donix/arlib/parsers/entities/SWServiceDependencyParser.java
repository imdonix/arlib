package dev.donix.arlib.parsers.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARProp;
import dev.donix.arlib.ARTag;
import dev.donix.arlib.entities.SWServiceDependency;
import dev.donix.arlib.parsers.ARTagBinding;
import dev.donix.arlib.parsers.IAREntityParser;
import dev.donix.arlib.parsers.IARTagParser;
import dev.donix.arlib.parsers.SimpleParser;
import dev.donix.arlib.props.RoleBasedPortAssignment;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;

@ARTagBinding(ARTag.SWC_SERVICE_DEPENDENCY)
public class SWServiceDependencyParser extends SimpleParser implements IAREntityParser
{

    private List<String> _serviceRefs;
    private List<RoleBasedPortAssignment> _ports;

    @Override
    public void start(String path, String tag)
    {
        super.start(path, tag);

        _serviceRefs = new ArrayList<>();
        _ports = new ArrayList<>();
    }

    @Override
    public IARTagParser factory()
    {
        return new SWServiceDependencyParser();
    }

    @Override
    protected void declareNeeds() {}

    @Override
    public AREntity end(ARModel model) throws SAXException
    {
        return new SWServiceDependency(
                model,
                getCaptured(ARTag.ARLIB_PATH),
                getCaptured(ARTag.SHORT_NAME),
                _serviceRefs,
                _ports
        );
    }

    @Override
    public void passProp(ARProp prop) throws SAXException
    {
        if(parserPath().equals(ARTag.ASSIGNED_PORT_LIST))
        {
            _ports.add((RoleBasedPortAssignment) prop);
        }
        else
        {
            throw new SAXException("Illegal prop was found during parsing ServiceDependency -> " + prop.getClass().getName());
        }
    }

    @Override
    public void passEntity(AREntity entity) throws SAXException
    {
        if(parserPath().equals(ARTag.SERVICE_NEED_LIST))
        {
            _serviceRefs.add(entity.getReference());
        }
        else
        {
            throw new SAXException("Illegal entity was found during parsing ServiceDependency -> " + entity.getClass().getName());
        }
    }
}
