package es.unizar.webeng.lab2

import java.time.LocalDateTime
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

// Define Data Transfer Object for time representation
data class TimeDTO(val time: LocalDateTime)

// Define an interface for providing the current time
interface TimeProvider {
    fun now(): LocalDateTime
}

// Implement the TimeProvider interface to return the current system time
@Service
class TimeService : TimeProvider {
    override fun now(): LocalDateTime = LocalDateTime.now()
}

// Extension function to convert LocalDateTime to TimeDTO
fun LocalDateTime.toDTO(): TimeDTO = TimeDTO(time = this)

// Define a REST controller to handle local time requests
@RestController
class TimeController(private val service: TimeProvider) {
    @GetMapping("/time")
    fun getTime(): TimeDTO = service.now().toDTO()
}