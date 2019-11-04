import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.vertx.core.json.JsonObject;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;


public class ValidateAPITest {

    public static JsonObject user;
    public static String baseUrl;
    public static final int PORT = 8085;
    public static RequestSpecification requestSpec;

    @Before
    public void initializer(){
        user = new JsonObject();
        baseUrl = "http://localhost";
        requestSpec = new RequestSpecBuilder().setPort(PORT).build();
    }

    @Test
    public void testingApiForBaseUrl() {

        given().spec(requestSpec).when().post(baseUrl).then().assertThat().statusCode(200).body("status", equalTo("welcome"));
    }

    @Test
    public void testingApiForValidateWithEmptyBody(){
        given().spec(requestSpec).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("body should not be empty"));
    }

    @Test
    public void testingApiForValidateWithEmptyBodyJson(){

        given().spec(requestSpec).body(user.toString()).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("name field is not available"));
    }

    @Test
    public void testingApiForValidateWithOnlyOneField(){

        given().spec(requestSpec).body(user.put("name", "sam").toString()).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("gender field is not available"));
    }

    @Test
    public void testingApiForValidateWithTwoField(){

        given().spec(requestSpec).body(user.put("gender", "male").put("name","sam").toString()).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("aadhar field is not available"));
    }

    @Test
    public void testingApiForValidateWithThreeField(){

        given().spec(requestSpec).body(user.put("gender", "male").put("name", "sam").put("aadhar", "123412341234").toString()).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("registration Successful"));
    }

    @Test
    public void testingEmptyNameField(){
        given().spec(requestSpec).body(user.put("gender", "male").put("name", "").put("aadhar", "123412341234").toString()).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("name field cannot be empty"));
    }

    @Test
    public void testingEmptyGenderField(){
        given().spec(requestSpec).body(user.put("gender", "").put("name", "sam").put("aadhar", "123412341234").toString()).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("gender field cannot be empty"));
    }

    @Test
    public void testingEmptyAadharField(){
        given().spec(requestSpec).body(user.put("gender", "male").put("name", "sam").put("aadhar", "").toString()).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("aadhar number must be a unique 12 digit number"));
    }

    @Test
    public void testingEmptyFields(){
        given().spec(requestSpec).body(user.put("gender", "").put("name", "").put("aadhar", "").toString()).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("gender field cannot be empty"));
    }

    @Test
    public void testingValidFields(){
        given().spec(requestSpec).body(user.put("gender", "male").put("name", "sam").put("aadhar", "123123123123").toString()).when().post(baseUrl+"/validate").then().assertThat().statusCode(200).body("status", equalTo("registration Successful"));
    }
}
