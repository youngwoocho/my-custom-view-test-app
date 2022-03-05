package com.example.mycustomviewtestapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class RectWithText(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var textToShow: String? = null

    private val textPaint = Paint().apply {
        color = Color.GREEN
        isAntiAlias = true
        textSize = 50F
    }

    private val rectPaint = Paint().apply {
        isAntiAlias = true
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 10F
    }

    private val textBounds: Rect = Rect()

    private var xCoord: Float = 0F
    private var yCoord: Float = 0F

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.RectWithText, 0, 0).apply {
            try {
                textToShow = getString(R.styleable.RectWithText_text)
            } finally {
                recycle()
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        xCoord = width / 4F
        yCoord = height / 4F

        textPaint.getTextBounds(textToShow, 0, textToShow?.length ?: 0, textBounds)
        canvas?.let {

            it.drawText(
                textToShow ?: "",
                xCoord - textBounds.left - rectPaint.strokeWidth / 2,
                yCoord - textBounds.bottom - rectPaint.strokeWidth / 2,
                textPaint
            )
            it.drawRect(xCoord, yCoord, 3 * xCoord, 3 * yCoord, rectPaint)
        }
    }
}