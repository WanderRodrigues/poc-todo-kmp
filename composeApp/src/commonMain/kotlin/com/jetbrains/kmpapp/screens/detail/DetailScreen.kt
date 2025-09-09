package com.jetbrains.kmpapp.screens.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.jetbrains.kmpapp.data.PlaceObject
import com.jetbrains.kmpapp.screens.EmptyScreenContent
import kmp_app_template.composeapp.generated.resources.Res
import kmp_app_template.composeapp.generated.resources.back
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
    placeId: Int,
    navigateBack: () -> Unit,
) {
    val viewModel = koinViewModel<DetailViewModel>()

    val place by viewModel.getPlace(placeId).collectAsStateWithLifecycle(initialValue = null)
    AnimatedContent(place != null) { placeAvailable ->
        if (placeAvailable) {
            PlaceDetails(place!!, onBackClick = navigateBack)
        } else {
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@Composable
private fun PlaceDetails(
    place: PlaceObject,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, stringResource(Res.string.back))
                    }
                }
            )
        },
        modifier = modifier.windowInsetsPadding(WindowInsets.systemBars),
    ) { paddingValues ->
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {
            AsyncImage(
                model = place.primaryImage,
                contentDescription = place.name,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray)
            )

            SelectionContainer {
                Column(Modifier.padding(16.dp)) {
                    Text(
                        text = place.name,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(Modifier.height(8.dp))
                    
                    Text(
                        text = "${place.city}, ${place.country}",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Spacer(Modifier.height(16.dp))
                    
                    Text(
                        text = place.description,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    
                    Spacer(Modifier.height(16.dp))
                    
                    // Informações básicas
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LabeledInfo("Categoria", place.category)
                        LabeledInfo("Dificuldade", place.difficulty)
                    }
                    
                    Spacer(Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LabeledInfo("Avaliação", "⭐ ${place.rating}")
                        LabeledInfo("Visitantes/Ano", "${place.visitorsPerYear}")
                    }
                    
                    Spacer(Modifier.height(16.dp))
                    
                    // Preços
                    Text(
                        text = "Preços",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LabeledInfo("Adulto", "${place.price.currency} ${place.price.adult}")
                        LabeledInfo("Criança", "${place.price.currency} ${place.price.child}")
                    }
                    
                    Spacer(Modifier.height(16.dp))
                    
                    // Tags
                    Text(
                        text = "Tags",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    
                    Text(
                        text = place.tags.joinToString(", "),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Spacer(Modifier.height(16.dp))
                    
                    // Melhor época para visitar
                    Text(
                        text = "Melhor Época",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    
                    Text(
                        text = place.bestTimeToVisit.joinToString(", "),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    
                    Spacer(Modifier.height(16.dp))
                    
                    // Acessibilidade
                    Text(
                        text = "Acessibilidade",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    
                    LabeledInfo("Cadeirante", if (place.accessibility.wheelchairAccessible) "Sim" else "Não")
                    LabeledInfo("Elevador", if (place.accessibility.elevatorAvailable) "Sim" else "Não")
                    LabeledInfo("Guia de Áudio", if (place.accessibility.audioGuide) "Sim" else "Não")
                    
                    Spacer(Modifier.height(16.dp))
                    
                    // Contato
                    Text(
                        text = "Contato",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(8.dp))
                    
                    LabeledInfo("Website", place.website)
                    LabeledInfo("Telefone", place.phone)
                    LabeledInfo("Email", place.email)
                }
            }
        }
    }
}

@Composable
private fun LabeledInfo(
    label: String,
    data: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier.padding(vertical = 4.dp)) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$label: ")
                }
                append(data)
            },
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
