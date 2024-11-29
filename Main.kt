package machine

class CoffeeMachine(
    var water: Int = 0,
    var milk: Int = 0,
    var beans: Int = 0,
    var money: Int = 0,
    var cups: Int = 0,
    var state: String = "choosing an action"
) {
    val info: String
        get() {
            return "The coffee machine has:\n" +
                    "$water ml of water\n" +
                    "$milk ml of milk\n" +
                    "$beans g of coffee beans\n" +
                    "$cups disposable cups\n" +
                    "\$$money of money"
        }
    val espressoLimit: Int
        get() = minOf(cups, water / 250, beans / 16)

    val latteLimit: Int
        get() = minOf(cups, water / 350, milk / 75, beans / 20)

    val cappuccinoLimit: Int
        get() = minOf(cups, water / 200, milk / 100, beans / 12)

    fun buy(choice: String) {
        when (choice) {
            "1" -> {
                if (espressoLimit >= 1) {
                    println("I have enough resources, making you a coffee!")
                    this.cups -= 1
                    this.water -= 250
                    this.beans -= 16
                    this.money += 4
                } else {
                    println("Sorry, not enough")
                }
            }

            "2" -> {
                if (latteLimit >= 1) {
                    println("I have enough resources, making you a coffee!")
                    this.cups -= 1
                    this.water -= 350
                    this.milk -= 75
                    this.beans -= 20
                    this.money += 7
                } else {
                    println("Sorry, not enough")
                }
            }


            "3" -> {
                if (cappuccinoLimit >= 1) {
                    println("I have enough resources, making you a coffee!")
                    this.cups -= 1
                    this.water -= 200
                    this.milk -= 100
                    this.beans -= 12
                    this.money += 6
                } else {
                    println("Sorry, not enough")
                }
            }

            "back" -> {
                state = "choosing an action"
                return
            }

            else -> {
                println("invalid input")
                return
            }
        }
        state = "choosing an action"
    }

    fun fill(water: Int, milk: Int, beans: Int, cups: Int) {
        this.water += water
        this.milk += milk
        this.beans += beans
        this.cups += cups
        state = "choosing an action"
    }

    fun take() {
        println("I gave you \$$money")
        this.money = 0
    }

    fun remaining() {
        println(info)
    }

    fun readCommand() {
        when (state) {
            "choosing an action" -> {
                println("Write action (buy, fill, take, remaining, exit):")
                val command = readln()
                when (command) {
                    "buy" -> {
                        state = "choosing a product"
                        readCommand()
                    }

                    "fill" -> {
                        state = "filling"
                        readCommand()
                    }

                    "take" -> {
                        take()
                        readCommand()
                    }

                    "remaining" -> {
                        remaining()
                        readCommand()
                    }

                    "exit" -> {
                        return
                    }

                    else -> {
                        println("Invalid action")
                        readCommand()
                    }

                }
            }

            "choosing a product" -> {
                println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
                val command = readln()
                buy(command)
                readCommand()
            }

            "filling" -> {
                println("Write how many ml of water you want to add:")
                val water = readln().toInt()
                println("Write how many ml of milk you want to add:")
                val milk = readln().toInt()
                println("Write how many grams of coffee beans you want to add:")
                val beans = readln().toInt()
                println("Write how many disposable cups you want to add:")
                val cups = readln().toInt()
                fill(water, milk, beans, cups)
                readCommand()
            }
        }
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine(
        water = 400,
        milk = 540,
        beans = 120,
        money = 550,
        cups = 9,
        state = "choosing an action"
    )
    coffeeMachine.readCommand()
}
