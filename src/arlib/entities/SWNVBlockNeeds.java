package arlib.entities;

import arlib.ARModel;

//TODO
public final class SWNVBlockNeeds extends SWServiceNeed
{
    public SWNVBlockNeeds(ARModel _model, String _path, String _shortName)
    {
        super(_model, _path, _shortName);
    }

    /*
    Internal data is not that interesting for now. But the name is useful.
      <CALC-RAM-BLOCK-CRC>true</CALC-RAM-BLOCK-CRC>
      <CHECK-STATIC-BLOCK-ID>true</CHECK-STATIC-BLOCK-ID>
      <N-DATA-SETS>0</N-DATA-SETS>
      <RAM-BLOCK-STATUS-CONTROL>API</RAM-BLOCK-STATUS-CONTROL>
      <READONLY>false</READONLY>
      <RELIABILITY>ERROR-CORRECTION</RELIABILITY>
      <RESISTANT-TO-CHANGED-SW>true</RESISTANT-TO-CHANGED-SW>
      <RESTORE-AT-START>true</RESTORE-AT-START>
      <STORE-AT-SHUTDOWN>true</STORE-AT-SHUTDOWN>
      <USE-AUTO-VALIDATION-AT-SHUT-DOWN>true</USE-AUTO-VALIDATION-AT-SHUT-DOWN>
      <USE-CRC-COMP-MECHANISM>true</USE-CRC-COMP-MECHANISM>
      <WRITE-ONLY-ONCE>false</WRITE-ONLY-ONCE>
      <WRITE-VERIFICATION>true</WRITE-VERIFICATION>
    */

}
