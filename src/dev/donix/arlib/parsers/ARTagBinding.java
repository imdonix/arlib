package dev.donix.arlib.parsers;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ARTagBindingContainer.class)
public @interface ARTagBinding
{
    public String value();
}