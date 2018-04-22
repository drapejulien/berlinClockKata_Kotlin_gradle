package fr.jdrape.kata.berlinclockkata.service

import org.springframework.stereotype.Service
import fr.jdrape.kata.berlinclockkata.enumeration.EnumLampState

@Service
class BerlinClockServiceImpl : BerlinClockService {

    override fun convertDigitalToBerlin(time:String):String {
        // body
        val timeSplit = time.split(":").map { it.toInt()};

        val hours = timeSplit.get(0);
        val minutes = timeSplit.get(1);
        val seconds = timeSplit.get(2);
        return calculateSecondsLampRow(seconds) + calculateFiveHoursRow(hours) + calculateSingleHoursRow(hours) + calculateFiveMinutesRow(minutes) + calculateSingleMinutesRow(minutes);
    }

    /**
     * Calculate the 1st row : second
     * 
     * @param seconds
     *            : seconds in digital format time
     * @return : second in berlin clock format
     */
    fun calculateSecondsLampRow(seconds:Int) :String {
        if (seconds % 2 == 0) {
            return EnumLampState.YELLOW.code;
        } else {
            return EnumLampState.OFF.code;
        }
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
        return calculateRow(hours / 5, EnumLampState.RED);
    }

    /**
     * Calculate the 3nd row : single hour
     * 
     * @param hours
     *            : hours in digital format time
     * @return : single hours in berlin clock format
     */
    fun calculateSingleHoursRow(hours:Int) :String {
        return calculateRow(hours % 5, EnumLampState.RED);
    }

    /**
     * Calculate the hour row. RED if ON,
     * 
     * @param numberLampToOn
     *            : number of LAMP to switch ON
     * @return : berlin clock row.
     */
    fun   calculateRow(numberLampToOn:Int, onState:EnumLampState) :String{
        val row = StringBuilder();
        for (i in 1..4) {
            if (i <= numberLampToOn) {
                row.append(onState.code);
            } else {
                row.append(EnumLampState.OFF.code);
            }
        }
        return row.toString();
    }

    /**
     * Calculate the 4th row : five minutes row.
     * 
     * @param minutes
     *            : minutes in digital format time
     * @return : berlin clock 5 minutes row.
     */
    fun calculateFiveMinutesRow(minutes:Int) : String {
        val  row =  StringBuilder();
        val  numberLampToOn = minutes / 5;
          for (i in 1..11) {
            if (i <= numberLampToOn) {
                // Each 3 lamp, it's RED
                if (i % 3 == 0) {
                    row.append(EnumLampState.RED.code);
                } else {
                    row.append(EnumLampState.YELLOW.code);
                }
            } else {
                row.append(EnumLampState.OFF.code);
            }
        }

        return row.toString();
    }

    /**
     * Calculate the 5th row : single minutes
     * 
     * @param minutes
     *            : minutes in digital format time
     * @return : single minutes in berlin clock format
     */
    fun calculateSingleMinutesRow(minutes:Int) : String {
        return calculateRow(minutes % 5, EnumLampState.YELLOW);
    }


}