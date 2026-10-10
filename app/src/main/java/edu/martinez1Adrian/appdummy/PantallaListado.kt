package edu.martinez1Adrian.appdummy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import edu.martinez1Adrian.appdummy.ui.theme.AppDummyv1Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaListado(
    librosIniciales: List<LibroUI> = librosDeEjemplo,
    modifier: Modifier = Modifier
) {
    var libros by remember { mutableStateOf(librosIniciales) }
    var textoBusqueda by remember { mutableStateOf("") }
    var autorSeleccionado by remember { mutableStateOf<String?>(null) }

    val autoresDisponibles = remember(librosIniciales) {
        librosIniciales.map { it.autor }.distinct()
    }

    // Filtrado reactivo combinado: por texto de búsqueda y por autor
    val librosFiltrados = libros.filter { libro ->
        val coincideTexto = libro.titulo.contains(textoBusqueda.trim(), ignoreCase = true)
        val coincideAutor = autorSeleccionado == null || libro.autor == autorSeleccionado
        coincideTexto && coincideAutor
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Catálogo de Libros") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Buscador con botón de borrado animado
            OutlinedTextField(
                value = textoBusqueda,
                onValueChange = { textoBusqueda = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar por título...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                },
                trailingIcon = {
                    AnimatedVisibility(
                        visible = textoBusqueda.isNotEmpty(),
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        IconButton(onClick = { textoBusqueda = "" }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Limpiar búsqueda"
                            )
                        }
                    }
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Filtros por autor mediante FilterChip
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(
                    selected = autorSeleccionado == null,
                    onClick = { autorSeleccionado = null },
                    label = { Text("Todos") }
                )
                autoresDisponibles.forEach { autor ->
                    FilterChip(
                        selected = autorSeleccionado == autor,
                        onClick = {
                            autorSeleccionado = if (autorSeleccionado == autor) null else autor
                        },
                        label = { Text(autor) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Rejilla o Estado Vacío
            if (librosFiltrados.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            imageVector = Icons.Default.SearchOff,
                            contentDescription = "Sin resultados",
                            tint = MaterialTheme.colorScheme.outline,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "No se encontraron libros",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(librosFiltrados, key = { it.id }) { libro ->
                        ItemLibro(
                            libro = libro,
                            onToggleFavorito = {
                                libros = libros.map {
                                    if (it.id == libro.id) it.copy(esFavorito = !it.esFavorito) else it
                                }
                            },
                            onToggleLeido = {
                                libros = libros.map {
                                    if (it.id == libro.id) it.copy(leido = !it.leido) else it
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PantallaListadoPreview() {
    AppDummyv1Theme {
        PantallaListado()
    }
}