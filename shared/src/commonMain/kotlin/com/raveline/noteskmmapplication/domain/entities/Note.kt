package com.raveline.noteskmmapplication.domain.entities

import com.raveline.noteskmmapplication.utils.BabyBlueHex
import com.raveline.noteskmmapplication.utils.ChartreuseBlueHex
import com.raveline.noteskmmapplication.utils.ChartreuseHex
import com.raveline.noteskmmapplication.utils.LightVioletBlueHex
import com.raveline.noteskmmapplication.utils.LightVioletHex
import com.raveline.noteskmmapplication.utils.OrangeBlackHex
import com.raveline.noteskmmapplication.utils.RedOrangeHex
import com.raveline.noteskmmapplication.utils.RedPinkHex
import com.raveline.noteskmmapplication.utils.VioletHex
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val createdAt: LocalDateTime
) {
    companion object {
        private val colors = listOf(
            RedOrangeHex,
            RedPinkHex,
            BabyBlueHex,
            VioletHex,
            LightVioletHex,
            ChartreuseHex,
            OrangeBlackHex,
            LightVioletBlueHex,
            ChartreuseBlueHex,
        )

        fun generateRandomColor() = colors.shuffled().first()
    }
}
