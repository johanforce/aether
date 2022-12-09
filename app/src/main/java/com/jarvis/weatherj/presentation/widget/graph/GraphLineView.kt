package com.jarvis.weatherj.presentation.widget.graph

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.jarvis.design_system.textview.CustomTextView
import com.jarvis.weatherj.R
import com.jarvis.weatherj.domain.model.model.demo.WeatherHourModel
import com.jarvis.weatherj.presentation.base.adapter.SimpleListAdapter
import com.jarvis.weatherj.presentation.common.DataUtils

class GraphLineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val viewChart by lazy {
        inflate(context, R.layout.view_graph_linechart, this)
    }

    private val graph by lazy {
        viewChart.findViewById<LineChart>(R.id.graphChart)
    }

    private val scroll by lazy {
        viewChart.findViewById<HorizontalScrollView>(R.id.scroll)
    }

    private val rvWeather by lazy {
        viewChart.findViewById<RecyclerView>(R.id.rvWeather)
    }

    private var onClickListener: OnClickListener? = null

    init {
        setupGraph()
    }

    private fun setupGraph() {
        with(graph) {
            setTouchEnabled(false)
            setPinchZoom(false)
            setScaleEnabled(false)
            isDoubleTapToZoomEnabled = false
            isHighlightPerTapEnabled = true
            isClickable = true
            setDrawBorders(false)
            setDrawGridBackground(false)

            description?.isEnabled = false
            legend?.isEnabled = false

            xAxis?.run {
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
                position = XAxis.XAxisPosition.BOTTOM
            }

            axisRight?.run {
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
            }

            extraLeftOffset = 20.0f
            extraRightOffset = 20.0f
            extraTopOffset = -20.0f
        }

    }

    private fun createListWeather(data: List<WeatherHourModel>) {
        val adapter =
            SimpleListAdapter<WeatherHourModel>(R.layout.item_weather) { itemView, item, _ ->
                val tvTime = itemView.findViewById<CustomTextView>(R.id.tvTimeRC)
                val tvTemp = itemView.findViewById<CustomTextView>(R.id.tvTempRC)
                tvTime?.text = DataUtils.converTimeToString(item.time?:"")
                tvTemp?.text = context.getString(R.string.current_temp, item.tempC)
            }
        rvWeather.adapter = adapter
        adapter.submitList(data)
    }

    fun drawLineChart(listWeatherHourModel: List<WeatherHourModel>) {
        val listEntry = convertData(listWeatherHourModel)
        graph?.apply {
            axisLeft?.run {
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawAxisLine(false)
                setDrawLimitLinesBehindData(true)
                textColor = context.getColor(com.jarvis.design_system.R.color.ink_4)
                axisMaximum = (listEntry.maxOfOrNull { it.y } ?: 0.0f) + 0.5f
                axisMinimum = (listEntry.minOfOrNull { it.y } ?: 0.0f) - 0.5f
            }
        }
        setDataToLineChart(listEntry)
        graph?.invalidate()
        createListWeather(listWeatherHourModel)
    }

    private fun setDataToLineChart(listEntry: List<Entry>) {

        val lineDataSet = LineDataSet(listEntry, "Data")
        lineDataSet.lineWidth = 1.5f
        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        lineDataSet.color =
            ContextCompat.getColor(
                context, com.jarvis.design_system.R.color.ink_4
            )
        lineDataSet.valueTextColor =
            ContextCompat.getColor(context, com.jarvis.design_system.R.color.transparent)

        lineDataSet.circleRadius = 2f
        lineDataSet.setCircleColor(
            ContextCompat.getColor(
                context,
                com.jarvis.design_system.R.color.ink_4
            )
        )
        lineDataSet.setDrawCircles(true)
        lineDataSet.setDrawCircleHole(false)


        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(lineDataSet)

        val lineData = LineData(dataSet)
        graph.data = lineData

        graph.invalidate()
    }

    private fun convertData(listWeatherHourModel: List<WeatherHourModel>): ArrayList<Entry> {
        val data = ArrayList<Entry>()
        var stt = 0
        listWeatherHourModel.map {
            data.add(Entry(stt.toFloat(), it.tempC?.toFloat() ?: 0f))
            stt++
        }
        return data
    }

    /**
     * interface set on click in graph
     */
    override fun setOnClickListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }
}
