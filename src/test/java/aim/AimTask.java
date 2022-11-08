package aim;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AimTask {

    @Test
    public void taskPost(){

        RestAssured.baseURI = "https://1ryu4whyek.execute-api.us-west-2.amazonaws.com";
        RestAssured.basePath = "/dev/skus";

      Response response = RestAssured.given().accept(ContentType.JSON).contentType(ContentType.JSON).
              body("{\n" +
                "    \"sku\": \"berliner\",\n" +
                "    \"description\": \"Jelly donut\",\n" +
                "    \"price\": \"2.99\"\n" +
                "}").when().post()
                .then().statusCode(200)//it should be HTTP status code 201 which is created
                .extract().response();

        Map<String, Object> parsedResponse = response.as(new TypeRef<Map<String, Object>>() {
        });

        System.out.println(parsedResponse.get("sku"));


    }

    @Test
    public void taskGet(){
        RestAssured.baseURI = "https://1ryu4whyek.execute-api.us-west-2.amazonaws.com";
        RestAssured.basePath = "/dev/skus";

        Response response = RestAssured.given().accept(ContentType.JSON).
                when().get().then().statusCode(200).extract().response();

        List<Map<String, Object>> list = response.as(new TypeRef<List<Map<String, Object>>>() {
        });

        for (int i = 0; i < list.size(); i++) {
        Map<String, Object> map = list.get(i);

        if (map.get("price").equals("14.77")){
            System.out.println(map.get("description"));
            Assert.assertEquals("Test singular GET", map.get("description"));
            break;
        }


        }
    }

    @Test
    public void taskDelete(){

        RestAssured.baseURI = "https://1ryu4whyek.execute-api.us-west-2.amazonaws.com";
        RestAssured.basePath = "/dev/skus";

        Response response = RestAssured.given().accept(ContentType.JSON).
                when().delete().then().statusCode(200).extract().response();






    }

}
