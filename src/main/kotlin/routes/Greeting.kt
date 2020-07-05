package routes

import com.fasterxml.jackson.databind.ObjectMapper
import io.undertow.server.HttpServerExchange
import io.undertow.util.Headers

data class Greeting(
    val message: String
)

val getMockData = { mapper: ObjectMapper ->
    { exchange: HttpServerExchange ->
        exchange.responseHeaders.put(Headers.CONTENT_TYPE, "application/json;charset=utf-8")
        exchange.responseSender.send(mapper.writeValueAsString(Greeting(message = "你好，世界！")))
    }
}
