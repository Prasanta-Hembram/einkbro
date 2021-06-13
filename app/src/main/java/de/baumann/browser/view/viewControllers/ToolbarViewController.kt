package de.baumann.browser.view.viewControllers

import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import de.baumann.browser.Ninja.R
import de.baumann.browser.preference.ConfigManager
import de.baumann.browser.view.toolbaricons.ToolbarAction

class ToolbarViewController(
    context: Context,
    private val toolbarContainer: ViewGroup,
) {
    private val iconBar: ViewGroup = toolbarContainer.findViewById(R.id.icon_bar)
    private val config: ConfigManager by lazy { ConfigManager(context) }

    fun isDisplayed(): Boolean = toolbarContainer.visibility == VISIBLE

    fun show() = toggleIconsOnOmnibox(true)

    fun hide() = toggleIconsOnOmnibox(false)

    fun reorderIcons() {
        toolbarActionViews.size

        val iconEnums = config.toolbarActions
        if (iconEnums.isNotEmpty()) {
            iconBar.removeAllViews()
            iconEnums.forEach { actionEnum ->
                iconBar.addView(toolbarActionViews[actionEnum.ordinal])
            }
            if (ToolbarAction.Settings !in iconEnums) {
                iconBar.addView(toolbarActionViews[ToolbarAction.Settings.ordinal])
            }
            iconBar.requestLayout()
        }
    }

    private fun toggleIconsOnOmnibox(shouldShow: Boolean) {
        toolbarContainer.visibility = if (shouldShow) VISIBLE else GONE
    }

    private val toolbarActionViews: List<View> by lazy {
        val childCount = iconBar.childCount
        val children = mutableListOf<View>()
        for (i in 0 until childCount) {
            children.add(iconBar.getChildAt(i))
        }

        children
    }


}