import com.fasterxml.jackson.databind.ObjectMapper
import io.undertow.Undertow

import routes.router

object HelloWorldServer {
    private val objectMapper = ObjectMapper()

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
