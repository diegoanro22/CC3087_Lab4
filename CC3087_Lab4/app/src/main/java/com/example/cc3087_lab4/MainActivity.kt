package com.example.cc3087_lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.cc3087_lab4.ui.theme.CC3087_Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CC3087_Lab4Theme {
                RecipeScreen()
            }
        }
    }
}

@Composable
fun RecipeScreen() {
    // Estado para manejar la lista de recetas
    var recipeName by remember { mutableStateOf("") }
    var recipeImageUrl by remember { mutableStateOf("") }
    val recipeList = remember { mutableStateListOf<Recipe>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        // Campos de texto para ingresar el nombre de la receta y el URL de la imagen
        OutlinedTextField(
            value = recipeName,
            onValueChange = { recipeName = it },
            label = { Text("Nombre de la receta") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = recipeImageUrl,
            onValueChange = { recipeImageUrl = it },
            label = { Text("URL de la imagen") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para agregar la receta a la lista
        Button(
            onClick = {
                if (recipeName.isNotEmpty() && recipeImageUrl.isNotEmpty()) {
                    recipeList.add(Recipe(recipeName, recipeImageUrl))
                    recipeName = ""
                    recipeImageUrl = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Agregar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar la lista de recetas usando LazyColumn
        RecipeList(recipeList)
    }
}

@Composable
fun RecipeList(recipeList: List<Recipe>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(recipeList) { recipe ->
            RecipeItem(recipe)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = recipe.name, style = MaterialTheme.typography.bodyLarge)
            }
            Spacer(modifier = Modifier.width(8.dp))
            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = "Recipe Image",
                modifier = Modifier.size(64.dp),

            )
        }
    }
}

data class Recipe(val name: String, val imageUrl: String)
