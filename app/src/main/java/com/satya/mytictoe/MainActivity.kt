package com.satya.mytictoe

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.satya.mytictoe.ui.theme.MyTicToeTheme
import com.satya.mytictoe.viewModel.GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTicToeTheme {
                val viewModel = viewModel<GameViewModel>()
                GameScreen(viewModel = viewModel)
            }
        }
    }


    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @Composable
    fun MyTopAppBar() {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Tik Tac Toe") },
                    navigationIcon = {
                        AppBarDefaults
                    }
                )
            }
        ) {
            // Screen content
        }

    }

}

