package es.unizar.webeng.lab2

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDateTime
import org.mockito.BDDMockito.given

// Test class for TimeController using MockMvc
@WebMvcTest(TimeController::class)
class TimeControllerMVCTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var timeProvider: TimeProvider

    @Test
    fun `should return the current server time as JSON`() {
        // Mock the timeProvider to return a fixed date-time
        given(timeProvider.now()).willReturn(LocalDateTime.of(2025, 10, 17, 12, 0))

        // Perform the GET request and verify the response
        mockMvc.perform(get("/time"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.time").value("2025-10-17T12:00:00"))
    }
}