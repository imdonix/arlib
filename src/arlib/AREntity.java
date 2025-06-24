package arlib;

public abstract class AREntity
{
    protected final ARModel _model;
    protected final String _path;
    protected final String _shortName ;

    public AREntity(ARModel _model, String _path, String _shortName)
    {
        this._model = _model;
        this._path = _path;
        this._shortName = _shortName;
    }

    public String getPath()
    {
        return _path;
    }

    public String getShortName()
    {
        return _shortName;
    }

    public String getReference()
    {
        return String.format("/%s/%s", _path, _shortName);
    }

    public String toString()
    {
        return "{" + this.getClass().getName() + "} " + getReference();
    }
}
