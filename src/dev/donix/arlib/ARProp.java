package dev.donix.arlib;

public class ARProp
{
    protected final ARModel _model;

    public ARProp(ARModel model)
    {
        _model = model;
    }

    @Override
    public String toString()
    {
        return "{" + this.getClass().getName() + "}";
    }
}
