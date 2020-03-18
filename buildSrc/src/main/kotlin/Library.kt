@file:Suppress("InvalidPackageDeclaration")

object Library {
    private const val VERSION_MAJOR = 0
    private const val VERSION_MINOR = 2
    private const val VERSION_PATCH = 0

    const val VERSION_NAME = "${VERSION_MAJOR}.${VERSION_MINOR}.${VERSION_PATCH}"
    const val VERSION_CODE = VERSION_MAJOR * 100_000 + VERSION_MINOR * 100 + VERSION_PATCH

    const val GROUP_ID = "com.lordcodes.REPLACE_ME"
    const val ARTIFACT_ID = "REPLACE_ME"

    const val DESCRIPTION = "REPLACE_ME"
    const val LICENSE = "Apache-2.0"
    const val WEBSITE = "https://github.com/lordcodes/REPLACE_ME"
    const val ISSUE_TRACKER = "https://github.com/lordcodes/REPLACE_ME/issues"
    const val SOURCE_CONTROL = "https://github.com/lordcodes/REPLACE_ME.git"

    const val BINTRAY_USER = "lordcodes"
    const val BINTRAY_REPOSITORY = "maven"

    const val DEVELOPER_USER = "lordcodes"
    const val DEVELOPER_NAME = "Andrew Lord"
}
