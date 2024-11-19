package com.example.firstaplication.ui.views.Solicitudes

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.firstaplication.data.model.sData
import com.example.firstaplication.data.model.spData
import com.example.firstaplication.ui.views.InfoCotizaciones.DetalleCotizacionViewModel
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SolicitudesScreen(viewModel: SolicitudViewModel, navController: NavController, context: Context, viewModelCotizado: DetalleCotizacionViewModel) {
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Lista filtrada para la búsqueda
    val filteredData = remember { mutableStateListOf<String>() }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(Color(0xFF0C0C22)),
                title = {
                    Text(
                        text = "COTIZACIÓN",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = null, tint = Color.White)
                    }
                },
                actions = {
                    if (isSearchVisible) {
                        BasicTextField(
                            value = searchText,
                            onValueChange = {
                                searchText = it
                                filteredData.clear()
                                filteredData.addAll(
                                    if (selectedTabIndex == 0) {
                                        viewModel.dataPendiente.filterIsInstance<String>().filter { data ->
                                            data.contains(searchText.toLowerCase(), ignoreCase = true)}
                                    } else {
                                        viewModel.dataAprobada.filterIsInstance<String>().filter { data ->
                                            data.contains(searchText.toLowerCase(), ignoreCase = true)}
                                    }
                                )
                            },
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Search
                            ),
                            keyboardActions = KeyboardActions(
                                onSearch = {
                                    filteredData.clear()
                                    filteredData.addAll(
                                        if (selectedTabIndex == 0) {
                                            viewModel.dataPendiente.filterIsInstance<String>().filter { data ->
                                                data.contains(searchText.toLowerCase(), ignoreCase = true)}
                                        } else {
                                            viewModel.dataAprobada.filterIsInstance<String>().filter { data ->
                                                data.contains(searchText.toLowerCase(), ignoreCase = true)}
                                        }
                                    )
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.White)
                                .padding(16.dp)
                        )

                        IconButton(
                            onClick = {
                                isSearchVisible = false
                                searchText = ""
                                filteredData.clear()
                            }
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Cerrar búsqueda", tint = Color.Black)
                        }
                    } else {
                        IconButton(
                            onClick = { isSearchVisible = true }
                        ) {
                            Icon(imageVector = Icons.Default.Search, contentDescription = "Buscar", tint = Color.White)
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF000080))
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                val tabs = listOf("Pendientes", "Cotizadas")

                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    modifier = Modifier.fillMaxWidth(),
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                            modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                        )
                    }
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(text = title) },
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index }
                        )
                    }
                }

                if (!viewModel.isLoading) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        if (searchText.isNotEmpty()) {
                            items(filteredData) { name ->
                                // Tarjeta de cotización según la búsqueda
                                // Determina la tarjeta a mostrar según la pestaña seleccionada
                                val dataToShow = if (selectedTabIndex == 0) {
                                    viewModel.dataPendiente.find { it.name == name } as? spData
                                } else {
                                    viewModel.dataAprobada.find { it.name == name } as? sData
                                }

                                // Muestra la tarjeta correspondiente
                                if (dataToShow != null) {
                                    if (selectedTabIndex == 0) {
                                        CotizacionCardPendiente(navController = navController, data = dataToShow as spData, viewModel = viewModel)
                                    } else {
                                        CotizacionCardAprobada(navController = navController, data = dataToShow as sData, context = context, viewModelCotizado = viewModelCotizado)
                                    }
                                }
                            }
                        } else {
                            if (selectedTabIndex == 0) {
                                items(viewModel.dataPendiente) { data ->
                                    CotizacionCardPendiente(navController = navController, data = data, viewModel = viewModel)
                                }
                            } else {
                                items(viewModel.dataAprobada) { data ->
                                    CotizacionCardAprobada(navController = navController, data = data, context = context, viewModelCotizado = viewModelCotizado)
                                }
                            }
                        }
                    }
                } else {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(60.dp)
                            .fillMaxSize()
                            .absoluteOffset(180.dp, 270.dp),
                        color = Color.Gray
                    )
                    Text(
                        text = "Cargando...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .absoluteOffset(160.dp, 280.dp),
                        //style = MaterialTheme.typography.bodySmall,
                        //textAlign = TextAlign.Center,
                        color = Color.White
                    )
                    LaunchedEffect(Unit) {
                        viewModel.cargarData()
                    }
                }
            }
        },
        bottomBar = {
            Text(
                text = "© 2023 CONDOSA. Todos los derechos reservados",
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF0C0C22))
                    .padding(16.dp),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    )
}








