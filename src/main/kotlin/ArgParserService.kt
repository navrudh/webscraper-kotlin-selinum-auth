import com.gargoylesoftware.htmlunit.util.UrlUtils
import com.xenomachina.argparser.ArgParser

class ArgParserService(parser: ArgParser) {
    val verbose by parser.flagging(
        "-v", "--verbose",
        help = "enable verbose mode"
    )

    val username by parser.storing(
        "-U", "--user",
        help = "Username"
    )

    val password by parser.storing(
        "-P", "--password",
        help = "Password"
    )

    val searchStrings by parser.adding(
        "-S",
        "--search-string",
        help = "search string"
    )

    val url by parser.positional(
        "URL",
        help = "source url"
    ) { UrlUtils.toUrlSafe(this) }

    val destination by parser.positional(
        "DEST",
        help = "destination folder"
    )
}