import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object Program {
    val Jsonx = Json { ignoreUnknownKeys = true; isLenient = true }

    val logger: Logger = LogManager.getLogger(this::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        logger.error("Hello from Log4j 2")

        try {
            val json = "{\"example.example@example.com\":{\"details\":{\"full_name\":\"Example Example\",\"login\":\"example\",\"sid\":\"00000000-0000-0000-0000-000000000000\",\"sub\":\"example.example@example.com\"},\"token\":{\"error\":\"invalid_grant\",\"error_description\":\"The provided authorization grant (e.g., authorization code, resource owner credentials) or refresh token is invalid, expired, revoked, does not match the redirection URI used in the authorization request, or was issued to another client\",\"status_code\":400,\"expires_at\":1597074627}}}"
            Jsonx.parseToJsonElement(json).jsonObject.map {
                it.key to Jsonx.decodeFromJsonElement(AccountInfo.serializer(), it.value)
            }.toMap().toMutableMap()
        } catch (e: Exception) {
            logger.error(e)
        }
    }
}
@Serializable
data class AccountInfo(
    val details: Details,
    val token: OAuth2Token
) {
    @Serializable
    data class Details(
        @SerialName("full_name") val fullName: String? = null,
        @SerialName("login") val login: String? = null,
        val sid: String? = null,
        val sub: String
    )

    val email get() = details.sub
    val fullName get() = details.fullName

    fun isValidAccount() = email.endsWith("@example.com")

    val authorizationHeader get() =  "Bearer " + token.idToken

    fun isExpiredOrAlmostExpired() : Boolean {
        return false
    }

    val expiresIn get() = token.expiresIn
}

@Serializable
data class OAuth2Token(
    @SerialName("access_token") val accessToken: String? = null,
    @SerialName("id_token") val idToken: String? = null,
    @SerialName("refresh_token") val refreshToken: String? = null,
    val scope: String? = null,
    @SerialName("token_type") val tokenType: String? = null,
    val error: String? = null,
    @SerialName("error_description") val errorDescription: String? = null,
    @SerialName("status_code") val statusCode: Long? = null,
    /**
     * Token validity period in seconds.
     */
    @SerialName("expires_in") val expiresIn: Long? = null,
    /**
     * Token expiration time in seconds since epoch.
     */
    @SerialName("expires_at") var expiresAt: Long? = null
)
