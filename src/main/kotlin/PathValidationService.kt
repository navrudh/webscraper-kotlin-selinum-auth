import java.io.File

class PathValidationService {

    companion object {
        @JvmStatic
        fun getSafePath(pathLocation: String, other: String): String {
            val path = File(pathLocation)
            val exists = path.exists()

            if (!exists) {
                val createIt =
                    UserInput.userInputYN("The specified path '$path' does not exist. Should it be created? (Yes/No)?")
                if (createIt) path.mkdirs()
                else throw RuntimeException("User did not want missing path '$path' to be created!")
            }

            val safePath = path.toPath().resolve(other)
            safePath.toFile().mkdirs()
            println("Creating path '$safePath'")
            return safePath.toString()
        }
    }
}