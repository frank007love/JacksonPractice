package org.tonylin.practice.jackson.resolver;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;

@JsonTypeInfo(
use = Id.NAME,
include = As.PROPERTY,
property = "type",
visible = true
)
@JsonTypeIdResolver(AnimalResolver.class)
public interface Animal {
	String getName();
	String getType();
}
