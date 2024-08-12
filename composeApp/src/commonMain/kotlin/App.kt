import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jetbrains.cmp.notes.NoteEditor
import com.jetbrains.cmp.notes.NotesListScreen
import com.jetbrains.cmp.notes.ui.theme.NotesAppTheme
import com.jetbrains.cmp.notes.utils.koinInjectViewModel
import com.jetbrains.cmp.notes.viewmodel.NotesViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean,
) {
    NotesAppTheme(darkTheme, dynamicColor) {
        KoinContext {
            NotesHome()
        }
    }
}

@Composable
fun NotesHome() {
    val navController = rememberNavController()
    val notesViewModel = koinInjectViewModel<NotesViewModel>()

    Scaffold(
        topBar = {
            TopAppBarComposable(navController, notesViewModel)
        },
        floatingActionButton = {
            if (notesViewModel.toggleToolbar.value) {
                FloatingActionButton(
                    onClick = {
                        notesViewModel.toggle()
                        navController.navigate("note_edit")
                    },
                ) {
                    Icon(Icons.Filled.Add, "Floating action button.")
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(1f),
            color = MaterialTheme.colorScheme.background,
        ) {
            NavHost(navController, startDestination = "home") {
                composable("home") {
                    NotesListScreen(navController, viewmodel = notesViewModel)
                }
                composable("note_edit") {
                    NoteEditor(viewmodel = notesViewModel)
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarComposable(
    navController: NavHostController,
    notesViewModel: NotesViewModel
) {
    val toolBarState by notesViewModel.toggleToolbar

    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        navigationIcon = {
            if (!toolBarState) {
                IconButton(onClick = {
                    notesViewModel.toggle()
                    navController.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Navigation icon"
                    )
                }
            }
        },
        title = {
            if (toolBarState) {
                Text(
                    "Notes",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        },
        actions = {
            if (!toolBarState) {
                Text(
                    modifier = Modifier.align(Alignment.CenterVertically)
                        .padding(horizontal = 16.dp).clickable {
                            notesViewModel.addNotes()
                            notesViewModel.toggle()
                            navController.popBackStack()
                        },
                    text = "Done",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        })
}