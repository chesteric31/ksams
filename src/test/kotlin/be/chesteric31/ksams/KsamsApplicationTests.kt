package be.chesteric31.ksams

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension::class)
class KsamsApplicationTests {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    @Test
    fun testCategoriesApi() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v2/categories/")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
    }

    @Test
    fun testCategoriesApiPost4xx() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v2/categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().is4xxClientError)
    }

    companion object {
        @JvmStatic
        fun setup(ksamsApplicationTests: KsamsApplicationTests): Unit {
            ksamsApplicationTests.mockMvc =
                MockMvcBuilders.webAppContextSetup(ksamsApplicationTests.webApplicationContext).build()
        }
    }

}
