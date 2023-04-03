@file:Suppress("unused")

package com.jarvis.weatherj.presentation.widget.listItem.config

enum class ListItemContentElement(var value: Int) {
    ONLY_TITLE(0), TITLE_AND_DESCRIPTION_STYLE1(1), TITLE_AND_DESCRIPTION_STYLE2(2), PROGRESS_AND_TITLE_AND_DESCRIPTION(
        3
    ),
    TITLE_AND_DESCRIPTION_STYLE3(4), TITLE_AND_DESCRIPTION_END_WITH_TEXT_AND_ICON(5), BOTTOMSHEET_DROPDOWN_DIALOG(
        6
    );
}
