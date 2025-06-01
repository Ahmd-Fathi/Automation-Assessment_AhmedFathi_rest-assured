package apis;

import io.restassured.response.Response;
import pojo.Post;
import static io.restassured.RestAssured.given;

public class PostApi {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public Response getAllPosts() {
        return given()
                .baseUri(BASE_URL)
                .when()
                .get("/posts")
                .then()
                .extract().response();
    }

    public Response getPostById(int id) {
        return given()
                .baseUri(BASE_URL)
                .pathParam("id", id)
                .when()
                .get("/posts/{id}")
                .then()
                .extract().response();
    }

    public Response createPost(Post post) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-Type", "application/json")
                .body(post)
                .when()
                .post("/posts")
                .then()
                .extract().response();
    }
}