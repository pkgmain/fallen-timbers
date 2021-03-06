package com.barrongineer.web

import com.barrongineer.service.FlickrService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class MainController(val flickrService: FlickrService,
                     val objectMapper: ObjectMapper) {

    @GetMapping
    fun get(model: Model): String {
        val productGroups = flickrService.getProductGroups()

        val charmSpring = Event(
                url = "http://www.charmatthefarm.com/",
                image = "/img/charm_at_the_farm.jpg",
                dates = listOf(
                        "June 11-13, 2021"
                ),
                venue = "Charm at the Farm - June Market",
                location = "Lebanon, OH"
        )

        model.addAttribute("events", listOf(
                charmSpring))
        model.addAttribute("productGroups", productGroups)
        model.addAttribute("productGroupsJson", objectMapper.writeValueAsString(productGroups))

        return "index"
    }
}

data class Event(
        val url: String = "",
        val image: String = "",
        val dates: List<String> = emptyList(),
        val venue: String = "",
        val location: String = ""
)