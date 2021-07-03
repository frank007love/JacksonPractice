package org.tonylin.practice.jackson.optional;

import com.google.common.base.Optional;

public class ParentObject {

    private Optional<ChildObject> guavaOptionalChild = Optional.absent();
    private java.util.Optional<ChildObject> jdkOptionalChild = java.util.Optional.empty();
    private String value;
    
    public java.util.Optional<String> getValue(){
        return java.util.Optional.ofNullable(value);
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public void setGuavaOptionalChild(ChildObject childObject) {
        guavaOptionalChild = Optional.fromNullable(childObject);
    }
    
    public Optional<ChildObject> getGuavaOptionalChild(){
       return guavaOptionalChild;
    }

 
    public void setJdkOptionalChild(ChildObject childObject) {
        jdkOptionalChild = java.util.Optional.ofNullable(childObject);
    }
    
    public java.util.Optional<ChildObject> getJdkOptionalChild(){
       return jdkOptionalChild;
    }
}
