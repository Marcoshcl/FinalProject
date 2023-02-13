package com.example.cardapplication

data class Action(
    val id:String,
    val action: String,
    val name:String,
    val image: String,
    val price: Double,
    val variation: Double,
    val lasthourvariation: Double,
    val lastdayvariation: Double,
    val lastweekvariation: Double
)

fun getAction() : Action{
    return Action(
        id = "1",
        action = "MGLU3",
        name = "Magalu",
        image = "https://s3-symbol-logo.tradingview.com/magaz-luiza-on-nm--600.png",
        price = 4.13,
        variation = 0.14,
        lasthourvariation = 0.00,
        lastdayvariation = 3.51,
        lastweekvariation = -2.58
    )
}
fun getAction2() : Action{
    return Action(
        id = "2",
        action = "BBDC4",
        name = "Bradesco",
        image = "https://banco.bradesco/inovacao/media/images/share.png",
        price = 13.12,
        variation = 0.58,
        lasthourvariation = 0.12,
        lastdayvariation = -2.66,
        lastweekvariation = 2.18
    )
}

