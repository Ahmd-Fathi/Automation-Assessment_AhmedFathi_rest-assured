package tests;

import apis.PostApi;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import pojo.Post;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.util.List;

@Epic("API Testing")
@Feature("JSONPlaceholder Post API Tests")
public class PostApiTests {

    private PostApi postApi;

    @BeforeClass
    public void setUp() {
        postApi = new PostApi();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test(priority = 1)
    @Story("Create Post")
    @Description("Verify that a new post can be created with valid data")
    @Severity(SeverityLevel.CRITICAL)
    public void createPost_shouldReturn201_andValidResponse() throws Exception {
        step("Load valid post data from JSON");
        Post post = JsonReader.readPostFromJson("testdata/postData.json", "validPost");

        step("Send POST request to create a post");
        Response response = postApi.createPost(post);

        step("Verify response status code and body");
        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        Post responsePost = response.as(Post.class);
        Assert.assertEquals(responsePost.getTitle(), post.getTitle(), "Title mismatch");
        Assert.assertEquals(responsePost.getBody(), post.getBody(), "Body mismatch");
        Assert.assertEquals(responsePost.getUserId(), post.getUserId(), "UserId mismatch");
        Assert.assertNotNull(responsePost.getId(), "ID should not be null");
        attachResponseBody(response.getBody().asString());
    }

    @Test(priority = 2)
    @Story("Create Invalid Post")
    @Description("Verify behavior when creating a post with invalid data")
    @Severity(SeverityLevel.NORMAL)
    public void createInvalidPost_shouldReturn201() throws Exception {
        step("Load invalid post data from JSON");
        Post post = JsonReader.readPostFromJson("testdata/postData.json", "invalidPost");

        step("Send POST request to create a post");
        Response response = postApi.createPost(post);

        step("Verify response status code and body");
        Assert.assertEquals(response.getStatusCode(), 201, "Status code should be 201");
        Post responsePost = response.as(Post.class);
        Assert.assertEquals(responsePost.getTitle(), post.getTitle(), "Title mismatch");
        Assert.assertEquals(responsePost.getBody(), post.getBody(), "Body mismatch");
        Assert.assertEquals(responsePost.getUserId(), post.getUserId(), "UserId mismatch");
        attachResponseBody(response.getBody().asString());
    }

    @Test(priority = 3)
    @Story("Retrieve All Posts")
    @Description("Verify that all posts can be retrieved and count is correct")
    @Severity(SeverityLevel.NORMAL)
    public void getAllPosts_shouldReturn200_andValidateCount() {
        step("Send GET request to retrieve all posts");
        Response response = postApi.getAllPosts();

        step("Verify response status code and count");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        List<Post> posts = response.jsonPath().getList(".", Post.class);
        Assert.assertEquals(posts.size(), 100, "Total posts count should be 100");
        attachResponseBody(response.getBody().asString());
    }

    @Test(priority = 4)
    @Story("Retrieve Specific Post")
    @Description("Verify that post with ID 20 can be retrieved")
    @Severity(SeverityLevel.NORMAL)
    public void getSpecificPostById_shouldReturn200_andCorrectPost() {
        step("Send GET request for post ID 20");
        Response response = postApi.getPostById(20);

        step("Verify response status code and post ID");
        Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
        Post responsePost = response.as(Post.class);
        Assert.assertEquals(responsePost.getId(), Integer.valueOf(20), "Post ID should be 20");
        Assert.assertNotNull(responsePost.getTitle(), "Title should not be null");
        Assert.assertNotNull(responsePost.getBody(), "Body should not be null");
        Assert.assertNotNull(responsePost.getUserId(), "UserId should not be null");
        attachResponseBody(response.getBody().asString());
    }

    @Test(priority = 5)
    @Story("Error Handling")
    @Description("Verify behavior for invalid post ID")
    @Severity(SeverityLevel.MINOR)
    public void getPostWithInvalidId_shouldReturn404_orEmptyObject() {
        step("Send GET request for invalid post ID 9999");
        Response response = postApi.getPostById(9999);

        step("Verify response status code");
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 404, "Status code should be 200 or 404");
        attachResponseBody(response.getBody().asString());
    }

    @Step("{description}")
    private void step(String description) {
        // Log step description for Allure report
    }

    @Attachment(value = "Response Body", type = "application/json")
    private String attachResponseBody(String body) {
        return body;
    }
}