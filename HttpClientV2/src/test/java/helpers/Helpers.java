package helpers;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import pojo.Users;

import java.io.IOException;

public class Helpers {


    //Marshalling : Object to json
    public static String objToJson(Object obj) throws IOException {
        String toJsonValue = "";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        toJsonValue = objectMapper.writeValueAsString(obj);
        return toJsonValue;
    }

    //Unmarshalling : json to obj
    public static Object jsonToObj1(String json, String className) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Class ResponseClass = Class.forName(className);
            Object responseObject = ResponseClass.newInstance();
            responseObject = objectMapper.readValue(json, responseObject.getClass());
            System.out.println("cgvhbnm" + responseObject);
            return responseObject;
        } catch (Exception e) {
            System.out.println("JSON to POJO Mapping Error:   " + e.getMessage());
            return null;
        }
    }

    public static Users jsonToObj(String json, String className)  {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convert JSON string to Object
            Class ResponseClass = Class.forName(className);
            Object responseObject = ResponseClass.newInstance();
            responseObject = objectMapper.readValue(json, responseObject.getClass());
            System.out.println("cgvhbnm" + responseObject);
            return (Users) responseObject;

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Users generateUsers() throws IOException {
        Users user = Users.builder().name("Tom").job("Engineer").build();
        return user;

    }
}
