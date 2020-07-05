import com.fasterxml.jackson.databind.ObjectMapper
import io.undertow.Handlers
import io.undertow.Undertow
import io.undertow.UndertowOptions
import io.undertow.server.RoutingHandler
import io.undertow.util.Headers
import routes.ROUTE_CONFIG

object HelloWorldServer {
    private val objectMapper = ObjectMapper()

    private fun router(objectMapper: ObjectMapper): RoutingHandler {
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

    @JvmStatic
    fun main(args: Array<String>) {
        val listener = Undertow.ListenerBuilder()
            .setType(Undertow.ListenerType.HTTP)
            .setHost("localhost")
            .setPort(8080)
        val server = Undertow.builder()
            .addListener(listener)
            .setHandler(router(objectMapper = objectMapper))
            .build()
        server.start()
        println("Started server at http://localhost:8080  Hit ^C to stop")
    }
}
