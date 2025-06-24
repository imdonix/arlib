package arlib.entities;

import arlib.AREntity;
import arlib.ARModel;
import arlib.props.RoleBasedPortAssignment;

import java.util.List;

public class SWServiceDependency extends AREntity
{

    private final List<String> _serviceRefs;
    private final List<RoleBasedPortAssignment> _roleBasedPortAssignmentRefs;

    public SWServiceDependency(ARModel _model,
                               String _path,
                               String _shortName,
                               List<String> serviceRefs,
                               List<RoleBasedPortAssignment> roleBasedPortAssignmentRefs)
    {
        super(_model, _path, _shortName);
        this._serviceRefs = serviceRefs;
        this._roleBasedPortAssignmentRefs = roleBasedPortAssignmentRefs;
    }

    public List<RoleBasedPortAssignment> getRoleBasedPortAssignment()
    {
        return _roleBasedPortAssignmentRefs;
    }

    public List<SWServiceNeed> getServiceNeeds()
    {
        return _model.getAllByReference(SWServiceNeed.class, _serviceRefs);
    }
}
