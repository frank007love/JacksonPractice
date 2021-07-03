package org.tonylin.practice.jackson.resolver;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

public class AnimalResolver extends TypeIdResolverBase {

	private JavaType superType;
	 
    @Override
    public void init(JavaType baseType) {
        superType = baseType;
        System.out.println("superType: " + superType);
    }
	
	@Override
	public String idFromValue(Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String idFromValueAndType(Object value, Class<?> suggestedType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Id getMechanism() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JavaType typeFromId(DatabindContext context, String id) throws IOException {
		Class<?> subType = null;
        switch (id) {
        case "cat":
            subType = Cat.class;
            break;
        default:
        	throw new RuntimeException("Can't find tye type: "+id);
        }
        System.out.println("subType: " + subType);
        return context.constructSpecializedType(superType, subType);
	}
}
