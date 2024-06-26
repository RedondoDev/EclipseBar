package graphs.bar

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import graphs.bar.render.label.ILabelDrawer
import graphs.bar.render.xaxis.IXAxisDrawer

/**
 * @Author bytebeats
 * @Email <happychinapc@gmail.com>
 * @Github https://github.com/bytebeats
 * @Created at 2022/3/10 20:23
 * @Version 1.0
 * @Description TO-DO
 */

internal fun axisAreas(
    drawScope: DrawScope,
    totalSize: Size,
    xAxisDrawer: IXAxisDrawer,
    labelDrawer: ILabelDrawer
): Pair<Rect, Rect> {
    with(drawScope) {

        val yAxisTop = labelDrawer.requiredAboveBarHeight(drawScope)
        val yAxisRight = 50.dp.toPx().coerceAtMost(size.width * 10F / 100F)
        val xAxisRight = totalSize.width
        val xAxisTop = totalSize.height - xAxisDrawer.requiredHeight(drawScope)

        return Rect(yAxisRight, xAxisTop, xAxisRight, totalSize.height) to Rect(
            0F,
            yAxisTop,
            yAxisRight,
            xAxisTop
        )
    }
}

internal fun barDrawableArea(xAxisArea: Rect): Rect =
    Rect(left = xAxisArea.left, top = 0F, right = xAxisArea.right, bottom = xAxisArea.top)

internal fun BarChartData.forEachWithArea(
    drawScope: DrawScope,
    barDrawableArea: Rect,
    progress: Float,
    labelDrawer: ILabelDrawer,
    block: (barArea: Rect, bar: BarChartData.Bar) -> Unit
) {
    val barCount = bars.size
    val widthOfBarArea = barDrawableArea.width / barCount
    val offsetOfBar = widthOfBarArea * 0.2F

    bars.forEachIndexed { index, bar ->
        val left = barDrawableArea.left + index * widthOfBarArea
        val height = barDrawableArea.height
        val barHeight = (height - labelDrawer.requiredAboveBarHeight(drawScope)) * progress
        val barArea = Rect(
            left = left + offsetOfBar,
            top = barDrawableArea.bottom - bar.value / maxBarValue * barHeight,
            right = left + widthOfBarArea - offsetOfBar,
            bottom = barDrawableArea.bottom
        )
        block(barArea, bar)
    }
}