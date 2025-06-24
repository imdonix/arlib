package arlib;

public final class ARTag
{
    public static final String ARLIB_PATH = "@ARLIB-PATH";
    public static final String ARLIB_TAG = "@ARLIB-TAG";
    public static final String ARLIB_SELF = "@ARLIB_SELF";

    public final static String AUTOSAR = "AUTOSAR";
    public final static String PACKAGE_LIST = "AR-PACKAGES";
    public final static String PACKAGE = "AR-PACKAGE";
    public final static String ELEMENT_LIST = "ELEMENTS";
    public final static String TOP_LEVEL_PACKAGES = "TOP-LEVEL-PACKAGES";
    public final static String SUB_PACKAGES = "SUB-PACKAGES";
    public final static String SHORT_NAME = "SHORT-NAME";
    public final static String LONG_NAME = "LONG-NAME";
    public final static String L4 = "L-4";
    public final static String DEST = "DEST";
    public final static String SHORT_LABEL = "SHORT-LABEL";
    public final static String CATEGORY = "CATEGORY";
    public final static String V = "V";
    public final static String SYMBOL = "SYMBOL";
    public final static String ROLE = "ROLE";
    public final static String ADMIN_DATA = "ADMIN-DATA";
    public final static String REFERENCE_BASES = "REFERENCE-BASES";
    public final static String DESC = "DESC";
    public final static String INTRODUCTION = "INTRODUCTION";

    public final static String SENDER_RECEIVER_INTERFACE = "SENDER-RECEIVER-INTERFACE";
    public final static String CLIENT_SERVER_INTERFACE = "CLIENT-SERVER-INTERFACE";
    public final static String MODE_SWITCH_INTERFACE = "MODE-SWITCH-INTERFACE";
    public final static String IS_SERVICE = "IS-SERVICE";
    public final static String DATA_ELEMENT_LIST = "DATA-ELEMENTS";
    public final static String OPERATION_LIST = "OPERATIONS";
    public final static String VARIABLE_DATA_PROTOTYPE = "VARIABLE-DATA-PROTOTYPE";
    public final static String CLIENT_SERVER_OPERATION = "CLIENT-SERVER-OPERATION";


    public final static String APPLICATION_SW_COMPONENT_TYPE = "APPLICATION-SW-COMPONENT-TYPE";
    public final static String COMPLEX_DEVICE_DRIVER_SW_COMPONENT_TYPE = "COMPLEX-DEVICE-DRIVER-SW-COMPONENT-TYPE";
    public final static String SENSOR_ACTUATOR_SW_COMPONENT_TYPE = "SENSOR-ACTUATOR-SW-COMPONENT-TYPE";

    public final static String PORT_LIST = "PORTS";
    public final static String P_PORT_PROTOTYPE = "P-PORT-PROTOTYPE";
    public final static String R_PORT_PROTOTYPE = "R-PORT-PROTOTYPE";
    public final static String PR_PORT_PROTOTYPE = "PR-PORT-PROTOTYPE";
    public final static String PROVIDED_INTERFACE_TREF = "PROVIDED-INTERFACE-TREF";
    public final static String REQUIRED_INTERFACE_TREF = "REQUIRED-INTERFACE-TREF";
    public final static String DATA_ELEMENT_REF = "DATA-ELEMENT-REF";
    public final static String HANDLE_OUT_OF_RANGE = "HANDLE-OUT-OF-RANGE";
    public final static String ALIVE_TIMEOUT = "ALIVE-TIMEOUT";
    public final static String ENABLE_UPDATE = "ENABLE-UPDATE";
    public final static String HANDLE_NEVER_RECEIVED = "HANDLE-NEVER-RECEIVED";
    public final static String HANDLE_TIMEOUT_TYPE = "HANDLE-TIMEOUT-TYPE";
    public final static String APPLICATION_VALUE_SPECIFICATION = "APPLICATION-VALUE-SPECIFICATION";
    public final static String CONSTANT_REFERENCE = "CONSTANT-REFERENCE";
    public final static String CONSTANT_REF = "CONSTANT-REF";
    public final static String SW_VALUE_CONT = "SW-VALUE-CONT";
    public final static String UNIT_REF = "UNIT-REF";
    public final static String SW_VALUES_PHYS = "SW-VALUES-PHYS";
    public final static String ARRAY_VALUE_SPECIFICATION = "ARRAY-VALUE-SPECIFICATION";
    public final static String OPERATION_REF = "OPERATION-REF";
    public final static String QUEUE_LENGTH = "QUEUE-LENGTH";
    public final static String ENHANCED_MODE_API = "ENHANCED-MODE-API";
    public final static String MODE_GROUP_REF = "MODE-GROUP-REF";
    public final static String SUPPORTS_ASYNCHRONOUS_MODE_SWITCH = "SUPPORTS-ASYNCHRONOUS-MODE-SWITCH";
    public final static String RECORD_VALUE_SPECIFICATION = "RECORD-VALUE-SPECIFICATION";

    public final static String HANDLE_OUT_OF_RANGE_STATUS = "HANDLE-OUT-OF-RANGE-STATUS";
    public final static String USES_END_TO_END_PROTECTION = "USES-END-TO-END-PROTECTION";

    public final static String PROVIDED_COM_SPEC_LIST = "PROVIDED-COM-SPECS";
    public final static String REQUIRED_COM_SPECS = "REQUIRED-COM-SPECS";
    public final static String NONQUEUED_RECEIVER_COM_SPEC = "NONQUEUED-RECEIVER-COM-SPEC";
    public final static String NONQUEUED_SENDER_COM_SPEC = "NONQUEUED-SENDER-COM-SPEC";
    public final static String CLIENT_COM_SPEC = "CLIENT-COM-SPEC";
    public final static String SERVER_COM_SPEC = "SERVER-COM-SPEC";
    public final static String MODE_SWITCH_RECEIVER_COM_SPEC = "MODE-SWITCH-RECEIVER-COM-SPEC";
    public final static String MODE_SWITCH_SENDER_COM_SPEC = "MODE-SWITCH-SENDER-COM-SPEC";

    public final static String INTERNAL_BEHAVIOR_LIST = "INTERNAL-BEHAVIORS";
    public final static String SWC_INTERNAL_BEHAVIOR = "SWC-INTERNAL-BEHAVIOR";
    public final static String SUPPORTS_MULTIPLE_INSTANTIATION = "SUPPORTS-MULTIPLE-INSTANTIATION";
    public final static String HANDLE_TERMINATION_AND_RESTART = "HANDLE-TERMINATION-AND-RESTART";
    public final static String DATA_TYPE_MAPPING_REFS = "DATA-TYPE-MAPPING-REFS";
    public final static String DATA_TYPE_MAPPING_REF = "DATA-TYPE-MAPPING-REF";
    public final static String EVENTS = "EVENTS";
    public final static String SWC_MODE_SWITCH_EVENT = "SWC-MODE-SWITCH-EVENT";
    public final static String START_ON_EVENT_REF = "START-ON-EVENT-REF";
    public final static String ACTIVATION = "ACTIVATION";
    public final static String MODE_IREF_LIST = "MODE-IREFS";
    public final static String MODE_IREF = "MODE-IREF";
    public final static String DISABLED_MODE_IREF_LIST = "DISABLED-MODE-IREFS";
    public final static String DISABLED_MODE_IREF = "DISABLED-MODE-IREF";
    public final static String CONTEXT_PORT_REF = "CONTEXT-PORT-REF";
    public final static String CONTEXT_MODE_DECLARATION_GROUP_PROTOTYPE_REF = "CONTEXT-MODE-DECLARATION-GROUP-PROTOTYPE-REF";
    public final static String TARGET_MODE_DECLARATION_REF = "TARGET-MODE-DECLARATION-REF";
    public final static String TIMING_EVENT = "TIMING-EVENT";
    public final static String PERIOD = "PERIOD";
    public final static String PORT_API_OPTION_LIST = "PORT-API-OPTIONS";
    public final static String PORT_API_OPTION = "PORT-API-OPTION";
    public final static String ENABLE_TAKE_ADDRESS = "ENABLE-TAKE-ADDRESS";
    public final static String INDIRECT_API = "INDIRECT-API";
    public final static String PORT_REF = "PORT-REF";
    public final static String IMPLICIT_INTER_RUNNABLE_VARIABLE_LIST = "IMPLICIT-INTER-RUNNABLE-VARIABLES";
    public final static String EXPLICIT_INTER_RUNNABLE_VARIABLE_LIST = "EXPLICIT-INTER-RUNNABLE-VARIABLES";
    public final static String READ_LOCAL_VARIABLE_LIST = "READ-LOCAL-VARIABLES";
    public final static String WRITTEN_LOCAL_VARIABLES = "WRITTEN-LOCAL-VARIABLES";
    public final static String DATA_RECEIVED_EVENT = "DATA-RECEIVED-EVENT";

    public final static String MODE_ACCESS_POINT_LIST = "MODE-ACCESS-POINTS";
    public final static String MODE_ACCESS_POINT = "MODE-ACCESS-POINT";
    public final static String MODE_GROUP_IREF = "MODE-GROUP-IREF";
    public final static String R_MODE_GROUP_IN_ATOMIC_SWC_INSTANCE_REF = "R-MODE-GROUP-IN-ATOMIC-SWC-INSTANCE-REF";
    public final static String CONTEXT_R_PORT_REF = "CONTEXT-R-PORT-REF";
    public final static String TARGET_MODE_GROUP_REF = "TARGET-MODE-GROUP-REF";

    public final static String DATA_READ_ACCESS_LIST = "DATA-READ-ACCESSS";
    public final static String DATA_RECEIVE_POINT_BY_VALUE_LIST = "DATA-RECEIVE-POINT-BY-VALUES";
    public final static String DATA_RECEIVE_POINT_BY_ARGUMENTS = "DATA-RECEIVE-POINT-BY-ARGUMENTS";
    public final static String DATA_WRITE_ACCESS_LIST = "DATA-WRITE-ACCESSS";
    public final static String DATA_SEND_POINT_LIST = "DATA-SEND-POINTS";

    public final static String VARIABLE_ACCESS = "VARIABLE-ACCESS";
    public final static String ACCESSED_VARIABLE = "ACCESSED-VARIABLE";
    public final static String AUTOSAR_VARIABLE_IREF = "AUTOSAR-VARIABLE-IREF";
    public final static String PORT_PROTOTYPE_REF = "PORT-PROTOTYPE-REF";
    public final static String TARGET_DATA_PROTOTYPE_REF = "TARGET-DATA-PROTOTYPE-REF";

    public final static String SERVER_CALL_POINT_LIST = "SERVER-CALL-POINTS";
    public final static String SYNCHRONOUS_SERVER_CALL_POINT = "SYNCHRONOUS-SERVER-CALL-POINT";
    public final static String OPERATION_IREF = "OPERATION-IREF";
    public final static String TARGET_REQUIRED_OPERATION_REF = "TARGET-REQUIRED-OPERATION-REF";

    public final static String RUNNABLE_LIST = "RUNNABLES";
    public final static String RUNNABLE_ENTITY = "RUNNABLE-ENTITY";
    public final static String MINIMUM_START_INTERVAL = "MINIMUM-START-INTERVAL";
    public final static String CAN_BE_INVOKED_CONCURRENTLY = "CAN-BE-INVOKED-CONCURRENTLY";

    public final static String AR_TYPED_PER_INSTANCE_MEMORY_LIST = "AR-TYPED-PER-INSTANCE-MEMORYS";

    public final static String SERVICE_DEPENDENCY_LIST = "SERVICE-DEPENDENCYS";
    public final static String SWC_SERVICE_DEPENDENCY = "SWC-SERVICE-DEPENDENCY";
    public final static String ASSIGNED_PORT_LIST = "ASSIGNED-PORTS";
    public final static String ROLE_BASED_PORT_ASSIGNMENT = "ROLE-BASED-PORT-ASSIGNMENT";
    public final static String SERVICE_NEED_LIST = "SERVICE-NEEDS";
    public final static String NV_BLOCK_NEEDS = "NV-BLOCK-NEEDS";

    public final static String SHARED_PARAMETER_LIST = "SHARED-PARAMETERS";
    public final static String PARAMETER_DATA_PROTOTYPE = "PARAMETER-DATA-PROTOTYPE";
    public final static String INIT_VALUE = "INIT-VALUE";

    public final static String COMPOSITION_SW_COMPONENT_TYPE = "COMPOSITION-SW-COMPONENT-TYPE";
    public final static String COMPONENT_LIST = "COMPONENTS";
    public final static String SW_COMPONENT_PROTOTYPE = "SW-COMPONENT-PROTOTYPE";
    public final static String TYPE_TREF = "TYPE-TREF";
    public final static String CONNECTOR_LIST = "CONNECTORS";
    public final static String DELEGATION_SW_CONNECTOR = "DELEGATION-SW-CONNECTOR";
    public final static String INNER_PORT_IREF = "INNER-PORT-IREF";
    public final static String P_PORT_IN_COMPOSITION_INSTANCE_REF = "P-PORT-IN-COMPOSITION-INSTANCE-REF";
    public final static String R_PORT_IN_COMPOSITION_INSTANCE_REF = "R-PORT-IN-COMPOSITION-INSTANCE-REF";
    public final static String CONTEXT_COMPONENT_REF = "CONTEXT-COMPONENT-REF";
    public final static String TARGET_P_PORT_REF = "TARGET-P-PORT-REF";
    public final static String TARGET_R_PORT_REF = "TARGET-R-PORT-REF";
    public final static String OUTER_PORT_REF = "OUTER-PORT-REF";
    public final static String ASSEMBLY_SW_CONNECTOR = "ASSEMBLY-SW-CONNECTOR";
    public final static String PROVIDER_IREF = "PROVIDER-IREF";
    public final static String REQUESTER_IREF = "REQUESTER-IREF";





    public final static String SWC_IMPLEMENTATION = "SWC-IMPLEMENTATION";




    public final static StringBuilder _combineBuilder = new StringBuilder();
    public static String combine(String... args)
    {
        _combineBuilder.setLength(0);
        for (int i = 0; i < args.length - 1; i++)
        {
            _combineBuilder.append(args[i]);
            _combineBuilder.append("|");
        }

        _combineBuilder.append(args[args.length - 1]);

        return _combineBuilder.toString();
    }

}
