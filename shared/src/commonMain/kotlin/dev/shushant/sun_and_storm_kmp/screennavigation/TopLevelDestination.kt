package dev.shushant.sun_and_storm_kmp.screennavigation

import dev.shushant.sun_and_storm_kmp.designsystem.ImageVectorIcon
import dev.shushant.sun_and_storm_kmp.designsystem.SunAndStormIcon


/**
 * Type for the top level destinations in the application. Each of these destinations
 * can contain one or more screens (based on the window size). Navigation from one screen to the
 * next within a single destination will be handled directly in composables.
 */
internal const val forYouR: String = "For you"
internal const val appName: String = "SunAndStorm"
internal const val bookmarksR: String = "Saved"
internal const val interestsR: String = "Interests"
internal const val settingsR: String = "Settings"

enum class TopLevelDestination(
    val selectedIcon: ImageVectorIcon,
    val unselectedIcon: ImageVectorIcon,
    val iconTextId: String,
    val titleTextId: String,
) {

    FOR_YOU(
        selectedIcon = ImageVectorIcon(SunAndStormIcon.Upcoming),
        unselectedIcon = ImageVectorIcon(SunAndStormIcon.UpcomingBorder),
        iconTextId = forYouR,
        titleTextId = appName,
    ),
    BOOKMARKS(
        selectedIcon = ImageVectorIcon(SunAndStormIcon.Bookmarks),
        unselectedIcon = ImageVectorIcon(SunAndStormIcon.BookmarksBorder),
        iconTextId = bookmarksR,
        titleTextId = bookmarksR,
    ),
    INTERESTS(
        selectedIcon = ImageVectorIcon(SunAndStormIcon.Grid3x3),
        unselectedIcon = ImageVectorIcon(SunAndStormIcon.Grid3x3),
        iconTextId = interestsR,
        titleTextId = interestsR,
    ), ;
}
