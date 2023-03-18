package dev.shushant.sun_and_storm_kmp.designsystem

import androidx.compose.ui.graphics.vector.ImageVector
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcBookmark
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcBookmarkBorder
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcBookmarks
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcBookmarksBorder
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcMenuBook
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcMenuBookBorder
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcPlaceholderDefault
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcUpcoming
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.IcUpcomingBorder
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.Location
import dev.shushant.sun_and_storm_kmp.designsystem.myiconpack.Hover
import kotlin.collections.List as ____KtList

internal object MyIconPack

private var __AllIcons: ____KtList<ImageVector>? = null

internal val MyIconPack.AllIcons: ____KtList<ImageVector>
    get() {
        if (__AllIcons != null) {
            return __AllIcons!!
        }
        __AllIcons = listOf(
            IcBookmark,
            IcUpcomingBorder,
            IcBookmarks,
            IcBookmarkBorder,
            IcBookmarksBorder,
            IcMenuBook,
            IcPlaceholderDefault,
            IcMenuBookBorder,
            IcUpcoming,
            Location,
            Hover
        )
        return __AllIcons!!
    }
