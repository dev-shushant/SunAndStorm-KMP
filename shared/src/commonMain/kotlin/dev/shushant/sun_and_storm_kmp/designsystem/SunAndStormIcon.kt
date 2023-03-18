package dev.shushant.sun_and_storm_kmp.designsystem

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ExpandLess
import androidx.compose.material.icons.rounded.Fullscreen
import androidx.compose.material.icons.rounded.Grid3x3
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShortText
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material.icons.rounded.ViewDay
import androidx.compose.material.icons.rounded.VolumeOff
import androidx.compose.material.icons.rounded.VolumeUp
import androidx.compose.ui.graphics.vector.ImageVector
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcBookmark
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcBookmarkBorder
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcBookmarks
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcBookmarksBorder
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcUpcoming
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcUpcomingBorder
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.Location

object SunAndStormIcon {
    val AccountCircle = Icons.Outlined.AccountCircle
    val Add = Icons.Rounded.Add
    val ArrowBack = Icons.Rounded.ArrowBack
    val ArrowDropDown = Icons.Default.ArrowDropDown
    val ArrowDropUp = Icons.Default.ArrowDropUp
    val Bookmark = MyIconPack.IcBookmark
    val BookmarkBorder = MyIconPack.IcBookmarkBorder
    val Bookmarks = MyIconPack.IcBookmarks
    val BookmarksBorder = MyIconPack.IcBookmarksBorder
    val Check = Icons.Rounded.Check
    val Close = Icons.Rounded.Close
    val ExpandLess = Icons.Rounded.ExpandLess
    val Fullscreen = Icons.Rounded.Fullscreen
    val Grid3x3 = Icons.Rounded.Grid3x3
    val MoreVert = Icons.Default.MoreVert
    val Person = Icons.Rounded.Person
    val PlayArrow = Icons.Rounded.PlayArrow
    val Search = Icons.Rounded.Search
    val Settings = Icons.Rounded.Settings
    val ShortText = Icons.Rounded.ShortText
    val Tag = Icons.Rounded.Tag
    val Upcoming = MyIconPack.IcUpcoming
    val UpcomingBorder = IcUpcomingBorder
    val LocationIcon = Location
    val ViewDay = Icons.Rounded.ViewDay
    val VolumeOff = Icons.Rounded.VolumeOff
    val VolumeUp = Icons.Rounded.VolumeUp
}

data class ImageVectorIcon(val imageVector: ImageVector)
