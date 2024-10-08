package br.com.fiap.challenge.component

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.challenge.R
import br.com.fiap.challenge.ui.theme.LocalThemeColors
import java.util.Calendar

@Composable
fun Header(navController: NavController) {

    val context = LocalContext.current
    val themeColors = LocalThemeColors.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(themeColors.surface),
        contentAlignment = Alignment.TopStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 20.dp)
        ) {
            Button(
                onClick = { showDatePicker(context) },
                modifier = Modifier
                    .padding(top = 20.dp, start = 16.dp)
                    .size(height = 43.dp, width = 70.dp),
                colors = ButtonDefaults.buttonColors(themeColors.surface)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Ícone de Calendário",
                    tint = themeColors.primary,
                    modifier = Modifier.size(23.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.locaweb),
                contentDescription = "Logo Da Empresa",
                modifier = Modifier
                    .size(150.dp, 90.dp)
                    .padding(start = 16.dp)
                    .clickable { navController.navigateUp() },
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navController.navigate("config") },
                modifier = Modifier
                    .padding(top = 20.dp, end = 16.dp)
                    .size(height = 43.dp, width = 70.dp),
                colors = ButtonDefaults.buttonColors(themeColors.surface)
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Ícone de Configurações",
                    tint = themeColors.primary,
                    modifier = Modifier.size(23.dp)
                )
            }
        }
    }
}



private fun showDatePicker(context: android.content.Context) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    val datePickerDialog = DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            // Ação a ser tomada quando a data é selecionada
            Toast.makeText(context, "Data Selecionada: $selectedDay/${selectedMonth + 1}/$selectedYear", Toast.LENGTH_SHORT).show()
        },
        year, month, day
    )
    datePickerDialog.show()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun HeaderPreview() {
    Header(navController = rememberNavController())
}