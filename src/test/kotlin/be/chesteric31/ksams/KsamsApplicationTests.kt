package be.chesteric31.ksams

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [(KsamsApplication::class)])
@ActiveProfiles("test")
@SpringBootTest
class KsamsApplicationTests {

    lateinit var mockMvc : MockMvc

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    @Before
    fun setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

	@Test
	fun testCategoriesApi() {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v2/categories/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk)
	}

    @Test
    fun testCategoriesApiPost4xx() {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v2/categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError)
    }

}
