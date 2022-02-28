package com.patrykkosieradzki.cryptobag.common.ui.compose.shimmer

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.OnGloballyPositionedModifier
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import kotlinx.coroutines.flow.collect

fun Modifier.shimmer(
    customShimmer: Shimmer? = null
): Modifier = composed(
    factory = {
        val shimmer = customShimmer ?: rememberShimmer(ShimmerBounds.View)

        val width = with(LocalDensity.current) { shimmer.theme.shimmerWidth.toPx() }
        val area = remember(width, shimmer.theme.rotation) {
            ShimmerArea(width, shimmer.theme.rotation)
        }

        LaunchedEffect(area, shimmer) {
            shimmer.boundsFlow.collect {
                area.updateBounds(it)
            }
        }

        remember(area, shimmer) { ShimmerModifier(area, shimmer.effect) }
    },
    inspectorInfo = debugInspectorInfo {
        name = "shimmer"
        properties["customShimmer"] = customShimmer
    }
)

internal class ShimmerModifier(
    private val area: ShimmerArea,
    private val effect: ShimmerEffect,
) : DrawModifier, OnGloballyPositionedModifier {

    override fun ContentDrawScope.draw() {
        with(effect) { draw(area) }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        val viewBounds = coordinates.unclippedBoundsInWindow()
        area.viewBounds = viewBounds
    }
}

fun LayoutCoordinates.unclippedBoundsInWindow(): Rect {
    val positionInWindow = positionInWindow()
    return Rect(
        positionInWindow.x,
        positionInWindow.y,
        positionInWindow.x + size.width,
        positionInWindow.y + size.height,
    )
}
