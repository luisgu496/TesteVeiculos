package com.example.backend

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class VehicleApiTest {
  @Test
  fun testHealth() = testApplication {
    application { module() }
    val response = client.get("/")
    assertEquals(HttpStatusCode.OK, response.status)
  }

  @Test
  fun testCreateAndList() = testApplication {
    application { module() }
    val postRes = client.post("/veiculos") {
      contentType(ContentType.Application.Json)
      setBody("{\"veiculo\":\"Civic\",\"marca\":\"Honda\",\"ano\":2018,\"descricao\":\"Sedan\",\"vendido\":false}")
    }
    assertEquals(HttpStatusCode.Created, postRes.status)
    val listRes = client.get("/veiculos")
    assertEquals(HttpStatusCode.OK, listRes.status)
  }
}
