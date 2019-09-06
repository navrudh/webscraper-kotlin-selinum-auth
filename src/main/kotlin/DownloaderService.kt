import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.security.UserAndPassword
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.util.*


class DownloaderService {
    fun handleAuthRequest(driver: ChromeDriver, username: String, password: String) {
        val wait = WebDriverWait(driver, 10)
        val alert = wait.until(ExpectedConditions.alertIsPresent())
        alert.authenticateUsing(UserAndPassword(username, password))
        alert.accept()
    }

    fun createChromeDriverWithDownloadFolder(folder: String): ChromeDriver {
        val chromePrefs = HashMap<String, Any>()
        chromePrefs["profile.default_content_settings.popups"] = 0
        chromePrefs["download.default_directory"] = folder
        chromePrefs["profile.content_settings.exceptions.automatic_downloads.*.setting"] = 1
        chromePrefs["download.prompt_for_download"] = false
        chromePrefs["download.directory_upgrade"] = true
        chromePrefs["plugins.always_open_pdf_externally"] = true
        chromePrefs["pdfjs.disabled"] = true

        val options = ChromeOptions()
        options.setExperimentalOption("prefs", chromePrefs)
        val cap = DesiredCapabilities.chrome()
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
        cap.setCapability(ChromeOptions.CAPABILITY, options)
        return ChromeDriver(cap)
    }

    fun batchDownload(searchResults: Map<String, List<String>>, path: String, username: String, password: String) {
        searchResults.forEach { (term, fileUrls) ->
            run {
                val folder = PathValidationService.getSafePath(path, term)
                val driver = createChromeDriverWithDownloadFolder(folder)
                driver.get(fileUrls[0])
                handleAuthRequest(driver, username, password)
                fileUrls.forEach { driver.get(it) }
            }
        }
    }
}