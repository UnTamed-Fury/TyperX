package com.typerx.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.typerx.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    homeViewModel: HomeViewModel = viewModel()
) {
    val resultCount by homeViewModel.resultCount
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        // Background decorative elements
        androidx.compose.foundation.background(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = (-50).dp),
            shape = RoundedCornerShape(bottomStart = 50.dp, bottomEnd = 50.dp),
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
        )
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            
            // App logo/title
            Card(
                modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 16.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // Placeholder
                    contentDescription = "TyperX Logo",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimary)
                )
            }
            
            Text(
                text = "TyperX",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            
            Text(
                text = "Advanced Typing Tutor",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            
            // Stats card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 32.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatItem(label = "Sessions", value = resultCount.toString())
                    VerticalDivider()
                    StatItem(label = "Best WPM", value = "?") // Would come from repository
                    VerticalDivider()
                    StatItem(label = "Streak", value = "0") // Would come from repository
                }
            }
            
            // Action buttons
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ElevatedButton(
                    onClick = { navController.navigate("typing") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = "Start Typing",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                OutlinedButton(
                    onClick = { navController.navigate("history") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "History",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                
                OutlinedButton(
                    onClick = { navController.navigate("settings") },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(60.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Settings",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
        
        // Floating action button
        FloatingActionButton(
            onClick = { /* Quick action */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.FlashOn,
                contentDescription = "Quick Practice"
            )
        }
    }
}

@Composable
fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun VerticalDivider() {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .width(1.dp),
        color = MaterialTheme.colorScheme.outline
    )
}