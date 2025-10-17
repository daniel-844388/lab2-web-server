package es.unizar.webeng.lab2

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.HttpMethod
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpEntity

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    // Test that a request to a non-existing endpoint returns the custom error HTML page
    @Test
    fun `should return the error html custom error page`() {

        // Set the Accept header to text/html
        val headers = HttpHeaders()
        headers.accept = listOf(MediaType.TEXT_HTML)

        // Create the HttpEntity with the headers
        val entity = HttpEntity<String>(headers)

        // Make the GET request to a non-existing endpoint
        val response = restTemplate.exchange(
            "http://localhost:$port/not-found",
            HttpMethod.GET,
            entity,
            String::class.java
        )

        // Assert the response status, content type, and body
        assertThat(response.statusCode).isEqualTo(HttpStatus.NOT_FOUND)
        assertThat(response.headers.contentType!!.isCompatibleWith(MediaType.TEXT_HTML)).isTrue
        assertThat(response.body).contains("<title>Error Page</title>")
            .contains("<h1>Welcome to the error page</h1>")
    }
}