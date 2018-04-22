package fr.jdrape.kata.berlinclockkata.service

import org.springframework.stereotype.Service
import fr.jdrape.kata.berlinclockkata.enumeration.EnumLampState
import fr.jdrape.kata.berlinclockkata.enumeration.EnumLampState.*

@Service
class BerlinClockServiceImpl : BerlinClockService {

    override fun convertDigitalToBerlin(time:String):String {
        // body
        val timeSplit = time.split(":").map { it.toInt()}

        val hours = timeSplit.get(0)
        val minutes = timeSplit.get(1)
        val seconds = timeSplit.get(2)

        return calculateSecondsLampRow(seconds) + calculateFiveHoursRow(hours) + calculateSingleHoursRow(hours) + calculateFiveMinutesRow(minutes) + calculateSingleMinutesRow(minutes)
    }

    /**
     * Calculate the 1st row : second
     *
     * @param seconds
     *            : seconds in digital format time
     * @return : second in berlin clock format
     */
    fun calculateSecondsLampRow(seconds:Int) :String {
        // if even its YELLOW, else OFF. So we add "+1"
        return calculateRow(
                numberLampToOn = (seconds+1) % 2,
                onState = YELLOW,
                numberLamp = 1
        )
    }

    /**
     * Calculate the 2nd row : 5 hours
     *
     * @param hours
     *            : hours in digital format time
     * @return : 5 hours in berlin clock format
     */
    fun calculateFiveHoursRow(hours:Int) :String {
        // Divide hours by 5 to know how many lamp need to be ON
        return calculateRow(
                numberLampToOn = hours / 5,
                onState = RED,
                numberLamp = 4
        )
    }

    /**
     * Calculate the 3nd row : single hour
     *
     * @param hours
     *            : hours in digital format time
     * @return : single hours in berlin clock format
     */
    fun calculateSingleHoursRow(hours:Int) :String {
        return calculateRow(
                numberLampToOn = hours % 5,
                onState = RED,
                numberLamp = 4
        )
    }

    /**
     * Calculate the 4th row : five minutes row.
     *
     * @param minutes
     *            : minutes in digital format time
     * @return : berlin clock 5 minutes row.
     */
    fun calculateFiveMinutesRow(minutes:Int) : String {
        fun show5MinutesRow(builder:StringBuilder, index:Int):Unit{
            // each 3 LAMP, it's RED
            if (index % 3 == 0) {
                builder.append(RED.code)
            } else {
                builder.append(YELLOW.code)
            }
        }

        return calculateRow(
                numberLampToOn = minutes / 5,
                numberLamp = 11,
                specificLampOn = ::show5MinutesRow
        )
    }

    /**
     * Calculate the 5th row : single minutes
     *
     * @param minutes
     *            : minutes in digital format time
     * @return : single minutes in berlin clock format
     */
    fun calculateSingleMinutesRow(minutes:Int) : String {
        return calculateRow(numberLampToOn = minutes % 5, onState = YELLOW, numberLamp = 4)
    }

    /**
     * Calculate the hour row. RED if ON,
     *
     * @param numberLampToOn
     *            : number of LAMP to switch ON
     * @param onState : state if LAMP ON. Only use if specificLampOn null
     * @param numberLamp : number total of LAMP in the row
     * @param specificLampOn : specific function to LAMP ON
     * @return : berlin clock row.
     */
    fun  calculateRow(
            numberLampToOn:Int,
            onState:EnumLampState ?= null,
            numberLamp:Int,
            specificLampOn :((builder:StringBuilder, index:Int)->Unit) ? = null
    ) :String{
        val row = StringBuilder()
        for (i in 1..numberLamp) {
            // we illuminate only some lamps
            if (i <= numberLampToOn) {
                if (specificLampOn == null){
                    if (onState !=null) {
                        row.append(onState.code)
                    }
                } else {
                    specificLampOn(row,i)
                }
            } else {
                row.append(OFF.code)
            }
        }
        return row.toString()
    }

}