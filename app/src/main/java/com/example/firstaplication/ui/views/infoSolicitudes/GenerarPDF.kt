package com.example.firstaplication.ui.views.infoSolicitudes

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.pdf.PdfDocument
import android.graphics.pdf.PdfDocument.PageInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.compose.rememberNavController
import com.example.firstaplication.R
import com.example.firstaplication.data.model.sDataDetalle
import com.example.firstaplication.ui.views.InfoCotizaciones.DetalleCotizacionViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

fun generatePDF(context: Context, directory: File, idSolicitud: String?, viewModelCotizado: DetalleCotizacionViewModel) {
    if (idSolicitud != null) {
        val coroutineScope = viewModelCotizado.viewModelScope
        coroutineScope.launch {
            viewModelCotizado.cargarDataCotizada(idSolicitud)

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // Puedes elegir tu formato de fecha
            val fechaActual = sdf.format(Date())
            val pageHeight = 1120
            val pageWidth = 792
            val pdfDocument = PdfDocument()
            val paint = Paint()
            val title = Paint()
            val myPageInfo = PageInfo.Builder(pageWidth, pageHeight, 1).create()
            val myPage = pdfDocument.startPage(myPageInfo)
            val canvas: Canvas = myPage.canvas
            val bitmap: Bitmap? = drawableToBitmap(context.resources.getDrawable(R.drawable.group_2_icon_icons_com_63255))
            val scaleBitmap: Bitmap? = Bitmap.createScaledBitmap(bitmap!!, 120, 120, false)
            canvas.drawBitmap(scaleBitmap!!, 480f, 730f, paint)
            title.typeface = Typeface.DEFAULT_BOLD
            title.textSize = 30f
            canvas.drawText("CONDOSA S.A.", 40f, 100f, title)

            title.textSize = 40f
            //title.textAlign = Paint.Align.CENTER
            title.typeface = Typeface.DEFAULT_BOLD
            canvas.drawText("COTIZACIÓN DE SERVICIOS", 200f, 200f, title)
            title.typeface = Typeface.DEFAULT
            title.textSize = 15f
            canvas.drawText("Id-Solicitud: $idSolicitud", 230f, 250f, title)
            canvas.drawText("Fecha: $fechaActual", 550f, 250f, title)

            title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
            title.textSize = 25f
            canvas.drawText("Solicitante", 160f, 320f, title)
            title.typeface = Typeface.DEFAULT
            title.textSize = 20f
            canvas.drawText("Nombre: ${viewModelCotizado.dataDetalle.nombre} - ${viewModelCotizado.dataDetalle.rol}", 160f, 350f, title)
            canvas.drawText("Nro documento: ${viewModelCotizado.dataDetalle.nro_documento}", 160f, 380f, title)
            canvas.drawText("Correo: ${viewModelCotizado.dataDetalle.correo}", 460f, 380f, title)
            canvas.drawText("Predio: ${viewModelCotizado.dataDetalle.nombre_predio}", 160f, 410f, title)
            canvas.drawText("Tipo de predio: ${viewModelCotizado.dataDetalle.tipo_predio}", 460f, 410f, title)
            canvas.drawText("R.U.C: ${viewModelCotizado.dataDetalle.ruc_predio}", 160f, 440f, title)

            title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
            title.textSize = 25f
            canvas.drawText("Servicios", 160f, 500f, title)
            title.typeface = Typeface.DEFAULT
            title.textSize = 20f
            canvas.drawText("Tipo de servicio: ${viewModelCotizado.dataDetalle.tipo_servicio}", 160f, 530f, title)
            canvas.drawText("Cantidad de administradores: ${viewModelCotizado.dataDetalle.cant_administracion}", 160f, 560f, title)
            canvas.drawText("Cantidad de plimpieza: ${viewModelCotizado.dataDetalle.cant_limpieza}", 460f, 560f, title)
            canvas.drawText("Cantidad de jardineros: ${viewModelCotizado.dataDetalle.cant_jardineria}", 160f, 590f, title)
            canvas.drawText("Cantidad de vigilantes: ${viewModelCotizado.dataDetalle.cant_vigilantes}", 460f, 590f, title)

            var monto_neto = (viewModelCotizado.dataDetalle.cant_administracion * viewModelCotizado.dataDetalle.precio_administracion)+(viewModelCotizado.dataDetalle.cant_limpieza * viewModelCotizado.dataDetalle.precio_limpieza)+(viewModelCotizado.dataDetalle.cant_jardineria * viewModelCotizado.dataDetalle.precio_jardineria)+(viewModelCotizado.dataDetalle.cant_vigilantes * viewModelCotizado.dataDetalle.precio_vigilante)
            var monto_total = (0.18 * monto_neto) + monto_neto
            title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
            title.textSize = 25f
            canvas.drawText("Cotización", 160f, 650f, title)
            title.typeface = Typeface.DEFAULT
            title.textSize = 20f
            canvas.drawText("Monto Neto: S/. " + monto_neto, 160f, 680f, title)
            canvas.drawText("IGV (18%): S/. " + (0.18 * monto_neto), 160f, 710f, title)
            title.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            title.textSize = 25f
            canvas.drawText("Monto Total: S/. " + monto_total, 160f, 740f, title)

            title.textAlign = Paint.Align.LEFT
            title.textSize = 15f
            canvas.drawText("© 2023 CONDOSA. Todos los derechos reservados", 50f, 1050f, title)

            pdfDocument.finishPage(myPage)
            val file = File(directory, "Cotización_$idSolicitud.pdf")

            try {
                pdfDocument.writeTo(FileOutputStream(file))
                Toast.makeText(context, "PDF generado correctamente", Toast.LENGTH_SHORT).show()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
            pdfDocument.close()
        }
    }
}

fun drawableToBitmap(drawable: Drawable): Bitmap? {
    if (drawable is BitmapDrawable) {
        return drawable.bitmap
    }
    val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}
