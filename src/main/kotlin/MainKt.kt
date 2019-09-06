import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody

class MainKt {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) = mainBody {
            ArgParser(args).parseInto(::ArgParserService).run {
                println("Hello, ${username}!")
                println("Downloading resources from ${url} to ${destination}.")

                val scraper = ScraperService()
                val searchResults = scraper.scrape(url, searchStrings)

                val downloader = DownloaderService()
                downloader.batchDownload(searchResults, destination, username, password)
            }
        }
    }
}
