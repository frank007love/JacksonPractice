package org.tonylin.practice.jackson.optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class OptionalPerformanceTest {
    private ObjectMapper jackson = new ObjectMapper();
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(GuavaOptionalTypeAdapter.FACTORY)
            .disableHtmlEscaping().create();
    
    private void givenGuavaAndJdk8Module() {
      jackson.registerModule(new GuavaModule());
      jackson.registerModule(new Jdk8Module());
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
    
    @Before
    public void setup() {
        givenGuavaAndJdk8Module();
    }
    
    @Test
    public void Should_GetSameCopy_When_DeepCopyThreeLevelObjectWithGson() throws JsonProcessingException, IllegalArgumentException {        
        ParentObject parentObject = givenThreeLevelObject();
        
        ParentObject copy = gson.fromJson(gson.toJson(parentObject), ParentObject.class);
        
        String result = gson.toJson(copy);
        assertEquals("{\"guavaOptionalChild\":{\"guavaOptionalChild\":{\"jdkOptionalChild\":{},\"value\":\"leefTestValue\"},"
                + "\"jdkOptionalChild\":{\"value\":{\"jdkOptionalChild\":{},\"value\":\"leefTestValue\"}},\"value\":\"childTestValue\"},"
                + "\"jdkOptionalChild\":{\"value\":{\"guavaOptionalChild\":{\"jdkOptionalChild\":{},\"value\":\"leefTestValue\"},"
                + "\"jdkOptionalChild\":{\"value\":{\"jdkOptionalChild\":{},\"value\":\"leefTestValue\"}},\"value\":\"childTestValue\"}},"
                + "\"value\":\"testValue\"}", result);
    }
    
    @Test
    public void Should_LessThan1Ms_When_DeepCopyThreeLevelObjectWithGson() {  
        ParentObject parentObject = givenThreeLevelObject();
        
        gson.fromJson(gson.toJson(parentObject), ParentObject.class);
        
        final double max = 10000;
        long sumOfGsonCopy = 0;
        for( int i = 0 ; i < max ; i++ ) {
            long before = System.currentTimeMillis();
            gson.fromJson(gson.toJson(parentObject), ParentObject.class);
            long after = System.currentTimeMillis();
            sumOfGsonCopy += (after-before);
        }
        
        System.out.println("AvgOfGsonCopy: " + sumOfGsonCopy / max);
        assertTrue(sumOfGsonCopy / max < 1);
    }
    
    
    @Test
    public void Should_LessThan1Ms_When_DeepCopyThreeLevelObjectWithJackson() throws JsonProcessingException, IllegalArgumentException {  
        ParentObject parentObject = givenThreeLevelObject();
        
        jackson.treeToValue(jackson.valueToTree(parentObject), ParentObject.class);
        jackson.readValue(jackson.writeValueAsString(parentObject), ParentObject.class);
        
        final double max = 10000;
        long sumOfTreeToValue = 0;
        for( int i = 0 ; i < max ; i++ ) {
            long before = System.currentTimeMillis();
            jackson.treeToValue(jackson.valueToTree(parentObject), ParentObject.class);
            long after = System.currentTimeMillis();
            sumOfTreeToValue += (after-before);
        }
        
        long sumOfReadValue = 0;
        for( int i = 0 ; i < max ; i++ ) {
            long before = System.currentTimeMillis();
            jackson.readValue(jackson.writeValueAsString(parentObject), ParentObject.class);
            long after = System.currentTimeMillis();
            sumOfReadValue += (after-before);
        }
        
        System.out.println("AvgOfTreeToValue: " + sumOfTreeToValue / max);
        System.out.println("AvgOfReadValue: " + sumOfReadValue / max);
        assertTrue(sumOfTreeToValue / max < 1);
        assertTrue(sumOfReadValue / max < 1);
    }
}
