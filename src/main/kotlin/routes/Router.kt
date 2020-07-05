package routes

import com.fasterxml.jackson.databind.ObjectMapper
import io.undertow.Handlers
import io.undertow.server.RoutingHandler
import io.undertow.util.Headers

fun router(objectMapper: ObjectMapper): RoutingHandler {
    val router = Handlers.routing()

    ROUTE_CONFIG.entries.forEach { verb ->
        verb.value.entries.forEach { route ->
            router.add(verb.key, route.key, route.value(objectMapper))
        }

        router.setFallbackHandler { exchange ->
            exchange.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain;charset=utf-8")
            exchange.statusCode = 404
            exchange.responseSender.send("無")
        }
    }

    return router
}