package br.ufpe.cin.hcs3.documentmanager.test_api;

import static io.restassured.RestAssured.*;

import br.ufpe.cin.hcs3.documentmanager.builders.DocumentBuilder;
import br.ufpe.cin.hcs3.documentmanager.builders.DocumentRequestBuilder;
import br.ufpe.cin.hcs3.documentmanager.v1.service.DocumentService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DocumentApiTest {

    public static final String BASE_PATH = "/v1/diff/";

    @Autowired
    private DocumentService service;

    @LocalServerPort
    private Integer port;

    private Long persistedValidDocumentId;
    private Long persistedInvalidDocumentId;

    @BeforeAll
    public void setup(){
        var validDocument = DocumentBuilder.buildDocumentSameSizeAndContentDiffId();
        validDocument = this.service.saveDocument(validDocument);
        this.persistedValidDocumentId = validDocument.getId();

        var invalidDocument = DocumentBuilder.buildDocumentWithNullLeftContent();
        invalidDocument.setId(6L);
        invalidDocument = this.service.saveDocument(invalidDocument);
        this.persistedInvalidDocumentId = invalidDocument.getId();
    }

    @Test
    public void shouldReturn201_WhenSaveValidLeftEntity() {
        given()
                .basePath(BASE_PATH + 1L + "/left")
                .port(port)
                .contentType(ContentType.JSON)
                .body(DocumentRequestBuilder.validDocumentRequest())
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturn201_WhenSaveValidRightEntity() {
        given()
                .basePath(BASE_PATH + 1L + "/right")
                .port(port)
                .contentType(ContentType.JSON)
                .body(DocumentRequestBuilder.validDocumentRequest())
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void shouldReturn400_WhenSaveInvalidLeftEntity() {
        given()
                .basePath(BASE_PATH + 1L + "/left")
                .port(port)
                .contentType(ContentType.JSON)
                .body(DocumentRequestBuilder.invalidDocumentRequest())
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturn400_WhenSaveInvalidRightEntity() {
        given()
                .basePath(BASE_PATH + 1L + "/right")
                .port(port)
                .contentType(ContentType.JSON)
                .body(DocumentRequestBuilder.invalidDocumentRequest())
        .when()
                .post()
        .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldReturn404_WhenEvaluateNotFoundDocumentEntity(){
        given()
                .basePath(BASE_PATH + 5L)
                .port(port)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void shouldReturn200_WhenEvaluateValidPersistedDocumentId(){
        given()
                .basePath(BASE_PATH + this.persistedValidDocumentId)
                .port(port)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void shouldReturn412_WhenEvaluateInvalidPersistedDocumentId(){
        given()
                .basePath(BASE_PATH + this.persistedInvalidDocumentId)
                .port(port)
        .when()
                .get()
        .then()
                .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value());
    }

}
