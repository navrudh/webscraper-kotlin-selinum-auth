class UserInput {
    companion object {
        @JvmStatic
        fun userInputYN(question: String): Boolean {
            while (true) {
                println(question)
                val response = readLine().toString()
                if (response.equals("y", false) || response.equals("yes", false)) return true
                if (response.equals("n", false) || response.equals("no", false)) return false
            }
        }
    }
}