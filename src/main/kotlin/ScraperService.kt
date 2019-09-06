import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import java.net.URL

class ScraperService {
    private val driver = ChromeDriver()

    fun scrape(url: String, searchStrings: List<String>): Map<String, List<String>> {
        driver.get(url)

        val hrefs = driver.findElements(By.tagName("a")).mapNotNull { href -> href.getAttribute("href") }

        val searchResults = searchStrings.map { searchTerm ->
            searchTerm to hrefs
                .filter { hrefText ->
                    hrefText.contains(searchTerm)
                }
        }.toMap()

        return searchResults
    }
}