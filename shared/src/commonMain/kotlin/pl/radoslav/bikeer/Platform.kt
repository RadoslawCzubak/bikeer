package pl.radoslav.bikeer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform