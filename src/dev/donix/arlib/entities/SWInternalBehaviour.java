package dev.donix.arlib.entities;

import dev.donix.arlib.AREntity;
import dev.donix.arlib.ARModel;
import dev.donix.arlib.ARUtils;
import dev.donix.arlib.props.DataTypeMappingRef;
import dev.donix.arlib.props.PortApiOption;

import java.util.List;
import java.util.stream.Collectors;

public final class SWInternalBehaviour extends AREntity
{

    private final String _supportMultipleInstantiation;
    private final String _handleTerminationAndRestart;

    private final List<DataTypeMappingRef> _mappings;
    private final List<String> _eventRefs;
    private final List<PortApiOption> _portApiOptions;
    private final List<String> _runnableRefs;
    private final List<String> _variableDataRefs;
    private final List<String> _serviceDependencyRefs;

    public SWInternalBehaviour(ARModel _model,
                               String _path,
                               String _shortName,
                               String supportMultipleInstantiation,
                               String handleTerminationAndRestart,
                               List<DataTypeMappingRef> mappings,
                               List<String> eventRefs,
                               List<PortApiOption> portApiOptions,
                               List<String> runnableRefs,
                               List<String> variableDataRefs,
                               List<String> serviceDependencyRefs)
    {
        super(_model, _path, _shortName);
        _supportMultipleInstantiation = supportMultipleInstantiation;
        _handleTerminationAndRestart = handleTerminationAndRestart;
        _mappings = mappings;
        _eventRefs = eventRefs;
        _portApiOptions = portApiOptions;
        _runnableRefs = runnableRefs;
        _variableDataRefs = variableDataRefs;
        _serviceDependencyRefs = serviceDependencyRefs;
    }

    public boolean isSupportMultipleInstantiation()
    {
        return ARUtils.isTrue(_supportMultipleInstantiation);
    }

    public String getHandleTerminationAndRestart()
    {
        return _handleTerminationAndRestart;
    }

    public List<DataTypeMappingRef> getDataTypeMappingRefs()
    {
        return _mappings;
    }

    @SuppressWarnings("unchecked")
    public <T extends SWEvent> List<T> getEvents(Class<T> clazz)
    {
        return (List<T>) _model.getAllByReference(SWEvent.class, _eventRefs).stream().filter(clazz::isInstance).collect(Collectors.toList());
    }

    public List<PortApiOption> getPortApiOptions()
    {
        return _portApiOptions;
    }

    @SuppressWarnings("unchecked")
    public List<SWRunnableEntity> getRunnableEntities()
    {
        return _model.getAllByReference(SWRunnableEntity.class, _runnableRefs);
    }

    public List<SWServiceDependency> getServiceDependencies()
    {
        return _model.getAllByReference(SWServiceDependency.class, _serviceDependencyRefs);
    }

    @Override
    public String toString()
    {
        return super.toString() +
                " M=" + _supportMultipleInstantiation +
                " T=" + _handleTerminationAndRestart +
                " MAPS_C=" + _mappings.size() +
                " EVE_C=" + _eventRefs.size() +
                " PAPI_C=" + _portApiOptions.size() +
                " RUN_C=" + _runnableRefs.size() +
                " VD_C=" + _variableDataRefs.size() +
                " SDEP_C=" + _serviceDependencyRefs.size();
    }
}
