package arlib.parsers.props;

import arlib.ARModel;
import arlib.ARProp;
import arlib.ARTag;
import arlib.parsers.ARTagBinding;
import arlib.parsers.IARPropParser;
import arlib.parsers.IARTagParser;
import arlib.parsers.SimpleParser;
import arlib.props.RoleBasedPortAssignment;
import org.xml.sax.SAXException;

@ARTagBinding(ARTag.ROLE_BASED_PORT_ASSIGNMENT)
public class RoleBasedPortAssignmentParser extends SimpleParser implements IARPropParser
{

    @Override
    public IARTagParser factory()
    {
        return new RoleBasedPortAssignmentParser();
    }


    @Override
    protected void declareNeeds()
    {
        addTagPathNeed(ARTag.PORT_PROTOTYPE_REF);
        addTagPathNeed(ARTag.ROLE);
    }

    @Override
    public ARProp end(ARModel model) throws SAXException
    {
        return new RoleBasedPortAssignment(
                model,
                getCaptured(ARTag.PORT_PROTOTYPE_REF),
                getCaptured(ARTag.ROLE)
        );
    }



}
