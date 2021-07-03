package org.tonylin.practice.jackson.optional;

import static org.junit.Assert.*;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class OptionalOperationTest {
    
    private ObjectMapper sut = new ObjectMapper();
    
    private void givenGuavaAndJdk8Module() {
      sut.registerModule(new GuavaModule());
      sut.registerModule(new Jdk8Module());
    }
    
    private ParentObject givenThreeLevelObject() {
        ChildObject leefObject = new ChildObject();
        leefObject.setValue("leefTestValue");
        
        ChildObject childObject = new ChildObject();
        childObject.setValue("childTestValue");
        childObject.setGuavaOptionalChild(leefObject);
        childObject.setJdkOptionalChild(leefObject);
        
        ParentObject parentObject = new ParentObject();
        parentObject.setValue("testValue");
        parentObject.setGuavaOptionalChild(childObject);
        parentObject.setJdkOptionalChild(childObject);
        
        return parentObject;
    }
    
    @Test
    public void Should_LessThan1Ms_When_DeepCopyThreeLevelObject() throws JsonProcessingException, IllegalArgumentException {
        givenGuavaAndJdk8Module();        
        ParentObject parentObject = givenThreeLevelObject();
        
        sut.treeToValue(sut.valueToTree(parentObject), ParentObject.class);
        sut.readValue(sut.writeValueAsString(parentObject), ParentObject.class);
        
        final double max = 10000;
        long sumOfTreeToValue = 0;
        for( int i = 0 ; i < max ; i++ ) {
            long before = System.currentTimeMillis();
            sut.treeToValue(sut.valueToTree(parentObject), ParentObject.class);
            long after = System.currentTimeMillis();
            sumOfTreeToValue += (after-before);
        }
        
        long sumOfReadValue = 0;
        for( int i = 0 ; i < max ; i++ ) {
            long before = System.currentTimeMillis();
            sut.readValue(sut.writeValueAsString(parentObject), ParentObject.class);
            long after = System.currentTimeMillis();
            sumOfReadValue += (after-before);
        }
        
        System.out.println("sumOfTreeToValue: " + sumOfTreeToValue / max);
        System.out.println("sumOfReadValue: " + sumOfReadValue / max);
        assertTrue(sumOfTreeToValue / max < 1);
        assertTrue(sumOfReadValue / max < 1);
    }
    
    @Test
    public void Should_GetSameCopy_When_DeepCopyThreeLevelObject() throws JsonProcessingException, IllegalArgumentException {
        givenGuavaAndJdk8Module();        
        ParentObject parentObject = givenThreeLevelObject();
        
        ParentObject copy = sut.treeToValue(sut.valueToTree(parentObject), ParentObject.class);
        
        String result = sut.writeValueAsString(copy);
        assertEquals("{\"guavaOptionalChild\":{\"guavaOptionalChild\":{\"guavaOptionalChild\":null,\"jdkOptionalChild\":null,\"value\":\"leefTestValue\"},"
                + "\"jdkOptionalChild\":{\"guavaOptionalChild\":null,\"jdkOptionalChild\":null,\"value\":\"leefTestValue\"},\"value\":\"childTestValue\"},"
                + "\"jdkOptionalChild\":{\"guavaOptionalChild\":{\"guavaOptionalChild\":null,\"jdkOptionalChild\":null,\"value\":\"leefTestValue\"},"
                + "\"jdkOptionalChild\":{\"guavaOptionalChild\":null,\"jdkOptionalChild\":null,\"value\":\"leefTestValue\"},"
                + "\"value\":\"childTestValue\"},\"value\":\"testValue\"}", result);
    }

    @Test
    public void Should_GetJsonStringWithExpectValue_When_GivenPresentOptionalsWithCustomModule() throws JsonProcessingException {
        givenGuavaAndJdk8Module();        
        ParentObject parentObject = givenThreeLevelObject();

        String result = sut.writeValueAsString(parentObject);
        
        assertEquals("{\"guavaOptionalChild\":{\"guavaOptionalChild\":{\"guavaOptionalChild\":null,\"jdkOptionalChild\":null,\"value\":\"leefTestValue\"},"
                + "\"jdkOptionalChild\":{\"guavaOptionalChild\":null,\"jdkOptionalChild\":null,\"value\":\"leefTestValue\"},\"value\":\"childTestValue\"},"
                + "\"jdkOptionalChild\":{\"guavaOptionalChild\":{\"guavaOptionalChild\":null,\"jdkOptionalChild\":null,\"value\":\"leefTestValue\"},"
                + "\"jdkOptionalChild\":{\"guavaOptionalChild\":null,\"jdkOptionalChild\":null,\"value\":\"leefTestValue\"},"
                + "\"value\":\"childTestValue\"},\"value\":\"testValue\"}", result);
    }
    
    @Test
    public void Should_GetJsonStringWithNullValue_When_GivenEmptyOptionalsWithCustomModule() throws JsonProcessingException {
        givenGuavaAndJdk8Module();
        ParentObject parentObject = new ParentObject();
        parentObject.setValue("testValue");
        
        String result = sut.writeValueAsString(parentObject);
        
        assertEquals("{\"guavaOptionalChild\":null,\"jdkOptionalChild\":null,\"value\":\"testValue\"}", result);
    }
    
    @Test
    public void Should_GetJsonStringWithPresentValue_When_GivenEmptyOptionalsWithoutCustomModule() throws JsonProcessingException {
        ParentObject parentObject = new ParentObject();
        parentObject.setValue("testValue");
        
        String result = sut.writeValueAsString(parentObject);
        
        assertEquals("{\"guavaOptionalChild\":{\"present\":false},\"jdkOptionalChild\":{\"present\":false},\"value\":{\"present\":true}}", result);
    }

}
