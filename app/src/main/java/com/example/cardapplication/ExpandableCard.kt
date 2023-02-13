package com.example.cardapplication

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.cardapplication.ui.theme.Shapes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(action: Action) {
    var expandedState by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )

    Box {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .animateContentSize(),
            shape = Shapes.medium,
            backgroundColor = Color.White,
            onClick = {
                expandedState = !expandedState
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = action.id,
                        fontSize = 16.sp
                    )
                    SimpleWebImageComponent(action.image)
                    Column() {
                        Text(
                            text = action.action,
                            fontSize = 16.sp
                        )
                        Row() {
                            IconButton(
                                modifier = Modifier
                                    .alpha(ContentAlpha.medium)
                                    .rotate(rotateState)
                                    .size(20.dp),
                                onClick = {
                                    expandedState = !expandedState
                                }) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowDown,
                                    contentDescription = "Drop Down"
                                )
                            }
                            Text(
                                text = "More",
                                fontSize = 16.sp
                            )
                        }
                    }
                    Spacer(
                        Modifier
                            .weight(1f, true)
                            .background(Color.Green)
                    )
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "R$ ${action.price.toString()}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "${action.variation.toString()}%",
                            fontSize = 16.sp,
                            style = checkValue(action.variation)
                        )
                    }
                }
                if (expandedState) {
                    Column() {
                        Text(
                            text = "${action.name} - Ação",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(2.dp)
                        )
                        Row() {
                            Text(
                                text = "Last Hour",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(2.dp)
                            )
                            Spacer(
                                Modifier
                                    .weight(1f, true)
                            )
                            Text(
                                text = "${action.lasthourvariation.toString()}%",
                                style = checkValue(action.lasthourvariation),
                                fontSize = 16.sp,
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                        Row() {
                            Text(
                                text = "Last Day",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(2.dp)
                            )
                            Spacer(
                                Modifier
                                    .weight(1f, true)
                            )
                            Text(
                                text = "${action.lastdayvariation.toString()}%",
                                style = checkValue(action.lastdayvariation),
                                fontSize = 16.sp,
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                        Row() {
                            Text(
                                text = "Last week",
                                fontSize = 16.sp,
                                modifier = Modifier.padding(2.dp)
                            )
                            Spacer(
                                Modifier
                                    .weight(1f, true)
                            )
                            Text(
                                text = "${action.lastweekvariation}%",
                                style = checkValue(action.lastweekvariation),
                                fontSize = 16.sp,
                                modifier = Modifier.padding(2.dp)
                            )
                        }
                    }
                }
            }
        }
        if (expandedState) {
            RoundedCornerCardComponent()
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun ExpandableCardPreview() {
    Column {
        ExpandableCard(getAction())
        ExpandableCard(getAction2())
    }
}

@Composable
fun SimpleWebImageComponent(image: String) {
    AsyncImage(
        model = image,
        contentDescription = "Imagem da internet",
        modifier = Modifier
            .width(80.dp)
            .height(50.dp)
            .padding(2.dp),
    )
}

@Preview(showBackground = false)
@Composable
fun RoundedCornerCardComponent() {
    Card(
        shape = RoundedCornerShape(30.dp),
        backgroundColor = Color.White,
        modifier = Modifier
            .padding(16.dp, 5.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        Text(
            text = "Details",
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            ),
            letterSpacing = 2.sp,
            modifier = Modifier.padding(8.dp)
        )
    }
}

fun checkValue(value: Double): TextStyle {
    return if (value > 0) {
        TextStyle(
            fontWeight = FontWeight.Bold,
            color = Color.Green
        )
    } else if (value < 0) {
        TextStyle(
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    } else {
        TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}