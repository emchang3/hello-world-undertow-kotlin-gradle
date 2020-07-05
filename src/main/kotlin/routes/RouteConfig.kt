package routes

import com.fasterxml.jackson.databind.ObjectMapper
import io.undertow.server.HttpServerExchange
import io.undertow.util.Methods

val GET_ROUTES = mapOf(
    "/api/greeting" to getMockData
)

val POST_ROUTES: Map<String, (ObjectMapper) -> (HttpServerExchange) -> Unit> = emptyMap()

val ROUTE_CONFIG = mapOf(
    Methods.GET to GET_ROUTES,
    Methods.POST to POST_ROUTES
)