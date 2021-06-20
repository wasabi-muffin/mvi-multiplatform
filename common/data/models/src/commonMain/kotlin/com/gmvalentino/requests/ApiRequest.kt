// package com.gmvalentino.requests
//
// import co.touchlab.stately.freeze
// import io.ktor.client.statement.HttpResponse
// import io.ktor.http.HttpStatusCode
//
// /** Defines the interaction between the library and the host app for making API requests */
// public interface Network {
//
//     /**
//      * Makes an API request
//      *
//      * @param host The host, to which to make the request
//      * @param request The request details
//      * @param completion: The closure to call when the request is complete with the response
//      */
//     public fun makeRequest(
//         host: APIHost,
//         request: Request,
//         completion: (Request.DataResponse) -> Unit
//     ): NetworkDisposable
// }
//
// /** Helper for making coroutine-driven network calls */
// internal suspend fun Network.makeRequest(host: APIHost, request: Request): Request.DataResponse {
//     // Use deferred, so that we can capture coroutine cancellation
//     // and use that to cancel our network request, if needed
//     return threadSafeSuspendCallback { completion ->
//         val requestCompletion: (Request.DataResponse) -> Unit = {
//             completion(Result.success(it))
//         }
//         val cancellable = makeRequest(host, request, requestCompletion.freeze())
//         return@threadSafeSuspendCallback { cancellable.cancel() }
//     }
// }
//
//
// /**
//  * Details of the network request
//  */
// public data class Request(
//
//     /**
//      * The request's path, starting with "/"
//      */
//     public val path: String,
//
//     /**
//      * A [Map] of the request's query parameters
//      */
//     public val queryParameters: Map<String, String>?,
//
//     /**
//      * The request's body, if any
//      */
//     public val body: String?,
//
//     /**
//      * The request's method
//      */
//     public val method: Method
// ) {
//     init { freeze() }
//
//     public enum class Method { GET, POST, PUT, DELETE, PATCH }
//
//     /** The kinds of responses that can be returned by the host app */
//     public sealed class DataResponse {
//
//         /**
//          * An HTTP response of in-memory data
//          *
//          * @param data The wrapped data
//          * @param statusCode The status code of the network request. Expected to be in the success range.
//          */
//         public class Success(public val data: HttpResponse, public val statusCode: HttpStatusCode) :
//             DataResponse() {
//             init { freeze() }
//         }
//
//         /**
//          * An error during the request
//          */
//         public class Failure(public val error: Throwable) : DataResponse() {
//             init { freeze() }
//         }
//     }
// }
