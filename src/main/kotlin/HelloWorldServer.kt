import io.undertow.Undertow
import io.undertow.util.Headers

object HelloWorldServer {
    @JvmStatic
    fun main(args: Array<String>) {
        val listener = Undertow.ListenerBuilder()
                .setType(Undertow.ListenerType.HTTP)
                .setHost("localhost")
                .setPort(8080)
        val server = Undertow.builder()
                .addListener(listener)
                .setHandler { exchange ->
                    exchange.responseHeaders.put(Headers.CONTENT_TYPE, "text/plain")
                    exchange.responseSender.send("Hello World")
                }
                .build()
        server.start()
        println("Started server at http://127.0.0.1:8080/  Hit ^C to stop")
    }
}
