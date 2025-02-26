package banquemisr.challenge05.movieApp.common

sealed class ResourceData<T>(
    val data: T? = null,
    val message: String? = null
) {
    class success<T>(data: T) : ResourceData<T>(data)
    class loading<T>(data: T? = null) : ResourceData<T>(data)
    class error<T>(message: String, data: T? = null) : ResourceData<T>(data, message)
}