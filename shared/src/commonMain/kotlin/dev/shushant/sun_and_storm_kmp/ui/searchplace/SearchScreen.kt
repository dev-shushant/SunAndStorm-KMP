package dev.shushant.sun_and_storm_kmp.ui.searchplace

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.ui.viewModel

@OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalCoroutinesApi::class
)
@Composable
internal fun SearchScreen(onClick: () -> Unit) {
    val searchState = remember { mutableStateOf("") }
    val viewModel = viewModel(SearchViewModel::class) {
        SearchViewModel()
    }

    val lazyListState = rememberLazyListState()

    val keyboardController = LocalSoftwareKeyboardController.current

    val state by viewModel.state.collectAsStateWithLifecycle(null)

    LaunchedEffect(state) {
        when (state) {
            is SearchScreenState.Success -> {
                keyboardController?.hide()
            }

            else -> {}
        }
    }


    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        TextField(value = searchState.value, modifier = Modifier.background(
            brush = Brush.verticalGradient(
                listOf(
                    Color(
                        0x7766E0D1
                    ), Color(0xFFF5F5F5)
                )
            ), shape = RoundedCornerShape(16.dp)
        ).fillMaxWidth(), onValueChange = { value ->
            searchState.value = value
            viewModel.search(value)
        }, textStyle = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 28.sp, color = Color.Black
        ), keyboardOptions = KeyboardOptions(
            KeyboardCapitalization.Words, imeAction = ImeAction.Search
        ), keyboardActions = KeyboardActions(onSearch = {
            viewModel.search(searchState.value)
            keyboardController?.hide()
        }), placeholder = {
            Text(
                text = "Search any place!", style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 28.sp, color = Color.Black, textAlign = TextAlign.Start
                )
            )
        }, colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.Black
        ), singleLine = true
        )
        LazyColumn(modifier = Modifier.fillMaxSize(), state = lazyListState) {
            when (state) {
                is SearchScreenState.Success -> {
                    items((state as SearchScreenState.Success).places) {
                        Text(
                            text = it.displayName.toString(),
                            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 18.sp),
                            color = Color.Black,
                            modifier = Modifier.clickable {
                                viewModel.userClicked(it).also {
                                    onClick.invoke()
                                }
                            }.padding(20.dp)
                        )
                    }
                }

                is SearchScreenState.Error -> {
                    item {
                        Text("Not found")
                    }
                }

                is SearchScreenState.Loading -> {
                    item {
                        CircularProgressIndicator()
                    }
                }

                else -> {}
            }

        }
    }
}