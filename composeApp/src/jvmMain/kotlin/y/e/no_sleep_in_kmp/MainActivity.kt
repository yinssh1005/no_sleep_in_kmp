package y.e.no_sleep_in_kmp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import y.e.no_sleep_in_kmp.database.AppDatabase
import y.e.no_sleep_in_kmp.database.Note

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainActivity() {
    val database = remember { AppDatabase.getDatabase() }
    val noteDao = remember { database.noteDao() }
    val coroutineScope = rememberCoroutineScope()
    
    var notes by remember { mutableStateOf<List<Note>>(emptyList()) }
    
    LaunchedEffect(Unit) {
        noteDao.getAllNotes().collect { notesList ->
            notes = notesList
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notes") },
                actions = {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                noteDao.insertNote(
                                    Note(
                                        title = "New Note ${System.currentTimeMillis()}",
                                        content = "This is a new note created at ${System.currentTimeMillis()}"
                                    )
                                )
                            }
                        }
                    ) {
                        Text("Add Note")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notes) { note ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = note.title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = note.content,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "ID: ${note.id} | Timestamp: ${note.timestamp}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}