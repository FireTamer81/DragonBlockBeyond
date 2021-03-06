package io.github.firetamer.dbb.api.extensions;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(ElementType.FIELD)
public @interface ApiExtension {

	Class<? extends ApiExtendable> value();

}
