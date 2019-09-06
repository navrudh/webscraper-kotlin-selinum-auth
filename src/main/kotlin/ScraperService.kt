import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import java.net.URL

class ScraperService {
    private val driver = ChromeDriver()

    fun scrape(url: URL, searchStrings: List<String>): Map<String, List<String>> {
        driver.get(url.toString())

        val hrefs = driver.findElements(By.tagName("a"));

        val searchResults = searchStrings.map { searchTerm ->
            searchTerm to hrefs
                .map { href -> href.getAttribute("href") }
                .filter { hrefText ->
                    hrefText.contains(searchTerm)
                }
        }.toMap()

        return searchResults
    }
}